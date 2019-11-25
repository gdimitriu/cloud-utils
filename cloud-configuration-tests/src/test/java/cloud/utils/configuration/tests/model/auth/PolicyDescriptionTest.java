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
package cloud.utils.configuration.tests.model.auth;

import cloud.utils.configuration.model.auth.PolicyConditionDescription;
import cloud.utils.configuration.model.auth.PolicyDescription;
import cloud.utils.configuration.model.auth.PolicyPrincipalDescription;
import org.junit.Test;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class PolicyDescriptionTest {

    @Test
    public void  testLoadFromXml() throws JAXBException, IOException {
        JAXBContext context = JAXBContext.newInstance(PolicyDescription.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        PolicyDescription policyDescription = (PolicyDescription) unmarshaller.unmarshal(getClass()
                .getModule().getResourceAsStream("PolicyDescription.xml"));
        PolicyConditionDescription[] conditions = policyDescription.getConditions();
        assertEquals(1, conditions.length);
        assertEquals("ArnEquals", conditions[0].getPredicate());
        assertEquals("aws:SourceArn", conditions[0].getType());
        assertEquals("arn:aws:sns:us-west-2:599109622955:myTopic", conditions[0].getResource());
        assertEquals("SNS_WITH_SQS", policyDescription.getName());
        assertEquals("2008-10-17", policyDescription.getVersion());
        assertEquals("topic-subscription-arn:aws:sns:us-west-2:599109622955:myTopic", policyDescription.getSid());
        assertEquals("Allow", policyDescription.getEfect());
        PolicyPrincipalDescription[] principals = policyDescription.getPrincipal();
        assertEquals(1, principals.length);
        assertEquals("AWS", principals[0].getService());
        assertEquals("*", principals[0].getRule());
        String[] actions = policyDescription.getActions();
        assertEquals(2, actions.length);
        assertEquals("SQS:SendMessage", actions[0]);
        assertEquals("SQS:ReceiveMessage", actions[1]);
        assertEquals("arn:aws:sqs:us-west-2:599109622955:myQueue", policyDescription.getResource());
    }
}
