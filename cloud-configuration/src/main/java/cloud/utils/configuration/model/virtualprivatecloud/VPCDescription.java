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
package cloud.utils.configuration.model.virtualprivatecloud;

import cloud.utils.configuration.model.network.SecurityGroupDescription;
import cloud.utils.configuration.model.network.SubnetDescription;

import javax.xml.bind.annotation.*;

@XmlRootElement(name = "VPC")
@XmlAccessorType(XmlAccessType.FIELD)
public class VPCDescription {

    @XmlAttribute(name = "Name")
    private String vpcName;

    @XmlElement(name = "IpRange")
    private String ipRange;

    @XmlElement(name = "SecurityGroup")
    private SecurityGroupDescription[] securityGroups;

    @XmlElement(name = "Subnet")
    private SubnetDescription[] subnets;

    /** the id of the vpc to be used in construction */
    @XmlTransient
    private String vpcId;

    public String getVpcName() {
        return vpcName;
    }

    public String getIpRange() {
        return ipRange;
    }

    public String getVpcId() {
        return vpcId;
    }
}
