/** create by auto at 2017-07-10 11:18:35**/
package com.booting.pub.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.booting.pub.dto.MessageReadDTO;
import com.booting.pub.entity.MessageReadEntity;
import com.booting.pub.service.MessageReadService;

import com.star.framework.jdbc.dao.JDBCSupport;

import com.star.framework.jdbc.dao.result.PageList;

import com.star.framework.jdbc.dao.result.QueryParam;

@Service("messageReadService")
public class MessageReadServiceImpl extends JDBCSupport<MessageReadEntity, MessageReadDTO> implements MessageReadService{

	private static final long serialVersionUID = 1L;

	@Override
	public MessageReadDTO save(MessageReadEntity messageReadEntity) {
		long id = this.persist(messageReadEntity);
		return get(id);
	}

	@Override
	public MessageReadDTO update(MessageReadEntity messageReadEntity) {
		this.dynamicMerge(messageReadEntity);
		return get(messageReadEntity.getId());
	}

	@Override
	public MessageReadDTO updateAll(MessageReadEntity messageReadEntity) {
		this.merge(messageReadEntity);
		return get(messageReadEntity.getId());
	}

	@Override
	public MessageReadDTO updateBySql(MessageReadDTO messageReadDTO) {
		if(null == messageReadDTO) return null;
		this.execute("messageRead.updateMessageRead", toMap(messageReadDTO, "yyyy-MM-dd HH:mm:ss"));
		if(null == messageReadDTO.getId()) return null;
		return get(messageReadDTO.getId());
	}

	@Override
	public int delete(long id) {
		return this.del(id);
	}

	@Override
	public void batchSave(List<MessageReadEntity> entities) {
		if(null == entities || entities.isEmpty()){
			return;
		}
		Map<String, Object>[] params = toMap(entities);
		this.batch("messageRead.insertMessageRead", params);
	}

	@Override
	public void batchUpdate(List<MessageReadDTO> dtos) {
		if(null == dtos || dtos.isEmpty()){
			return;
		}
		Map<String, Object>[] params = toMap(dtos);
		this.batch("messageRead.updateMessageRead", params);
	}

	@Override
	public MessageReadDTO get(long id) {
		return getById(id);
	}

	@Override
	public MessageReadDTO get(MessageReadDTO messageReadDTO) {
		if(null == messageReadDTO) {
			return null;
		}
		Map<String, Object> param = toMap(messageReadDTO, "yyyy-MM-dd HH:mm:ss");
		return this.queryForObject("messageRead.getSimpleMessageReadList", param);
	}

	@Override
	public List<MessageReadDTO> getSimpleList(MessageReadDTO messageReadDTO) {
		Map<String, Object> param = toMap(messageReadDTO, "yyyy-MM-dd HH:mm:ss");
		return this.queryForList("messageRead.getSimpleMessageReadList", param);
	}

	@Override
	public PageList<MessageReadDTO> getSimpleListForPage(MessageReadDTO messageReadDTO, int pageNo, int pageSize) {
		QueryParam queryParam = new QueryParam();
		queryParam.setPageNo(pageNo);
		queryParam.setPageSize(pageSize);
		queryParam.setParam(messageReadDTO);
		return this.queryForPage("messageRead.getSimpleMessageReadListCount", "messageRead.getSimpleMessageReadList", queryParam);
	}

	@Override
	public PageList<MessageReadDTO> getSimpleListForPage(QueryParam queryParam) {
		return this.queryForPage("messageRead.getSimpleMessageReadListCount", "messageRead.getSimpleMessageReadList", queryParam);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> List<T> getList(T dto) {
		Map<String, Object> param = toMap(dto, "yyyy-MM-dd HH:mm:ss");
		return (List<T>)this.queryForList("messageRead.getMessageReadList", param);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> PageList<T> getListForPage(T dto, int pageNo, int pageSize) {
		QueryParam queryParam = new QueryParam();
		queryParam.setPageNo(pageNo);
		queryParam.setPageSize(pageSize);
		queryParam.setParam(dto);
		return (PageList<T>) this.queryForPage("messageRead.getMessageReadListCount", "messageRead.getMessageReadList", queryParam, dto.getClass());
	}

	@Override
	public <T> PageList<T> getListForPage(QueryParam queryParam, Class<T> clazz) {
		return (PageList<T>) this.queryForPage("messageRead.getMessageReadListCount", "messageRead.getMessageReadList", queryParam, clazz);
	}

}