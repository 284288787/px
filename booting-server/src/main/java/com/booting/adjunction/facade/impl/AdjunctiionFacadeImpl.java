/** create by auto at 2017-10-17 10:14:20**/
package com.booting.adjunction.facade.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.booting.adjunction.facade.AdjunctiionFacade;
import com.star.framework.jdbc.dao.result.PageList;
import com.star.framework.jdbc.dao.result.QueryParam;
import com.star.framework.utils.CglibBeanUtils;

import com.booting.adjunction.dto.PopularizeDTO;
import com.booting.adjunction.entity.PopularizeEntity;
import com.booting.adjunction.service.PopularizeService;

@Service("adjunctiionFacade")
public class AdjunctiionFacadeImpl implements AdjunctiionFacade {
	private static final long serialVersionUID = 1L;

	@Autowired
	private PopularizeService popularizeService;


	@Override
	public Long savePopularize(PopularizeDTO popularizeDTO){
		if (null == popularizeDTO) {
			return null;
		}
		PopularizeEntity entity = toPopularizeEntity(popularizeDTO);
		popularizeDTO = popularizeService.save(entity);
		return popularizeDTO.getId();
	}

	@Override
	public void batchSavePopularize(List<PopularizeDTO> dtos){
		if (null == dtos || dtos.isEmpty()) {
			return;
		}
		List<PopularizeEntity> entities = toPopularizeEntities(dtos);
		popularizeService.batchSave(entities);
	}

	@Override
	public int updatePopularize(PopularizeDTO popularizeDTO){
		popularizeDTO = popularizeService.updateBySql(popularizeDTO);
		return 1;
	}

	@Override
	public void batchUpdatePopularize(List<PopularizeDTO> dtos){
		if (null == dtos || dtos.isEmpty()) {
			return;
		}
		popularizeService.batchUpdate(dtos);
	}

	@Override
	public int deletePopularize(long id){
		return popularizeService.delete(id);
	}

	@Override
	public PopularizeDTO getPopularize(long id){
		return popularizeService.get(id);
	}

	@Override
	public PopularizeDTO getPopularize(PopularizeDTO popularizeDTO){
		return popularizeService.get(popularizeDTO);
	}

	@Override
	public List<PopularizeDTO> getPopularizeList(PopularizeDTO popularizeDTO){
		return popularizeService.getSimpleList(popularizeDTO);
	}

	@Override
	public PageList<PopularizeDTO> getPopularizeListForPage(PopularizeDTO popularizeDTO, int pageNumber, int pageSize){
		return popularizeService.getSimpleListForPage(popularizeDTO, pageNumber, pageSize);
	}

	@Override
	public PageList<PopularizeDTO> getPopularizeListForPage(QueryParam queryParam){
		return popularizeService.getSimpleListForPage(queryParam);
	}

	@Override
	public PopularizeEntity toPopularizeEntity(PopularizeDTO dto){
		PopularizeEntity entity = new PopularizeEntity();
		CglibBeanUtils.copy(dto, entity);
		return entity;
	}

	@Override
	public List<PopularizeEntity> toPopularizeEntities(List<PopularizeDTO> dtos){
		List<PopularizeEntity> entities = new ArrayList<>();
		for(PopularizeDTO dto : dtos){
			entities.add(toPopularizeEntity(dto));
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

}