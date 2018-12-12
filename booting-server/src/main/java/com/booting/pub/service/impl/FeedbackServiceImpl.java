/** create by auto at 2017-06-13 14:16:44**/
package com.booting.pub.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.booting.pub.dto.FeedbackDTO;
import com.booting.pub.entity.FeedbackEntity;
import com.booting.pub.service.FeedbackService;

import com.star.framework.jdbc.dao.JDBCSupport;

import com.star.framework.jdbc.dao.result.PageList;

import com.star.framework.jdbc.dao.result.QueryParam;

@Service("feedbackService")
public class FeedbackServiceImpl extends JDBCSupport<FeedbackEntity, FeedbackDTO> implements FeedbackService{

	private static final long serialVersionUID = 1L;

	@Override
	public FeedbackDTO save(FeedbackEntity feedbackEntity) {
		long id = this.persist(feedbackEntity);
		return get(id);
	}

	@Override
	public FeedbackDTO update(FeedbackEntity feedbackEntity) {
		this.dynamicMerge(feedbackEntity);
		return get(feedbackEntity.getId());
	}

	@Override
	public FeedbackDTO updateAll(FeedbackEntity feedbackEntity) {
		this.merge(feedbackEntity);
		return get(feedbackEntity.getId());
	}

	@Override
	public FeedbackDTO updateBySql(FeedbackDTO feedbackDTO) {
		if(null == feedbackDTO) return null;
		this.execute("feedback.updateFeedback", toMap(feedbackDTO, "yyyy-MM-dd HH:mm:ss"));
		if(null == feedbackDTO.getId()) return null;
		return get(feedbackDTO.getId());
	}

	@Override
	public int delete(long id) {
		return this.del(id);
	}

	@Override
	public void batchSave(List<FeedbackEntity> entities) {
		if(null == entities || entities.isEmpty()){
			return;
		}
		Map<String, Object>[] params = toMap(entities);
		this.batch("feedback.insertFeedback", params);
	}

	@Override
	public void batchUpdate(List<FeedbackDTO> dtos) {
		if(null == dtos || dtos.isEmpty()){
			return;
		}
		Map<String, Object>[] params = toMap(dtos);
		this.batch("feedback.updateFeedback", params);
	}

	@Override
	public FeedbackDTO get(long id) {
		return getById(id);
	}

	@Override
	public FeedbackDTO get(FeedbackDTO feedbackDTO) {
		if(null == feedbackDTO) {
			return null;
		}
		Map<String, Object> param = toMap(feedbackDTO, "yyyy-MM-dd HH:mm:ss");
		return this.queryForObject("feedback.getSimpleFeedbackList", param);
	}

	@Override
	public List<FeedbackDTO> getSimpleList(FeedbackDTO feedbackDTO) {
		Map<String, Object> param = toMap(feedbackDTO, "yyyy-MM-dd HH:mm:ss");
		return this.queryForList("feedback.getSimpleFeedbackList", param);
	}

	@Override
	public PageList<FeedbackDTO> getSimpleListForPage(FeedbackDTO feedbackDTO, int pageNo, int pageSize) {
		QueryParam queryParam = new QueryParam();
		queryParam.setPageNo(pageNo);
		queryParam.setPageSize(pageSize);
		queryParam.setParam(feedbackDTO);
		return this.queryForPage("feedback.getSimpleFeedbackListCount", "feedback.getSimpleFeedbackList", queryParam);
	}

	@Override
	public PageList<FeedbackDTO> getSimpleListForPage(QueryParam queryParam) {
		return this.queryForPage("feedback.getSimpleFeedbackListCount", "feedback.getSimpleFeedbackList", queryParam);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> List<T> getList(T dto) {
		Map<String, Object> param = toMap(dto, "yyyy-MM-dd HH:mm:ss");
		return (List<T>)this.queryForList("feedback.getFeedbackList", param);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> PageList<T> getListForPage(T dto, int pageNo, int pageSize) {
		QueryParam queryParam = new QueryParam();
		queryParam.setPageNo(pageNo);
		queryParam.setPageSize(pageSize);
		queryParam.setParam(dto);
		return (PageList<T>) this.queryForPage("feedback.getFeedbackListCount", "feedback.getFeedbackList", queryParam, dto.getClass());
	}

	@Override
	public <T> PageList<T> getListForPage(QueryParam queryParam, Class<T> clazz) {
		return (PageList<T>) this.queryForPage("feedback.getFeedbackListCount", "feedback.getFeedbackList", queryParam, clazz);
	}

}