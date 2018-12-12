/**create by liuhua at 2017年6月21日 上午11:51:51**/
package com.booting.pub.dto;

import java.io.Serializable;
import java.util.List;

import com.star.framework.aop.annotation.Description;

@Description(name = "地区")
public class AreaDTO implements Serializable {
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
	
	private String types;
	private List<AreaDTO> children;
	
	public Long getAreaId() {
		return areaId;
	}
	
	public Long getParentId() {
		return parentId;
	}
	
	public Integer getCode() {
		return code;
	}
	
	public Integer getType() {
		return type;
	}
	
	public Integer getStatus() {
		return status;
	}
	
	public String getShortName() {
		return shortName;
	}
	
	public String getAreaName() {
		return areaName;
	}
	
	public String getPinyin() {
		return pinyin;
	}
	
	public String getPy() {
		return py;
	}
	
	public String getMarker() {
		return marker;
	}
	
	public String getLongitude() {
		return longitude;
	}
	
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

	public List<AreaDTO> getChildren() {
		return children;
	}

	public void setChildren(List<AreaDTO> children) {
		this.children = children;
	}

	public String getTypes() {
		return types;
	}

	public void setTypes(String types) {
		this.types = types;
	}
}
