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
package cloud.utils.configuration.tests.model;

import cloud.utils.configuration.tests.model.auth.PolicyConditionDescriptionTest;
import cloud.utils.configuration.tests.model.auth.PolicyDescriptionTest;
import cloud.utils.configuration.tests.model.auth.PolicyPrincipalDescriptionTest;
import cloud.utils.configuration.tests.model.computing.ComputingResourceDescriptionTest;
import cloud.utils.configuration.tests.model.network.*;
import cloud.utils.configuration.tests.model.virtualprivatecloud.VPCDescriptionTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({FirewallRuleDescriptionTest.class, SecurityGroupDescriptionTest.class,
        SubnetDescriptionTest.class, InternetGatewayDescriptionTest.class, NatGatewayDescriptionTest.class,
        ListenerDescriptionTest.class, LoadBalancerDescriptionTest.class,
        VPCDescriptionTest.class,
        ComputingResourceDescriptionTest.class,
        PolicyPrincipalDescriptionTest.class, PolicyConditionDescriptionTest.class, PolicyDescriptionTest.class})
public class AllModelSuiteTests {
}
