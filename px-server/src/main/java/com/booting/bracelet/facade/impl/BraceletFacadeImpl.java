/** create by auto at 2018-06-01 11:34:19**/
package com.booting.bracelet.facade.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.booting.bracelet.dto.BraceletDTO;
import com.booting.bracelet.dto.TotalData;
import com.booting.bracelet.entity.BraceletEntity;
import com.booting.bracelet.facade.BraceletFacade;
import com.booting.bracelet.service.BraceletService;
import com.star.framework.jdbc.dao.result.PageList;
import com.star.framework.jdbc.dao.result.QueryParam;
import com.star.framework.utils.CglibBeanUtils;

@Service("braceletFacade")
public class BraceletFacadeImpl implements BraceletFacade {
	private static final long serialVersionUID = 1L;

	@Autowired
	private BraceletService braceletService;


	@Override
	public Long saveBracelet(BraceletDTO braceletDTO){
		if (null == braceletDTO) {
			return null;
		}
		BraceletEntity entity = toBraceletEntity(braceletDTO);
		braceletDTO = braceletService.save(entity);
		return braceletDTO.getBraceletId();
	}

	@Override
	public void batchSaveBracelet(List<BraceletDTO> dtos){
		if (null == dtos || dtos.isEmpty()) {
			return;
		}
		List<BraceletEntity> entities = toBraceletEntities(dtos);
		braceletService.batchSave(entities);
	}

	@Override
	public int updateBracelet(BraceletDTO braceletDTO){
		braceletDTO = braceletService.updateBySql(braceletDTO);
		return 1;
	}

	@Override
	public void batchUpdateBracelet(List<BraceletDTO> dtos){
		if (null == dtos || dtos.isEmpty()) {
			return;
		}
		braceletService.batchUpdate(dtos);
	}

	@Override
	public int deleteBracelet(long braceletId){
		return braceletService.delete(braceletId);
	}

	@Override
	public BraceletDTO getBracelet(long braceletId){
		return braceletService.get(braceletId);
	}

	@Override
	public BraceletDTO getBracelet(BraceletDTO braceletDTO){
		return braceletService.get(braceletDTO);
	}

	@Override
	public List<BraceletDTO> getBraceletList(BraceletDTO braceletDTO){
		return braceletService.getSimpleList(braceletDTO);
	}

	@Override
	public PageList<BraceletDTO> getBraceletListForPage(BraceletDTO braceletDTO, int pageNumber, int pageSize){
		return braceletService.getSimpleListForPage(braceletDTO, pageNumber, pageSize);
	}

	@Override
	public PageList<BraceletDTO> getBraceletListForPage(QueryParam queryParam){
		return braceletService.getSimpleListForPage(queryParam);
	}

	@Override
	public BraceletEntity toBraceletEntity(BraceletDTO dto){
		BraceletEntity entity = new BraceletEntity();
		CglibBeanUtils.copy(dto, entity);
		return entity;
	}

	@Override
	public List<BraceletEntity> toBraceletEntities(List<BraceletDTO> dtos){
		List<BraceletEntity> entities = new ArrayList<>();
		for(BraceletDTO dto : dtos){
			entities.add(toBraceletEntity(dto));
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
  public List<BraceletDTO> getBraceletList(BraceletDTO braceletDTO, Integer num) {
    return this.braceletService.getBraceletList(braceletDTO, num);
  }

  @Override
  public PageList<TotalData> getStudentTotalData(QueryParam queryParam) {
    return braceletService.getStudentTotalData(queryParam);
  }

  @Override
  public Map<String, Object> getExtremeValue(BraceletDTO bracelet, String field) {
    return braceletService.getExtremeValue(bracelet, field);
  }

  @Override
  public PageList<BraceletDTO> getBraceletListForPageGroupDate(QueryParam queryParam) {
    return this.braceletService.getBraceletListForPageGroupDate(queryParam);
  }

  @Override
  public List<BraceletDTO> getDataByHour(BraceletDTO bracelet) {
    return braceletService.getDataByHour(bracelet);
  }

}