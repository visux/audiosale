

package TestsalaAudio;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import it.sauronsoftware.cron4j.Scheduler;
import salaprove.AscoltaAvvisi;
import salaprove.Avvisi;
/**
 *
 * @author Francesco Lopez
 */
public class Crono4JTest {

     public Crono4JTest() {

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
    public void avviaCron4J() {
               // ascoltatore
		AscoltaAvvisi listener = new AscoltaAvvisi();
		// preparazione dei processi
		Avvisi task = new Avvisi();
		// creazine dello scheduler
		Scheduler scheduler = new Scheduler();
		// registra ascoltatore
		scheduler.addSchedulerListener(listener);
		//ogni minuto scatta
		scheduler.schedule("* * * * *", task);
		// avvia lo scheduler
		scheduler.start();
		// si riposa per 5 minuti e poi riparte
		try {
			Thread.sleep(5L * 60L * 1000L);
		} catch (InterruptedException e) {
			;
		}
		// stop dello scheduler
		scheduler.stop();
    }
}
