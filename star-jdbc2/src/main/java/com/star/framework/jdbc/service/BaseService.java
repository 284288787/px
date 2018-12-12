/**create by liuhua at 2016年6月18日 上午9:59:43**/
package com.star.framework.jdbc.service;

import java.io.Serializable;
import java.util.List;

import com.star.framework.jdbc.dao.result.PageList;
import com.star.framework.jdbc.dao.result.QueryParam;

public interface BaseService<E, D> extends Serializable{

	/**
	 * 保存一个对象
	 */
	public D save(E entity);

	/**
	 * 更新一个对象
	 * 更新值不为空的字段
	 */
	public D update(E entity);

	/**
	 * 更新一个对象
	 * 更新所有的字段，可能会将以前的值改为空
	 */
	public D updateAll(E entity);
	
	/**
	 * 更新一个对象
	 * 根据sql
	 */
	public D updateBySql(D dto);
	
	/**
	 * 删除一个对象
	 */
	public int delete(long id);

	/**
	 * 批量插入
	 */
	public void batchSave(List<E> entities);
	
	/**
	 * 批量更新
	 */
	public void batchUpdate(List<D> dtos);
	
	/**
	 * 根据主键获取对象
	 */
	public D get(long id);
	
	/**
	 * 根据条件获取对象
	 */
	public D get(D dto);
	
	/**
	 * 获取满足条件的记录(单表) 
	 */
	public List<D> getSimpleList(D dto);
	
	/**
	 * 分页(单表) 
	 */
	public PageList<D> getSimpleListForPage(D dto, int pageNo, int pageSize);
	
	/**
	 * 分页(单表) 
	 */
	public PageList<D> getSimpleListForPage(QueryParam queryParam);
	
	/**
	 * 获取满足条件的记录(多表) 
	 */
	public <T> List<T> getList(T dto);
	
	/**
	 * 分页(多表) 
	 */
	public <T> PageList<T> getListForPage(T dto, int pageNo, int pageSize);
	
	/**
	 * 分页(多表) 
	 */
	public <T> PageList<T> getListForPage(QueryParam queryParam, Class<T> clazz);
}
