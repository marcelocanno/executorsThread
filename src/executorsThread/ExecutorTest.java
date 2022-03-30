package executorsThread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

class ThreadTrabalhadoraExecutor implements Runnable{

	private String num;
	
	public ThreadTrabalhadoraExecutor(String num) {
		super();
		this.num = num;
	}

	@Override
	public void run() {
		System.out.println(Thread.currentThread().getName() + "iniciou" + num);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(Thread.currentThread().getName() + "finalizou");
	}
	
}
	public class ExecutorTest {
		public static void main(String[] Args) {
			System.out.println(Runtime.getRuntime().availableProcessors());
			//ExecutorService executorService = Executors.newFixedThreadPool(4);
			ExecutorService executorService = Executors.newCachedThreadPool();
			//ExecutorService executorService = Executors.newSingleThreadExecutor();
			ThreadPoolExecutor threadPoolExecutor = (ThreadPoolExecutor) executorService;
			threadPoolExecutor.setCorePoolSize(2);   // não suporta numero que excede a memoria = exception
			threadPoolExecutor.setCorePoolSize(Runtime.getRuntime().availableProcessors());
			executorService.execute(new ThreadTrabalhadoraExecutor("1"));
			executorService.execute(new ThreadTrabalhadoraExecutor("2"));
			executorService.execute(new ThreadTrabalhadoraExecutor("3"));
			executorService.execute(new ThreadTrabalhadoraExecutor("4"));
			//Executors.newFixedThreadPool(16);
			executorService.shutdown();
			while(!executorService.isTerminated()) {}
			System.out.println(executorService.isTerminated());
			System.out.println("Finalizado");
 }
	
	
	
	
	
}
