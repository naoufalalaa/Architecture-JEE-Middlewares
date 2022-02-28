package dao;

import org.springframework.stereotype.Component;

@Component
public class DaoImp implements IDao{

    public double getValue() {
        /*
            Traitement Base de données
         */
        return 5;
    }
}
