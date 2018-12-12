/**create by liuhua at 2017年6月21日 上午11:51:51**/
package com.booting.pub.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.star.framework.aop.annotation.Description;

@Description(name = "地区")
@Entity(name = "pub_area")
public class AreaEntity implements Serializable {
	private static final long serialVersionUID = 5810898369342836039L;
	
	private Long areaId;
	private Long parentId;
	private Integer code;
	private Integer type;         //1直辖市 2港澳地区 3省份 4 市
	private Integer status;       //状态 1可用 0禁用
	private String shortName;
	private String areaName;
	private String pinyin;
	private String py;
	private String marker;
	private String longitude;     //经度
	private String latitude;      //纬度
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "areaId")
	public Long getAreaId() {
		return areaId;
	}
	@Column(name = "parentId")
	public Long getParentId() {
		return parentId;
	}
	@Column(name = "code")
	public Integer getCode() {
		return code;
	}
	@Column(name = "type")
	public Integer getType() {
		return type;
	}
	@Column(name = "status")
	public Integer getStatus() {
		return status;
	}
	@Column(name = "shortName")
	public String getShortName() {
		return shortName;
	}
	@Column(name = "areaName")
	public String getAreaName() {
		return areaName;
	}
	@Column(name = "pinyin")
	public String getPinyin() {
		return pinyin;
	}
	@Column(name = "py")
	public String getPy() {
		return py;
	}
	@Column(name = "marker")
	public String getMarker() {
		return marker;
	}
	@Column(name = "longitude")
	public String getLongitude() {
		return longitude;
	}
	@Column(name = "latitude")
	public String getLatitude() {
		return latitude;
	}
	
	public void setAreaId(Long areaId) {
		this.areaId = areaId;
	}
	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}
	public void setCode(Integer code) {
		this.code = code;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public void setShortName(String shortName) {
		this.shortName = shortName;
	}
	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}
	public void setPinyin(String pinyin) {
		this.pinyin = pinyin;
	}
	public void setPy(String py) {
		this.py = py;
	}
	public void setMarker(String marker) {
		this.marker = marker;
	}
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
}
