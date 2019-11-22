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
package cloud.utils.configuration.tests.model.virtualprivatecloud;

import cloud.utils.configuration.model.network.*;
import cloud.utils.configuration.model.virtualprivatecloud.VPCDescription;
import org.junit.Test;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class VPCDescriptionTest {

    @Test
    public void testLoadFromXml() throws JAXBException, IOException {
        JAXBContext context = JAXBContext.newInstance(VPCDescription.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        VPCDescription vpcDescription = (VPCDescription) unmarshaller.unmarshal(getClass()
                .getModule().getResourceAsStream("VPCDescription.xml"));
        assertEquals(2, vpcDescription.getSecurityGroups().length);
        assertEquals(4, vpcDescription.getSubnets().length);
        assertEquals("PharmaVPC", vpcDescription.getVpcName());
        assertEquals("10.0.0.0/16", vpcDescription.getIpRange());
        SecurityGroupDescription[] securityGroups = vpcDescription.getSecurityGroups();
        //first security group
        SecurityGroupDescription securityGroup = securityGroups[0];
        assertEquals("WebServerSecurityGroupLinux", securityGroup.getName());
        assertEquals("Web Security Group", securityGroup.getDescription());
        FirewallRuleDescription[] firewallRules = securityGroup.getFirewallRuleDescriptions();
        assertEquals(2, firewallRules.length);
        assertEquals("0.0.0.0/0", firewallRules[0].getIpRanges()[0]);
        assertEquals("tcp", firewallRules[0].getProtocol());
        assertEquals(22, firewallRules[0].getFromPort().intValue());
        assertEquals(22, firewallRules[0].getToPort().intValue());
        assertEquals("0.0.0.0/0", firewallRules[1].getIpRanges()[0]);
        assertEquals("tcp", firewallRules[1].getProtocol());
        assertEquals(80, firewallRules[1].getFromPort().intValue());
        assertEquals(80, firewallRules[1].getToPort().intValue());
        //second security group
        securityGroup = securityGroups[1];
        assertEquals("DBServerSecurityGroupLinux", securityGroup.getName());
        assertEquals("DB Security Group", securityGroup.getDescription());
        firewallRules = securityGroup.getFirewallRuleDescriptions();
        assertEquals(1, firewallRules.length);
        assertEquals("0.0.0.0/0", firewallRules[0].getIpRanges()[0]);
        assertEquals("tcp", firewallRules[0].getProtocol());
        assertEquals(22, firewallRules[0].getFromPort().intValue());
        assertEquals(22, firewallRules[0].getToPort().intValue());
        //subnets
        SubnetDescription[] subnets = vpcDescription.getSubnets();
        assertEquals(4, subnets.length);
        //first subnet
        SubnetDescription subnet = subnets[0];
        assertEquals("1aPublicPharma", subnet.getName());
        assertEquals("10.0.1.0/24", subnet.getIpRange());
        assertEquals("us-east-1a", subnet.getAZ());
        //second subnet
        subnet = subnets[1];
        assertEquals("1bPublicPharma", subnet.getName());
        assertEquals("10.0.2.0/24", subnet.getIpRange());
        assertEquals("us-east-1b", subnet.getAZ());
        //third subnet
        subnet = subnets[2];
        assertEquals("1aPrivatePharma", subnet.getName());
        assertEquals("10.0.3.0/24", subnet.getIpRange());
        assertEquals("us-east-1a", subnet.getAZ());
        //forth subnet
        subnet = subnets[3];
        assertEquals("1bPrivatePharma", subnet.getName());
        assertEquals("10.0.4.0/24", subnet.getIpRange());
        assertEquals("us-east-1b", subnet.getAZ());
        //internet gateway
        InternetGatewayDescription internetGatewayDescription = vpcDescription.getInternetGateway();
        assertEquals("IgwPharma", internetGatewayDescription.getName());
        assertEquals("PubicPharmaRT", internetGatewayDescription.getRouteTableName());
        assertEquals(2, internetGatewayDescription.getSubnetNames().length);
        assertEquals("1aPublicPharma", internetGatewayDescription.getSubnetNames()[0]);
        assertEquals("1bPublicPharma", internetGatewayDescription.getSubnetNames()[1]);
        assertEquals("0.0.0.0/0", internetGatewayDescription.getAddress());
        //NAT Gateways
        NatGatewayDescription[] natGateways = vpcDescription.getNatGateways();
        assertEquals(2, natGateways.length);
        //first NAT Gateway
        assertEquals("NatPharma1", natGateways[0].getName());
        assertEquals("eilpPharma1", natGateways[0].getElasticIpName());
        assertEquals("1aPrivatePharma", natGateways[0].getSubnetName());
        assertEquals("PrivatePharmaRT1", natGateways[0].getRouteTableName());
        assertEquals("0.0.0.0/0", natGateways[0].getDestinationAddress());
        //second NAT Gateway
        assertEquals("NatPharma2", natGateways[1].getName());
        assertEquals("eilpPharma2", natGateways[1].getElasticIpName());
        assertEquals("1bPrivatePharma", natGateways[1].getSubnetName());
        assertEquals("PrivatePharmaRT2", natGateways[1].getRouteTableName());
        assertEquals("0.0.0.0/0", natGateways[1].getDestinationAddress());
    }
}
