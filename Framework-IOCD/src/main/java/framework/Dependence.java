package framework;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
@XmlRootElement(name = "dependence")
@XmlAccessorType(XmlAccessType.FIELD)
class Dependence implements Serializable {
    @XmlAttribute
    private String name;
    @XmlAttribute
    private String ref;

    public Dependence() {
    }

    public Dependence(String id, String name) {
        this.name = id;
        this.ref = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRef() {
        return ref;
    }

    public void setRef(String ref) {
        this.ref = ref;
    }
}
