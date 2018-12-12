/** create by auto at 2017-07-14 16:22:09**/
package com.booting.order.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.booting.order.dto.OrderDetailDTO;
import com.booting.order.entity.OrderDetailEntity;
import com.booting.order.service.OrderDetailService;

import com.star.framework.jdbc.dao.JDBCSupport;

import com.star.framework.jdbc.dao.result.PageList;

import com.star.framework.jdbc.dao.result.QueryParam;

@Service("orderDetailService")
public class OrderDetailServiceImpl extends JDBCSupport<OrderDetailEntity, OrderDetailDTO> implements OrderDetailService{

	private static final long serialVersionUID = 1L;

	@Override
	public OrderDetailDTO save(OrderDetailEntity orderDetailEntity) {
		long id = this.persist(orderDetailEntity);
		return get(id);
	}

	@Override
	public OrderDetailDTO update(OrderDetailEntity orderDetailEntity) {
		this.dynamicMerge(orderDetailEntity);
		return get(orderDetailEntity.getId());
	}

	@Override
	public OrderDetailDTO updateAll(OrderDetailEntity orderDetailEntity) {
		this.merge(orderDetailEntity);
		return get(orderDetailEntity.getId());
	}

	@Override
	public OrderDetailDTO updateBySql(OrderDetailDTO orderDetailDTO) {
		if(null == orderDetailDTO) return null;
		this.execute("orderDetail.updateOrderDetail", toMap(orderDetailDTO, "yyyy-MM-dd HH:mm:ss"));
		if(null == orderDetailDTO.getId()) return null;
		return get(orderDetailDTO.getId());
	}

	@Override
	public int delete(long id) {
		return this.del(id);
	}

	@Override
	public void batchSave(List<OrderDetailEntity> entities) {
		if(null == entities || entities.isEmpty()){
			return;
		}
		Map<String, Object>[] params = toMap(entities);
		this.batch("orderDetail.insertOrderDetail", params);
	}

	@Override
	public void batchUpdate(List<OrderDetailDTO> dtos) {
		if(null == dtos || dtos.isEmpty()){
			return;
		}
		Map<String, Object>[] params = toMap(dtos);
		this.batch("orderDetail.updateOrderDetail", params);
	}

	@Override
	public OrderDetailDTO get(long id) {
		return getById(id);
	}

	@Override
	public OrderDetailDTO get(OrderDetailDTO orderDetailDTO) {
		if(null == orderDetailDTO) {
			return null;
		}
		Map<String, Object> param = toMap(orderDetailDTO, "yyyy-MM-dd HH:mm:ss");
		return this.queryForObject("orderDetail.getSimpleOrderDetailList", param);
	}

	@Override
	public List<OrderDetailDTO> getSimpleList(OrderDetailDTO orderDetailDTO) {
		Map<String, Object> param = toMap(orderDetailDTO, "yyyy-MM-dd HH:mm:ss");
		return this.queryForList("orderDetail.getSimpleOrderDetailList", param);
	}

	@Override
	public PageList<OrderDetailDTO> getSimpleListForPage(OrderDetailDTO orderDetailDTO, int pageNo, int pageSize) {
		QueryParam queryParam = new QueryParam();
		queryParam.setPageNo(pageNo);
		queryParam.setPageSize(pageSize);
		queryParam.setParam(orderDetailDTO);
		return this.queryForPage("orderDetail.getSimpleOrderDetailListCount", "orderDetail.getSimpleOrderDetailList", queryParam);
	}

	@Override
	public PageList<OrderDetailDTO> getSimpleListForPage(QueryParam queryParam) {
		return this.queryForPage("orderDetail.getSimpleOrderDetailListCount", "orderDetail.getSimpleOrderDetailList", queryParam);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> List<T> getList(T dto) {
		Map<String, Object> param = toMap(dto, "yyyy-MM-dd HH:mm:ss");
		return (List<T>)this.queryForList("orderDetail.getOrderDetailList", param);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> PageList<T> getListForPage(T dto, int pageNo, int pageSize) {
		QueryParam queryParam = new QueryParam();
		queryParam.setPageNo(pageNo);
		queryParam.setPageSize(pageSize);
		queryParam.setParam(dto);
		return (PageList<T>) this.queryForPage("orderDetail.getOrderDetailListCount", "orderDetail.getOrderDetailList", queryParam, dto.getClass());
	}

	@Override
	public <T> PageList<T> getListForPage(QueryParam queryParam, Class<T> clazz) {
		return (PageList<T>) this.queryForPage("orderDetail.getOrderDetailListCount", "orderDetail.getOrderDetailList", queryParam, clazz);
	}

}