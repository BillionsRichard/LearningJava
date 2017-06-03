/**
 * 
 */
package com.cd.ncdh.multithread.semphore;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * @author Administrator
 *
 */
public class SemphoreDemo01 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		final Semaphore sp = new Semaphore(10);
		ExecutorService threadPool = Executors.newCachedThreadPool();
		final Random r = new Random();
		
		for(int i=0; i<10; i++)
		{
			threadPool.submit(new Runnable() {
				
				@Override
				public void run() {
					try {
						sp.acquire();
						Thread.sleep(r.nextInt(800));
						System.out.println(Thread.currentThread().getName() + " is running, and total:" + (10-sp.availablePermits()) + " thread is running now.");
						System.out.println(Thread.currentThread().getName() + " is leaving...");
					} catch (InterruptedException e) {
						e.printStackTrace();
					}finally
					{
						sp.release();
					}
				}
			});
		}
	}

}
