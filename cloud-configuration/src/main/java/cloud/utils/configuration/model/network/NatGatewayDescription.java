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
@XmlRootElement(name = "NatGateway")
public class NatGatewayDescription {
    @XmlAttribute(name = "Name")
    private String name;

    @XmlElement(name = "ElasticIpName")
    private String elasticIpName;

    @XmlElement(name = "SubnetName")
    private String subnetName;

    @XmlElement(name = "RouteTableName")
    private String routeTableName;

    @XmlElement(name = "Address")
    private String destinationAddress;

    @XmlTransient
    private String id;

    public String getDestinationAddress() {
        return destinationAddress;
    }

    public String getRouteTableName() {
        return routeTableName;
    }

    public String getSubnetName() {
        return subnetName;
    }

    public String getElasticIpName() {
        return elasticIpName;
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
