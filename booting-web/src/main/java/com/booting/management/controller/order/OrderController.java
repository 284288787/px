/**create by liuhua at 2017年7月14日 下午4:25:44**/
package com.booting.management.controller.order;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.booting.order.dto.OrderDTO;
import com.booting.order.facade.OrderFacade;
import com.booting.service.OrderWebService;
import com.star.framework.jdbc.dao.result.PageList;
import com.star.framework.jdbc.dao.result.QueryParam;

@Controller
@RequestMapping("/order")
public class OrderController {

	@Autowired
	private OrderFacade orderFacade;
	@Autowired
	private OrderWebService orderWebService;
	
	@ResponseBody
	@RequestMapping(value = "/list", method = RequestMethod.POST)
	public Map<String, Object> list(OrderDTO orderDTO, Integer page, Integer rows, String sord, String sidx){
		QueryParam queryParam = new QueryParam();
		queryParam.setOrderBy(sidx);
		queryParam.setOrderType(sord);
		queryParam.setPageNo(page);
		queryParam.setPageSize(rows);
		queryParam.setParam(orderDTO);
		PageList<OrderDTO> pageList = orderFacade.getOrderListForPage(queryParam);
		Map<String, Object> map = new HashMap<>();
		map.put("page", pageList.getPageNo());
		map.put("total", pageList.getTotalPage());
		map.put("records", pageList.getTotalRecord());
		map.put("rows", pageList.getDataList());
		return map;
	}
	
	@RequestMapping(value = "/view/{orderId}", method = RequestMethod.GET, produces = MediaType.TEXT_HTML_VALUE)
	public String editBefore(@PathVariable Long orderId, Model model){
		OrderDTO orderDTO = this.orderWebService.getOrder(orderId);
		model.addAttribute("order", orderDTO);
		return "management/order/orderView";
	}
}
