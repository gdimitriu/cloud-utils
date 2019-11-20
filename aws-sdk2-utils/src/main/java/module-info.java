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
module cloud.utils.aws.sdk2 {
    requires software.amazon.awssdk.core;
    requires software.amazon.awssdk.auth;
    requires software.amazon.awssdk.http.urlconnection;
    requires software.amazon.awssdk.regions;
    requires software.amazon.awssdk.services.ec2;
    requires software.amazon.awssdk.services.elasticloadbalancing;
    requires software.amazon.awssdk.services.iam;
    requires software.amazon.awssdk.services.s3;
    requires cloud.utils.aws.utils;
}