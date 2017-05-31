/**
 * 自定义一个缓存系统
 */
package com.cd.ncdh.multithread.zxx;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 自定义一个缓存系统
 * @author Administrator
 *
 */
public class CacheDemo {
	private Map<String, Object> cache = new HashMap<>();
	private ReentrantReadWriteLock rwl = new ReentrantReadWriteLock();
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {

	}
	
	public Object getData(String key)
	{
		Object value = null;
		try
		{
			rwl.readLock().lock();
			value = cache.get(key);
			if(null == value)
			{
				rwl.readLock().unlock();
				rwl.writeLock().lock();
				
				if(null == value)//Double checking is very necessary.
				{
					//TODO: Get data from other place and write to cache.
					value = "Qry DB result";
					cache.put(key, value);//Write new data to cache.
				}
				
				rwl.writeLock().unlock();
				rwl.readLock().lock();
			}
		}
		finally 
		{
			rwl.readLock().unlock();
		}
		return value;
	}

}
