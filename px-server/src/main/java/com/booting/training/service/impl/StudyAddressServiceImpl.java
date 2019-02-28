/** create by auto at 2019-02-28 10:38:10**/
package com.booting.training.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.booting.training.dto.StudyAddressDTO;
import com.booting.training.entity.StudyAddressEntity;
import com.booting.training.service.StudyAddressService;

import com.star.framework.jdbc.dao.JDBCSupport;

import com.star.framework.jdbc.dao.result.PageList;

import com.star.framework.jdbc.dao.result.QueryParam;

@Service("studyAddressService")
public class StudyAddressServiceImpl extends JDBCSupport<StudyAddressEntity, StudyAddressDTO> implements StudyAddressService{

	private static final long serialVersionUID = 1L;

	@Override
	public StudyAddressDTO save(StudyAddressEntity studyAddressEntity) {
		long id = this.persist(studyAddressEntity);
		return get(id);
	}

	@Override
	public StudyAddressDTO update(StudyAddressEntity studyAddressEntity) {
		this.dynamicMerge(studyAddressEntity);
		return get(studyAddressEntity.getAddrId());
	}

	@Override
	public StudyAddressDTO updateAll(StudyAddressEntity studyAddressEntity) {
		this.merge(studyAddressEntity);
		return get(studyAddressEntity.getAddrId());
	}

	@Override
	public StudyAddressDTO updateBySql(StudyAddressDTO studyAddressDTO) {
		if(null == studyAddressDTO) return null;
		this.execute("studyAddress.updateStudyAddress", toMap(studyAddressDTO, "yyyy-MM-dd HH:mm:ss"));
		if(null == studyAddressDTO.getAddrId()) return null;
		return get(studyAddressDTO.getAddrId());
	}

	@Override
	public int delete(long id) {
		return this.del(id);
	}

	@Override
	public void batchSave(List<StudyAddressEntity> entities) {
		if(null == entities || entities.isEmpty()){
			return;
		}
		Map<String, Object>[] params = toMap(entities);
		this.batch("studyAddress.insertStudyAddress", params);
	}

	@Override
	public void batchUpdate(List<StudyAddressDTO> dtos) {
		if(null == dtos || dtos.isEmpty()){
			return;
		}
		Map<String, Object>[] params = toMap(dtos);
		this.batch("studyAddress.updateStudyAddress", params);
	}

	@Override
	public StudyAddressDTO get(long addrId) {
		return getById(addrId);
	}

	@Override
	public StudyAddressDTO get(StudyAddressDTO studyAddressDTO) {
		if(null == studyAddressDTO) {
			return null;
		}
		Map<String, Object> param = toMap(studyAddressDTO, "yyyy-MM-dd HH:mm:ss");
		return this.queryForObject("studyAddress.getSimpleStudyAddressList", param);
	}

	@Override
	public List<StudyAddressDTO> getSimpleList(StudyAddressDTO studyAddressDTO) {
		Map<String, Object> param = toMap(studyAddressDTO, "yyyy-MM-dd HH:mm:ss");
		return this.queryForList("studyAddress.getSimpleStudyAddressList", param);
	}

	@Override
	public PageList<StudyAddressDTO> getSimpleListForPage(StudyAddressDTO studyAddressDTO, int pageNo, int pageSize) {
		QueryParam queryParam = new QueryParam();
		queryParam.setPageNo(pageNo);
		queryParam.setPageSize(pageSize);
		queryParam.setParam(studyAddressDTO);
		return this.queryForPage("studyAddress.getSimpleStudyAddressListCount", "studyAddress.getSimpleStudyAddressList", queryParam);
	}

	@Override
	public PageList<StudyAddressDTO> getSimpleListForPage(QueryParam queryParam) {
		return this.queryForPage("studyAddress.getSimpleStudyAddressListCount", "studyAddress.getSimpleStudyAddressList", queryParam);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> List<T> getList(T dto) {
		Map<String, Object> param = toMap(dto, "yyyy-MM-dd HH:mm:ss");
		return (List<T>)this.queryForList("studyAddress.getStudyAddressList", param);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> PageList<T> getListForPage(T dto, int pageNo, int pageSize) {
		QueryParam queryParam = new QueryParam();
		queryParam.setPageNo(pageNo);
		queryParam.setPageSize(pageSize);
		queryParam.setParam(dto);
		return (PageList<T>) this.queryForPage("studyAddress.getStudyAddressListCount", "studyAddress.getStudyAddressList", queryParam, dto.getClass());
	}

	@Override
	public <T> PageList<T> getListForPage(QueryParam queryParam, Class<T> clazz) {
		return (PageList<T>) this.queryForPage("studyAddress.getStudyAddressListCount", "studyAddress.getStudyAddressList", queryParam, clazz);
	}

}