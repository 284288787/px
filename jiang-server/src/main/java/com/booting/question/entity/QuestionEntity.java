/**create by liuhua at 2018年3月2日 下午4:25:27**/
package com.booting.question.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import com.star.framework.aop.annotation.Description;

@Description(name = "用户角色")
@Entity(name = "px_question")
public class QuestionEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Long questionId;
	private String ask;
	private String answers;
	private String rightAnswer;
	private Date createTime;
	private Integer enabled;       //是否有效 1有效     0无效
	private Integer deleted;       //是否删除 1已删除     0未删除
	
	@Id
	@Column(name = "questionId")
	public Long getQuestionId() {
		return questionId;
	}
	@Column(name = "ask")
	public String getAsk() {
		return ask;
	}
	@Column(name = "answers")
	public String getAnswers() {
		return answers;
	}
	@Column(name = "rightAnswer")
	public String getRightAnswer() {
		return rightAnswer;
	}
	@Column(name = "createTime")
	public Date getCreateTime() {
		return createTime;
	}
	@Column(name = "enabled")
	public Integer getEnabled() {
		return enabled;
	}
	@Column(name = "deleted")
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
