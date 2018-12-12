/**create by liuhua at 2016年6月8日 上午10:53:39**/
package com.star.framework.jdbc.dao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.object.GenericStoredProcedure;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.LongSerializationPolicy;
import com.google.gson.reflect.TypeToken;
import com.star.framework.jdbc.JDBCBaseDAO;
import com.star.framework.jdbc.dao.result.PageList;
import com.star.framework.jdbc.dao.result.QueryParam;
import com.star.framework.jdbc.dao.rowmapper.RowMapperFactory;
import com.star.framework.jdbc.parse.EntityParser;
import com.star.framework.jdbc.parse.EntityParserManager;
import com.star.framework.jdbc.parse.FreeMakerParser;
import com.star.framework.jdbc.parse.ValueParser;
import com.star.framework.jdbc.parse.bean.SqlBean;
import com.star.framework.jdbc.resource.XmlResource;
import com.star.framework.jdbc.utils.SomeUtils;

public class JDBCSupport<E, D> implements JDBCBaseDAO<E, D>{
	private static Logger logger = LoggerFactory.getLogger(JDBCSupport.class);

	@Autowired
	protected JdbcTemplate jdbcTemplate;
	@Autowired
	private XmlResource xmlResource;

	Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").setLongSerializationPolicy(LongSerializationPolicy.STRING).create();
	
	@SuppressWarnings("unchecked")
	@Override
	public <T> T persist(E entity) {
		EntityParser sqlParser = EntityParserManager.getEntityParser(entity.getClass());
		String insertSQL = sqlParser.getInsert();
		Map<String, Object> paramMap = ValueParser.parser(entity);
		KeyHolder keyHolder = new GeneratedKeyHolder();
		long beginDate = System.currentTimeMillis();

		NamedParameterJdbcTemplate jdbcTemplate2 = new NamedParameterJdbcTemplate(jdbcTemplate);
		if (null != sqlParser.getSequenceName() && "uuid".equals(sqlParser.getSequenceName().toLowerCase())) {
			paramMap.put(sqlParser.getId(), getUUID());
		} else {
			if (sqlParser.querySequence() != null) {
				Object seq = queryBySequence(jdbcTemplate2, sqlParser.querySequence(), sqlParser.isMysql());
				paramMap.put(sqlParser.getId(), seq);
			}
		}
		insertSQL = FreeMakerParser.process(insertSQL, paramMap);
		jdbcTemplate2.update(insertSQL, new MapSqlParameterSource(paramMap), keyHolder);
		Object key = paramMap.get(sqlParser.getId());
		if ((key == null) || ((key instanceof Number) && (((Number) key).doubleValue() == 0.0D))) {
			key = keyHolder.getKey();
		}
		SomeUtils.setProperty(entity, sqlParser.getId(), key);
		logger.debug("persist EndTime: " + (System.currentTimeMillis() - beginDate));
		return (T) key;
	}

	@Override
	public int dynamicMerge(E entity) {
		Map<String, Object> paramMap = ValueParser.parser(entity);
		String updateSql = EntityParserManager.getEntityParser(entity.getClass()).getDynamicUpdate(paramMap);
		updateSql = FreeMakerParser.process(updateSql, paramMap);
		logger.debug("dynamicMerge: " + updateSql + "\n" + paramMap);
		long beginDate = System.currentTimeMillis();
		NamedParameterJdbcTemplate jdbcTemplate2 = new NamedParameterJdbcTemplate(jdbcTemplate);
		int result = jdbcTemplate2.update(updateSql, paramMap);
		logger.debug("dynamicMerge EndTime: " + (System.currentTimeMillis() - beginDate));

		return result;
	}

	@Override
	public int merge(E entity) {
		String updateSql = EntityParserManager.getEntityParser(entity.getClass()).getUpdate();
		Map<String, Object> paramMap = ValueParser.parser(entity);
		updateSql = FreeMakerParser.process(updateSql, paramMap);
		logger.debug("merge: " + updateSql + "\n" + paramMap);
		long beginDate = System.currentTimeMillis();
		NamedParameterJdbcTemplate jdbcTemplate2 = new NamedParameterJdbcTemplate(jdbcTemplate);
		int result = jdbcTemplate2.update(updateSql, paramMap);

		logger.debug("merge EndTime: " + (System.currentTimeMillis() - beginDate));

		return result;
	}
	
	//@CachePut
	@Override
	public int del(E entity) {
		String removeSql = EntityParserManager.getEntityParser(entity.getClass()).getDelete();
		Map<String, Object> paramMap = ValueParser.parser(entity);
		removeSql = FreeMakerParser.process(removeSql, paramMap);
		logger.debug("remove: "+ removeSql + "\n" + paramMap);
		long beginDate = System.currentTimeMillis();
		NamedParameterJdbcTemplate jdbcTemplate2 = new NamedParameterJdbcTemplate(jdbcTemplate);
		int result = jdbcTemplate2.update(removeSql, paramMap);
		logger.debug("remove EndTime: " + (System.currentTimeMillis() - beginDate));
		
		return result;
	}

	@Override
	public int del(Serializable id) {
		EntityParser ep = EntityParserManager.getEntityParser(getEntityClass());
		String removeSql = ep.getDelete();
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put(ep.getIdName(), id);
		removeSql = FreeMakerParser.process(removeSql, paramMap);
		logger.debug("remove: "+ removeSql + "\n" + paramMap);
		long beginDate = System.currentTimeMillis();
		NamedParameterJdbcTemplate jdbcTemplate2 = new NamedParameterJdbcTemplate(jdbcTemplate);
		int result = jdbcTemplate2.update(removeSql, paramMap);
		logger.debug("remove EndTime: " + (System.currentTimeMillis() - beginDate));

		return result;
	}
	
	@Override
	public D getById(Serializable id) {
		EntityParser ep = EntityParserManager.getEntityParser(getEntityClass());
		String selectSql = ep.getSelect();
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put(ep.getIdName(), id);
		selectSql = FreeMakerParser.process(selectSql, paramMap);
		logger.debug("select one: " + selectSql + "\n" + paramMap);
		long beginDate = System.currentTimeMillis();
		NamedParameterJdbcTemplate jdbcTemplate2 = new NamedParameterJdbcTemplate(jdbcTemplate);
		D entity = jdbcTemplate2.queryForObject(selectSql, paramMap, new RowMapperFactory<D>(getDTOClass()).getRowMapper());
		logger.debug("select one EndTime: " + (System.currentTimeMillis() - beginDate));
		
		return entity;
	}

	@Override
	public List<D> queryList() {
		EntityParser ep = EntityParserManager.getEntityParser(getEntityClass());
		String selectSql = ep.getSelectAll();
		logger.debug("select all: " + selectSql);
		long beginDate = System.currentTimeMillis();
		NamedParameterJdbcTemplate jdbcTemplate2 = new NamedParameterJdbcTemplate(jdbcTemplate);
		List<D> resultList = jdbcTemplate2.query(selectSql, new HashMap<String, Object>(), new RowMapperFactory<D>(getDTOClass()).getRowMapper());
		logger.debug("select all EndTime: " + (System.currentTimeMillis() - beginDate));
		
		return resultList;
	}
	
	@Override
	public List<D> queryForList(String sqlId, Map<String, Object> paramMap) {
		return queryForList(sqlId, paramMap, new RowMapperFactory<D>(getDTOClass()).getRowMapper());
	}
	
	@Override
	public List<D> queryForList(String sqlId, D dto){
		Map<String, Object> paramMap = new HashMap<>();
		if (null != dto) {
			Type type = new TypeToken<Map<String, Object>>(){}.getType();
			paramMap = gson.fromJson(gson.toJson(dto), type);
		}
		return queryForList(sqlId, paramMap);
	}
	
	@Override
	public <T> T queryForObject(String sqlId, Map<String, Object> paramMap, Class<T> clazz){
		SqlBean sqlBean = this.xmlResource.getSQL(sqlId);
		String sql = FreeMakerParser.process(paramMap, sqlBean.getContent(), sqlId);
		sql = FreeMakerParser.process(sql, paramMap);
		logger.debug("select Object: " + sql + "\n" + paramMap);
		long beginDate = System.currentTimeMillis();
		NamedParameterJdbcTemplate jdbcTemplate2 = new NamedParameterJdbcTemplate(jdbcTemplate);
		T object = jdbcTemplate2.queryForObject(sql, paramMap, new RowMapperFactory<T>(clazz).getRowMapper());
		logger.debug("select Object EndTime: " + (System.currentTimeMillis() - beginDate));
		
		return object;
	}
	
	@Override
	public int[] batchUpdate(String sqlId, Map<String, Object>[] batchValues) {
		 Map<String, Object> paramMap = new HashMap<>();
		 if ((batchValues != null) && (batchValues[0] != null)) {
		   paramMap = batchValues[0];
		 }
		 SqlBean sqlBean = this.xmlResource.getSQL(sqlId);
		 String sql = FreeMakerParser.process(paramMap, sqlBean.getContent(), sqlId);
		 logger.debug("batchUpdate: " + sql + "\n" + String.valueOf(batchValues == null ? 0 : batchValues.length));
		 long beginDate = System.currentTimeMillis();
		 NamedParameterJdbcTemplate jdbcTemplate2 = new NamedParameterJdbcTemplate(jdbcTemplate);
		 int[] result = jdbcTemplate2.batchUpdate(sql, batchValues);
		 logger.debug("batchUpdate End: " + (System.currentTimeMillis() - beginDate));
		 return result;
	}
	
	@Override
	public Map<String, Object> call(String sqlId, Map<String, Object> paramMap, List<SqlParameter> sqlParameters) {
		if (null == paramMap) {
			paramMap = new HashMap<>();
		}
		SqlBean sqlBean = this.xmlResource.getSQL(sqlId);
		String sql = FreeMakerParser.process(paramMap, sqlBean.getContent(), sqlId);
		logger.debug("call: "+ sql + "\n" + paramMap);
		long beginDate = System.currentTimeMillis();
		GenericStoredProcedure storedProcedure = new GenericStoredProcedure();
		storedProcedure.setDataSource(jdbcTemplate.getDataSource());
		storedProcedure.setSql(sqlBean.getContent());
		for (SqlParameter sqlParameter : sqlParameters) {
		  storedProcedure.declareParameter(sqlParameter);
		}
		logger.debug("call End: " + (System.currentTimeMillis() - beginDate));
		return storedProcedure.execute(paramMap);
	}
	
	@Override
	public <T> T queryForObject(String sqlId, D dto, Class<T> clazz){
		Map<String, Object> paramMap = new HashMap<>();
		if (null != dto) {
			Type type = new TypeToken<Map<String, Object>>(){}.getType();
			paramMap = gson.fromJson(gson.toJson(dto), type);
		}
		return queryForObject(sqlId, paramMap, clazz);
	}
	
	@Override
	public D queryForObject(String sqlId, Map<String, Object> paramMap){
		return queryForObject(sqlId, paramMap, getDTOClass());
	}
	
	@Override
	public D queryForObject(String sqlId, D dto){
		Map<String, Object> paramMap = new HashMap<>();
		if (null != dto) {
			Type type = new TypeToken<Map<String, Object>>(){}.getType();
			paramMap = gson.fromJson(gson.toJson(dto), type);
		}
		return queryForObject(sqlId, paramMap);
	}
	
	@Override
	public Map<String, Object> queryForMap(String sqlId, Map<String, Object> paramMap){
		SqlBean sqlBean = this.xmlResource.getSQL(sqlId);
		String sql = FreeMakerParser.process(paramMap, sqlBean.getContent(), sqlId);
		sql = FreeMakerParser.process(sql, paramMap);
		logger.debug("queryForMap: " + sql + "\n" + paramMap);
		long beginDate = System.currentTimeMillis();
		NamedParameterJdbcTemplate jdbcTemplate2 = new NamedParameterJdbcTemplate(jdbcTemplate);
		Map<String, Object> map = jdbcTemplate2.queryForMap(sql, paramMap);
		logger.debug("queryForMap EndTime: " + (System.currentTimeMillis() - beginDate));
		return map;
	}
	
	@Override
	public PageList<D> queryForPage(String countSqlId, String listSqlId, QueryParam queryParam){
		PageList<D> pageList = new PageList<>();
		if (null == queryParam) {
			return pageList;
		}
		Map<String, Object> paramMap = queryParam.getParam();
		int pageNo = queryParam.getPageNo();
		int pageSize = queryParam.getPageSize();
		if (null == paramMap) {
			paramMap = new HashMap<>();
		}
		if (pageNo < 1) {
			pageNo = 1;
		}
		int totalRecord = queryForObject(countSqlId, paramMap, Integer.class);
		if (totalRecord > 0) {
			int indexNumber = (pageNo - 1) * pageSize;
			if (totalRecord <= indexNumber) {
				pageNo = (totalRecord + pageSize - 1) / pageSize;
			}
			indexNumber = (pageNo - 1) * pageSize;
			int totalPage = totalRecord / pageSize;
			totalPage += totalRecord % pageSize > 0 ? 1 : 0;
			paramMap.put("startIndex", indexNumber);
			paramMap.put("maxCount", pageSize);
			paramMap.put("orderBy", queryParam.getOrderBy());
			paramMap.put("orderType", queryParam.getOrderType());
			List<D> dataList = queryForList(listSqlId, paramMap);
			pageList = new PageList<>(pageNo, pageSize, totalRecord, totalPage, dataList);
		}
		return pageList;
	}
	
	public List<D> queryForList(String sqlId, Map<String, Object> paramMap, RowMapper<D> rowMapper) {
		SqlBean sqlBean = this.xmlResource.getSQL(sqlId);
		String sql = FreeMakerParser.process(paramMap, sqlBean.getContent(), sqlId);
		logger.debug("queryForList: " + sql + "\n" + paramMap);
		long beginDate = System.currentTimeMillis();
		NamedParameterJdbcTemplate jdbcTemplate2 = new NamedParameterJdbcTemplate(jdbcTemplate);
		List<D> resultList = jdbcTemplate2.query(sql, paramMap, rowMapper);
		logger.debug("queryForList EndTime: " + (System.currentTimeMillis() - beginDate));

		return resultList;
	}
	
	@Override
	public <T> List<T> queryForList(String sqlId, Map<String, Object> paramMap, Class<T> clazz){
		SqlBean sqlBean = this.xmlResource.getSQL(sqlId);
		String sql = FreeMakerParser.process(paramMap, sqlBean.getContent(), sqlId);
		sql = FreeMakerParser.process(sql, paramMap);
		logger.debug("queryForList: " + sql + "\n" + paramMap);
		long beginDate = System.currentTimeMillis();
		NamedParameterJdbcTemplate jdbcTemplate2 = new NamedParameterJdbcTemplate(jdbcTemplate);
		List<T> resultList = jdbcTemplate2.query(sql, paramMap, new RowMapperFactory<T>(clazz).getRowMapper());
		logger.debug("queryForList EndTime: " + (System.currentTimeMillis() - beginDate));
		
		return resultList;
	}
	
	@Override
	public <T> List<T> queryForList(String sqlId, D dto, Class<T> clazz){
		Map<String, Object> paramMap = new HashMap<>();
		if (null != dto) {
			Type type = new TypeToken<Map<String, Object>>(){}.getType();
			paramMap = gson.fromJson(gson.toJson(dto), type);
		}
		return queryForList(sqlId, paramMap, clazz);
	}
	
	private String getUUID() {
		return UUID.randomUUID().toString().replace("-", "");
	}

	private Object queryBySequence(NamedParameterJdbcTemplate jdbcTemplate, String sequence, boolean needUpdate) {
		if (needUpdate) {
			jdbcTemplate.update(sequence, new HashMap<String, Object>());
			Map<String, Object> result = jdbcTemplate.queryForMap("select last_insert_id() as seq", new HashMap<String, Object>());

			return result.get("seq");
		}
		Map<String, Object> result = jdbcTemplate.queryForMap(sequence, new HashMap<String, Object>());
		return result.get("1");
	}
	
	private Class<E> getEntityClass() {
		Class<E> clazz = getGenericClass(getClass(), 1);
		if (null == clazz)
			throw new RuntimeException("concreate class must provide entity type or the type is incorrect!");
		return clazz;
	}

	private Class<D> getDTOClass() {
		Class<D> clazz = getGenericClass(getClass(), 2);
		if (null == clazz)
			throw new RuntimeException("concreate class must provide entity type or the type is incorrect!");
		return clazz;
	}

	@SuppressWarnings("unchecked")
	private static <T> Class<T> getGenericClass(final Class<?> baseClass, final int index) {
		Type genericType = baseClass.getGenericSuperclass();

		if (genericType instanceof ParameterizedType) {
			Type[] params = ((ParameterizedType) genericType).getActualTypeArguments();
			if (null != params && params.length >= index) {
				if (params[index - 1] instanceof Class)
					return (Class<T>) params[index - 1];
			}
		}
		return null;
	}

	@Override
	public int execute(String sqlId, Map<String, Object> paramMap) {
		SqlBean sqlBean = this.xmlResource.getSQL(sqlId);
		String sql = FreeMakerParser.process(paramMap, sqlBean.getContent(), sqlId);
		sql = FreeMakerParser.process(sql, paramMap);
		logger.debug("execute sql: " + sql + "\n" + paramMap);
		long beginDate = System.currentTimeMillis();
		NamedParameterJdbcTemplate jdbcTemplate2 = new NamedParameterJdbcTemplate(jdbcTemplate);
		int res = jdbcTemplate2.update(sql, paramMap);
		logger.debug("sql EndTime: " + (System.currentTimeMillis() - beginDate));
		return res;
	}
	
	@Override
	public int execute(String sqlId, D dto) {
		Map<String, Object> paramMap = new HashMap<>();
		if (null != dto) {
			Type type = new TypeToken<Map<String, Object>>(){}.getType();
			paramMap = gson.fromJson(gson.toJson(dto), type);
		}
		return execute(sqlId, paramMap);
	}
}
