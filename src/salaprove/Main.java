
package salaprove;

import Gui.FrLogutente;

/**
 *
 * @author demone
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        try
        {
            ClassLoader c = ClassLoader.getSystemClassLoader();
            Generic.codebase = c.getResource("").toString();
        }
        catch(Exception e)
        {
            System.out.println("codebase non inizializzato");
        }

        FrLogutente oLogin = new FrLogutente();
        oLogin.setVisible(true);
    }

}
