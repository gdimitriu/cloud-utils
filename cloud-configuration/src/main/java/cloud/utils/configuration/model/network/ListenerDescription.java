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

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "Listener")
public class ListenerDescription {

    @XmlElement(name = "LbProtocol")
    private String lbProtocol;

    @XmlElement(name = "LbPort")
    private int lbPort;

    @XmlElement(name = "InstanceProtocol")
    private String instanceProtocol;

    @XmlElement(name = "InstancePort")
    private int instancePort;

    public String getLbProtocol() {
        return lbProtocol;
    }

    public int getLbPort() {
        return lbPort;
    }

    public String getInstanceProtocol() {
        return instanceProtocol;
    }

    public int getInstancePort() {
        return instancePort;
    }
}
