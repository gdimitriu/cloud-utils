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
        assertEquals("LINUX", computingResourceDescription.getOsTypeAsString());
        assertEquals("WebServerSecurityGroupLinux", computingResourceDescription.getSecurityGroupName());
        assertEquals("1aPublicPharma", computingResourceDescription.getSubnetName());
        assertEquals("javaAutoKeyPair", computingResourceDescription.getKeyPairName());
        assertEquals(1, computingResourceDescription.getMinInstances());
        assertEquals(1, computingResourceDescription.getMaxInstances());
        String[] installScript = computingResourceDescription.getInstallScript();
        assertEquals(13, installScript.length);
        assertEquals("#!/bin/bash", installScript[0]);
        assertEquals("exec > /tmp/start.log  2>&1", installScript[1]);
        assertEquals("sudo yum update -y", installScript[2]);
        assertEquals("sudo yum install httpd -y", installScript[3]);
        assertEquals("sudo chkconfig httpd on", installScript[4]);
        assertEquals("cd /home/ec2-user", installScript[5]);
        assertEquals("aws s3 cp s3://gabrieldimitriupharmaweb/pharma_webservers/web1.zip web.zip", installScript[6]);
        assertEquals("sudo unzip web.zip -d /var/www/html/", installScript[7]);
        assertEquals("rm web.zip", installScript[8]);
        assertEquals("aws s3 cp s3://gabrieldimitriupharmaweb/pharma_webservers/welcome.conf welcome.conf", installScript[9]);
        assertEquals("sudo cp welcome.conf /etc/httpd/conf.d/", installScript[10]);
        assertEquals("rm welcome.conf", installScript[11]);
        assertEquals("sudo /etc/init.d/httpd start", installScript[12]);
    }
}
