/**create by liuhua at 2016年4月18日 下午4:37:20**/
package com.star.framework.queue;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 消息队列工厂
 * @author liuhua
 *
 */
public class QueueFactory {

	private static Map<String, Queue<?>> queues = new ConcurrentHashMap<String, Queue<?>>();

	public static <T> Queue<T> createQueue() {
		return createQueue("");
	}

	public static <T> Queue<T> createQueue(String name) {
		return createQueue(name, 10000, 3000);
	}

	public static synchronized <T> Queue<T> createQueue(String name, int size, long timeout) {
		@SuppressWarnings("unchecked")
		Queue<T> queue = (Queue<T>) queues.get(name);
		if (queue == null) {
			queue = new Queue<T>(size, timeout);
			queues.put(name, queue);
		}
		return queue;
	}
}
