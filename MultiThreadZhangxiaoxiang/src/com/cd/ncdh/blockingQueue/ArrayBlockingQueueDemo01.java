/**
 * 
 */
package com.cd.ncdh.blockingQueue;

import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * @author Administrator
 *
 */
public class ArrayBlockingQueueDemo01 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		BlockingQueue<Object> bq = new ArrayBlockingQueue<>(3);
		Random r = new Random();
		
		for(int i=0; i<2; i++)
		{
			new Thread(new Runnable() {
				@Override
				public void run() {
					while(true)
					{
						try 
						{
							int sleepTime = r.nextInt(1500);
							Thread.sleep(3000);
							Object putData = "data" + sleepTime;
							bq.put(putData);
							System.out.println(Thread.currentThread().getName() + " has put data:" + putData + "; queue size:" + bq.size());
							
						} catch (InterruptedException e) 
						{
							e.printStackTrace();
						}
					}
				}
			}, "Producer"+i).start();
		}
		
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				while(true)
				{
					try {
						Thread.sleep(200);
						Object gotdata = bq.take();
						System.out.println("<--" + Thread.currentThread().getName() + " has got data:" + gotdata + ", queue size:" + bq.size());
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}, "Consumer").start();
	}

}
