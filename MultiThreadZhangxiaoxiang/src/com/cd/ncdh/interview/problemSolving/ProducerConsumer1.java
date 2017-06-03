package com.cd.ncdh.interview.problemSolving;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * �ֳɳ����е�ProducerConsumer1���еĴ����ڲ��ϵز������ݣ�Ȼ�󽻸�TestDo.doSome()����ȥ�����ͺ����������ڲ��ϵز������ݣ�
 * �������ڲ����������ݡ��뽫����������10���߳������������߲��������ݣ���Щ�����߶�����TestDo.doSome()����ȥ���д���
 * ��ÿ�������߶���Ҫһ����ܴ����꣬����Ӧ��֤��Щ�������߳�����������������ݣ�ֻ����һ���������������
 * ��һ�������߲����������ݣ���һ����������˭�����ԣ���Ҫ��֤��Щ�������߳��õ�����������˳��ġ�ԭʼ�������£�
 * 
 * @author Administrator
 *
 */
public class ProducerConsumer1 {

	public static void main(String[] args) {
		BlockingQueue<String> bq = new ArrayBlockingQueue<>(1);
		ExecutorService threadPool = Executors.newFixedThreadPool(10);
		Object lock = new Object();
		
		for(int i=0; i<10; i++)
		{
			threadPool.execute(new Runnable() {
				
				@Override
				public void run() {
					try {
						String data = bq.take();
						synchronized (lock)
						{
							String output = TestDo.doSome(data);
							System.out.println(Thread.currentThread().getName() + ":" + output);
						}
	
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					
				}
			});
		}
		
		System.out.println("begin:"+(System.currentTimeMillis()/1000));
		for(int i=0;i<10;i++){  //���в��ܸĶ�
			String input = i+"";  //���в��ܸĶ�
//			String output = TestDo.doSome(input);
//			System.out.println(Thread.currentThread().getName()+ ":" + output);
			try {
				bq.put(input);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}

//���ܸĶ���TestDo��
class TestDo {
	public static String doSome(String input){
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		String output = input + ":"+ (System.currentTimeMillis() / 1000);
		return output;
	}
}