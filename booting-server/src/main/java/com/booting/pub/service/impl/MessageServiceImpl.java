/** create by auto at 2017-07-07 11:26:09**/
package com.booting.pub.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.booting.pub.dto.MessageDTO;
import com.booting.pub.entity.MessageEntity;
import com.booting.pub.service.MessageService;

import com.star.framework.jdbc.dao.JDBCSupport;

import com.star.framework.jdbc.dao.result.PageList;

import com.star.framework.jdbc.dao.result.QueryParam;

@Service("messageService")
public class MessageServiceImpl extends JDBCSupport<MessageEntity, MessageDTO> implements MessageService{

	private static final long serialVersionUID = 1L;

	@Override
	public MessageDTO save(MessageEntity messageEntity) {
		long id = this.persist(messageEntity);
		return get(id);
	}

	@Override
	public MessageDTO update(MessageEntity messageEntity) {
		this.dynamicMerge(messageEntity);
		return get(messageEntity.getId());
	}

	@Override
	public MessageDTO updateAll(MessageEntity messageEntity) {
		this.merge(messageEntity);
		return get(messageEntity.getId());
	}

	@Override
	public MessageDTO updateBySql(MessageDTO messageDTO) {
		if(null == messageDTO) return null;
		this.execute("message.updateMessage", toMap(messageDTO, "yyyy-MM-dd HH:mm:ss"));
		if(null == messageDTO.getId()) return null;
		return get(messageDTO.getId());
	}

	@Override
	public int delete(long id) {
		return this.del(id);
	}

	@Override
	public void batchSave(List<MessageEntity> entities) {
		if(null == entities || entities.isEmpty()){
			return;
		}
		Map<String, Object>[] params = toMap(entities);
		this.batch("message.insertMessage", params);
	}

	@Override
	public void batchUpdate(List<MessageDTO> dtos) {
		if(null == dtos || dtos.isEmpty()){
			return;
		}
		Map<String, Object>[] params = toMap(dtos);
		this.batch("message.updateMessage", params);
	}

	@Override
	public MessageDTO get(long id) {
		return getById(id);
	}

	@Override
	public MessageDTO get(MessageDTO messageDTO) {
		if(null == messageDTO) {
			return null;
		}
		Map<String, Object> param = toMap(messageDTO, "yyyy-MM-dd HH:mm:ss");
		return this.queryForObject("message.getSimpleMessageList", param);
	}

	@Override
	public List<MessageDTO> getSimpleList(MessageDTO messageDTO) {
		Map<String, Object> param = toMap(messageDTO, "yyyy-MM-dd HH:mm:ss");
		return this.queryForList("message.getSimpleMessageList", param);
	}

	@Override
	public PageList<MessageDTO> getSimpleListForPage(MessageDTO messageDTO, int pageNo, int pageSize) {
		QueryParam queryParam = new QueryParam();
		queryParam.setPageNo(pageNo);
		queryParam.setPageSize(pageSize);
		queryParam.setParam(messageDTO);
		return this.queryForPage("message.getSimpleMessageListCount", "message.getSimpleMessageList", queryParam);
	}

	@Override
	public PageList<MessageDTO> getSimpleListForPage(QueryParam queryParam) {
		return this.queryForPage("message.getSimpleMessageListCount", "message.getSimpleMessageList", queryParam);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> List<T> getList(T dto) {
		Map<String, Object> param = toMap(dto, "yyyy-MM-dd HH:mm:ss");
		return (List<T>)this.queryForList("message.getMessageList", param);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> PageList<T> getListForPage(T dto, int pageNo, int pageSize) {
		QueryParam queryParam = new QueryParam();
		queryParam.setPageNo(pageNo);
		queryParam.setPageSize(pageSize);
		queryParam.setParam(dto);
		return (PageList<T>) this.queryForPage("message.getMessageListCount", "message.getMessageList", queryParam, dto.getClass());
	}

	@Override
	public <T> PageList<T> getListForPage(QueryParam queryParam, Class<T> clazz) {
		return (PageList<T>) this.queryForPage("message.getMessageListCount", "message.getMessageList", queryParam, clazz);
	}

	@Override
	public Integer getMessageCount(MessageDTO messageDTO) {
		int totalRecord = queryForObject("message.getSimpleMessageListCount", toMap(messageDTO, "yyyy-MM-dd HH:mm:ss"), Integer.class);
		return totalRecord;
	}

}