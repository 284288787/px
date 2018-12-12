/** create by auto at 2018-03-02 16:40:49**/
package com.booting.question.facade.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.booting.question.facade.QuestionFacade;
import com.star.framework.jdbc.dao.result.PageList;
import com.star.framework.jdbc.dao.result.QueryParam;
import com.star.framework.utils.CglibBeanUtils;

import com.booting.question.dto.QuestionDTO;
import com.booting.question.entity.QuestionEntity;
import com.booting.question.service.QuestionService;

@Service("questionFacade")
public class QuestionFacadeImpl implements QuestionFacade {
	private static final long serialVersionUID = 1L;

	@Autowired
	private QuestionService questionService;


	@Override
	public Long saveQuestion(QuestionDTO questionDTO){
		if (null == questionDTO) {
			return null;
		}
		QuestionEntity entity = toQuestionEntity(questionDTO);
		questionDTO = questionService.save(entity);
		return questionDTO.getQuestionId();
	}

	@Override
	public void batchSaveQuestion(List<QuestionDTO> dtos){
		if (null == dtos || dtos.isEmpty()) {
			return;
		}
		List<QuestionEntity> entities = toQuestionEntities(dtos);
		questionService.batchSave(entities);
	}

	@Override
	public int updateQuestion(QuestionDTO questionDTO){
		questionDTO = questionService.updateBySql(questionDTO);
		return 1;
	}

	@Override
	public void batchUpdateQuestion(List<QuestionDTO> dtos){
		if (null == dtos || dtos.isEmpty()) {
			return;
		}
		questionService.batchUpdate(dtos);
	}

	@Override
	public int deleteQuestion(long questionId){
		return questionService.delete(questionId);
	}

	@Override
	public QuestionDTO getQuestion(long questionId){
		return questionService.get(questionId);
	}

	@Override
	public QuestionDTO getQuestion(QuestionDTO questionDTO){
		return questionService.get(questionDTO);
	}

	@Override
	public List<QuestionDTO> getQuestionList(QuestionDTO questionDTO){
		return questionService.getSimpleList(questionDTO);
	}

	@Override
	public PageList<QuestionDTO> getQuestionListForPage(QuestionDTO questionDTO, int pageNumber, int pageSize){
		return questionService.getSimpleListForPage(questionDTO, pageNumber, pageSize);
	}

	@Override
	public PageList<QuestionDTO> getQuestionListForPage(QueryParam queryParam){
		return questionService.getSimpleListForPage(queryParam);
	}

	@Override
	public QuestionEntity toQuestionEntity(QuestionDTO dto){
		QuestionEntity entity = new QuestionEntity();
		CglibBeanUtils.copy(dto, entity);
		return entity;
	}

	@Override
	public List<QuestionEntity> toQuestionEntities(List<QuestionDTO> dtos){
		List<QuestionEntity> entities = new ArrayList<>();
		for(QuestionDTO dto : dtos){
			entities.add(toQuestionEntity(dto));
		}
		return entities;
	}

	@Override
	public <T> List<T> getList(T dto){
		return null;
	}

	@Override
	public <T> PageList<T> getListForPage(T dto, int pageNumber, int pageSize){
		return null;
	}

	@Override
	public <T> PageList<T> getListForPage(QueryParam queryParam, Class<T> clazz){
		return null;
	}

}