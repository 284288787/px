/** create by auto at 2017-07-14 16:22:09**/
package com.booting.order.facade;

import java.io.Serializable;
import java.util.List;

import com.star.framework.jdbc.dao.result.PageList;
import com.star.framework.jdbc.dao.result.QueryParam;
import com.booting.order.dto.AlipayPayDetailDTO;
import com.booting.order.entity.AlipayPayDetailEntity;
import com.booting.order.dto.OrderDetailDTO;
import com.booting.order.entity.OrderDetailEntity;
import com.booting.order.dto.OrderDTO;
import com.booting.order.entity.OrderEntity;
import com.booting.order.dto.WeixinPayDetailDTO;
import com.booting.order.entity.WeixinPayDetailEntity;

public interface OrderFacade extends Serializable {

	/**
	 * 新增 支付宝回调数据
	 */
	public Long saveAlipayPayDetail(AlipayPayDetailDTO alipayPayDetailDTO);

	/**
	 * 批量新增 支付宝回调数据
	 */
	public void batchSaveAlipayPayDetail(List<AlipayPayDetailDTO> dtos);

	/**
	 * 更新 支付宝回调数据
	 */
	public int updateAlipayPayDetail(AlipayPayDetailDTO alipayPayDetailDTO);

	/**
	 * 批量 支付宝回调数据
	 */
	public void batchUpdateAlipayPayDetail(List<AlipayPayDetailDTO> dtos);

	/**
	 * 删除 支付宝回调数据
	 */
	public int deleteAlipayPayDetail(long id);

	/**
	 * 根据主键获取 支付宝回调数据
	 */
	public AlipayPayDetailDTO getAlipayPayDetail(long id);

	/**
	 * 根据条件获取一条 支付宝回调数据
	 */
	public AlipayPayDetailDTO getAlipayPayDetail(AlipayPayDetailDTO alipayPayDetailDTO);

	/**
	 * 查询满足条件的 支付宝回调数据 列表(单表)
	 */
	public List<AlipayPayDetailDTO> getAlipayPayDetailList(AlipayPayDetailDTO alipayPayDetailDTO);

	/**
	 * 查询满足条件的 支付宝回调数据 列表(分页)(单表)
	 */
	public PageList<AlipayPayDetailDTO> getAlipayPayDetailListForPage(AlipayPayDetailDTO alipayPayDetailDTO, int pageNumber, int pageSize);

	/**
	 * 查询满足条件的 支付宝回调数据 列表(分页)(单表)
	 */
	public PageList<AlipayPayDetailDTO> getAlipayPayDetailListForPage(QueryParam queryParam);

	/**
	 * 支付宝回调数据DTO 转换成 Entity
	 */
	public AlipayPayDetailEntity toAlipayPayDetailEntity(AlipayPayDetailDTO alipayPayDetailDTO);

	/**
	 * 支付宝回调数据DTOs 转换成 Entities
	 */
	public List<AlipayPayDetailEntity> toAlipayPayDetailEntities(List<AlipayPayDetailDTO> dtoes);

	/**
	 * 新增 订单详情
	 */
	public Long saveOrderDetail(OrderDetailDTO orderDetailDTO);

	/**
	 * 批量新增 订单详情
	 */
	public void batchSaveOrderDetail(List<OrderDetailDTO> dtos);

	/**
	 * 更新 订单详情
	 */
	public int updateOrderDetail(OrderDetailDTO orderDetailDTO);

	/**
	 * 批量 订单详情
	 */
	public void batchUpdateOrderDetail(List<OrderDetailDTO> dtos);

	/**
	 * 删除 订单详情
	 */
	public int deleteOrderDetail(long id);

	/**
	 * 根据主键获取 订单详情
	 */
	public OrderDetailDTO getOrderDetail(long id);

	/**
	 * 根据条件获取一条 订单详情
	 */
	public OrderDetailDTO getOrderDetail(OrderDetailDTO orderDetailDTO);

	/**
	 * 查询满足条件的 订单详情 列表(单表)
	 */
	public List<OrderDetailDTO> getOrderDetailList(OrderDetailDTO orderDetailDTO);

	/**
	 * 查询满足条件的 订单详情 列表(分页)(单表)
	 */
	public PageList<OrderDetailDTO> getOrderDetailListForPage(OrderDetailDTO orderDetailDTO, int pageNumber, int pageSize);

	/**
	 * 查询满足条件的 订单详情 列表(分页)(单表)
	 */
	public PageList<OrderDetailDTO> getOrderDetailListForPage(QueryParam queryParam);

	/**
	 * 订单详情DTO 转换成 Entity
	 */
	public OrderDetailEntity toOrderDetailEntity(OrderDetailDTO orderDetailDTO);

	/**
	 * 订单详情DTOs 转换成 Entities
	 */
	public List<OrderDetailEntity> toOrderDetailEntities(List<OrderDetailDTO> dtoes);

	/**
	 * 新增 订单
	 */
	public Long saveOrder(OrderDTO orderDTO);

	/**
	 * 批量新增 订单
	 */
	public void batchSaveOrder(List<OrderDTO> dtos);

	/**
	 * 更新 订单
	 */
	public int updateOrder(OrderDTO orderDTO);

	/**
	 * 批量 订单
	 */
	public void batchUpdateOrder(List<OrderDTO> dtos);

	/**
	 * 删除 订单
	 */
	public int deleteOrder(long orderId);

	/**
	 * 根据主键获取 订单
	 */
	public OrderDTO getOrder(long orderId);

	/**
	 * 根据条件获取一条 订单
	 */
	public OrderDTO getOrder(OrderDTO orderDTO);

	/**
	 * 查询满足条件的 订单 列表(单表)
	 */
	public List<OrderDTO> getOrderList(OrderDTO orderDTO);

	/**
	 * 查询满足条件的 订单 列表(分页)(单表)
	 */
	public PageList<OrderDTO> getOrderListForPage(OrderDTO orderDTO, int pageNumber, int pageSize);

	/**
	 * 查询满足条件的 订单 列表(分页)(单表)
	 */
	public PageList<OrderDTO> getOrderListForPage(QueryParam queryParam);

	/**
	 * 订单DTO 转换成 Entity
	 */
	public OrderEntity toOrderEntity(OrderDTO orderDTO);

	/**
	 * 订单DTOs 转换成 Entities
	 */
	public List<OrderEntity> toOrderEntities(List<OrderDTO> dtoes);

	/**
	 * 新增 微信回调数据
	 */
	public Long saveWeixinPayDetail(WeixinPayDetailDTO weixinPayDetailDTO);

	/**
	 * 批量新增 微信回调数据
	 */
	public void batchSaveWeixinPayDetail(List<WeixinPayDetailDTO> dtos);

	/**
	 * 更新 微信回调数据
	 */
	public int updateWeixinPayDetail(WeixinPayDetailDTO weixinPayDetailDTO);

	/**
	 * 批量 微信回调数据
	 */
	public void batchUpdateWeixinPayDetail(List<WeixinPayDetailDTO> dtos);

	/**
	 * 删除 微信回调数据
	 */
	public int deleteWeixinPayDetail(long id);

	/**
	 * 根据主键获取 微信回调数据
	 */
	public WeixinPayDetailDTO getWeixinPayDetail(long id);

	/**
	 * 根据条件获取一条 微信回调数据
	 */
	public WeixinPayDetailDTO getWeixinPayDetail(WeixinPayDetailDTO weixinPayDetailDTO);

	/**
	 * 查询满足条件的 微信回调数据 列表(单表)
	 */
	public List<WeixinPayDetailDTO> getWeixinPayDetailList(WeixinPayDetailDTO weixinPayDetailDTO);

	/**
	 * 查询满足条件的 微信回调数据 列表(分页)(单表)
	 */
	public PageList<WeixinPayDetailDTO> getWeixinPayDetailListForPage(WeixinPayDetailDTO weixinPayDetailDTO, int pageNumber, int pageSize);

	/**
	 * 查询满足条件的 微信回调数据 列表(分页)(单表)
	 */
	public PageList<WeixinPayDetailDTO> getWeixinPayDetailListForPage(QueryParam queryParam);

	/**
	 * 微信回调数据DTO 转换成 Entity
	 */
	public WeixinPayDetailEntity toWeixinPayDetailEntity(WeixinPayDetailDTO weixinPayDetailDTO);

	/**
	 * 微信回调数据DTOs 转换成 Entities
	 */
	public List<WeixinPayDetailEntity> toWeixinPayDetailEntities(List<WeixinPayDetailDTO> dtoes);

	/**
	 * 查询满足条件的 列表(多表)
	 */
	public <T> List<T> getList(T dto);

	/**
	 * 查询满足条件的列表(分页)(多表)
	 */
	public <T> PageList<T> getListForPage(T dto, int pageNumber, int pageSize);

	/**
	 * 查询满足条件的列表(分页)(多表)
	 */
	public <T> PageList<T> getListForPage(QueryParam queryParam, Class<T> clazz);

}