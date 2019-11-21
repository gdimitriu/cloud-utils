package cloud.utils.configuration.model.network;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class FirewallRuleDescription {

    @XmlElement(name = "IpRange")
    private String[] ipRanges;

    @XmlElement(name = "Protocol")
    private String protocol;

    @XmlElement(name = "from")
    private Integer fromPort;

    @XmlElement(name = "to")
    private Integer toPort;

    public String[] getIpRanges() {
        return ipRanges;
    }

    public String getProtocol() {
        return protocol;
    }

    public Integer getFromPort() {
        return fromPort;
    }

    public Integer getToPort() {
        return toPort;
    }
}
