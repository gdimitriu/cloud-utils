package cloud.utils.configuration.model.virtualprivatecloud;

import cloud.utils.configuration.model.network.SecurityGroupDescription;
import cloud.utils.configuration.model.network.SubnetDescription;

import javax.xml.bind.annotation.*;

@XmlRootElement(name = "VPC")
@XmlAccessorType(XmlAccessType.FIELD)
public class VPCDescription {

    @XmlAttribute(name = "Name")
    private String vpcName;

    @XmlElement(name = "IpRange")
    private String ipRange;

    @XmlElement(name = "SecurityGroup")
    private SecurityGroupDescription[] securityGroups;

    @XmlElement(name = "Subnet")
    private SubnetDescription[] subnets;

    /** the id of the vpc to be used in construction */
    @XmlTransient
    private String vpcId;

    public String getVpcName() {
        return vpcName;
    }

    public String getIpRange() {
        return ipRange;
    }

    public String getVpcId() {
        return vpcId;
    }
}
