/** create by auto at 2017-07-07 11:26:09**/
package com.booting.pub.service;

import com.star.framework.jdbc.service.BaseService;
import com.booting.pub.dto.MessageDTO;
import com.booting.pub.entity.MessageEntity;

/**
 * 消息服务
 *
 * @author auto
 *
 */
public interface MessageService extends BaseService<MessageEntity, MessageDTO> {

	public Integer getMessageCount(MessageDTO messageDTO);

}