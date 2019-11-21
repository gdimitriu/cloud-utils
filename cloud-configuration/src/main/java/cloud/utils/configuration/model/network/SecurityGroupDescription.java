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
package cloud.utils.configuration.model.network;

import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "SecurityGroup")
public class SecurityGroupDescription {

    /** the id from amazon after the group is created */
    @XmlTransient
    private String securityGroupId;

    @XmlAttribute(name = "Name")
    private String name;

    @XmlElement(name = "Description")
    private String desciption;

    @XmlElement(name = "FirewallRule")
    private FirewallRuleDescription[] firewallRuleDescriptions;

    public String getSecurityGroupId() {
        return securityGroupId;
    }

    public String getName() {
        return name;
    }

    public String getDesciption() {
        return desciption;
    }

    public FirewallRuleDescription[] getFirewallRuleDescriptions() {
        return firewallRuleDescriptions;
    }
}
