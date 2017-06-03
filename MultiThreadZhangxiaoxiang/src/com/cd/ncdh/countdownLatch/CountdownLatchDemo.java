/**
 * 
 */
package com.cd.ncdh.countdownLatch;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author Administrator
 *
 */
public class CountdownLatchDemo {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ExecutorService threadPool = Executors.newCachedThreadPool();
		
		final CountDownLatch cmdCdl = new CountDownLatch(1);
		final CountDownLatch endCdl = new CountDownLatch(3);
		Random r = new Random();
		
		for(int i=0;i<3; i++)
		{
			Player p = new Player(r, cmdCdl, endCdl);
			threadPool.execute(p);
		}
		
		System.out.println(Thread.currentThread().getName() + " is sending command.");
		cmdCdl.countDown();
		
		System.out.println(Thread.currentThread().getName() + " is waiting for result.");
		try {
			endCdl.await();
			System.out.println("All palyers have reached the end!!!");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		threadPool.shutdown();
	}

}

class Player implements Runnable
{	
	Random r;
	CountDownLatch cmdCdl;
	CountDownLatch endCdl;
	
	public Player(Random r, CountDownLatch cmdCdl, CountDownLatch endCdl) {
		super();
		this.r = r;
		this.cmdCdl = cmdCdl;
		this.endCdl = endCdl;
	}

	@Override
	public void run() {
		System.out.println(Thread.currentThread().getName() + " is ready...");
		try {
			this.cmdCdl.await();
			System.out.println(Thread.currentThread().getName() + " go out-->");
			
			Thread.sleep(r.nextInt(1000));
			
			System.out.println(Thread.currentThread().getName() + " reach the end!");
			this.endCdl.countDown();
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		} 
		
		
	}
}
