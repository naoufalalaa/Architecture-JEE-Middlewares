package framework;


import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.lang.reflect.Method;
import java.util.List;

public class ServiceCr {
    private String object;

    public ServiceCr() {
    }

    public ServiceCr(String object) {
        this.object = object;
    }

    public String getObject() {
        return object;
    }

    public void setObject(String object) {
        this.object = object;
    }

    public void createXML(Object object , Object dependence) throws JAXBException {
        Beans beans = new Beans();
        Dependence dep = new Dependence();
        dep.setName(dependence.getClass().getName().split("\\.")[1].toLowerCase());
        dep.setRef(dependence.getClass().getName().split("\\.")[1].toLowerCase());
        Bean bean2 = new Bean(dep.getName(), dependence.getClass().getCanonicalName());

        Bean bean1 = new Bean();
        bean1.setId(object.getClass().getName().split("\\.")[1].toLowerCase());
        bean1.setClassName(object.getClass().getCanonicalName());
        bean1.setDependence(dep);

        beans.addBean(bean2);
        beans.addBean(bean1);
        JAXBContext context = JAXBContext.newInstance(Beans.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT,true);
        marshaller.marshal(beans, new File("config.xml"));
    }

    public void createXML(Object object) throws JAXBException {
        Beans beans = new Beans();

        Bean bean1 = new Bean();
        bean1.setId(object.getClass().getName().split("\\.")[1].toLowerCase());
        bean1.setClassName(object.getClass().getCanonicalName());

        beans.addBean(bean1);

        JAXBContext context = JAXBContext.newInstance(Beans.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT,true);
        marshaller.marshal(beans, new File("config.xml"));
    }

    public Object createObject(String object1,String object2) throws Exception {
        Class objectAA = Class.forName(object1);
        Class objectBB = Class.forName(object2);

        Object iObjectAA = objectAA.newInstance();
        Object iObjectBB = objectBB.newInstance();

        Method meth=objectAA.getMethod("setDao",objectBB.getInterfaces()[0]);
        meth.invoke(iObjectAA,iObjectBB);
        createXML(iObjectAA,iObjectBB);
        return iObjectAA;
    }

    public List<Bean> readBeans () throws Exception {
        JAXBContext context = JAXBContext.newInstance(Beans.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        Beans beans = (Beans) unmarshaller.unmarshal(new File("config.xml"));
        return beans.getBeans();
    }

    public Object readXMLBean(String idClass) throws Exception {
        Object myObject = null;
        List<Bean> beans = readBeans();
        for (Bean bean : beans){
            if(idClass.equals(bean.getId().toLowerCase())){
                myObject = Class.forName(bean.getClassName()).newInstance();
                if(bean.getDependence()==null) {
                    System.out.println(idClass + "     |      " + bean.getId().toLowerCase());
                    return myObject;
                }else{
                    for(Bean bean1: beans){
                        if((bean.getDependence().getRef()).equals(bean1.getId())){
                            Method meth = Class.forName(bean.getClassName()).getMethod("setDao",Class.forName(bean1.getClassName()).getInterfaces()[0]);
                            meth.invoke(myObject,Class.forName(bean1.getClassName()).newInstance());
                        }
                    }
                }
            }
        }
        return myObject;
    }
}