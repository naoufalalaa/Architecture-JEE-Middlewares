package metier;


import org.junit.gen5.api.Assertions;
import org.junit.gen5.api.Test;

public class CalculTest {
    private Calcul calcul;
    @Test
    public void testSomme(){
        calcul = new Calcul();
        double a = 5;
        double b = 9;
        double expected = 14;
        double res = calcul.Somme(a,b);
        Assertions.assertTrue(res==expected);
    }
}
