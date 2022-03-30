package executorsThread;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public class ScheduledThreadPoolTest {
	
	private static final ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(1);
	 private static void beep() {
		final Runnable beeper = new Runnable() {
			@Override
			public void run() {
				System.out.println(new SimpleDateFormat("HH:mm:ss").format(new Date(0)) + "beep");
				try {
					TimeUnit.SECONDS.sleep(5);       // não está acontecendo o intervalo de tempo
				} catch (InterruptedException e) {   // aulas java 8 ano 2016
					e.printStackTrace();             // ocorre diferenças entre IDE uso eclipse aulas IJ
				}
			}
		};
		scheduledExecutorService.scheduleAtFixedRate(beeper, 1, 5, TimeUnit.SECONDS);
		//scheduledExecutorService.scheduleWithFixedDelay(beeper, 1, 5, TimeUnit.SECONDS);
		ScheduledFuture<?> scheduledFuture = scheduledExecutorService.scheduleWithFixedDelay(beeper, 1, 5,TimeUnit.SECONDS);
		scheduledExecutorService.schedule(new Runnable() {
			@Override
			public void run() {
				System.out.println("Cancelando");
				scheduledFuture.cancel(false);
			//	scheduledExecutorService.shutdown();
			}
		},10, TimeUnit.SECONDS);
	 }
	
	 public static void main(String[] args) {
		 beep();
	 }
}
