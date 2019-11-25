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

@XmlRootElement(name = "LoadBalancer")
@XmlAccessorType(XmlAccessType.FIELD)
public class LoadBalancerDescription {

    @XmlAttribute(name = "Name")
    private String name;

    @XmlElement(name = "SubnetName")
    private String[] subnets;

    @XmlElement(name = "SecurityGroupName")
    private String securityGroupName;

    @XmlElement(name = "Listener")
    private ListenerDescription[] listners;

    @XmlElement(name = "CrossBalancing")
    private boolean crossBalancing;

    @XmlElement(name = "InstanceName")
    private String[] instanceNames;

    @XmlTransient
    private String id;

    public String[] getInstanceNames() {
        return instanceNames;
    }

    public boolean isCrossBalancing() {
        return crossBalancing;
    }

    public ListenerDescription[] getListners() {
        return listners;
    }

    public String getSecurityGroupName() {
        return securityGroupName;
    }

    public String[] getSubnets() {
        return subnets;
    }

    public String getName() {
        return name;
    }

    /**
     * get the id resource from cloud
     * @return  id resource
     */
    public String getId() {
        return id;
    }

    /**
     * set the id resource from cloud
     * @param id the resource id
     */
    public void setId(String id) {
        this.id = id;
    }
}
