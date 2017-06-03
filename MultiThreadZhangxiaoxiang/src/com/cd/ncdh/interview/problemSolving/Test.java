/*  �����⣺���г���ͬʱ������4���߳�ȥ����TestDo.doSome(key, value)����������TestDo.doSome(key, value)�����ڵĴ���������ͣ1�룬
 * Ȼ�����������Ϊ��λ�ĵ�ǰʱ��ֵ�����ԣ����ӡ��4����ͬ��ʱ��ֵ��������ʾ��
	4:4:1258199615
	1:1:1258199615
	3:3:1258199615
	1:2:1258199615
    ���޸Ĵ��룬����м����̵߳���TestDo.doSome(key, value)����ʱ�����ݽ�ȥ��key��ȣ�equals�Ƚ�Ϊtrue�������⼸���߳�Ӧ�����Ŷ���������
    �����������̵߳�key����"1"ʱ�������е�һ��Ҫ�����������߳���1����������������ʾ��
	4:4:1258199615
	1:1:1258199615
	3:3:1258199615
	1:2:1258199616
  ��֮����ÿ���߳���ָ����key���ʱ����Щ���key���߳�Ӧÿ��һ���������ʱ��ֵ��Ҫ�û��⣩�����key��ͬ������ִ�У��໥֮�䲻���⣩��
  ԭʼ�������£�*/

package com.cd.ncdh.interview.problemSolving;

import java.util.concurrent.ConcurrentHashMap;

//���ܸĶ���Test��	
/**
 * @author Administrator
 *
 */
public class Test extends Thread {

	private Consumer consumer;
	private String key;
	private String value;

	
	public Test(String key, String key2, String value) {
		this.consumer = Consumer.getInstance();
		/*
		 * ����"1"��"1"��ͬһ�������������д������Ҫ��"1"+""�ķ�ʽ�����µĶ���
		 * ��ʵ������û�иı䣬��Ȼ��ȣ�����Ϊ"1"����������ȴ������ͬһ����Ч��
		 */
		this.key = key + key2;
		this.value = value;

		
	}

	public static void main(String[] args) throws InterruptedException {
		Test a = new Test("1", "", "1");
		Test b = new Test("1", "", "2");
		Test c = new Test("3", "", "3");
		Test d = new Test("4", "", "4");
		System.out.println("begin:" + (System.currentTimeMillis() / 1000));
		a.start();
		b.start();
		c.start();
		d.start();

	}

	public void run() {
		consumer.doSome(key, value);
	}


}

class Consumer {
	private ConcurrentHashMap<Object, Object> lockMap = new ConcurrentHashMap<>();
	
	private Consumer() {
	}
	
	private static Consumer _instance = new Consumer();
	
	public static Consumer getInstance() {
		return _instance;
	}
	
	public void doSome(Object key, String value) {
		if(!lockMap.containsKey(key))
		{
			lockMap.put(key, new Object());
		}		
		Object lock = lockMap.get(key);
		// �Դ������ڵ�����Ҫ�ֲ�ͬ���Ĵ��룬���ܸĶ�!
		synchronized (lock) {
			try {
				Thread.sleep(1000);
				System.out.println(key + ":" + value + ":" + (System.currentTimeMillis() / 1000));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}