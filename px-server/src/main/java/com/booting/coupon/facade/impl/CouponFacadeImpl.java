/** create by auto at 2018-05-07 09:32:07**/
package com.booting.coupon.facade.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.booting.coupon.facade.CouponFacade;
import com.star.framework.jdbc.dao.result.PageList;
import com.star.framework.jdbc.dao.result.QueryParam;
import com.star.framework.utils.CglibBeanUtils;

import com.booting.coupon.dto.CouponRelationDTO;
import com.booting.coupon.entity.CouponRelationEntity;
import com.booting.coupon.service.CouponRelationService;

@Service("couponFacade")
public class CouponFacadeImpl implements CouponFacade {
	private static final long serialVersionUID = 1L;

	@Autowired
	private CouponRelationService couponRelationService;


	@Override
	public Long saveCouponRelation(CouponRelationDTO couponRelationDTO){
		if (null == couponRelationDTO) {
			return null;
		}
		CouponRelationEntity entity = toCouponRelationEntity(couponRelationDTO);
		couponRelationDTO = couponRelationService.save(entity);
		return couponRelationDTO.getId();
	}

	@Override
	public void batchSaveCouponRelation(List<CouponRelationDTO> dtos){
		if (null == dtos || dtos.isEmpty()) {
			return;
		}
		List<CouponRelationEntity> entities = toCouponRelationEntities(dtos);
		couponRelationService.batchSave(entities);
	}

	@Override
	public int updateCouponRelation(CouponRelationDTO couponRelationDTO){
		couponRelationDTO = couponRelationService.updateBySql(couponRelationDTO);
		return 1;
	}

	@Override
	public void batchUpdateCouponRelation(List<CouponRelationDTO> dtos){
		if (null == dtos || dtos.isEmpty()) {
			return;
		}
		couponRelationService.batchUpdate(dtos);
	}

	@Override
	public int deleteCouponRelation(long id){
		return couponRelationService.delete(id);
	}

	@Override
	public CouponRelationDTO getCouponRelation(long id){
		return couponRelationService.get(id);
	}

	@Override
	public CouponRelationDTO getCouponRelation(CouponRelationDTO couponRelationDTO){
		return couponRelationService.get(couponRelationDTO);
	}

	@Override
	public List<CouponRelationDTO> getCouponRelationList(CouponRelationDTO couponRelationDTO){
		return couponRelationService.getSimpleList(couponRelationDTO);
	}

	@Override
	public PageList<CouponRelationDTO> getCouponRelationListForPage(CouponRelationDTO couponRelationDTO, int pageNumber, int pageSize){
		return couponRelationService.getSimpleListForPage(couponRelationDTO, pageNumber, pageSize);
	}

	@Override
	public PageList<CouponRelationDTO> getCouponRelationListForPage(QueryParam queryParam){
		return couponRelationService.getSimpleListForPage(queryParam);
	}

	@Override
	public CouponRelationEntity toCouponRelationEntity(CouponRelationDTO dto){
		CouponRelationEntity entity = new CouponRelationEntity();
		CglibBeanUtils.copy(dto, entity);
		return entity;
	}

	@Override
	public List<CouponRelationEntity> toCouponRelationEntities(List<CouponRelationDTO> dtos){
		List<CouponRelationEntity> entities = new ArrayList<>();
		for(CouponRelationDTO dto : dtos){
			entities.add(toCouponRelationEntity(dto));
		}
		return entities;
	}

	@Override
	public <T> List<T> getList(T dto){
		return null;
	}

	@Override
	public <T> PageList<T> getListForPage(T dto, int pageNumber, int pageSize){
		return null;
	}

	@Override
	public <T> PageList<T> getListForPage(QueryParam queryParam, Class<T> clazz){
		return null;
	}

	@Override
	public void deleteCouponRelationByBusiness(String businessType, Long businessId) {
		couponRelationService.deleteCouponRelationByBusiness(businessType, businessId);
	}

}