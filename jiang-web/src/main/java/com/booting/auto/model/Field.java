/**create by liuhua at 2018年3月27日 下午4:24:10**/
package com.booting.auto.model;

import com.booting.auto.common.FieldType;

public class Field {

	private String caption;
	private String name;
	private FieldType type;
	private String options;   //{value:'1:可用;0:禁用'}
	private String width;
	
	public Field(String caption, String name){
		this.caption = caption;
		this.name = name;
		this.type = FieldType.text;
	}
	
	public Field(String caption, String name, FieldType fieldType, String options){
		this.caption = caption;
		this.name = name;
		this.type = fieldType;
		this.options = options;
	}
	
	public Field(String caption, String name, FieldType fieldType, String options, String width){
		this.caption = caption;
		this.name = name;
		this.type = fieldType;
		this.options = options;
		this.width = width;
	}
	
	public Field(String caption, String name, String width){
		this.caption = caption;
		this.name = name;
		this.type = FieldType.text;
		this.width = width;
	}
	
	public String getCaption() {
		return caption;
	}
	public String getName() {
		return name;
	}
	public FieldType getType() {
		return type;
	}
	public void setCaption(String caption) {
		this.caption = caption;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setType(FieldType type) {
		this.type = type;
	}
	public String getOptions() {
		return options;
	}
	public void setOptions(String options) {
		this.options = options;
	}

	public String getWidth() {
		return width;
	}

	public void setWidth(String width) {
		this.width = width;
	}
}
