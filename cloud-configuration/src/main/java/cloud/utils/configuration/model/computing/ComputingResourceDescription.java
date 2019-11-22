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
package cloud.utils.configuration.model.computing;

import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "ComputingResource")
public class ComputingResourceDescription {
    @XmlAttribute(name = "Name")
    private String name;

    @XmlElement(name = "ImageName")
    private String imageName;

    @XmlElement(name = "InstanceType")
    private String instanceType;

    @XmlElement(name = "OSType")
    private OSType osType;

    @XmlElement(name = "SecurityGroupName")
    private String securityGroupName;

    @XmlElement(name = "SubnetName")
    private String subnetName;

    @XmlElement(name = "KeyPairName")
    private String keyPairName;

    @XmlElement(name = "MinInstances")
    private int minInstances;

    @XmlElement(name = "MaxInstances")
    private int maxInstances;

    @XmlElement(name = "InstallScript")
    private String[] installScript;

    @XmlTransient
    private String id;

    public String[] getInstallScript() {
        return installScript;
    }

    public int getMaxInstances() {
        return maxInstances;
    }

    public int getMinInstances() {
        return minInstances;
    }

    public String getKeyPairName() {
        return keyPairName;
    }

    public String getSubnetName() {
        return subnetName;
    }

    public String getSecurityGroupName() {
        return securityGroupName;
    }

    public String getOsTypeAsString() {
        return osType.name();
    }

    public OSType getOsType() {
        return osType;
    }

    public String getInstanceType() {
        return instanceType;
    }

    public String getImageName() {
        return imageName;
    }

    public String getName() {
        return name;
    }

    /**
     * get the resource id from cloud
     * @return the resource id
     */
    public String getId() {
        return id;
    }

    /**
     * set the resource id from cloud
     * @param id the resource if
     */
    public void setId(String id) {
        this.id = id;
    }
}

enum OSType {
    LINUX,
    WINDOWS
}