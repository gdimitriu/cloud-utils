package cloud.utils.configuration.model.network;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class SubnetDescription {

    @XmlAttribute(name = "Name")
    private String name;

    @XmlElement(name = "IpRange")
    private String ipRange;

    @XmlElement(name = "AZ")
    private String aZ;

    public String getName() {
        return name;
    }

    public String getIpRange() {
        return ipRange;
    }

    public String getaZ() {
        return aZ;
    }
}
