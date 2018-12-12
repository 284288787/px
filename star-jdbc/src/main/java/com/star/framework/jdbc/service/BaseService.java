/**create by liuhua at 2016年6月18日 上午9:59:43**/
package com.star.framework.jdbc.service;

import java.io.Serializable;
import java.util.List;

public interface BaseService<E, D> {

	/**
	 * 保存一个对象
	 * @author liuhua
	 *
	 * @param entity
	 * @return
	 */
	public D save(E entity);

	/**
	 * 更新一个对象
	 * 更新值不为空的字段
	 * 
	 * @author liuhua
	 *
	 * @param entity
	 * @return
	 */
	public D update(E entity);

	/**
	 * 更新一个对象
	 * 更新所有的字段，可能会将以前的值改为空
	 * @author liuhua
	 *
	 * @param entity
	 * @return
	 */
	public int updateAll(E entity);
	
	/**
	 * 删除一个对象
	 * @author liuhua
	 *
	 * @param entity
	 * @return
	 */
	public int delete(E entity);
	
	/**
	 * 删除一个对象
	 * @author liuhua
	 *
	 * @param id
	 * @return
	 */
	public int delete(Serializable id);

	/**
	 * 根据id获取entity
	 * 
	 * @author liuhua
	 *
	 * @param id
	 * @return
	 */
	public D get(Serializable id);
	
	/**
	 * 所有的entity
	 * @author liuhua
	 *
	 * @return
	 */
	public List<D> queryAll();
	
}
