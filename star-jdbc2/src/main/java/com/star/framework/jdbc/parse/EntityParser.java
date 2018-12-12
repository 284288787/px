/**create by liuhua at 2016年6月8日 上午11:12:23**/
package com.star.framework.jdbc.parse;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import org.springframework.beans.BeanUtils;
import org.springframework.util.StringUtils;

public class EntityParser {
	private String tableName;
	private String id;
	private String idName;
	private boolean isGenerator = true;
	private String sequenceName;
	private String catalog = "db2";

	private List<String> columnList = new ArrayList<>();

	private List<String> columnNameList = new ArrayList<>();

	private List<String> fieldList = new ArrayList<>();
	private String insert;
	private String update;
	private String delete;
	private String select;
	private String dynamicUpdate;
	private String selectAll;
	private String querySequence;
	private boolean isMysql;
	private Map<String, Column> propertyMap = new HashMap<>();

	private Map<String, Column> fieldMap = new HashMap<>();

	public EntityParser(Class<? extends Object> clazz) {
		setFieldList(clazz);
		setTable(clazz);
		setId(clazz);
		setColumnList(clazz);

		setInsertSql();
		setUpdateSql();
		setDeleteSql();
		setSelectSql();
		setSelectAllSql();
	}

	public String getId() {
		return this.id;
	}

	public String getInsert() {
		return this.insert;
	}

	public String getUpdate() {
		return this.update;
	}

	public String getDynamicUpdate(Map<String, ?> object) {
		setDynamicUpdateSql(object);
		return this.dynamicUpdate;
	}

	public String getDelete() {
		return this.delete;
	}

	public String getSelect() {
		return this.select;
	}

	public String querySequence() {
		buildQuerySequence();
		return this.querySequence;
	}

	private void buildQuerySequence() {
		if ((this.catalog != null) && (!StringUtils.isEmpty(this.sequenceName)) && (StringUtils.isEmpty(this.querySequence))) {
			StringBuffer buffer = new StringBuffer();
			if ("db2".equalsIgnoreCase(this.catalog)) {
				buffer.append("SELECT NEXTVAL FOR ").append(this.sequenceName).append(" FROM SYSIBM.SYSSEQUENCES WHERE SEQNAME='").append(this.sequenceName).append("'");

				this.querySequence = buffer.toString();
			}

			if (!"mysql".equalsIgnoreCase(this.catalog))
				;
		}
	}

	public boolean isMysql() {
		return this.isMysql;
	}

	public String getSelectAll() {
		return this.selectAll;
	}

	private void setDynamicUpdateSql(Map<String, ?> object) {
		StringBuffer sb = new StringBuffer("UPDATE ");
		sb.append(this.tableName).append(" SET ");
		int size = this.columnNameList.size();
		for (int i = 0; i < size; i++)
			if ((object.get(this.columnList.get(i)) != null) && (((Column) this.propertyMap.get(this.columnNameList.get(i))).updatable())) {
				sb.append("`").append((String) this.columnNameList.get(i)).append("`").append(" = :").append((String) this.columnList.get(i));
				sb.append(", ");
			}
		sb.deleteCharAt(sb.length() - 2);
		sb.append(" WHERE ");
		sb.append(this.idName).append(" = :").append(this.id);
		this.dynamicUpdate = sb.toString();
	}

	private void setInsertSql() {
		StringBuffer sb = new StringBuffer("INSERT INTO ");
		sb.append(this.tableName).append("(");

		if ((!this.isGenerator) && (this.idName != null) && (this.id != null)) {
			sb.append(this.idName);
			sb.append(", ");
		}

		int size = this.columnNameList.size();
		for (int i = 0; i < size; i++) {
			if (((Column) this.propertyMap.get(this.columnNameList.get(i))).insertable()) {
				sb.append("`");
				sb.append((String) this.columnNameList.get(i));
				sb.append("`");
				sb.append(", ");
			}
		}
		sb.deleteCharAt(sb.length() - 2);
		sb.append(") VALUES (");
		if ((!this.isGenerator) && (this.idName != null) && (this.id != null)) {
			sb.append(":").append(this.id);
			sb.append(", ");
		}

		size = this.columnList.size();
		for (int i = 0; i < size; i++) {
			if (((Column) this.fieldMap.get(this.columnList.get(i))).insertable()) {
				sb.append(":").append((String) this.columnList.get(i));
				sb.append(", ");
			}
		}
		sb.deleteCharAt(sb.length() - 2);
		sb.append(")");
		this.insert = sb.toString();
	}

	private void setUpdateSql() {
		StringBuffer sb = new StringBuffer("UPDATE ");
		sb.append(this.tableName).append(" SET ");
		int size = this.columnNameList.size();
		for (int i = 0; i < size; i++) {
			if (((Column) this.propertyMap.get(this.columnNameList.get(i))).updatable()) {
				sb.append("`").append((String) this.columnNameList.get(i)).append("`").append(" = :").append((String) this.columnList.get(i));
				sb.append(", ");
			}
		}
		sb.deleteCharAt(sb.length() - 2);
		sb.append(" WHERE ");
		sb.append(this.idName).append(" = :").append(this.id);
		this.update = sb.toString();
	}

	private void setDeleteSql() {
		StringBuffer sb = new StringBuffer("DELETE FROM ");
		sb.append(this.tableName).append(" WHERE ");
		sb.append(this.idName).append(" = :").append(this.id);
		this.delete = sb.toString();
	}

	private void setSelectSql() {
		StringBuffer sb = new StringBuffer("SELECT ");
		List<String> tempList = new ArrayList<>(this.columnNameList);
		tempList.add(this.idName);
		int size = tempList.size();
		for (int i = 0; i < size; i++) {
			sb.append("`");
			sb.append((String) tempList.get(i));
			sb.append("`");
			if (i < size - 1) {
				sb.append(", ");
			}
		}
		sb.append(" FROM ").append(this.tableName).append(" WHERE ");
		sb.append(this.idName).append(" = :").append(this.id);
		this.select = sb.toString();
	}

	private void setSelectAllSql() {
		StringBuffer sb = new StringBuffer("SELECT ");
		List<String> tempList = new ArrayList<>(this.columnNameList);
		tempList.add(this.idName);
		int size = tempList.size();
		for (int i = 0; i < size; i++) {
			sb.append("`");
			sb.append((String) tempList.get(i));
			sb.append("`");
			if (i < size - 1) {
				sb.append(", ");
			}
		}
		sb.append(" FROM ").append(this.tableName);
		this.selectAll = sb.toString();
	}

	private void setTable(Class<? extends Object> clazz) {
		Entity entity = (Entity) clazz.getAnnotation(Entity.class);
		this.tableName = entity.name().toUpperCase();
	}

	private void setFieldList(Class<? extends Object> clazz) {
		Field[] fields = clazz.getDeclaredFields();
		for (Field field : fields)
			this.fieldList.add(field.getName());
	}

	private void setId(Class<? extends Object> clazz) {
		Method[] methods = clazz.getMethods();
		for (Method method : methods)
			if (method.isAnnotationPresent(Id.class)) {
				this.idName = ((Column) method.getAnnotation(Column.class)).name();
				this.id = BeanUtils.findPropertyForMethod(method).getName();
				GeneratedValue generatedValue = (GeneratedValue) method.getAnnotation(GeneratedValue.class);
				SequenceGenerator sequenceGenerator = (SequenceGenerator) method.getAnnotation(SequenceGenerator.class);
				if ((generatedValue != null) && (generatedValue.strategy() != null) && (generatedValue.strategy().compareTo(GenerationType.AUTO) != 0)) {
					this.isGenerator = false;
				} else
					this.isGenerator = true;

				if ((sequenceGenerator != null) && (generatedValue != null) && (sequenceGenerator.name().equals(generatedValue.generator()))) {
					this.sequenceName = sequenceGenerator.sequenceName();
				}
			}
	}

	private void setColumnList(Class<? extends Object> clazz) {
		Method[] methods = clazz.getMethods();
		Field[] fields = clazz.getDeclaredFields();
		Field[] superFields = clazz.getSuperclass().getDeclaredFields();
		for (Method method : methods)
			if ((method.isAnnotationPresent(Column.class)) && (!method.isAnnotationPresent(Id.class))) {
				PropertyDescriptor descriptor = BeanUtils.findPropertyForMethod(method);
				if ((!isTransient(fields, descriptor.getName())) && (!isTransient(superFields, descriptor.getName()))) {
					Column columnAnnoation = (Column) method.getAnnotation(Column.class);
					this.columnNameList.add(columnAnnoation.name());
					this.columnList.add(BeanUtils.findPropertyForMethod(method).getName());
					this.propertyMap.put(columnAnnoation.name(), columnAnnoation);
					this.fieldMap.put(BeanUtils.findPropertyForMethod(method).getName(), columnAnnoation);
				}
			}
	}

	private boolean isTransient(Field[] fields, String fileName) {
		for (Field field : fields) {
			if ((field.getName().equals(fileName)) && (Modifier.isTransient(field.getModifiers()))) {
				return true;
			}
		}
		return false;
	}

	public String getSequenceName() {
		return sequenceName;
	}

	public String getIdName() {
		return idName;
	}
}
