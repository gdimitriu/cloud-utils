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
package cloud.utils.configuration.tests.model.network;

import cloud.utils.configuration.model.network.ListenerDescription;
import cloud.utils.configuration.model.network.LoadBalancerDescription;
import org.junit.Test;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class LoadBalancerDescriptionTest {
    @Test
    public void testLoadFromXml() throws JAXBException, IOException {
        JAXBContext context = JAXBContext.newInstance(LoadBalancerDescription.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        LoadBalancerDescription loadBalancerDescription = (LoadBalancerDescription) unmarshaller.unmarshal(getClass()
                .getModule().getResourceAsStream("LoadBalancerDescription.xml"));
        ListenerDescription[] listeners = loadBalancerDescription.getListners();
        assertEquals(2, listeners.length);
        assertEquals("HTTP", listeners[0].getLbProtocol());
        assertEquals(80, listeners[0].getLbPort());
        assertEquals("HTTP", listeners[0].getInstanceProtocol());
        assertEquals(80, listeners[0].getInstancePort());
        assertEquals("HTTPS", listeners[1].getLbProtocol());
        assertEquals(81, listeners[1].getLbPort());
        assertEquals("HTTPS", listeners[1].getInstanceProtocol());
        assertEquals(81, listeners[1].getInstancePort());
        assertEquals("PharmaLB", loadBalancerDescription.getName());
        assertEquals(2, loadBalancerDescription.getSubnets().length);
        assertEquals("1aPublicPharma", loadBalancerDescription.getSubnets()[0]);
        assertEquals("1bPublicPharma", loadBalancerDescription.getSubnets()[1]);
        assertEquals("WebServerSecurityGroupLinux", loadBalancerDescription.getSecurityGroupName());
        assertEquals(true, loadBalancerDescription.isCrossBalancing());
        assertEquals(2, loadBalancerDescription.getInstanceNames().length);
        assertEquals("WebInstance1", loadBalancerDescription.getInstanceNames()[0]);
        assertEquals("WebInstance2", loadBalancerDescription.getInstanceNames()[1]);

    }
}
