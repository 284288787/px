/**create by liuhua at 2017年5月20日 上午11:04:18**/
package com.booting.system.dto;

import java.io.Serializable;
import java.util.Date;

public class UserAccountDTO implements Serializable {
	private static final long serialVersionUID = -8831846809907386923L;
	
	private Long userId;           //主键
	private String account;        //帐号 默认手机号 
	private String password;       //密码加密后的字符串
	private Integer nonExpired;    //是否过期 1未过期 0已过期
	private Integer nonLocked;     //是否锁定 1未锁定 0已锁定
	private Integer enabled;       //是否有效 1有效     0无效
	private Integer deleted;       //是否删除 1已删除 0未删除
	private Date expiredTime;      //是否过期最后的操作时间
	private Date lockedTime;       //是否锁定最后的操作时间
	private Date enabledTime;      //是否有效最后的操作时间
	private Date deletedTime;      //是否删除最后的操作时间
	private Date createTime;       //创建时间
	private Date lastModifyTime;   //最后修改的时间
	
	private Integer sourceFrom;     //来源 1安卓 2ios 3后台
	private String name;           //姓名
	private String mobile;         //手机
	private String address;        //地址
	private Date infoModifyTime;   //用户信息最后修改时间
	private Integer identity;      //身份 UserIdentity
	private Integer sex;            //性别 1男 0女
	private String photo;           //头像路径
	private Integer age;            //年龄
	
	private String userIds;        //用户Ids 例如 1,3,4

	public Long getUserId() {
		return userId;
	}

	public String getAccount() {
		return account;
	}

	public String getPassword() {
		return password;
	}

	public Integer getNonExpired() {
		return nonExpired;
	}

	public Integer getNonLocked() {
		return nonLocked;
	}

	public Integer getEnabled() {
		return enabled;
	}

	public Integer getDeleted() {
		return deleted;
	}

	public Date getExpiredTime() {
		return expiredTime;
	}

	public Date getLockedTime() {
		return lockedTime;
	}

	public Date getEnabledTime() {
		return enabledTime;
	}

	public Date getDeletedTime() {
		return deletedTime;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public Date getLastModifyTime() {
		return lastModifyTime;
	}

	public Integer getSourceFrom() {
		return sourceFrom;
	}

	public String getName() {
		return name;
	}

	public String getMobile() {
		return mobile;
	}

	public String getAddress() {
		return address;
	}

	public Date getInfoModifyTime() {
		return infoModifyTime;
	}

	public Integer getIdentity() {
		return identity;
	}

	public Integer getSex() {
		return sex;
	}

	public String getPhoto() {
		return photo;
	}

	public Integer getAge() {
		return age;
	}

	public String getUserIds() {
		return userIds;
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

	public void setDeleted(Integer deleted) {
		this.deleted = deleted;
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

	public void setDeletedTime(Date deletedTime) {
		this.deletedTime = deletedTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public void setLastModifyTime(Date lastModifyTime) {
		this.lastModifyTime = lastModifyTime;
	}

	public void setSourceFrom(Integer sourceFrom) {
		this.sourceFrom = sourceFrom;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public void setInfoModifyTime(Date infoModifyTime) {
		this.infoModifyTime = infoModifyTime;
	}

	public void setIdentity(Integer identity) {
		this.identity = identity;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public void setUserIds(String userIds) {
		this.userIds = userIds;
	}
}
