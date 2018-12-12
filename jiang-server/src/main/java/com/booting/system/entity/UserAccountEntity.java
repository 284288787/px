/**create by liuhua at 2017年5月20日 上午11:04:18**/
package com.booting.system.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import com.star.framework.aop.annotation.Description;

@Description(name = "用户帐号")
@Entity(name = "sys_useraccount")
public class UserAccountEntity implements Serializable {
	private static final long serialVersionUID = -8831846809907386923L;
	
	private Long userId;           //主键
	private String account;        //帐号 默认手机号 
	private String password;       //密码加密后的字符串
	private Integer nonExpired;    //是否过期 1未过期 0已过期
	private Integer nonLocked;     //是否锁定 1未锁定 0已锁定
	private Integer enabled;       //是否有效 1有效     0无效
	private Integer deleted;       //是否删除 1已删除     0未删除
	private Date expiredTime;      //是否过期最后的操作时间
	private Date lockedTime;       //是否锁定最后的操作时间
	private Date enabledTime;      //是否有效最后的操作时间
	private Date deletedTime;      //是否删除最后的操作时间
	private Date createTime;       //创建时间
	private Date lastModifyTime;   //最后修改的时间
	
	@Id
	@Column(name = "userId")
	public Long getUserId() {
		return userId;
	}
	@Column(name = "account")
	public String getAccount() {
		return account;
	}
	@Column(name = "password")
	public String getPassword() {
		return password;
	}
	@Column(name = "nonExpired")
	public Integer getNonExpired() {
		return nonExpired;
	}
	@Column(name = "nonLocked")
	public Integer getNonLocked() {
		return nonLocked;
	}
	@Column(name = "enabled")
	public Integer getEnabled() {
		return enabled;
	}
	@Column(name = "expiredTime")
	public Date getExpiredTime() {
		return expiredTime;
	}
	@Column(name = "lockedTime")
	public Date getLockedTime() {
		return lockedTime;
	}
	@Column(name = "enabledTime")
	public Date getEnabledTime() {
		return enabledTime;
	}
	@Column(name = "createTime")
	public Date getCreateTime() {
		return createTime;
	}
	@Column(name = "lastModifyTime")
	public Date getLastModifyTime() {
		return lastModifyTime;
	}
	@Column(name = "deleted")
	public Integer getDeleted() {
		return deleted;
	}
	@Column(name = "deletedTime")
	public Date getDeletedTime() {
		return deletedTime;
	}
	
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public void setNonExpired(Integer nonExpired) {
		this.nonExpired = nonExpired;
	}
	public void setNonLocked(Integer nonLocked) {
		this.nonLocked = nonLocked;
	}
	public void setEnabled(Integer enabled) {
		this.enabled = enabled;
	}
	public void setExpiredTime(Date expiredTime) {
		this.expiredTime = expiredTime;
	}
	public void setLockedTime(Date lockedTime) {
		this.lockedTime = lockedTime;
	}
	public void setEnabledTime(Date enabledTime) {
		this.enabledTime = enabledTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public void setLastModifyTime(Date lastModifyTime) {
		this.lastModifyTime = lastModifyTime;
	}
	public void setDeleted(Integer deleted) {
		this.deleted = deleted;
	}
	public void setDeletedTime(Date deletedTime) {
		this.deletedTime = deletedTime;
	}
}
