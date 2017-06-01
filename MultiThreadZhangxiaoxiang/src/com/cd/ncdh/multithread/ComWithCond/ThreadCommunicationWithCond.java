package com.cd.ncdh.multithread.ComWithCond;

import java.util.Random;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class ThreadCommunicationWithCond {
	static ReentrantLock rl = new ReentrantLock();
	static Condition cdLb = rl.newCondition();
	static Condition cdGy = rl.newCondition();
	static Condition cdZf = rl.newCondition();
	static int seq = 0;
	
	public static void main(String[] args) {
		final Random r = new Random();
		
		new Thread(new Runnable() {
			@Override
			public void run() {
				while(true)
				{
					try
					{
						rl.lock();
						while(seq!=0)
						{
								cdLb.await();
						}
						for(int i=0; i<2; i++)
						{
							Thread.sleep(r.nextInt(800));
							System.out.println(Thread.currentThread().getName() + " is showing...");
						}
						seq = 1;
						cdGy.signal();
					}
					catch (Exception e) 
					{
						e.printStackTrace();
					}
					finally {
						rl.unlock();
					}
				}
			}
		}, "刘玄德").start();
		new Thread(new Runnable() {
			@Override
			public void run() {
				while(true)
				{
					try
					{
						rl.lock();
						while(seq!=1)
						{
								cdGy.await();
						}
						for(int i=0; i<3; i++)
						{
							Thread.sleep(r.nextInt(800));
							System.out.println(Thread.currentThread().getName() + " is showing...");
						}
						seq = 2;
						cdZf.signal();
					}
					catch (Exception e) 
					{
						e.printStackTrace();
					}
					finally 
					{
						rl.unlock();
					}
				}
			}
		}, "关云长").start();
		
		new Thread(new Runnable() {
			@Override
			public void run() {
				while(true)
				{
					try
					{
						rl.lock();
						while(seq!=2)
						{
							cdZf.await();
						}
						for(int i=0; i<4; i++)
						{
							Thread.sleep(r.nextInt(800));
							System.out.println(Thread.currentThread().getName() + " is showing...");
						}
						seq = 0;
						cdLb.signal();
					}
					catch (Exception e) {
						e.printStackTrace();
					}
					finally 
					{
						rl.unlock();
					}
				}
			}
		}, "张翼德").start();
	}
	
	

}
