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
package cloud.utils.aws.utils.tests.auth;


import org.junit.Test;
import cloud.utils.aws.utils.auth.Policy;

public class PolicyLoadTest {
    @Test
    public void loadAndTestJsonPolicy() {
        String jsonPolicy;
        Policy policy = new Policy().withSid("topic-subscription-arn:aws:sns:us-west-2:599109622955:myTopic")
                .withEffect("Allow").withPrincipal("AWS","*").withAction("SQS:SendMessage")
                .withResource("arn:aws:sqs:us-west-2:599109622955:myQueue")
                .withCondition("ArnLike", "aws:SourceArn", "arn:aws:sns:us-west-2:599109622955:myTopic");
        jsonPolicy = policy.toJson();
        System.out.println(jsonPolicy);
    }

    @Test
    public void EC2_With_full_S3() {
        String jsonPolicy;
        Policy policy = new Policy("2012-10-17").withEffect("Allow").withAction("sts:AssumeRole").withPrincipal("Service", "ec2.amazonaws.com");
        jsonPolicy = policy.toJson();
        System.out.println(jsonPolicy);
    }

    public static void main(String...args) {
        new PolicyLoadTest().loadAndTestJsonPolicy();
        new PolicyLoadTest().EC2_With_full_S3();
    }
}
