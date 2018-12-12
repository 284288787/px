/** create by auto at 2018-03-02 16:40:49**/
package com.booting.question.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.booting.question.dto.QuestionDTO;
import com.booting.question.entity.QuestionEntity;
import com.booting.question.service.QuestionService;

import com.star.framework.jdbc.dao.JDBCSupport;

import com.star.framework.jdbc.dao.result.PageList;

import com.star.framework.jdbc.dao.result.QueryParam;

@Service("questionService")
public class QuestionServiceImpl extends JDBCSupport<QuestionEntity, QuestionDTO> implements QuestionService{

	private static final long serialVersionUID = 1L;

	@Override
	public QuestionDTO save(QuestionEntity questionEntity) {
		long id = this.persist(questionEntity);
		return get(id);
	}

	@Override
	public QuestionDTO update(QuestionEntity questionEntity) {
		this.dynamicMerge(questionEntity);
		return get(questionEntity.getQuestionId());
	}

	@Override
	public QuestionDTO updateAll(QuestionEntity questionEntity) {
		this.merge(questionEntity);
		return get(questionEntity.getQuestionId());
	}

	@Override
	public QuestionDTO updateBySql(QuestionDTO questionDTO) {
		if(null == questionDTO) return null;
		this.execute("question.updateQuestion", toMap(questionDTO, "yyyy-MM-dd HH:mm:ss"));
		if(null == questionDTO.getQuestionId()) return null;
		return get(questionDTO.getQuestionId());
	}

	@Override
	public int delete(long id) {
		return this.del(id);
	}

	@Override
	public void batchSave(List<QuestionEntity> entities) {
		if(null == entities || entities.isEmpty()){
			return;
		}
		Map<String, Object>[] params = toMap(entities);
		this.batch("question.insertQuestion", params);
	}

	@Override
	public void batchUpdate(List<QuestionDTO> dtos) {
		if(null == dtos || dtos.isEmpty()){
			return;
		}
		Map<String, Object>[] params = toMap(dtos);
		this.batch("question.updateQuestion", params);
	}

	@Override
	public QuestionDTO get(long questionId) {
		return getById(questionId);
	}

	@Override
	public QuestionDTO get(QuestionDTO questionDTO) {
		if(null == questionDTO) {
			return null;
		}
		Map<String, Object> param = toMap(questionDTO, "yyyy-MM-dd HH:mm:ss");
		return this.queryForObject("question.getSimpleQuestionList", param);
	}

	@Override
	public List<QuestionDTO> getSimpleList(QuestionDTO questionDTO) {
		Map<String, Object> param = toMap(questionDTO, "yyyy-MM-dd HH:mm:ss");
		return this.queryForList("question.getSimpleQuestionList", param);
	}

	@Override
	public PageList<QuestionDTO> getSimpleListForPage(QuestionDTO questionDTO, int pageNo, int pageSize) {
		QueryParam queryParam = new QueryParam();
		queryParam.setPageNo(pageNo);
		queryParam.setPageSize(pageSize);
		queryParam.setParam(questionDTO);
		return this.queryForPage("question.getSimpleQuestionListCount", "question.getSimpleQuestionList", queryParam);
	}

	@Override
	public PageList<QuestionDTO> getSimpleListForPage(QueryParam queryParam) {
		return this.queryForPage("question.getSimpleQuestionListCount", "question.getSimpleQuestionList", queryParam);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> List<T> getList(T dto) {
		Map<String, Object> param = toMap(dto, "yyyy-MM-dd HH:mm:ss");
		return (List<T>)this.queryForList("question.getQuestionList", param);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> PageList<T> getListForPage(T dto, int pageNo, int pageSize) {
		QueryParam queryParam = new QueryParam();
		queryParam.setPageNo(pageNo);
		queryParam.setPageSize(pageSize);
		queryParam.setParam(dto);
		return (PageList<T>) this.queryForPage("question.getQuestionListCount", "question.getQuestionList", queryParam, dto.getClass());
	}

	@Override
	public <T> PageList<T> getListForPage(QueryParam queryParam, Class<T> clazz) {
		return (PageList<T>) this.queryForPage("question.getQuestionListCount", "question.getQuestionList", queryParam, clazz);
	}

}