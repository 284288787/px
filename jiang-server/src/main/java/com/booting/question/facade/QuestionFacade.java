/** create by auto at 2018-03-02 16:40:49**/
package com.booting.question.facade;

import java.io.Serializable;
import java.util.List;

import com.star.framework.jdbc.dao.result.PageList;
import com.star.framework.jdbc.dao.result.QueryParam;
import com.booting.question.dto.QuestionDTO;
import com.booting.question.entity.QuestionEntity;

public interface QuestionFacade extends Serializable {

	/**
	 * 新增 用户角色
	 */
	public Long saveQuestion(QuestionDTO questionDTO);

	/**
	 * 批量新增 用户角色
	 */
	public void batchSaveQuestion(List<QuestionDTO> dtos);

	/**
	 * 更新 用户角色
	 */
	public int updateQuestion(QuestionDTO questionDTO);

	/**
	 * 批量 用户角色
	 */
	public void batchUpdateQuestion(List<QuestionDTO> dtos);

	/**
	 * 删除 用户角色
	 */
	public int deleteQuestion(long questionId);

	/**
	 * 根据主键获取 用户角色
	 */
	public QuestionDTO getQuestion(long questionId);

	/**
	 * 根据条件获取一条 用户角色
	 */
	public QuestionDTO getQuestion(QuestionDTO questionDTO);

	/**
	 * 查询满足条件的 用户角色 列表(单表)
	 */
	public List<QuestionDTO> getQuestionList(QuestionDTO questionDTO);

	/**
	 * 查询满足条件的 用户角色 列表(分页)(单表)
	 */
	public PageList<QuestionDTO> getQuestionListForPage(QuestionDTO questionDTO, int pageNumber, int pageSize);

	/**
	 * 查询满足条件的 用户角色 列表(分页)(单表)
	 */
	public PageList<QuestionDTO> getQuestionListForPage(QueryParam queryParam);

	/**
	 * 用户角色DTO 转换成 Entity
	 */
	public QuestionEntity toQuestionEntity(QuestionDTO questionDTO);

	/**
	 * 用户角色DTOs 转换成 Entities
	 */
	public List<QuestionEntity> toQuestionEntities(List<QuestionDTO> dtoes);

	/**
	 * 查询满足条件的 列表(多表)
	 */
	public <T> List<T> getList(T dto);

	/**
	 * 查询满足条件的列表(分页)(多表)
	 */
	public <T> PageList<T> getListForPage(T dto, int pageNumber, int pageSize);

	/**
	 * 查询满足条件的列表(分页)(多表)
	 */
	public <T> PageList<T> getListForPage(QueryParam queryParam, Class<T> clazz);

}