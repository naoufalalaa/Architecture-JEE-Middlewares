package framework;

import javax.xml.bind.annotation.*;
import java.io.Serializable;
@XmlRootElement(name = "bean")
@XmlAccessorType(XmlAccessType.FIELD)
public class Bean implements Serializable{
    @XmlAttribute
    private String id;
    @XmlAttribute
    private String className;
    @XmlElement
    private Dependence dependence;

    public Bean() {
    }

    public Bean(String id, String className) {
        this.id = id;
        this.className = className;
    }

    public Bean(String id, String className, Dependence dependence) {
        this.id = id;
        this.className = className;
        this.dependence = dependence;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public Dependence getDependence() {
        return dependence;
    }

    public void setDependence(Dependence dependence) {
        this.dependence = dependence;
    }
}