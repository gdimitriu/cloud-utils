package cloud.utils.configuration.model.network;

import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
public class SecurityGroupDescription {

    /** the id from amazon after the group is created */
    @XmlTransient
    private String securityGroupId;

    @XmlAttribute(name = "Name")
    private String name;

    @XmlElement(name = "Description")
    private String desciption;

    @XmlElement(name = "FirewallRule")
    private FirewallRuleDescription[] firewallRuleDescriptions;

    public String getSecurityGroupId() {
        return securityGroupId;
    }

    public String getName() {
        return name;
    }

    public String getDesciption() {
        return desciption;
    }

    public FirewallRuleDescription[] getFirewallRuleDescriptions() {
        return firewallRuleDescriptions;
    }
}
