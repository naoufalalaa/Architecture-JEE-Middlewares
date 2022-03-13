package Presentation;

import framework.ServiceCr;
import metier.IMetier;


public class ReadIOC {
    public static void main(String[] args) throws Exception {
        ServiceCr serviceCr = new ServiceCr();
        IMetier metier = (IMetier) serviceCr.readXMLBean("metierimpl");
        System.out.println("version Read from beans = "+metier.calcul());
    }
}
