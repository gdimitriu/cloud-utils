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
package cloud.utils.configuration.tests.model.computing;

import cloud.utils.configuration.model.computing.ComputingResourceDescription;
import org.junit.Test;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class ComputingResourceDescriptionTest {
    @Test
    public void  testLoadFromXml() throws JAXBException, IOException {
        JAXBContext context = JAXBContext.newInstance(ComputingResourceDescription.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        ComputingResourceDescription computingResourceDescription = (ComputingResourceDescription) unmarshaller.unmarshal(getClass()
                .getModule().getResourceAsStream("ComputingResourceDescription.xml"));
        assertEquals("WebInstance1", computingResourceDescription.getName());
        assertEquals("ami-00eb20669e0990cb4", computingResourceDescription.getImageName());
        assertEquals("T2_Micro", computingResourceDescription.getInstanceType());
        assertEquals("WebServerSecurityGroupLinux", computingResourceDescription.getSecurityGroupName());
        assertEquals("1aPublicPharma", computingResourceDescription.getSubnetName());
        assertEquals("javaAutoKeyPair", computingResourceDescription.getKeyPairName());
        assertEquals(1, computingResourceDescription.getMinInstances());
        assertEquals(1, computingResourceDescription.getMaxInstances());
    }
}
