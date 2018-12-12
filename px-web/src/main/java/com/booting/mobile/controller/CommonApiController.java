/**create by liuhua at 2017年6月2日 上午9:17:24**/
package com.booting.mobile.controller;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.booting.common.CommonConstants.DocumentType;
import com.booting.common.PushUtil;
import com.booting.member.dto.MemberDTO;
import com.booting.pub.dto.DocumentDTO;
import com.booting.pub.dto.MessageDTO;
import com.booting.service.CommonWebService;
import com.star.framework.jdbc.dao.result.QueryParam;
import com.star.framework.specification.FailureCode;
import com.star.framework.specification.exception.ArgsException;
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
@Api(value = "公共接口", description = "公共接口")
public class CommonApiController extends ApiBaseController{

	@Autowired
	private CommonWebService commonWebService;
	
	@InterfaceVersion("1.0")
	@RequestMapping(value = "/{version}/testPush", method = {RequestMethod.POST, RequestMethod.GET}, produces = "text/html;charset=UTF-8")
	@ApiOperation(value = "1", notes = "123", httpMethod = "POST", response = String.class, produces = "text/html;charset=UTF-8")
	public String testPush(@ApiIgnore String params) throws Exception{
		PushUtil.testPush();
		return null;
	}
	
	@InterfaceVersion("1.0")
	@RequestMapping(value = "/{version}/latestVersion", method = {RequestMethod.POST, RequestMethod.GET}, produces = "text/html;charset=UTF-8")
	@ApiOperation(value = "检查APP的最新版", notes = "判断方法：<br>根据type去数据库找到相关的最新记录，然后用记录的版本号和version做比较，如果没找到记录或一样，则表示没有更新；否则当前记录为最新版", httpMethod = "POST", response = String.class, produces = "text/html;charset=UTF-8")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "type", value = "APP类型(1 android 2 ios)", paramType = "query", required = true, dataType = "Integer", allowableValues="1,2"),
		@ApiImplicitParam(name = "version", value = "当前已装APP版本号", paramType = "query", required = true, dataType = "String")
	})
	public String latestVersion(@ApiIgnore String params) throws Exception{
		ParamHandler paramHandler = new ParamHandler(params);
		Integer type = paramHandler.getInteger("type");
		String version = paramHandler.getString("version");
		Map<String, Object> data = this.commonWebService.latestVersion(type, version);
		ApiResult apiResult = new ApiResult(data);
		return ParamHandler.objToString(apiResult);
	}
	
	@InterfaceVersion("1.0")
	@RequestMapping(value = "/{version}/feedback", method = {RequestMethod.POST, RequestMethod.GET}, produces = "text/html;charset=UTF-8")
	@ApiOperation(value = "增加一个意见与反馈", notes = "意见与反馈", httpMethod = "POST", response = String.class, produces = "text/html;charset=UTF-8")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "title", value = "标题", paramType = "query", required = false, dataType = "String"),
		@ApiImplicitParam(name = "contact", value = "联系方式 字数不得超过50", paramType = "query", required = false, dataType = "String"),
		@ApiImplicitParam(name = "content", value = "反馈内容 字数不得超过160", paramType = "query", required = true, dataType = "String")
	})
	public String feedback(@ApiIgnore String params) throws Exception{
		ParamHandler paramHandler = new ParamHandler(params);
		String title = paramHandler.getString("title");
		String contact = paramHandler.getString("contact");
		String content = paramHandler.getString("content");
		this.commonWebService.feedback(title, content, contact);
		return null;
	}
	
	@InterfaceVersion("1.0")
	@RequestMapping(value = "/{version}/banner", method = {RequestMethod.POST, RequestMethod.GET}, produces = "text/html;charset=UTF-8")
	@ApiOperation(value = "获取一组轮播图", notes = "获取一组轮播图", httpMethod = "POST", response = String.class, produces = "text/html;charset=UTF-8")
	public String banner() throws Exception{
		List<Map<String, String>> banner = this.commonWebService.banner();
		ApiResult apiResult = new ApiResult(banner);
		return ParamHandler.objToString(apiResult);
	}
	
	@InterfaceVersion("1.0")
	@RequestMapping(value = "/{version}/articleList", method = {RequestMethod.POST, RequestMethod.GET}, produces = "text/html;charset=UTF-8")
	@ApiOperation(value = "获取资讯列表", notes = "获取资讯列表 <br> position(位置 1正常 2置顶)", httpMethod = "POST", response = String.class, produces = "text/html;charset=UTF-8")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "pageNo", value = "当前页面 默认 1", paramType = "query", required = true, dataType = "Integer"),
		@ApiImplicitParam(name = "pageSize", value = "一页最大记录数 默认10", paramType = "query", required = false, dataType = "Integer")
	})
	public String articleList(@ApiIgnore String params) throws Exception{
		ParamHandler paramHandler = new ParamHandler(params);
		Integer pageNo = paramHandler.getInteger("pageNo");
		Integer pageSize = paramHandler.getInteger("pageSize");
		QueryParam queryParam = new QueryParam();
		if (null != pageNo) {
			queryParam.setPageNo(pageNo);
		}
		if (null != pageSize) {
			queryParam.setPageSize(pageSize);
		}
		DocumentDTO documentDTO = new DocumentDTO();
		documentDTO.setType(DocumentType.article.getType());
		documentDTO.setEnabled(1);
		queryParam.setParam(documentDTO);
		ApiResult apiResult = this.commonWebService.articleList(queryParam);
		return ParamHandler.objToString(apiResult);
	}
	
	@InterfaceVersion("1.0")
	@RequestMapping(value = "/{version}/refresh", method = {RequestMethod.POST, RequestMethod.GET}, produces = "text/html;charset=UTF-8")
	@ApiOperation(value = "刷新获取最新资讯", notes = "刷新获取最新资讯 ", httpMethod = "POST", response = String.class, produces = "text/html;charset=UTF-8")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "time", value = "已获取列表的最新的时间，格式：yyyy-MM-dd HH:mm:ss", paramType = "query", required = true, dataType = "String"),
		@ApiImplicitParam(name = "pageSize", value = "获取一次的最大记录数 默认10", paramType = "query", required = false, dataType = "Integer")
	})
	public String refresh(@ApiIgnore String params) throws Exception{
		ParamHandler paramHandler = new ParamHandler(params);
		String time = paramHandler.getString("time");
		Integer pageSize = paramHandler.getInteger("pageSize");
		QueryParam queryParam = new QueryParam();
		if (null != pageSize) {
			queryParam.setPageSize(pageSize);
		}
		DocumentDTO documentDTO = new DocumentDTO();
		documentDTO.setType(DocumentType.article.getType());
		documentDTO.setEnabled(1);
		if (StringUtils.isNotBlank(time)) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			documentDTO.setModifyTime(sdf.parse(time));
		}
		queryParam.setParam(documentDTO);
		ApiResult apiResult = this.commonWebService.articleList(queryParam);
		return ParamHandler.objToString(apiResult);
	}
	
	@InterfaceVersion("1.0")
	@RequestMapping(value = "/{version}/messages", method = {RequestMethod.POST, RequestMethod.GET}, produces = "text/html;charset=UTF-8")
	@ApiOperation(value = "消息列表", notes = "消息列表", httpMethod = "POST", response = String.class, produces = "text/html;charset=UTF-8")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "openId", value = "登录标识", paramType = "query", required = true, dataType = "String"),
		@ApiImplicitParam(name = "type", value = "消息类型(1系统 2约球 3球队)", paramType = "query", required = true, dataType = "Integer", allowableValues = "1,2,3"),
		@ApiImplicitParam(name = "pageNo", value = "当前页面 默认 1", paramType = "query", required = true, dataType = "Integer"),
		@ApiImplicitParam(name = "time", value = "已获取列表的最新的时间，格式：yyyy-MM-dd HH:mm:ss", paramType = "query", required = true, dataType = "String"),
		@ApiImplicitParam(name = "pageSize", value = "一页最大记录数 默认10", paramType = "query", required = false, dataType = "Integer")
	})
	public String messages(@ApiIgnore String params) throws Exception{
		ParamHandler paramHandler = new ParamHandler(params);
		Long userId = getLoginUserId(paramHandler);
		Integer type = paramHandler.getInteger("type");
		String time = paramHandler.getString("time");
		if (null == type) {
			throw new ArgsException(FailureCode.ERR_002);
		}
		Integer pageNo = paramHandler.getInteger("pageNo");
		Integer pageSize = paramHandler.getInteger("pageSize");
		QueryParam queryParam = new QueryParam();
		if (null != pageNo) {
			queryParam.setPageNo(pageNo);
		}
		if (null != pageSize) {
			queryParam.setPageSize(pageSize);
		}
		MessageDTO messageDTO = new MessageDTO();
		messageDTO.setMessageType(type);
		messageDTO.setUserId(userId);
		if (StringUtils.isNotBlank(time)) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			messageDTO.setCreateTime(sdf.parse(time));
		}
		queryParam.setParam(messageDTO);
		ApiResult apiResult = this.commonWebService.messageList(queryParam);
		return ParamHandler.objToString(apiResult);
	}
	
	@InterfaceVersion("1.0")
	@RequestMapping(value = "/{version}/messageDetail", method = {RequestMethod.POST, RequestMethod.GET}, produces = "text/html;charset=UTF-8")
	@ApiOperation(value = "获取消息详情", notes = "获取消息详情 ", httpMethod = "POST", response = String.class, produces = "text/html;charset=UTF-8")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "openId", value = "登录标识", paramType = "query", required = true, dataType = "String"),
		@ApiImplicitParam(name = "messageId", value = "消息Id", paramType = "query", required = true, dataType = "Long")
	})
	public String messageDetail(@ApiIgnore String params) throws Exception{
		ParamHandler paramHandler = new ParamHandler(params);
		Long loginUserId = getLoginUserId(paramHandler);
		Long messageId = paramHandler.getLong("messageId");
		Map<String, Object> message = this.commonWebService.message(loginUserId, messageId);
		ApiResult apiResult = new ApiResult(message);
		return ParamHandler.objToString(apiResult);
	}
	
	@InterfaceVersion("1.0")
	@RequestMapping(value = "/{version}/delMessage", method = {RequestMethod.POST, RequestMethod.GET}, produces = "text/html;charset=UTF-8")
	@ApiOperation(value = "删除消息", notes = "删除消息 ", httpMethod = "POST", response = String.class, produces = "text/html;charset=UTF-8")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "openId", value = "登录标识", paramType = "query", required = true, dataType = "String"),
		@ApiImplicitParam(name = "messageIds", value = "消息Ids,多个消息ID用英文逗号分隔", paramType = "query", required = true, dataType = "String")
	})
	public String delMessage(@ApiIgnore String params) throws Exception{
		ParamHandler paramHandler = new ParamHandler(params);
		Long loginUserId = getLoginUserId(paramHandler);
		String messageIds = paramHandler.getString("messageIds");
		this.commonWebService.delMessage(loginUserId, messageIds);
		return null;
	}
	
	@InterfaceVersion("1.0")
	@RequestMapping(value = "/{version}/mineMessage", method = {RequestMethod.POST, RequestMethod.GET}, produces = "text/html;charset=UTF-8")
	@ApiOperation(value = "我的消息", notes = "获取每个类型的消息最新一条及未读总数 ", httpMethod = "POST", response = String.class, produces = "text/html;charset=UTF-8")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "openId", value = "登录标识", paramType = "query", required = true, dataType = "String"),
	})
	public String mineMessage(@ApiIgnore String params) throws Exception{
		ParamHandler paramHandler = new ParamHandler(params);
		Long loginUserId = getLoginUserId(paramHandler);
		Map<String, Object> data = this.commonWebService.mineMessage(loginUserId);
		ApiResult apiResult = new ApiResult(data);
		return ParamHandler.objToString(apiResult);
	}
	
	@InterfaceVersion("1.0")
	@RequestMapping(value = "/{version}/article", method = {RequestMethod.POST, RequestMethod.GET}, produces = "text/html;charset=UTF-8")
	@ApiOperation(value = "获取资讯", notes = "获取资讯 ", httpMethod = "POST", response = String.class, produces = "text/html;charset=UTF-8")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "artId", value = "文章Id", paramType = "query", required = true, dataType = "Long")
	})
	public String article(@ApiIgnore String params) throws Exception{
		ParamHandler paramHandler = new ParamHandler(params);
		Long artId = paramHandler.getLong("artId");
		Map<String, Object> article = this.commonWebService.article(artId);
		ApiResult apiResult = new ApiResult(article);
		return ParamHandler.objToString(apiResult);
	}

	@InterfaceVersion("1.0")
	@RequestMapping(value = "/{version}/sendSms", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	@ApiOperation(value = "发送短信", notes = "发送短信", httpMethod = "POST", response = String.class, produces = "text/html;charset=UTF-8")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "mobile", value = "帐号(手机号)", paramType = "query", required = true, dataType = "String"),
		@ApiImplicitParam(name = "tag", value = "标识(1绑定手机 2培训项目报名)", paramType = "query", required = true, dataType = "Integer")
	})
	public String sendSms(@ApiIgnore String params) throws Exception{
		ParamHandler paramHandler = new ParamHandler(params);
		String mobile = paramHandler.getString("mobile");
		Integer tag = paramHandler.getInteger("tag");
		this.commonWebService.sendSms(mobile, tag);
		return null;
	}
	
	@InterfaceVersion("1.0")
	@RequestMapping(value = "/{version}/verifySms", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	@ApiOperation(value = "校验短信", notes = "校验短信", httpMethod = "POST", response = String.class, produces = "text/html;charset=UTF-8")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "mobile", value = "帐号(手机号)", paramType = "query", required = true, dataType = "String"),
		@ApiImplicitParam(name = "code", value = "验证码", paramType = "query", required = true, dataType = "String"),
		@ApiImplicitParam(name = "tag", value = "标识(1绑定手机 2培训项目报名 3教练登录)", paramType = "query", required = true, dataType = "Integer")
	})
	public String verifySms(@ApiIgnore String params) throws Exception{
		ParamHandler paramHandler = new ParamHandler(params);
		String mobile = paramHandler.getString("mobile");
		String code = paramHandler.getString("code");
		Integer tag = paramHandler.getInteger("tag");
		this.commonWebService.verifySms(mobile, code, tag);
		return null;
	}
	
	@InterfaceVersion("1.0")
	@RequestMapping(value = "/{version}/refreshJson", method = {RequestMethod.POST, RequestMethod.GET}, produces = "text/html;charset=UTF-8")
	@ApiOperation(value = "刷新资源Json", notes = "刷新资源Json", httpMethod = "POST", response = String.class, produces = "text/html;charset=UTF-8")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "tag", value = "1area 2business", paramType = "query", required = true, dataType = "Integer", allowableValues = "1,2")
	})
	public String refreshJson(@ApiIgnore String params) throws Exception{
		ParamHandler paramHandler = new ParamHandler(params);
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();  
		Integer tag = paramHandler.getInteger("tag");
		String realPath = request.getServletContext().getRealPath("/static/js/");
		ParamHandler.staticPath = realPath;
		this.commonWebService.refreshJson(tag);
		return null;
	}
	
	@InterfaceVersion("1.0")
	@RequestMapping(value = "/{version}/bindMobile", method = {RequestMethod.POST, RequestMethod.GET}, produces = "text/html;charset=UTF-8")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "mobile", value = "电话", paramType = "query", required = true, dataType = "String"),
		@ApiImplicitParam(name = "openId", value = "微信唯一标识", paramType = "query", required = true, dataType = "String"),
		@ApiImplicitParam(name = "tag", value = "标识 1绑定手机", paramType = "query", required = true, dataType = "String"),
	})
	@ApiOperation(value = "绑定手机", notes = "绑定手机", httpMethod = "POST", response = String.class, produces = "text/html;charset=UTF-8")
	public String bindMobile(@ApiIgnore String params) throws Exception {
		ParamHandler paramHandler = new ParamHandler(params);
		MemberDTO memberDTO = paramHandler.getDTO(MemberDTO.class);
		commonWebService.bindMobile(memberDTO);
		return null;
	}
	
	@InterfaceVersion("1.0")
	@RequestMapping(value = "/{version}/getMemberInfo", method = {RequestMethod.POST, RequestMethod.GET}, produces = "text/html;charset=UTF-8")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "mobile", value = "电话", paramType = "query", required = false, dataType = "String"),
		@ApiImplicitParam(name = "openId", value = "微信唯一标识", paramType = "query", required = false, dataType = "String"),
	})
	@ApiOperation(value = "获取用户信息", notes = "获取用户信息", httpMethod = "POST", response = String.class, produces = "text/html;charset=UTF-8")
	public String getMemberInfo(@ApiIgnore String params) throws Exception {
		ParamHandler paramHandler = new ParamHandler(params);
		MemberDTO memberDTO = paramHandler.getDTO(MemberDTO.class);
		MemberDTO info = commonWebService.getMemberInfo(memberDTO);
		ApiResult apiResult = new ApiResult(info);
		return ParamHandler.objToString(apiResult);
	}
}
