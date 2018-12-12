/** create by auto at 2018-01-02 14:44:30**/
package com.booting.kindergarten.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.booting.kindergarten.dto.KindergartenDTO;
import com.booting.kindergarten.entity.KindergartenEntity;
import com.booting.kindergarten.service.KindergartenService;

import com.star.framework.jdbc.dao.JDBCSupport;

import com.star.framework.jdbc.dao.result.PageList;

import com.star.framework.jdbc.dao.result.QueryParam;

@Service("kindergartenService")
public class KindergartenServiceImpl extends JDBCSupport<KindergartenEntity, KindergartenDTO> implements KindergartenService{

	private static final long serialVersionUID = 1L;

	@Override
	public KindergartenDTO save(KindergartenEntity kindergartenEntity) {
		long id = this.persist(kindergartenEntity);
		return get(id);
	}

	@Override
	public KindergartenDTO update(KindergartenEntity kindergartenEntity) {
		this.dynamicMerge(kindergartenEntity);
		return get(kindergartenEntity.getSchoolId());
	}

	@Override
	public KindergartenDTO updateAll(KindergartenEntity kindergartenEntity) {
		this.merge(kindergartenEntity);
		return get(kindergartenEntity.getSchoolId());
	}

	@Override
	public KindergartenDTO updateBySql(KindergartenDTO kindergartenDTO) {
		if(null == kindergartenDTO) return null;
		this.execute("kindergarten.updateKindergarten", toMap(kindergartenDTO, "yyyy-MM-dd HH:mm:ss"));
		if(null == kindergartenDTO.getSchoolId()) return null;
		return get(kindergartenDTO.getSchoolId());
	}

	@Override
	public int delete(long id) {
		return this.del(id);
	}

	@Override
	public void batchSave(List<KindergartenEntity> entities) {
		if(null == entities || entities.isEmpty()){
			return;
		}
		Map<String, Object>[] params = toMap(entities);
		this.batch("kindergarten.insertKindergarten", params);
	}

	@Override
	public void batchUpdate(List<KindergartenDTO> dtos) {
		if(null == dtos || dtos.isEmpty()){
			return;
		}
		Map<String, Object>[] params = toMap(dtos);
		this.batch("kindergarten.updateKindergarten", params);
	}

	@Override
	public KindergartenDTO get(long schoolId) {
		return getById(schoolId);
	}

	@Override
	public KindergartenDTO get(KindergartenDTO kindergartenDTO) {
		if(null == kindergartenDTO) {
			return null;
		}
		Map<String, Object> param = toMap(kindergartenDTO, "yyyy-MM-dd HH:mm:ss");
		return this.queryForObject("kindergarten.getSimpleKindergartenList", param);
	}

	@Override
	public List<KindergartenDTO> getSimpleList(KindergartenDTO kindergartenDTO) {
		Map<String, Object> param = toMap(kindergartenDTO, "yyyy-MM-dd HH:mm:ss");
		return this.queryForList("kindergarten.getSimpleKindergartenList", param);
	}

	@Override
	public PageList<KindergartenDTO> getSimpleListForPage(KindergartenDTO kindergartenDTO, int pageNo, int pageSize) {
		QueryParam queryParam = new QueryParam();
		queryParam.setPageNo(pageNo);
		queryParam.setPageSize(pageSize);
		queryParam.setParam(kindergartenDTO);
		return this.queryForPage("kindergarten.getSimpleKindergartenListCount", "kindergarten.getSimpleKindergartenList", queryParam);
	}

	@Override
	public PageList<KindergartenDTO> getSimpleListForPage(QueryParam queryParam) {
		return this.queryForPage("kindergarten.getSimpleKindergartenListCount", "kindergarten.getSimpleKindergartenList", queryParam);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> List<T> getList(T dto) {
		Map<String, Object> param = toMap(dto, "yyyy-MM-dd HH:mm:ss");
		return (List<T>)this.queryForList("kindergarten.getKindergartenList", param);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> PageList<T> getListForPage(T dto, int pageNo, int pageSize) {
		QueryParam queryParam = new QueryParam();
		queryParam.setPageNo(pageNo);
		queryParam.setPageSize(pageSize);
		queryParam.setParam(dto);
		return (PageList<T>) this.queryForPage("kindergarten.getKindergartenListCount", "kindergarten.getKindergartenList", queryParam, dto.getClass());
	}

	@Override
	public <T> PageList<T> getListForPage(QueryParam queryParam, Class<T> clazz) {
		return (PageList<T>) this.queryForPage("kindergarten.getKindergartenListCount", "kindergarten.getKindergartenList", queryParam, clazz);
	}

}