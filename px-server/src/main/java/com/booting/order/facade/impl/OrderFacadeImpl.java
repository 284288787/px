/** create by auto at 2017-07-14 16:22:09**/
package com.booting.order.facade.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.booting.order.facade.OrderFacade;
import com.star.framework.jdbc.dao.result.PageList;
import com.star.framework.jdbc.dao.result.QueryParam;
import com.star.framework.utils.CglibBeanUtils;

import com.booting.order.dto.AlipayPayDetailDTO;
import com.booting.order.entity.AlipayPayDetailEntity;
import com.booting.order.service.AlipayPayDetailService;
import com.booting.order.dto.OrderDetailDTO;
import com.booting.order.entity.OrderDetailEntity;
import com.booting.order.service.OrderDetailService;
import com.booting.order.dto.OrderDTO;
import com.booting.order.entity.OrderEntity;
import com.booting.order.service.OrderService;
import com.booting.order.dto.WeixinPayDetailDTO;
import com.booting.order.entity.WeixinPayDetailEntity;
import com.booting.order.service.WeixinPayDetailService;

@Service("orderFacade")
public class OrderFacadeImpl implements OrderFacade {
	private static final long serialVersionUID = 1L;

	@Autowired
	private AlipayPayDetailService alipayPayDetailService;

	@Autowired
	private OrderDetailService orderDetailService;

	@Autowired
	private OrderService orderService;

	@Autowired
	private WeixinPayDetailService weixinPayDetailService;


	@Override
	public Long saveAlipayPayDetail(AlipayPayDetailDTO alipayPayDetailDTO){
		if (null == alipayPayDetailDTO) {
			return null;
		}
		AlipayPayDetailEntity entity = toAlipayPayDetailEntity(alipayPayDetailDTO);
		alipayPayDetailDTO = alipayPayDetailService.save(entity);
		return alipayPayDetailDTO.getId();
	}

	@Override
	public void batchSaveAlipayPayDetail(List<AlipayPayDetailDTO> dtos){
		if (null == dtos || dtos.isEmpty()) {
			return;
		}
		List<AlipayPayDetailEntity> entities = toAlipayPayDetailEntities(dtos);
		alipayPayDetailService.batchSave(entities);
	}

	@Override
	public int updateAlipayPayDetail(AlipayPayDetailDTO alipayPayDetailDTO){
		alipayPayDetailDTO = alipayPayDetailService.updateBySql(alipayPayDetailDTO);
		return 1;
	}

	@Override
	public void batchUpdateAlipayPayDetail(List<AlipayPayDetailDTO> dtos){
		if (null == dtos || dtos.isEmpty()) {
			return;
		}
		alipayPayDetailService.batchUpdate(dtos);
	}

	@Override
	public int deleteAlipayPayDetail(long id){
		return alipayPayDetailService.delete(id);
	}

	@Override
	public AlipayPayDetailDTO getAlipayPayDetail(long id){
		return alipayPayDetailService.get(id);
	}

	@Override
	public AlipayPayDetailDTO getAlipayPayDetail(AlipayPayDetailDTO alipayPayDetailDTO){
		return alipayPayDetailService.get(alipayPayDetailDTO);
	}

	@Override
	public List<AlipayPayDetailDTO> getAlipayPayDetailList(AlipayPayDetailDTO alipayPayDetailDTO){
		return alipayPayDetailService.getSimpleList(alipayPayDetailDTO);
	}

	@Override
	public PageList<AlipayPayDetailDTO> getAlipayPayDetailListForPage(AlipayPayDetailDTO alipayPayDetailDTO, int pageNumber, int pageSize){
		return alipayPayDetailService.getSimpleListForPage(alipayPayDetailDTO, pageNumber, pageSize);
	}

	@Override
	public PageList<AlipayPayDetailDTO> getAlipayPayDetailListForPage(QueryParam queryParam){
		return alipayPayDetailService.getSimpleListForPage(queryParam);
	}

	@Override
	public AlipayPayDetailEntity toAlipayPayDetailEntity(AlipayPayDetailDTO dto){
		AlipayPayDetailEntity entity = new AlipayPayDetailEntity();
		CglibBeanUtils.copy(dto, entity);
		return entity;
	}

	@Override
	public List<AlipayPayDetailEntity> toAlipayPayDetailEntities(List<AlipayPayDetailDTO> dtos){
		List<AlipayPayDetailEntity> entities = new ArrayList<>();
		for(AlipayPayDetailDTO dto : dtos){
			entities.add(toAlipayPayDetailEntity(dto));
		}
		return entities;
	}

	@Override
	public Long saveOrderDetail(OrderDetailDTO orderDetailDTO){
		if (null == orderDetailDTO) {
			return null;
		}
		OrderDetailEntity entity = toOrderDetailEntity(orderDetailDTO);
		orderDetailDTO = orderDetailService.save(entity);
		return orderDetailDTO.getId();
	}

	@Override
	public void batchSaveOrderDetail(List<OrderDetailDTO> dtos){
		if (null == dtos || dtos.isEmpty()) {
			return;
		}
		List<OrderDetailEntity> entities = toOrderDetailEntities(dtos);
		orderDetailService.batchSave(entities);
	}

	@Override
	public int updateOrderDetail(OrderDetailDTO orderDetailDTO){
		orderDetailDTO = orderDetailService.updateBySql(orderDetailDTO);
		return 1;
	}

	@Override
	public void batchUpdateOrderDetail(List<OrderDetailDTO> dtos){
		if (null == dtos || dtos.isEmpty()) {
			return;
		}
		orderDetailService.batchUpdate(dtos);
	}

	@Override
	public int deleteOrderDetail(long id){
		return orderDetailService.delete(id);
	}

	@Override
	public OrderDetailDTO getOrderDetail(long id){
		return orderDetailService.get(id);
	}

	@Override
	public OrderDetailDTO getOrderDetail(OrderDetailDTO orderDetailDTO){
		return orderDetailService.get(orderDetailDTO);
	}

	@Override
	public List<OrderDetailDTO> getOrderDetailList(OrderDetailDTO orderDetailDTO){
		return orderDetailService.getSimpleList(orderDetailDTO);
	}

	@Override
	public PageList<OrderDetailDTO> getOrderDetailListForPage(OrderDetailDTO orderDetailDTO, int pageNumber, int pageSize){
		return orderDetailService.getSimpleListForPage(orderDetailDTO, pageNumber, pageSize);
	}

	@Override
	public PageList<OrderDetailDTO> getOrderDetailListForPage(QueryParam queryParam){
		return orderDetailService.getSimpleListForPage(queryParam);
	}

	@Override
	public OrderDetailEntity toOrderDetailEntity(OrderDetailDTO dto){
		OrderDetailEntity entity = new OrderDetailEntity();
		CglibBeanUtils.copy(dto, entity);
		return entity;
	}

	@Override
	public List<OrderDetailEntity> toOrderDetailEntities(List<OrderDetailDTO> dtos){
		List<OrderDetailEntity> entities = new ArrayList<>();
		for(OrderDetailDTO dto : dtos){
			entities.add(toOrderDetailEntity(dto));
		}
		return entities;
	}

	@Override
	public Long saveOrder(OrderDTO orderDTO){
		if (null == orderDTO) {
			return null;
		}
		OrderEntity entity = toOrderEntity(orderDTO);
		orderDTO = orderService.save(entity);
		return orderDTO.getOrderId();
	}

	@Override
	public void batchSaveOrder(List<OrderDTO> dtos){
		if (null == dtos || dtos.isEmpty()) {
			return;
		}
		List<OrderEntity> entities = toOrderEntities(dtos);
		orderService.batchSave(entities);
	}

	@Override
	public int updateOrder(OrderDTO orderDTO){
		orderDTO = orderService.updateBySql(orderDTO);
		return 1;
	}

	@Override
	public void batchUpdateOrder(List<OrderDTO> dtos){
		if (null == dtos || dtos.isEmpty()) {
			return;
		}
		orderService.batchUpdate(dtos);
	}

	@Override
	public int deleteOrder(long orderId){
		return orderService.delete(orderId);
	}

	@Override
	public OrderDTO getOrder(long orderId){
		return orderService.get(orderId);
	}

	@Override
	public OrderDTO getOrder(OrderDTO orderDTO){
		return orderService.get(orderDTO);
	}

	@Override
	public List<OrderDTO> getOrderList(OrderDTO orderDTO){
		return orderService.getSimpleList(orderDTO);
	}

	@Override
	public PageList<OrderDTO> getOrderListForPage(OrderDTO orderDTO, int pageNumber, int pageSize){
		return orderService.getSimpleListForPage(orderDTO, pageNumber, pageSize);
	}

	@Override
	public PageList<OrderDTO> getOrderListForPage(QueryParam queryParam){
		return orderService.getListForPage(queryParam, OrderDTO.class);
	}

	@Override
	public OrderEntity toOrderEntity(OrderDTO dto){
		OrderEntity entity = new OrderEntity();
		CglibBeanUtils.copy(dto, entity);
		return entity;
	}

	@Override
	public List<OrderEntity> toOrderEntities(List<OrderDTO> dtos){
		List<OrderEntity> entities = new ArrayList<>();
		for(OrderDTO dto : dtos){
			entities.add(toOrderEntity(dto));
		}
		return entities;
	}

	@Override
	public Long saveWeixinPayDetail(WeixinPayDetailDTO weixinPayDetailDTO){
		if (null == weixinPayDetailDTO) {
			return null;
		}
		WeixinPayDetailEntity entity = toWeixinPayDetailEntity(weixinPayDetailDTO);
		weixinPayDetailDTO = weixinPayDetailService.save(entity);
		return weixinPayDetailDTO.getId();
	}

	@Override
	public void batchSaveWeixinPayDetail(List<WeixinPayDetailDTO> dtos){
		if (null == dtos || dtos.isEmpty()) {
			return;
		}
		List<WeixinPayDetailEntity> entities = toWeixinPayDetailEntities(dtos);
		weixinPayDetailService.batchSave(entities);
	}

	@Override
	public int updateWeixinPayDetail(WeixinPayDetailDTO weixinPayDetailDTO){
		weixinPayDetailDTO = weixinPayDetailService.updateBySql(weixinPayDetailDTO);
		return 1;
	}

	@Override
	public void batchUpdateWeixinPayDetail(List<WeixinPayDetailDTO> dtos){
		if (null == dtos || dtos.isEmpty()) {
			return;
		}
		weixinPayDetailService.batchUpdate(dtos);
	}

	@Override
	public int deleteWeixinPayDetail(long id){
		return weixinPayDetailService.delete(id);
	}

	@Override
	public WeixinPayDetailDTO getWeixinPayDetail(long id){
		return weixinPayDetailService.get(id);
	}

	@Override
	public WeixinPayDetailDTO getWeixinPayDetail(WeixinPayDetailDTO weixinPayDetailDTO){
		return weixinPayDetailService.get(weixinPayDetailDTO);
	}

	@Override
	public List<WeixinPayDetailDTO> getWeixinPayDetailList(WeixinPayDetailDTO weixinPayDetailDTO){
		return weixinPayDetailService.getSimpleList(weixinPayDetailDTO);
	}

	@Override
	public PageList<WeixinPayDetailDTO> getWeixinPayDetailListForPage(WeixinPayDetailDTO weixinPayDetailDTO, int pageNumber, int pageSize){
		return weixinPayDetailService.getSimpleListForPage(weixinPayDetailDTO, pageNumber, pageSize);
	}

	@Override
	public PageList<WeixinPayDetailDTO> getWeixinPayDetailListForPage(QueryParam queryParam){
		return weixinPayDetailService.getSimpleListForPage(queryParam);
	}

	@Override
	public WeixinPayDetailEntity toWeixinPayDetailEntity(WeixinPayDetailDTO dto){
		WeixinPayDetailEntity entity = new WeixinPayDetailEntity();
		CglibBeanUtils.copy(dto, entity);
		return entity;
	}

	@Override
	public List<WeixinPayDetailEntity> toWeixinPayDetailEntities(List<WeixinPayDetailDTO> dtos){
		List<WeixinPayDetailEntity> entities = new ArrayList<>();
		for(WeixinPayDetailDTO dto : dtos){
			entities.add(toWeixinPayDetailEntity(dto));
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