package salaprove;

import it.sauronsoftware.cron4j.SchedulerListener;
import it.sauronsoftware.cron4j.TaskExecutor;

/**
 * A very simple SchedulerListener, sending messages to the console.
 */
public class AscoltaAvvisi implements SchedulerListener {

	public void taskLaunching(TaskExecutor executor) {
		System.out.println("Controllo Prenotazioni!");
	}

	public void taskSucceeded(TaskExecutor executor) {
		System.out.println("Prenotazioni Controllate!");
	}

	public void taskFailed(TaskExecutor executor, Throwable exception) {
		System.out.println("Fallito il tentativo di controllare le prenotazioni!");
		exception.printStackTrace();
	}

}