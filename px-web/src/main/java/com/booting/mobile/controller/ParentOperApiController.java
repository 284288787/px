/**create by liuhua at 2018年1月31日 下午3:29:54**/
package com.booting.mobile.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.booting.kindergarten.dto.ParentDTO;
import com.booting.kindergarten.dto.StudentDTO;
import com.booting.service.impl.KindergartenWebService;
import com.star.framework.specification.result.v2.ApiResult;
import com.star.framework.specification.utils.ParamHandler;
import com.star.framework.version.InterfaceVersion;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import springfox.documentation.annotations.ApiIgnore;

@RestController
@RequestMapping("/api")
@Api(value = "长辈操作", description = "长辈操作")
public class ParentOperApiController extends ApiBaseController{

	@Autowired
	private KindergartenWebService kindergartenWebService;
	
	@InterfaceVersion("1.0")
	@RequestMapping(value = "/{version}/allChildrenOfMine", method = {RequestMethod.POST, RequestMethod.GET}, produces = "text/html;charset=UTF-8")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "openId", value = "微信唯一标识", paramType = "query", required = true, dataType = "String"),
	})
	@ApiOperation(value = "所有可被邀请家长的孩子列表", notes = "所有可被邀请家长的孩子列表", httpMethod = "POST", response = String.class, produces = "text/html;charset=UTF-8")
	public String allChildrenOfMine(@ApiIgnore String params) throws Exception {
		ParamHandler paramHandler = new ParamHandler(params);
		String openId = paramHandler.getString("openId");
		List<StudentDTO> list = this.kindergartenWebService.allChildrenOfMine(openId);
		ApiResult apiResult = new ApiResult(list);
		return ParamHandler.objToString(apiResult);
	}
	
	@InterfaceVersion("1.0")
	@RequestMapping(value = "/{version}/allChildren", method = {RequestMethod.POST, RequestMethod.GET}, produces = "text/html;charset=UTF-8")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "openId", value = "微信唯一标识", paramType = "query", required = true, dataType = "String"),
	})
	@ApiOperation(value = "所有和我建立了关系的孩子列表", notes = "所有和我建立了关系的孩子列表", httpMethod = "POST", response = String.class, produces = "text/html;charset=UTF-8")
	public String allChildren(@ApiIgnore String params) throws Exception {
		ParamHandler paramHandler = new ParamHandler(params);
		String openId = paramHandler.getString("openId");
		List<Map<String, Object>> list = this.kindergartenWebService.allChildren(openId);
		ApiResult apiResult = new ApiResult(list);
		return ParamHandler.objToString(apiResult);
	}
	
	@InterfaceVersion("1.0")
	@RequestMapping(value = "/{version}/bindChild", method = {RequestMethod.POST, RequestMethod.GET}, produces = "text/html;charset=UTF-8")
	@ApiImplicitParams({
	  @ApiImplicitParam(name = "openId", value = "微信唯一标识", paramType = "query", required = true, dataType = "String"),
	  @ApiImplicitParam(name = "studentId", value = "学生ID", paramType = "query", required = true, dataType = "Long"),
	  @ApiImplicitParam(name = "studentName", value = "学生姓名", paramType = "query", required = true, dataType = "Long"),
	  @ApiImplicitParam(name = "studentBirth", value = "学生生日 yyyy-MM-dd", paramType = "query", required = true, dataType = "Long"),
	  @ApiImplicitParam(name = "name", value = "家长姓名", paramType = "query", required = true, dataType = "Long"),
	  @ApiImplicitParam(name = "type", value = "被邀请人与孩子的关系 1:父亲,2:母亲,3:爷爷,4:奶奶,5:外公,6:外婆,7:其他亲人", paramType = "query", required = true, dataType = "int", allowableValues = "1,2,3,4,5,6,7"),
	})
	@ApiOperation(value = "绑定孩子", notes = "绑定孩子", httpMethod = "POST", response = String.class, produces = "text/html;charset=UTF-8")
	public String bindChild(@ApiIgnore String params) throws Exception {
	  ParamHandler paramHandler = new ParamHandler(params);
	  ParentDTO parentDTO = paramHandler.getDTO(ParentDTO.class);
	  this.kindergartenWebService.bindChild(parentDTO);
	  return null;
	}
	
	@InterfaceVersion("1.0")
	@RequestMapping(value = "/{version}/bindBracelet", method = {RequestMethod.POST, RequestMethod.GET}, produces = "text/html;charset=UTF-8")
	@ApiImplicitParams({
	  @ApiImplicitParam(name = "studentId", value = "学生ID", paramType = "query", required = true, dataType = "Long"),
	  @ApiImplicitParam(name = "studentName", value = "学生姓名", paramType = "query", required = true, dataType = "string"),
	  @ApiImplicitParam(name = "mac", value = "手环mac", paramType = "query", required = true, dataType = "string"),
	})
	@ApiOperation(value = "绑定手环", notes = "绑定手环", httpMethod = "POST", response = String.class, produces = "text/html;charset=UTF-8")
	public String bindBracelet(@ApiIgnore String params) throws Exception {
	  ParamHandler paramHandler = new ParamHandler(params);
	  Long studentId = paramHandler.getLong("studentId");
	  String studentName = paramHandler.getString("studentName");
	  String mac = paramHandler.getString("mac");
	  this.kindergartenWebService.bindBracelet(studentId, studentName, mac);
	  return null;
	}
	
	@InterfaceVersion("1.0")
	@RequestMapping(value = "/{version}/unbindBracelet", method = {RequestMethod.POST, RequestMethod.GET}, produces = "text/html;charset=UTF-8")
	@ApiImplicitParams({
	  @ApiImplicitParam(name = "studentId", value = "学生ID", paramType = "query", required = true, dataType = "Long"),
	  @ApiImplicitParam(name = "studentName", value = "学生姓名", paramType = "query", required = true, dataType = "string"),
	})
	@ApiOperation(value = "解除绑定手环", notes = "解除绑定手环", httpMethod = "POST", response = String.class, produces = "text/html;charset=UTF-8")
	public String unbindBracelet(@ApiIgnore String params) throws Exception {
	  ParamHandler paramHandler = new ParamHandler(params);
	  Long studentId = paramHandler.getLong("studentId");
	  String studentName = paramHandler.getString("studentName");
	  this.kindergartenWebService.unbindBracelet(studentId, studentName);
	  return null;
	}
	
	@InterfaceVersion("1.0")
	@RequestMapping(value = "/{version}/bindingStudents", method = {RequestMethod.POST, RequestMethod.GET}, produces = "text/html;charset=UTF-8")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "studentId", value = "学生ID", paramType = "query", required = true, dataType = "Long"),
		@ApiImplicitParam(name = "studentName", value = "学生姓名", paramType = "query", required = true, dataType = "string"),
		@ApiImplicitParam(name = "braceletMac", value = "手环mac", paramType = "query", required = true, dataType = "string"),
		@ApiImplicitParam(name = "pageNo", value = "页码 默认1", paramType = "query", required = true, dataType = "int"),
	    @ApiImplicitParam(name = "pageSize", value = "默认10", paramType = "query", required = true, dataType = "int"),
	})
	@ApiOperation(value = "绑定手环的学生列表", notes = "绑定手环的学生列表", httpMethod = "POST", response = String.class, produces = "text/html;charset=UTF-8")
	public String bindingStudents(@ApiIgnore String params) throws Exception {
		ParamHandler paramHandler = new ParamHandler(params);
		StudentDTO student = paramHandler.getDTO(StudentDTO.class);
        Integer pageNo = paramHandler.getInteger("pageNo");
        Integer pageSize = paramHandler.getInteger("pageSize");
        ApiResult apiResult = this.kindergartenWebService.bindingStudents(student, pageNo, pageSize);
		return ParamHandler.objToString(apiResult);
	}
	
	@InterfaceVersion("1.0")
	@RequestMapping(value = "/{version}/invitePatriarch", method = {RequestMethod.POST, RequestMethod.GET}, produces = "text/html;charset=UTF-8")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "openId", value = "微信唯一标识", paramType = "query", required = true, dataType = "String"),
		@ApiImplicitParam(name = "studentId", value = "学生ID", paramType = "query", required = true, dataType = "Long"),
		@ApiImplicitParam(name = "mobile", value = "被邀请人手机号", paramType = "query", required = true, dataType = "String"),
		@ApiImplicitParam(name = "type", value = "被邀请人与孩子的关系 1:父亲,2:母亲,3:爷爷,4:奶奶,5:外公,6:外婆,7:其他亲人", paramType = "query", required = true, dataType = "int", allowableValues = "1,2,3,4,5,6,7"),
	})
	@ApiOperation(value = "邀请家长", notes = "邀请家长", httpMethod = "POST", response = String.class, produces = "text/html;charset=UTF-8")
	public String invitePatriarch(@ApiIgnore String params) throws Exception {
		ParamHandler paramHandler = new ParamHandler(params);
		ParentDTO parentDTO = paramHandler.getDTO(ParentDTO.class);
		this.kindergartenWebService.invitePatriarch(parentDTO);
		return null;
	}
	
	@InterfaceVersion("1.0")
	@RequestMapping(value = "/{version}/inviteeList", method = {RequestMethod.POST, RequestMethod.GET}, produces = "text/html;charset=UTF-8")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "openId", value = "微信唯一标识", paramType = "query", required = true, dataType = "String"),
		@ApiImplicitParam(name = "studentId", value = "学生id", paramType = "query", required = true, dataType = "long"),
	})
	@ApiOperation(value = "被邀请家长列表", notes = "被邀请家长列表", httpMethod = "POST", response = String.class, produces = "text/html;charset=UTF-8")
	public String inviteeList(@ApiIgnore String params) throws Exception {
		ParamHandler paramHandler = new ParamHandler(params);
		String openId = paramHandler.getString("openId");
		Long studentId = paramHandler.getLong("studentId");
		List<Map<String, Object>> list = this.kindergartenWebService.inviteeList(openId, studentId);
		ApiResult apiResult = new ApiResult(list);
		return ParamHandler.objToString(apiResult);
	}
	
	@InterfaceVersion("1.0")
	@RequestMapping(value = "/{version}/deletePatriarch", method = {RequestMethod.POST, RequestMethod.GET}, produces = "text/html;charset=UTF-8")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "openId", value = "微信唯一标识", paramType = "query", required = true, dataType = "String"),
		@ApiImplicitParam(name = "studentId", value = "学生id", paramType = "query", required = true, dataType = "long"),
		@ApiImplicitParam(name = "parentId", value = "被删除关系的家长id", paramType = "query", required = true, dataType = "long"),
	})
	@ApiOperation(value = "删除被邀请的家长", notes = "删除被邀请的家长", httpMethod = "POST", response = String.class, produces = "text/html;charset=UTF-8")
	public String deletePatriarch(@ApiIgnore String params) throws Exception {
		ParamHandler paramHandler = new ParamHandler(params);
		String openId = paramHandler.getString("openId");
		Long studentId = paramHandler.getLong("studentId");
		Long parentId = paramHandler.getLong("parentId");
		this.kindergartenWebService.deletePatriarch(openId, studentId, parentId);
		return null;
	}
	
	@InterfaceVersion("1.0")
	@RequestMapping(value = "/{version}/lookupBaseData", method = {RequestMethod.POST, RequestMethod.GET}, produces = "text/html;charset=UTF-8")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "openId", value = "微信唯一标识", paramType = "query", required = true, dataType = "String"),
		@ApiImplicitParam(name = "studentId", value = "学生ID", paramType = "query", required = true, dataType = "Long"),
		@ApiImplicitParam(name = "pageNo", value = "页码 默认为1，pageSize固定为10", paramType = "query", required = true, dataType = "int"),
		@ApiImplicitParam(name = "beginTime", value = "开始日期 yyyy-MM-dd", paramType = "query", required = false, dataType = "String"),
		@ApiImplicitParam(name = "endTime", value = "结束日期 yyyy-MM-dd", paramType = "query", required = false, dataType = "String"),
	})
	@ApiOperation(value = "获取学生的体智能总成绩数据", notes = "获取学生的体智能总成绩数据，pageNo的作用：例如某人做了100次体测，那么请求第一次接口返回10条记录，这10条记录展示完后，请求第二页的10条，直至返回的列表为空", httpMethod = "POST", response = String.class, produces = "text/html;charset=UTF-8")
	public String lookupData(@ApiIgnore String params) throws Exception {
		ParamHandler paramHandler = new ParamHandler(params);
		Integer pageNo = paramHandler.getInteger("pageNo");
		ParentDTO parentDTO = paramHandler.getDTO(ParentDTO.class);
		ApiResult apiResult = this.kindergartenWebService.lookupBaseData(pageNo, parentDTO);
		return ParamHandler.objToString(apiResult);
	}
	
}
