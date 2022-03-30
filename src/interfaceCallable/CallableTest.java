package interfaceCallable;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadLocalRandom;

public class CallableTest implements Callable<String>{

	@Override
	public String call() throws Exception {
		int count = ThreadLocalRandom.current().nextInt(1,11);
		for(int i = 0; i < count; i++) {
			System.out.println(Thread.currentThread().getName() + " executando ...");
		}
		return "Trabalho finalizado o numero aleatorio eh: " + count;
	}
	public static void main(String[] args) {
		CallableTest ct = new CallableTest();
		ExecutorService executorService = Executors.newCachedThreadPool();
		Future<String> resultFuture = executorService.submit(ct);
		try {
			String s = resultFuture.get();
			System.out.println(s);
		} catch (InterruptedException e) {
		
			e.printStackTrace();
		} catch (ExecutionException e) {
			
			e.printStackTrace();
		}
		executorService.shutdown();
	}
}
