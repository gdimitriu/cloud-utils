/*
 Copyright (c) 2019 Gabriel Dimitriu All rights reserved.
 DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.

 This file is part of cloud-utils project.

 cloud-utils is free software: you can redistribute it and/or modify
 it under the terms of the GNU General Public License as published by
 the Free Software Foundation, either version 3 of the License, or
 (at your option) any later version.

 cloud-utils is distributed in the hope that it will be useful,
 but WITHOUT ANY WARRANTY; without even the implied warranty of
 MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 GNU General Public License for more details.

 You should have received a copy of the GNU General Public License
 along with cloud-utils.  If not, see <http://www.gnu.org/licenses/>.
 */
package cloud.utils.aws.sdk1.ec2;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.services.ec2.AmazonEC2;
import com.amazonaws.services.ec2.AmazonEC2ClientBuilder;
import com.amazonaws.services.ec2.model.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;
import java.util.stream.Collectors;

public class EC2Instances {
    /**
     * ec2 client for amazon instances
     */
    private AmazonEC2 ec2Client;

    public EC2Instances(AmazonEC2 client) {
        this.ec2Client = client;
    }

    public EC2Instances(String region) {
        AWSCredentials credentials = new ProfileCredentialsProvider().getCredentials();
        ec2Client = AmazonEC2ClientBuilder.standard().withCredentials(new AWSStaticCredentialsProvider(credentials))
                .withRegion(region).build();
    }

    /**
     * Run a specific instance
     *
     * @param osType           the type of the image (OS)
     * @param type             type of the instance (T2.micro for the Siplilearn account)
     * @param min              minimum number of instances
     * @param max              maximum number of instances
     * @param keyName          the keyPair to connect to instance
     * @param securityGroupId  the security group id assigned to this instance
     * @param subnetId         the id of the subnet in which will run the instance
     * @param installingScript the script to run at install
     * @param instanceProfile  the name of the instance profile (which is the same as the role name)
     * @return string representing the instance id
     * @paran name the name of the instance
     */
    public String runInstance(String osType, InstanceType type, int min, int max, String keyName, String securityGroupId, String subnetId, String installingScript, String name, String instanceProfile) {
        RunInstancesRequest request = new RunInstancesRequest();
        request.withImageId(osType).withInstanceType(type).withMinCount(min).withMaxCount(max).withKeyName(keyName);
        //do not work with security group and subnets.
        //.withSecurityGroupIds(securityGroupId);
        List<InstanceNetworkInterfaceSpecification> interfaces = new ArrayList<>();
        InstanceNetworkInterfaceSpecification interfaceDNS = new InstanceNetworkInterfaceSpecification();
        interfaceDNS.withSubnetId(subnetId).withAssociatePublicIpAddress(true).setDeviceIndex(0);
        interfaceDNS.setGroups(Arrays.asList(securityGroupId));
        interfaces.add(interfaceDNS);
        request.withNetworkInterfaces(interfaces).withAdditionalInfo("--associate-public-ip-address");
        if (installingScript != null) {
            request.withUserData(Base64.getEncoder().encodeToString(installingScript.getBytes()));
        }
        IamInstanceProfileSpecification profile = new IamInstanceProfileSpecification().withName(instanceProfile);
        request.withIamInstanceProfile(profile);
        RunInstancesResult result = ec2Client.runInstances(request);
        CreateTagsRequest tagNameRequest = new CreateTagsRequest().withResources(result.getReservation().getInstances().get(0).getInstanceId());
        tagNameRequest.withTags(new Tag().withKey("Name").withValue(name));
        ec2Client.createTags(tagNameRequest);
        return result.getReservation().getInstances().get(0).getInstanceId();
    }

    /**
     * Stop the instance
     *
     * @param instanceId the id of the instance to be stop
     * @param force      true if the instance will be forcefully stoped.
     */
    public void stopInstance(String instanceId, boolean force) throws AmazonEC2Exception {
        DescribeInstancesRequest describeRequest = new DescribeInstancesRequest().withInstanceIds(instanceId);
        DescribeInstancesResult describeResult = ec2Client.describeInstances(describeRequest);
        if (describeResult.getReservations().get(0).getInstances().get(0).getState().getCode() == 16) {
            StopInstancesRequest stopRequest = new StopInstancesRequest();
            stopRequest.withInstanceIds(instanceId).withForce(force);
            ec2Client.stopInstances(stopRequest);
        } else {
            System.out.printf("Instance with id %s is not running yet !\n", instanceId);
        }
    }

    /**
     * Start a stopped instance.
     *
     * @param instanceId the id of the instance to be started
     */
    public void startInstance(String instanceId) {
        StartInstancesRequest request = new StartInstancesRequest();
        request.withInstanceIds(instanceId);
        ec2Client.startInstances(request);
    }

    /**
     * Terminate an instance;
     *
     * @param instanceId the id of the instance to be terminated
     */
    public void terminateInstance(String instanceId) {
        TerminateInstancesRequest request = new TerminateInstancesRequest();
        request.withInstanceIds(instanceId);
        ec2Client.terminateInstances(request);
    }

    /**
     * get the instance id of the running ec2 machine.
     * @return the instanceId where this is run or null if is outside AWS
     */
    public String getRunningAWSInstanceId() {
        try {
            URL url = new URL("http://169.254.169.254/latest/meta-data/instance-id");
            //should be only one line
            return new BufferedReader(new InputStreamReader(url.openConnection().getInputStream())).lines().collect(Collectors.joining(""));
        } catch (IOException ex) {
            return null;
        }
    }
    /**
     * get the instance data from instanceId
     * @param instanceId  the id of the instance
     * @return the Instance
     */
    private Instance getInstanceFromId(String instanceId) {
        DescribeInstancesResult response = ec2Client.describeInstances(new DescribeInstancesRequest().withInstanceIds(instanceId));
        for (Reservation reservation : response.getReservations()) {
            for (Instance instance : reservation.getInstances()) {
                if(instanceId.equals(instance.getInstanceId())) {
                    return instance;
                }
            }
        }
        return null;
    }

    /**
     * clone an instance with specific installing script
     * @param instanceID the id of the instance to be cloned if null is current instance
     * @param installingScript the install script
     * @return the instanceId
     */
    public String cloneInstance(String instanceID, String installingScript) {
        String instanceToBeClonned = instanceID ;
        if (instanceToBeClonned == null) {
            instanceToBeClonned = getRunningAWSInstanceId();
        }
        if (instanceToBeClonned == null) {
            return null;
        }
        Instance instanceToClone = getInstanceFromId(instanceToBeClonned);
        if (instanceToClone == null) {
            return null;
        }
        List<String> securityGroupIds = new ArrayList<>();
        instanceToClone.getSecurityGroups().stream().forEach(group -> securityGroupIds.add(group.getGroupId()));
        RunInstancesRequest request = new RunInstancesRequest().withMinCount(1).withMaxCount(1);
        request.withImageId(instanceToClone.getImageId()).withInstanceType(instanceToClone.getInstanceType()).withKeyName(instanceToClone.getKeyName());
        List<InstanceNetworkInterfaceSpecification> interfaces = new ArrayList<>();
        InstanceNetworkInterfaceSpecification interfaceDNS = new InstanceNetworkInterfaceSpecification();
        interfaceDNS.withSubnetId(instanceToClone.getSubnetId()).withAssociatePublicIpAddress(true).setDeviceIndex(0);
        interfaceDNS.setGroups(securityGroupIds);
        interfaces.add(interfaceDNS);
        request.withNetworkInterfaces(interfaces).withAdditionalInfo("--associate-public-ip-address");
        if (installingScript != null) {
            request.withUserData(Base64.getEncoder().encodeToString(installingScript.getBytes()));
        }
        IamInstanceProfileSpecification profile = new IamInstanceProfileSpecification().withArn(instanceToClone.getIamInstanceProfile().getArn());
        request.withIamInstanceProfile(profile);
        RunInstancesResult result = ec2Client.runInstances(request);
        CreateTagsRequest tagNameRequest = new CreateTagsRequest().withResources(result.getReservation().getInstances().get(0).getInstanceId());
        tagNameRequest.withTags(new Tag().withKey("Name").withValue("cloned"));
        ec2Client.createTags(tagNameRequest);
        return result.getReservation().getInstances().get(0).getInstanceId();
    }
}
