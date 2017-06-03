/**
 * 
 */
package com.cd.ncdh.exchanger;

import java.util.Random;
import java.util.concurrent.Exchanger;

/**
 * @author Administrator
 *
 */
public class ExchangerDemo01 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
//	 ExecutorService threadPool = Executors.newFixedThreadPool(2);
	 Exchanger<Object> exg = new Exchanger<>();
	 Random r = new Random();
	 
	 Dealer duxiao = new Dealer("1�������", exg, r);
	 Dealer police = new Dealer("500������", exg, r);
	 
	 new Thread(duxiao, "ŵ��").start();
	 new Thread(police, "�����̾�").start();
	}

}


class Dealer implements Runnable
{
	Object holdData;
	Exchanger<Object> exchg;
	Random rd;
	
	public Dealer(Object holdData, Exchanger<Object> exchg, Random r) {
		this.holdData = holdData;
		this.exchg = exchg;
		this.rd = r;
	}

	@Override
	public void run() {
		System.out.println(Thread.currentThread().getName() + " holds:" + holdData);
		try {
			Thread.sleep(rd.nextInt(3));
			System.out.println(Thread.currentThread().getName() + " has reached the specified place to deal..");
			Object gotData = exchg.exchange(holdData);
			System.out.println(Thread.currentThread().getName() + " has got:" + gotData);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
}