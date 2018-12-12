/** create by auto at 2017-08-08 15:31:24**/
package com.booting.coupon.facade.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.booting.coupon.facade.CouponFacade;
import com.star.framework.jdbc.dao.result.PageList;
import com.star.framework.jdbc.dao.result.QueryParam;
import com.star.framework.utils.CglibBeanUtils;

import com.booting.coupon.dto.CouponDTO;
import com.booting.coupon.entity.CouponEntity;
import com.booting.coupon.service.CouponService;
import com.booting.coupon.dto.CourtCouponDTO;
import com.booting.coupon.entity.CourtCouponEntity;
import com.booting.coupon.service.CourtCouponService;
import com.booting.coupon.dto.UsedCouponDetailDTO;
import com.booting.coupon.entity.UsedCouponDetailEntity;
import com.booting.coupon.service.UsedCouponDetailService;
import com.booting.coupon.dto.UserCouponDTO;
import com.booting.coupon.entity.UserCouponEntity;
import com.booting.coupon.service.UserCouponService;

@Service("couponFacade")
public class CouponFacadeImpl implements CouponFacade {
	private static final long serialVersionUID = 1L;

	@Autowired
	private CouponService couponService;

	@Autowired
	private CourtCouponService courtCouponService;

	@Autowired
	private UsedCouponDetailService usedCouponDetailService;

	@Autowired
	private UserCouponService userCouponService;


	@Override
	public Long saveCoupon(CouponDTO couponDTO){
		if (null == couponDTO) {
			return null;
		}
		CouponEntity entity = toCouponEntity(couponDTO);
		couponDTO = couponService.save(entity);
		return couponDTO.getCouponId();
	}

	@Override
	public void batchSaveCoupon(List<CouponDTO> dtos){
		if (null == dtos || dtos.isEmpty()) {
			return;
		}
		List<CouponEntity> entities = toCouponEntities(dtos);
		couponService.batchSave(entities);
	}

	@Override
	public int updateCoupon(CouponDTO couponDTO){
		couponDTO = couponService.updateBySql(couponDTO);
		return 1;
	}

	@Override
	public void batchUpdateCoupon(List<CouponDTO> dtos){
		if (null == dtos || dtos.isEmpty()) {
			return;
		}
		couponService.batchUpdate(dtos);
	}

	@Override
	public int deleteCoupon(long couponId){
		return couponService.delete(couponId);
	}

	@Override
	public CouponDTO getCoupon(long couponId){
		return couponService.get(couponId);
	}

	@Override
	public CouponDTO getCoupon(CouponDTO couponDTO){
		return couponService.get(couponDTO);
	}

	@Override
	public List<CouponDTO> getCouponList(CouponDTO couponDTO){
		return couponService.getSimpleList(couponDTO);
	}

	@Override
	public PageList<CouponDTO> getCouponListForPage(CouponDTO couponDTO, int pageNumber, int pageSize){
		return couponService.getSimpleListForPage(couponDTO, pageNumber, pageSize);
	}

	@Override
	public PageList<CouponDTO> getCouponListForPage(QueryParam queryParam){
		return couponService.getSimpleListForPage(queryParam);
	}

	@Override
	public CouponEntity toCouponEntity(CouponDTO dto){
		CouponEntity entity = new CouponEntity();
		CglibBeanUtils.copy(dto, entity);
		return entity;
	}

	@Override
	public List<CouponEntity> toCouponEntities(List<CouponDTO> dtos){
		List<CouponEntity> entities = new ArrayList<>();
		for(CouponDTO dto : dtos){
			entities.add(toCouponEntity(dto));
		}
		return entities;
	}

	@Override
	public Long saveCourtCoupon(CourtCouponDTO courtCouponDTO){
		if (null == courtCouponDTO) {
			return null;
		}
		CourtCouponEntity entity = toCourtCouponEntity(courtCouponDTO);
		courtCouponDTO = courtCouponService.save(entity);
		return courtCouponDTO.getId();
	}

	@Override
	public void batchSaveCourtCoupon(List<CourtCouponDTO> dtos){
		if (null == dtos || dtos.isEmpty()) {
			return;
		}
		List<CourtCouponEntity> entities = toCourtCouponEntities(dtos);
		courtCouponService.batchSave(entities);
	}

	@Override
	public int updateCourtCoupon(CourtCouponDTO courtCouponDTO){
		courtCouponDTO = courtCouponService.updateBySql(courtCouponDTO);
		return 1;
	}

	@Override
	public void batchUpdateCourtCoupon(List<CourtCouponDTO> dtos){
		if (null == dtos || dtos.isEmpty()) {
			return;
		}
		courtCouponService.batchUpdate(dtos);
	}

	@Override
	public int deleteCourtCoupon(long id){
		return courtCouponService.delete(id);
	}

	@Override
	public CourtCouponDTO getCourtCoupon(long id){
		return courtCouponService.get(id);
	}

	@Override
	public CourtCouponDTO getCourtCoupon(CourtCouponDTO courtCouponDTO){
		return courtCouponService.get(courtCouponDTO);
	}

	@Override
	public List<CourtCouponDTO> getCourtCouponList(CourtCouponDTO courtCouponDTO){
		return courtCouponService.getSimpleList(courtCouponDTO);
	}

	@Override
	public PageList<CourtCouponDTO> getCourtCouponListForPage(CourtCouponDTO courtCouponDTO, int pageNumber, int pageSize){
		return courtCouponService.getSimpleListForPage(courtCouponDTO, pageNumber, pageSize);
	}

	@Override
	public PageList<CourtCouponDTO> getCourtCouponListForPage(QueryParam queryParam){
		return courtCouponService.getSimpleListForPage(queryParam);
	}

	@Override
	public CourtCouponEntity toCourtCouponEntity(CourtCouponDTO dto){
		CourtCouponEntity entity = new CourtCouponEntity();
		CglibBeanUtils.copy(dto, entity);
		return entity;
	}

	@Override
	public List<CourtCouponEntity> toCourtCouponEntities(List<CourtCouponDTO> dtos){
		List<CourtCouponEntity> entities = new ArrayList<>();
		for(CourtCouponDTO dto : dtos){
			entities.add(toCourtCouponEntity(dto));
		}
		return entities;
	}

	@Override
	public Long saveUsedCouponDetail(UsedCouponDetailDTO usedCouponDetailDTO){
		if (null == usedCouponDetailDTO) {
			return null;
		}
		UsedCouponDetailEntity entity = toUsedCouponDetailEntity(usedCouponDetailDTO);
		usedCouponDetailDTO = usedCouponDetailService.save(entity);
		return usedCouponDetailDTO.getId();
	}

	@Override
	public void batchSaveUsedCouponDetail(List<UsedCouponDetailDTO> dtos){
		if (null == dtos || dtos.isEmpty()) {
			return;
		}
		List<UsedCouponDetailEntity> entities = toUsedCouponDetailEntities(dtos);
		usedCouponDetailService.batchSave(entities);
	}

	@Override
	public int updateUsedCouponDetail(UsedCouponDetailDTO usedCouponDetailDTO){
		usedCouponDetailDTO = usedCouponDetailService.updateBySql(usedCouponDetailDTO);
		return 1;
	}

	@Override
	public void batchUpdateUsedCouponDetail(List<UsedCouponDetailDTO> dtos){
		if (null == dtos || dtos.isEmpty()) {
			return;
		}
		usedCouponDetailService.batchUpdate(dtos);
	}

	@Override
	public int deleteUsedCouponDetail(long id){
		return usedCouponDetailService.delete(id);
	}

	@Override
	public UsedCouponDetailDTO getUsedCouponDetail(long id){
		return usedCouponDetailService.get(id);
	}

	@Override
	public UsedCouponDetailDTO getUsedCouponDetail(UsedCouponDetailDTO usedCouponDetailDTO){
		return usedCouponDetailService.get(usedCouponDetailDTO);
	}

	@Override
	public List<UsedCouponDetailDTO> getUsedCouponDetailList(UsedCouponDetailDTO usedCouponDetailDTO){
		return usedCouponDetailService.getSimpleList(usedCouponDetailDTO);
	}

	@Override
	public PageList<UsedCouponDetailDTO> getUsedCouponDetailListForPage(UsedCouponDetailDTO usedCouponDetailDTO, int pageNumber, int pageSize){
		return usedCouponDetailService.getSimpleListForPage(usedCouponDetailDTO, pageNumber, pageSize);
	}

	@Override
	public PageList<UsedCouponDetailDTO> getUsedCouponDetailListForPage(QueryParam queryParam){
		return usedCouponDetailService.getSimpleListForPage(queryParam);
	}

	@Override
	public UsedCouponDetailEntity toUsedCouponDetailEntity(UsedCouponDetailDTO dto){
		UsedCouponDetailEntity entity = new UsedCouponDetailEntity();
		CglibBeanUtils.copy(dto, entity);
		return entity;
	}

	@Override
	public List<UsedCouponDetailEntity> toUsedCouponDetailEntities(List<UsedCouponDetailDTO> dtos){
		List<UsedCouponDetailEntity> entities = new ArrayList<>();
		for(UsedCouponDetailDTO dto : dtos){
			entities.add(toUsedCouponDetailEntity(dto));
		}
		return entities;
	}

	@Override
	public Long saveUserCoupon(UserCouponDTO userCouponDTO){
		if (null == userCouponDTO) {
			return null;
		}
		UserCouponEntity entity = toUserCouponEntity(userCouponDTO);
		userCouponDTO = userCouponService.save(entity);
		return userCouponDTO.getId();
	}

	@Override
	public void batchSaveUserCoupon(List<UserCouponDTO> dtos){
		if (null == dtos || dtos.isEmpty()) {
			return;
		}
		List<UserCouponEntity> entities = toUserCouponEntities(dtos);
		userCouponService.batchSave(entities);
	}

	@Override
	public int updateUserCoupon(UserCouponDTO userCouponDTO){
		userCouponDTO = userCouponService.updateBySql(userCouponDTO);
		return 1;
	}

	@Override
	public void batchUpdateUserCoupon(List<UserCouponDTO> dtos){
		if (null == dtos || dtos.isEmpty()) {
			return;
		}
		userCouponService.batchUpdate(dtos);
	}

	@Override
	public int deleteUserCoupon(long id){
		return userCouponService.delete(id);
	}

	@Override
	public UserCouponDTO getUserCoupon(long id){
		return userCouponService.get(id);
	}

	@Override
	public UserCouponDTO getUserCoupon(UserCouponDTO userCouponDTO){
		return userCouponService.get(userCouponDTO);
	}

	@Override
	public List<UserCouponDTO> getUserCouponList(UserCouponDTO userCouponDTO){
		return userCouponService.getSimpleList(userCouponDTO);
	}

	@Override
	public PageList<UserCouponDTO> getUserCouponListForPage(UserCouponDTO userCouponDTO, int pageNumber, int pageSize){
		return userCouponService.getSimpleListForPage(userCouponDTO, pageNumber, pageSize);
	}

	@Override
	public PageList<UserCouponDTO> getUserCouponListForPage(QueryParam queryParam){
		return userCouponService.getSimpleListForPage(queryParam);
	}

	@Override
	public UserCouponEntity toUserCouponEntity(UserCouponDTO dto){
		UserCouponEntity entity = new UserCouponEntity();
		CglibBeanUtils.copy(dto, entity);
		return entity;
	}

	@Override
	public List<UserCouponEntity> toUserCouponEntities(List<UserCouponDTO> dtos){
		List<UserCouponEntity> entities = new ArrayList<>();
		for(UserCouponDTO dto : dtos){
			entities.add(toUserCouponEntity(dto));
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
	public int getUsedCouponCountOfTeam(Long teamId, Long couponId) {
		return usedCouponDetailService.getUsedCouponCountOfTeam(teamId, couponId);
	}

	@Override
	public int getUsedCouponCount(Long userId, Long couponId) {
		return usedCouponDetailService.getUsedCouponCount(userId, couponId);
	}

}