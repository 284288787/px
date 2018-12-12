/**create by liuhua at 2016年6月7日 下午1:52:58**/
package com.star.framework.jdbc;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.SqlParameter;

import com.star.framework.jdbc.dao.result.PageList;
import com.star.framework.jdbc.dao.result.QueryParam;

/**
 * 数据库操作 接口
 * @author liuhua
 *
 */
public interface JDBCBaseDAO<E, D> {
	
	/**
	 * 保存一个对象
	 * @author liuhua
	 *
	 * @param entity
	 * @return
	 */
	public <T> T persist(E entity);

	/**
	 * 更新一个对象
	 * 更新值不为空的字段
	 * 
	 * @author liuhua
	 *
	 * @param entity
	 * @return
	 */
	public int dynamicMerge(E entity);

	/**
	 * 更新一个对象
	 * 更新所有的字段，可能会将以前的值改为空
	 * @author liuhua
	 *
	 * @param entity
	 * @return
	 */
	public int merge(E entity);
	
	/**
	 * 删除一个对象
	 * @author liuhua
	 *
	 * @param entity
	 * @return
	 */
	public int del(E entity);
	
	/**
	 * 删除一个对象
	 * @author liuhua
	 *
	 * @param id
	 * @return
	 */
	public int del(Serializable id);

	/**
	 * 根据id获取entity
	 * 
	 * @author liuhua
	 *
	 * @param id
	 * @return
	 */
	public D getById(Serializable id);
	
	/**
	 * 查询列表
	 * @author liuhua
	 *
	 * @param sqlId
	 * @param paramMap
	 * @param clazz
	 * @return
	 */
	public <T> List<T> queryForList(String sqlId, Map<String, Object> paramMap, Class<T> clazz);
	
	/**
	 * 查询一个对象
	 * 该对象可以是Long  String Integer 或者 Entity
	 * 
	 * @author liuhua
	 *
	 * @param sqlId
	 * @param paramMap
	 * @param clazz
	 * @return
	 */
	public <T> T queryForObject(String sqlId, Map<String, Object> paramMap, Class<T> clazz);
	
	/**
	 * 查询一个对象
	 * 该对象可以是Long  String Integer 或者 Entity
	 * 
	 * @author liuhua
	 *
	 * @param sqlId
	 * @param dto
	 * @param clazz
	 * @return
	 */
	public <T> T queryForObject(String sqlId, D dto, Class<T> clazz);
	
	/**
	 * 查询一个DTO对象
	 * @author liuhua
	 *
	 * @param sqlId
	 * @param paramMap
	 * @return
	 */
	public D queryForObject(String sqlId, Map<String, Object> paramMap);
	
	/**
	 * 查询一个DTO对象
	 *  
	 * @author liuhua
	 *
	 * @param sqlId
	 * @param dto
	 * @return
	 */
	public D queryForObject(String sqlId, D dto);
	
	/**
	 * 查询 返回 一条记录的map形式 
	 * @author liuhua
	 *
	 * @param sqlId
	 * @param paramMap
	 * @return
	 */
	public Map<String, Object> queryForMap(String sqlId, Map<String, Object> paramMap);
	
	/**
	 * 分页查询
	 * @author liuhua
	 *
	 * @param countSqlId
	 * @param listSqlId
	 * @param paramMap 参数
	 * @param pageNo 页码
	 * @param pageSize 一页最大记录数
	 * @return
	 */
	public PageList<D> queryForPage(String countSqlId, String listSqlId, QueryParam queryParam);
	
	/**
	 * 批量操作
	 * @author liuhua
	 *
	 * @param sqlId
	 * @param batchValues
	 * @return
	 */
	public int[] batch(String sqlId, Map<String, Object>[] batchValues);
	
	/**
	 * 调用过程
	 * @author liuhua
	 *
	 * @param paramString
	 * @param paramMap
	 * @param paramList
	 * @return
	 */
	public Map<String, Object> call(String sqlId, Map<String, Object> paramMap, List<SqlParameter> sqlParameters);
	
	public int execute(String sqlId, Map<String, Object> paramMap);
	
	public int execute(String sqlId, D dto);
}
