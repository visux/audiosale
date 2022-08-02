/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package salaprove;

import Gui.FrPopupAutomatico;
import it.sauronsoftware.cron4j.Task;
import it.sauronsoftware.cron4j.TaskExecutionContext;
import java.util.Vector;


public class Avvisi extends Task {

	FrPopupAutomatico oFr;
        Vector vtDati = new Vector();

        public boolean canBePaused() {
		return true;
	}

	public boolean canBeStopped() {
		return true;
	}

	public boolean supportsCompletenessTracking() {
		return true;
	}

	public boolean supportsStatusTracking() {
		return true;
	}

	public void execute(TaskExecutionContext context) throws RuntimeException {
		// Mi ricavo la Data corrente
                 // per ricavare tutte le prenotazioni di oggi
		 String Data = Generic.getSDateFromUtil(Generic.getCurrentDateTime() );
		 // Istanza della Classe prenotazioni
                 Prenotazione oPrenota = new Prenotazione();
		 // carica tutte le prenotazioni che sono nel range tra l'ora corrente e 10 minuti in +
                 // se ci sono le prenotazioni allora le preleva e le inserisce in un vettore

     	         vtDati = (Vector)oPrenota.getListaInVettore(" data = '" + Data + "'" +
		                                                        " and ora between " + "'" + Generic.getSTimeFromUtil(Generic. getCurrentDateTime()) + "'" +
		                                                        " and '" + Generic.aggiungiMinuti(10) + "'"  ).clone() ;

                
		 // se esiste l'orario di prenotazione
		 if(vtDati.size() > 0){
		         //if(!oFr.isVisible()){
		              oFr = new FrPopupAutomatico(vtDati);
		              oFr.setModal(false);
		              oFr.setVisible(true);
		         //}

		}

             //for (int i = 1; i <= 30; i++) {
			//System.out.println("Task says: " + i);
			//context.setStatusMessage("i = " + i);
			//context.setCompleteness(i / 30D);
			try {
 				Thread.sleep(1000);
			} catch (InterruptedException e) {
				;
			}
			context.pauseIfRequested();
			if (context.isStopped()) {
				//break;
			}
		//}
	}

}

