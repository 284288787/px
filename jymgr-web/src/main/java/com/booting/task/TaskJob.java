/**create by liuhua at 2018年3月12日 上午9:45:11**/
package com.booting.task;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Service;

import com.booting.common.PushInfo;
import com.booting.service.CommonWebService;
import com.star.framework.queue.Queue;
import com.star.framework.queue.QueueFactory;

@Service
@EnableScheduling
public class TaskJob {
	public static Queue<PushInfo> messages = QueueFactory.createQueue("message");
	private ScheduledExecutorService newScheduledThreadPool = Executors.newScheduledThreadPool(2);
	
	@Autowired
	private CommonWebService commonWebService;
	
	@PostConstruct
	public void systemStart(){
		newScheduledThreadPool.scheduleAtFixedRate(() -> {
			boolean bool = true;
			while (bool) {
				PushInfo pushInfo = messages.poll();
				if (null == pushInfo) {
					bool = false;
					break;
				}
				commonWebService.writeMessage(pushInfo);
			}
		}, 10, 10, TimeUnit.SECONDS);
	}
	
	
}
