package Presentation;

import framework.ServiceCr;
import metier.IMetier;

public class CreateIOC {
    public static void main(String[] args) throws Exception {
        ServiceCr serviceCr = new ServiceCr();
        IMetier metier = (IMetier) serviceCr.createObject("metier.MetierImpl","dao.DaoImp");
        System.out.println("version Create beans = "+metier.calcul());
    }
}
