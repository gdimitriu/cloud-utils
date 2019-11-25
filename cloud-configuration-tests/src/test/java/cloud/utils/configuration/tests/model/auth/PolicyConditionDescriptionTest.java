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
import org.junit.Test;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class PolicyConditionDescriptionTest {

    @Test
    public void  testLoadFromXml() throws JAXBException, IOException {
        JAXBContext context = JAXBContext.newInstance(PolicyConditionDescription.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        PolicyConditionDescription policyConditionDescription = (PolicyConditionDescription) unmarshaller.unmarshal(getClass()
                .getModule().getResourceAsStream("PolicyConditionDescription.xml"));
        assertEquals("ArnEquals", policyConditionDescription.getPredicate());
        assertEquals("aws:SourceArn", policyConditionDescription.getType());
        assertEquals("arn:aws:sns:us-west-2:599109622955:myTopic", policyConditionDescription.getResource());

    }
}
