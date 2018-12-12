/**create by liuhua at 2018年3月2日 下午4:25:27**/
package com.booting.question.dto;

import java.io.Serializable;
import java.util.Date;

import com.star.framework.aop.annotation.Description;

@Description(name = "用户角色")
public class QuestionDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Long questionId;
	private String ask;
	private String answers;
	private String rightAnswer;
	private Date createTime;
	private Integer enabled;       //是否有效 1有效     0无效
	private Integer deleted;       //是否删除 1已删除     0未删除
	
		
	public Long getQuestionId() {
		return questionId;
	}
	
	public String getAsk() {
		return ask;
	}
	
	public String getAnswers() {
		return answers;
	}
	
	public String getRightAnswer() {
		return rightAnswer;
	}
	
	public Date getCreateTime() {
		return createTime;
	}
	
	public Integer getEnabled() {
		return enabled;
	}
	
	public Integer getDeleted() {
		return deleted;
	}
	
	public void setQuestionId(Long questionId) {
		this.questionId = questionId;
	}
	public void setAsk(String ask) {
		this.ask = ask;
	}
	public void setAnswers(String answers) {
		this.answers = answers;
	}
	public void setRightAnswer(String rightAnswer) {
		this.rightAnswer = rightAnswer;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public void setEnabled(Integer enabled) {
		this.enabled = enabled;
	}
	public void setDeleted(Integer deleted) {
		this.deleted = deleted;
	}
}
