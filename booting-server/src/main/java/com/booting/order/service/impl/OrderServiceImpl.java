/** create by auto at 2017-07-14 16:22:09**/
package com.booting.order.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.booting.order.dto.OrderDTO;
import com.booting.order.entity.OrderEntity;
import com.booting.order.service.OrderService;

import com.star.framework.jdbc.dao.JDBCSupport;

import com.star.framework.jdbc.dao.result.PageList;

import com.star.framework.jdbc.dao.result.QueryParam;

@Service("orderService")
public class OrderServiceImpl extends JDBCSupport<OrderEntity, OrderDTO> implements OrderService{

	private static final long serialVersionUID = 1L;

	@Override
	public OrderDTO save(OrderEntity orderEntity) {
		long id = this.persist(orderEntity);
		return get(id);
	}

	@Override
	public OrderDTO update(OrderEntity orderEntity) {
		this.dynamicMerge(orderEntity);
		return get(orderEntity.getOrderId());
	}

	@Override
	public OrderDTO updateAll(OrderEntity orderEntity) {
		this.merge(orderEntity);
		return get(orderEntity.getOrderId());
	}

	@Override
	public OrderDTO updateBySql(OrderDTO orderDTO) {
		if(null == orderDTO) return null;
		this.execute("order.updateOrder", toMap(orderDTO, "yyyy-MM-dd HH:mm:ss"));
		if(null == orderDTO.getOrderId()) return null;
		return get(orderDTO.getOrderId());
	}

	@Override
	public int delete(long id) {
		return this.del(id);
	}

	@Override
	public void batchSave(List<OrderEntity> entities) {
		if(null == entities || entities.isEmpty()){
			return;
		}
		Map<String, Object>[] params = toMap(entities);
		this.batch("order.insertOrder", params);
	}

	@Override
	public void batchUpdate(List<OrderDTO> dtos) {
		if(null == dtos || dtos.isEmpty()){
			return;
		}
		Map<String, Object>[] params = toMap(dtos);
		this.batch("order.updateOrder", params);
	}

	@Override
	public OrderDTO get(long orderId) {
		return getById(orderId);
	}

	@Override
	public OrderDTO get(OrderDTO orderDTO) {
		if(null == orderDTO) {
			return null;
		}
		Map<String, Object> param = toMap(orderDTO, "yyyy-MM-dd HH:mm:ss");
		return this.queryForObject("order.getOrderList", param);
	}

	@Override
	public List<OrderDTO> getSimpleList(OrderDTO orderDTO) {
		Map<String, Object> param = toMap(orderDTO, "yyyy-MM-dd HH:mm:ss");
		return this.queryForList("order.getSimpleOrderList", param);
	}

	@Override
	public PageList<OrderDTO> getSimpleListForPage(OrderDTO orderDTO, int pageNo, int pageSize) {
		QueryParam queryParam = new QueryParam();
		queryParam.setPageNo(pageNo);
		queryParam.setPageSize(pageSize);
		queryParam.setParam(orderDTO);
		return this.queryForPage("order.getSimpleOrderListCount", "order.getSimpleOrderList", queryParam);
	}

	@Override
	public PageList<OrderDTO> getSimpleListForPage(QueryParam queryParam) {
		return this.queryForPage("order.getSimpleOrderListCount", "order.getSimpleOrderList", queryParam);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> List<T> getList(T dto) {
		Map<String, Object> param = toMap(dto, "yyyy-MM-dd HH:mm:ss");
		return (List<T>)this.queryForList("order.getOrderList", param);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> PageList<T> getListForPage(T dto, int pageNo, int pageSize) {
		QueryParam queryParam = new QueryParam();
		queryParam.setPageNo(pageNo);
		queryParam.setPageSize(pageSize);
		queryParam.setParam(dto);
		return (PageList<T>) this.queryForPage("order.getOrderListCount", "order.getOrderList", queryParam, dto.getClass());
	}

	@Override
	public <T> PageList<T> getListForPage(QueryParam queryParam, Class<T> clazz) {
		return (PageList<T>) this.queryForPage("order.getOrderListCount", "order.getOrderList", queryParam, clazz);
	}

}