package com.cd.ncdh.interview.problemSolving;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class PrintLog2 {
	
	public static void main(String[] args){
        BlockingQueue<String> logQueue = new ArrayBlockingQueue<>(1);
        
		System.out.println("begin:"+(System.currentTimeMillis()/1000));
		/*ģ�⴦��16����־������Ĵ��������16����־���󣬵�ǰ������Ҫ����16����ܴ�ӡ����Щ��־��
		�޸ĳ�����룬���ĸ��߳�����16��������4���Ӵ��ꡣ
		*/
		for(int i=0;i<4;i++)
		{
			new Thread(new Runnable() {
				@Override
				public void run() {
					for(int i=0; i<4; i++)
					{
						try 
						{
							String prtLog = logQueue.take();
							PrintLog.parseLog(prtLog);
						} catch (InterruptedException e) 
						{
							e.printStackTrace();
						}
					}
				}
			}).start();
			
		}
		
		for(int i=0;i<16;i++){  //���д��벻�ܸĶ�
			final String log = ""+(i+1);//���д��벻�ܸĶ�
				try
				{
					logQueue.put(log);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
		}
	}
	
	//parseLog�����ڲ��Ĵ��벻�ܸĶ�
	public static void parseLog(String log){
		System.out.println(log+":"+(System.currentTimeMillis()/1000));
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}		
	}
}