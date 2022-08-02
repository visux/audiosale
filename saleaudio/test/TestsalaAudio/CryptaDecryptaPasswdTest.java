/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package TestsalaAudio;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import salaprove.Utente;

/**
 *
 * @author Francesco Lopez
 */
public class CryptaDecryptaPasswdTest {

    public CryptaDecryptaPasswdTest() {
        
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @Test
    public void cryptaPasswd() {
        // test crypt password
        String sPassword =  Utente.CryptStr("ciaomondo");
        System.out.println(sPassword);
    }

    @Test
    public void deCryptaPasswd() {
       // crypta la password da console e poi me la decrypta daccapo
       String sPassword =  Utente.decryptStr(Utente.CryptStr("ciaomondo"));
       System.out.println(sPassword);
    }
     

}
