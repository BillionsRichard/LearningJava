/**
 * 
 */
package com.cd.ncdh.blockingQueue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 *  使用阻塞队列实现线程间的互斥与通信。
 *  具体实现功能：
 *  	A、B两个线程；A循环10次，B循环5次；总共循环3次.
 * @author Administrator
 *
 */
public class ThreadCommunicationWithBlockingQueue {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Business b = new Business();
		
		new Thread(new Runnable() {
			@Override
			public void run() {
				for(int i=0; i<3; i++)
				{
					b.main(10);
				}
			}
		}, "A").start();
		
		new Thread(new Runnable() {
			@Override
			public void run() {
				for(int i=0; i<3; i++)
				{
					b.sub(5);
				}
			}
		}, "B").start();
		
		
		
	}

}

class Business
{
	BlockingQueue<Integer> bq = new ArrayBlockingQueue<>(1);
	BlockingQueue<Integer> bq1 = new ArrayBlockingQueue<>(1);
	
	{
		try {
			bq1.put(1);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	void main(int times)
	{
		try {
			bq.put(1);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		for(int i=1; i<=times; i++)
		{
			System.out.println(Thread.currentThread().getName() + " is looping " + i);
		}
		
		try {
			bq1.take();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	void sub(int subTimes)
	{
		try {
			bq1.put(1);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		for(int i=1; i<=subTimes; i++)
		{
			System.out.println(Thread.currentThread().getName() + " is looping " + i);
		}
		
		try {
			bq.take();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
