/** create by auto at 2018-01-15 10:25:41**/
package com.booting.management.controller.kindergarten;

import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.booting.kindergarten.dto.PhysicalDataDTO;
import com.booting.service.impl.KindergartenWebService;
import com.star.framework.jdbc.dao.result.PageList;
import com.star.framework.jdbc.dao.result.QueryParam;
import com.star.framework.specification.FailureCode;
import com.star.framework.specification.exception.ArgsException;
import com.star.framework.specification.result.ResultMessage;
import com.star.framework.utils.DataExportUtil;

@Controller
@RequestMapping("/physicalData")
public class PhysicalDataController{

	@Autowired
	private KindergartenWebService kindergartenWebService;

	@ResponseBody
	@RequestMapping(value = "/list", method = RequestMethod.POST)
	public Map<String, Object> list(PhysicalDataDTO physicalDataDTO, Integer page, Integer rows, String sord, String sidx){
		physicalDataDTO.setDeleted(0);
		QueryParam queryParam = new QueryParam();
		queryParam.setOrderBy(sidx);
		queryParam.setOrderType(sord);
		queryParam.setPageNo(page);
		queryParam.setPageSize(rows);
		queryParam.setParam(physicalDataDTO);
		PageList<PhysicalDataDTO> pageList = kindergartenWebService.getListForPagePhysicalData(queryParam, PhysicalDataDTO.class);
		Map<String, Object> map = new HashMap<>();
		map.put("page", pageList.getPageNo());
		map.put("total", pageList.getTotalPage());
		map.put("records", pageList.getTotalRecord());
		map.put("rows", pageList.getDataList());
		return map;
	}

	@RequestMapping(value = "/editBefore/{id}", method = RequestMethod.GET, produces = MediaType.TEXT_HTML_VALUE)
	public String editBefore(@PathVariable Long id, Model model){
		PhysicalDataDTO physicalDataDTO = this.kindergartenWebService.getPhysicalData(id);
		model.addAttribute("physicalDataDTO", physicalDataDTO);
		return "management/kindergartenPhysicalData/editPhysicalData";
	}

	@ResponseBody
	@RequestMapping(value = "/edit", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public ResultMessage edit(@RequestBody PhysicalDataDTO physicalDataDTO){
		ResultMessage resultMessage = null;
		try {
			this.kindergartenWebService.updatePhysicalData(physicalDataDTO);
			resultMessage = new ResultMessage("ok", "编辑");
		} catch (ArgsException e) {
			e.printStackTrace();
			resultMessage = new ResultMessage("编辑", e.getCode(), e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			resultMessage = new ResultMessage("编辑", FailureCode.ERR_001);
		}
		return resultMessage;
	}

	@ResponseBody
	@RequestMapping(value = "/add", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public ResultMessage add(@RequestBody PhysicalDataDTO physicalDataDTO){
		ResultMessage resultMessage = null;
		try {
			this.kindergartenWebService.savePhysicalData(physicalDataDTO);
			resultMessage = new ResultMessage("ok", "添加");
		} catch (ArgsException e) {
			e.printStackTrace();
			resultMessage = new ResultMessage("添加", e.getCode(), e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			resultMessage = new ResultMessage("添加", FailureCode.ERR_001);
		}
		return resultMessage;
	}

	@ResponseBody
	@RequestMapping(value = "/enabled", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public ResultMessage enabled(@RequestParam String ids){
		ResultMessage resultMessage = null;
		try {
			this.kindergartenWebService.enabledPhysicalData(ids);
			resultMessage = new ResultMessage("ok", "启用");
		} catch (ArgsException e) {
			e.printStackTrace();
			resultMessage = new ResultMessage("启用", e.getCode(), e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			resultMessage = new ResultMessage("启用", FailureCode.ERR_001);
		}
		return resultMessage;
	}

	@ResponseBody
	@RequestMapping(value = "/disabled", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public ResultMessage disabled(@RequestParam String ids){
		ResultMessage resultMessage = null;
		try {
			this.kindergartenWebService.disabledPhysicalData(ids);
			resultMessage = new ResultMessage("ok", "禁用");
		} catch (ArgsException e) {
			e.printStackTrace();
			resultMessage = new ResultMessage("禁用", e.getCode(), e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			resultMessage = new ResultMessage("禁用", FailureCode.ERR_001);
		}
		return resultMessage;
	}

	@ResponseBody
	@RequestMapping(value = "/delete", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public ResultMessage delete(@RequestParam String ids){
		ResultMessage resultMessage = null;
		try {
			this.kindergartenWebService.deletePhysicalData(ids);
			resultMessage = new ResultMessage("ok", "删除");
		} catch (ArgsException e) {
			e.printStackTrace();
			resultMessage = new ResultMessage("删除", e.getCode(), e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			resultMessage = new ResultMessage("删除", FailureCode.ERR_001);
		}
		return resultMessage;
	}

	@RequestMapping(value = "/exportModel", method = RequestMethod.POST)
	public void exportModel(HttpServletRequest request, HttpServletResponse response) {
		OutputStream os = null;
		try {
			os = response.getOutputStream();
			response.setContentType("application/vnd.ms-excel");
			String filename = "幼儿体测数据.xlsx";
			filename = URLEncoder.encode(filename, "UTF-8");
			response.setHeader("Content-Disposition", "attachment; filename=" + filename);
			String[] titles = new String[] {"幼儿园","班级","幼儿姓名","体测时间(yyyy-MM-dd)","身高(cm)","体重(kg)","坐位体前屈(cm)","立定跳远(cm)","网球投掷(米)","双脚连续跳(秒)","10米折返跑(秒)","走平衡木(秒)","备注"};
			DataExportUtil.createXlsxExcelFile2(os, "幼儿体测数据", titles, null);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (null != os) {
				try {
					os.close();
					os = null;
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	/**
	 * @author liuhua
	 * 1.导出的文件：只有一所幼儿园为一个Excel文件，多个幼儿园为一个zip文件
	 * 2.一个学校一个Excel文件
	 * 3.一个班级二个sheet，分（男女）
	 *	
	 * @param request
	 * @param response
	 * @param physicalDataDTO
	 */
	@RequestMapping(value = "/batchExport", method = RequestMethod.POST)
	public void batchExport(HttpServletRequest request, HttpServletResponse response, PhysicalDataDTO physicalDataDTO) {
//		try {
//			Date today = new Date();
//			String path = DataExportUtil.createTempDirOnServer(request);
//	        List<File> srcfile = new ArrayList<File>();
//	        String filename = "幼儿体测数据列表_" + ParamHandler.getDateStringOfNow("yyyy-MM-dd HH:mm") + "_(共" + 1 + "个Excel文件).zip";
//	        String[] titles = new String[] {"用户id","服务商名称","服务商账号","手机号码","所在地址","所属销售","拉新地址","开始时间","结束时间","状态"};
//			int pageNo = 1;
//			for (; pageNo <= pages; pageNo++) {
//				String xlsName = "服务商列表_第" + pageNo + "个文件.xlsx";
//				List<String[]> datas = new ArrayList<String[]>();
//				if (null != result && null != result.getRows()) {
//					List<AreaPartnerVO> list = result.getRows();
//					for (AreaPartnerVO vo : list) {
//						if (null == vo) {
//							continue;
//						}
//						String[] str = new String[titles.length];
//						str[0] = vo.getHnUserId().toString();
//						str[1] = vo.getUserName();
//						str[2] = vo.getHnUserAccount();
//						str[3] = vo.getMobilePhone();
//						str[4] = vo.getFullAddress();
//						str[5] = vo.getBelongToSeller();
//						str[6] = vo.getRegisterUrl();
//						if (null == vo.getActiveTime()) {
//							str[7] = "";
//						}else{
//							str[7] = sdf.format(vo.getActiveTime());
//						}
//						if (null == vo.getEndTime()) {
//							str[8] = "";
//						}else{
//							str[8] = sdf.format(vo.getEndTime());
//						}
//						str[9] = statuses.get(vo.getPartnerStatus());
//						datas.add(str);
//					}
//					String filePath = path + File.separator + xlsName;
//					File xlsFile = new File(filePath);
//					FileOutputStream fileOutputStream = new FileOutputStream(xlsFile);
//					DataExportUtil.createXlsxExcelFile2(fileOutputStream, "服务商列表", titles, datas);
//					srcfile.add(xlsFile);
//				}
//			}
//			DataExportUtil.downZipFile(response, filename, srcfile);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
	}
}