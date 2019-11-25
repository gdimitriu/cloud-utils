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
package cloud.utils.configuration.model.auth;

import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "Policy")
public class PolicyDescription {

    @XmlAttribute(name = "Name")
    private String name;

    @XmlElement(name = "Version")
    private String version;

    @XmlElement(name = "Sid")
    private String sid;

    @XmlElement(name = "Effect")
    private String efect;

    @XmlElement(name = "Principal")
    private PolicyPrincipalDescription[] principal;

    @XmlElement(name = "Action")
    private String[] actions;

    @XmlElement(name = "Resource")
    private String resource;

    @XmlElement(name = "Condition")
    private PolicyConditionDescription[] conditions;

    public String getName() {
        return name;
    }

    public String getVersion() {
        return version;
    }

    public String getSid() {
        return sid;
    }

    public String getEfect() {
        return efect;
    }

    public PolicyPrincipalDescription[] getPrincipal() {
        return principal;
    }

    public String[] getActions() {
        return actions;
    }

    public String getResource() {
        return resource;
    }

    public PolicyConditionDescription[] getConditions() {
        return conditions;
    }
}
