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
@XmlRootElement(name = "FirewallRule")
public class FirewallRuleDescription {

    @XmlElement(name = "IpRange")
    private String[] ipRanges;

    @XmlElement(name = "Protocol")
    private String protocol;

    @XmlElement(name = "from")
    private Integer fromPort;

    @XmlElement(name = "to")
    private Integer toPort;

    @XmlTransient
    private String id;

    public String[] getIpRanges() {
        return ipRanges;
    }

    public String getProtocol() {
        return protocol;
    }

    public Integer getFromPort() {
        return fromPort;
    }

    public Integer getToPort() {
        return toPort;
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
