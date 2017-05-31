/**
 * 线程读写锁的应用：多个读线程不互斥，多个写线程互斥。
 */
package com.cd.ncdh.multithread.zxx;

import java.util.Random;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author Administrator
 *
 */
public class ReadWriteLockDemo01 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		final Queue q = new Queue();
		final Random r = new Random();
		
		for(int i=0;i<3;i++)
		{
			new Thread(new Runnable() 
			{
				@Override
				public void run()
				{
					while(true)
					{
						q.get();
					}
				}
			}).start();
			
			
			new Thread(new Runnable()
			{
				@Override
				public void run()
				{
					while(true)
					{
						q.put(r.nextInt(1000));
					}
				}
			}).start();
		}
	}

}

class Queue
{
	private Object data = null;
	private ReentrantReadWriteLock rwl = new ReentrantReadWriteLock();
	
	public void get()
	{
		rwl.readLock().lock();
		System.out.println(Thread.currentThread().getName() + " is ready to get data.");
		try {
			Thread.sleep((long)(Math.random()*1000));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(Thread.currentThread().getName() + " has got data:" + (Integer)data);
		rwl.readLock().unlock();
	}
	
	public void put(int putData)
	{
		rwl.writeLock().lock();
		System.out.println(Thread.currentThread().getName() + " is ready to write data.");
		try {
			Thread.sleep((long)(Math.random()*1000));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		data = putData;
		System.out.println(Thread.currentThread().getName() + " has wrote data:" + data);
		rwl.writeLock().unlock();
	}
}
