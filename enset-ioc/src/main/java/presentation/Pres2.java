package presentation;

import dao.DaoImp;
import metier.MetierImpl;

public class Pres2 {
    public static void main(String[] args) {
        DaoImp dao = new DaoImp();
        MetierImpl metier = new MetierImpl();
        metier.setDao(dao);
        System.out.println("version Static = "+metier.calcul());
    }
}
