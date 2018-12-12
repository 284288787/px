/**create by liuhua at 2017年6月14日 上午11:28:31**/
package com.booting.management.controller.pub;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.booting.pub.dto.FeedbackDTO;
import com.booting.pub.facade.CommonFacade;
import com.star.framework.jdbc.dao.result.PageList;
import com.star.framework.jdbc.dao.result.QueryParam;

@Controller
@RequestMapping("/back")
public class FeedbackController {

	@Autowired
	private CommonFacade commonFacade;
	
	@ResponseBody
	@RequestMapping(value = "/backList", method = RequestMethod.POST)
	public Map<String, Object> list(FeedbackDTO feedbackDTO, Integer page, Integer rows, String sord, String sidx){
		QueryParam queryParam = new QueryParam();
		queryParam.setOrderBy(sidx);
		queryParam.setOrderType(sord);
		queryParam.setPageNo(page);
		queryParam.setPageSize(rows);
		queryParam.setParam(feedbackDTO);
		PageList<FeedbackDTO> pageList = commonFacade.getFeedbackListForPage(queryParam);
		Map<String, Object> map = new HashMap<>();
		map.put("page", pageList.getPageNo());
		map.put("total", pageList.getTotalPage());
		map.put("records", pageList.getTotalRecord());
		map.put("rows", pageList.getDataList());
		return map;
	}
}
