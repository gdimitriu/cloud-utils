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
package cloud.utils.aws.utils.auth;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class Policy {
    @SerializedName("Version")
    private String version = "2008-10-17";
    @SerializedName("Statement")
    private List<PolicyStatement> statements;

    public Policy() {
        statements = new ArrayList<>();
        statements.add(new PolicyStatement());
    }

    public Policy(String version) {
        this();
        this.version = version;
    }

    public Policy withSid(String sid) {
        statements.get(0).setSid(sid);
        return this;
    }

    public Policy withEffect(String effect) {
        statements.get(0).setEffect(effect);
        return this;
    }

    public Policy withAction(String action) {
        statements.get(0).addAction(action);
        return this;
    }

    public Policy withResource(String resource) {
        statements.get(0).addResource(resource);
        return this;
    }

    public Policy withPrincipal(String service, String rule) {
        statements.get(0).addPrincipal(service, rule);
        return this;
    }

    public Policy withCondition(String condition, String type, String resource) {
        statements.get(0).addCondition(condition, type, resource);
        return this;
    }

    public String toJson() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }
}
