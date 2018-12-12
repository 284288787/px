/**create by liuhua at 2017年6月2日 上午9:35:54**/
package com.booting.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.booting.common.CommonConstants;
import com.booting.common.CommonConstants.DocumentType;
import com.booting.common.CommonConstants.MessageType;
import com.booting.common.CommonConstants.SmsTag;
import com.booting.common.PushInfo;
import com.booting.common.PushUtil;
import com.booting.common.SmsUtil;
import com.booting.member.dto.MemberDTO;
import com.booting.member.facade.MemberFacade;
import com.booting.pub.dto.AppVersionDTO;
import com.booting.pub.dto.AreaDTO;
import com.booting.pub.dto.BusinessDTO;
import com.booting.pub.dto.DocumentDTO;
import com.booting.pub.dto.FeedbackDTO;
import com.booting.pub.dto.MessageDTO;
import com.booting.pub.dto.MessageReadDTO;
import com.booting.pub.dto.SmsIdentityDTO;
import com.booting.pub.entity.SmsIdentityEntity;
import com.booting.pub.facade.CommonFacade;
import com.booting.pub.service.SmsIdentityService;
import com.booting.service.CommonWebService;
import com.booting.system.dto.UserAccountDTO;
import com.booting.system.facade.SystemFacade;
import com.fasterxml.jackson.core.type.TypeReference;
import com.star.framework.importdata.AbstractDataImport;
import com.star.framework.importdata.ImportResult;
import com.star.framework.jdbc.dao.result.PageList;
import com.star.framework.jdbc.dao.result.QueryParam;
import com.star.framework.specification.FailureCode;
import com.star.framework.specification.exception.ArgsException;
import com.star.framework.specification.result.PageInfo;
import com.star.framework.specification.result.v2.ApiResult;
import com.star.framework.specification.utils.ParamHandler;
import com.star.framework.utils.CglibBeanUtils;
import com.star.framework.utils.ClassUtils;
import com.star.framework.utils.MemoryCacheUtil;
import com.star.framework.utils.MemoryCacheUtil.CacheType;
import com.star.framework.utils.SomeUtils;

@Service("commonWebService")
public class CommonWebServiceImpl implements CommonWebService {

	@Autowired
	private SmsIdentityService smsIdentityService;
	@Autowired
	private SystemFacade systemFacade;
	@Autowired
	private CommonFacade commonFacade;
	@Autowired
	private MemberFacade memberFacade;
	
	@Override
	public void sendSms(String mobile, Integer tag, String yzm) throws ArgsException {
		if (StringUtils.isBlank(mobile) || StringUtils.isBlank(yzm) || null == tag) {
			throw new ArgsException(FailureCode.ERR_002);
		}
		String scode = MemoryCacheUtil.get(CacheType.reg_pic_code, mobile, new TypeReference<String>() {});
		if (null == scode || ! scode.toLowerCase().equals(yzm.toLowerCase())) {
			throw new ArgsException(FailureCode.ERR_018);
		}
		if (tag == SmsTag.registration.getTag()) {
			checkUsername(mobile);
		}
		SmsIdentityDTO param = new SmsIdentityDTO();
		param.setPhone(mobile);
		param.setTag(tag);
		List<SmsIdentityDTO> list = this.smsIdentityService.getSimpleList(param);
		int len = 0;
		if (null != list && ! list.isEmpty()) {
			len = list.size();
			for (SmsIdentityDTO smsIdentityDTO : list) {
				if (! smsIdentityDTO.isValid()) {
					smsIdentityService.delete(smsIdentityDTO.getId());
					len --;
				}
			}
		}
		if (len == 0) {
			//调用接口发短信
			PushInfo pushInfo = CommonConstants.smsNotes.get("code_sms");
			String code = "1234"; //SmsUtil.sendSms(mobile, pushInfo);
			SmsIdentityEntity entity = new SmsIdentityEntity();
			entity.setCode(code);
			entity.setContent(code + "为本次短信验证码，清及时使用！");
			entity.setCreateTime(new Date());
			entity.setPhone(mobile);
			entity.setTag(tag);
			smsIdentityService.save(entity);
		}else{
			throw new ArgsException(FailureCode.ERR_700);
		}
	}

	@Override
	public void verifySms(String mobile, String code, Integer tag) throws ArgsException {
		if (StringUtils.isBlank(mobile) || null == tag) {
			throw new ArgsException(FailureCode.ERR_002);
		}
		SmsIdentityDTO dto = new SmsIdentityDTO();
		dto.setPhone(mobile);
		dto.setTag(tag);
		dto = smsIdentityService.get(dto);
		if (null == dto || ! code.equals(dto.getCode())) {
			throw new ArgsException("102", "验证码错误");
		}
	}

	public void checkUsername(String username) throws ArgsException {
		UserAccountDTO userAccountDTO = new UserAccountDTO();
		userAccountDTO.setAccount(username);
		UserAccountDTO user = systemFacade.getUserAccount(userAccountDTO);
		if (null != user && null != user.getAccount()) {
			throw new ArgsException("101", "手机号已被注册");
		}
	}

	@Override
	public Map<String, Object> latestVersion(Integer type, String version) throws ArgsException {
		if (null == type || StringUtils.isBlank(version)) {
			throw new ArgsException(FailureCode.ERR_002);
		}
		AppVersionDTO appVersionDTO = new AppVersionDTO();
		appVersionDTO.setType(type);
		appVersionDTO.setStatus(1);
		AppVersionDTO appVersion = commonFacade.getAppVersion(appVersionDTO);
		if (null != appVersion && ! version.equals(appVersion.getVersion())) {
			Map<String, Object> data = new HashMap<>();
			data.put("version", appVersion.getVersion());
			data.put("url", appVersion.getUrl());
			data.put("upgrade", appVersion.getUpgrade());
			data.put("description", appVersion.getDescription());
			data.put("md5", appVersion.getMd5());
			return data;
		}
		return null;
	}

	@Override
	public void feedback(String title, String content, String contact) throws ArgsException {
		if (StringUtils.isBlank(content) || content.length() > 160) {
			throw new ArgsException(FailureCode.ERR_002);
		}
		if (StringUtils.isNotBlank(contact) && contact.length() > 50) {
			throw new ArgsException(FailureCode.ERR_002);
		}
		FeedbackDTO feedbackDTO = new FeedbackDTO();
		feedbackDTO.setContent(content);
		feedbackDTO.setContact(contact);
		feedbackDTO.setCreateTime(new Date());
		if (StringUtils.isNotBlank(title)) {
			feedbackDTO.setTitle(title);
		}
		commonFacade.saveFeedback(feedbackDTO);
	}

	@Override
	public List<Map<String, String>> banner() {
		DocumentDTO documentDTO = new DocumentDTO();
		documentDTO.setType(DocumentType.banner.getType());
		documentDTO.setEnabled(1);
		List<DocumentDTO> docs = this.commonFacade.getDocumentList(documentDTO);
		List<Map<String, String>> res = new ArrayList<>();
		if (null != docs && ! docs.isEmpty()) {
			for (DocumentDTO document : docs) {
				Map<String, String> rec = new HashMap<>();
				rec.put("logo", document.getLogo());
				rec.put("title", document.getTitle());
				rec.put("url", document.getContent());
				res.add(rec);
			}
			return res;
		}
		return null;
	}

	@Override
	public ApiResult articleList(QueryParam queryParam) {
		PageList<DocumentDTO> docs2 = this.commonFacade.getDocumentListForPage(queryParam);
		List<DocumentDTO> docs = docs2.getDataList();
		List<Map<String, Object>> res = new ArrayList<>();
		if (null != docs && ! docs.isEmpty()) {
			for (DocumentDTO document : docs) {
				Map<String, Object> rec = new HashMap<>();
				rec.put("artId", document.getDocId());
				rec.put("logo", document.getLogo());
				rec.put("title", document.getTitle());
				rec.put("position", document.getPosition());
				rec.put("time", document.getModifyTime());
				rec.put("intro", document.getIntro());
				res.add(rec);
			}
		}
		ApiResult apiResult = new ApiResult();
		apiResult.setData(res);
		PageInfo pageInfo = new PageInfo(docs2.getPageNo(), docs2.getPageSize(), docs2.getTotalRecord());
		apiResult.setPageInfo(pageInfo);
		return apiResult;
	}

	@Override
	public Map<String, Object> article(Long artId) throws ArgsException {
		if (null == artId) {
			throw new ArgsException(FailureCode.ERR_002);
		}
		DocumentDTO document = this.commonFacade.getDocument(artId);
		Map<String, Object> res = new HashMap<>();
		if (null != document && 1 == document.getEnabled()) {
			res.put("logo", document.getLogo());
			res.put("title", document.getTitle());
			res.put("time", document.getModifyTime());
			res.put("content", document.getContent());
		}
		return res;
	}

	public String createAreaJson() {
		AreaDTO areaDTO = new AreaDTO();
		areaDTO.setTypes("1,2,3");
		areaDTO.setStatus(1);
		List<AreaDTO> provinces = this.commonFacade.getAreaList(areaDTO);
		for (AreaDTO province : provinces) {
			AreaDTO cityDTO = new AreaDTO();
			cityDTO.setParentId(province.getAreaId());
			cityDTO.setStatus(1);
			List<AreaDTO> citys = this.commonFacade.getAreaList(cityDTO);
			province.setChildren(citys);
		}
		String areas = ParamHandler.gson.toJson(provinces);
		return areas;
	}

	public String createBusinessJson() {
		BusinessDTO businessDTO = new BusinessDTO();
		businessDTO.setEnabled(1);
		List<BusinessDTO> businesses = this.commonFacade.getBusinessList(businessDTO);
		return ParamHandler.gson.toJson(businesses);
	}

	@Override
	public void refreshJson(Integer tag) throws IOException {
		String data = null;
		String fileName = null;
		if (tag == 1) { //area
			data = "var areas=" + createAreaJson();
			fileName = "areadata.json";
		}else if (tag == 2) {
			data  = "var businesses=" + createBusinessJson();
			fileName = "businessdata.json";
		}
		System.out.println(data);
		if (null != data) {
			SomeUtils.createFile(ParamHandler.staticPath, fileName, data);
		}
	}

	@Override
	public void updateBusiness(BusinessDTO businessDTO) throws ArgsException, IOException {
		this.commonFacade.updateBusiness(businessDTO);
		refreshJson(2);
	}

	@Override
	public void enabledBusiness(String ids) throws ArgsException, IOException {
		this.commonFacade.enabledBusiness(ids);
		refreshJson(2);
	}

	@Override
	public void disabledBusiness(String ids) throws ArgsException, IOException {
		this.commonFacade.disabledBusiness(ids);
		refreshJson(2);
	}

	@Override
	public void saveBusiness(BusinessDTO businessDTO) throws ArgsException, IOException {
		this.commonFacade.saveBusiness(businessDTO);
		refreshJson(2);
	}

	@Override
	public void writeMessage(PushInfo pushInfo) {
		if (pushInfo.isSendMsg()) {
			Integer to = pushInfo.getTo();  //发送给 1单个人 2部分群发 3全部群发
			Long[] userIds = pushInfo.getUserId();
			Long userId = null;
			String userIdss = null;
			if (to.intValue() == 1) {
				userId = userIds[0];
			}else if (to.intValue() == 2) {
				userId = -2l;
				userIdss = "";
				for (int i = 0; i < userIds.length; i++) {
					userIdss += "," + userIds[i] + ",";
				}
			}else{
				userId = -1l;
			}
			MessageDTO messageDTO = new MessageDTO();
			messageDTO.setContent(pushInfo.getHtmlContent2());
			messageDTO.setCreateTime(new Date());
			messageDTO.setMessageType(pushInfo.getMessageType().getType());
			messageDTO.setStatus(1);
			messageDTO.setTitle(pushInfo.getTitle());
			messageDTO.setUserId(userId);
			messageDTO.setUserIds(userIdss);
			Long messageId = this.commonFacade.saveMessage(messageDTO);
			if (null == pushInfo.getObjectValue()) {
				pushInfo.setObjectValue(messageId);
			}
			String param = "{\"type\": \"" + pushInfo.getMethod() + "\", \"key\": \"" + pushInfo.getKey() + "\", value: \"" + pushInfo.getObjectValue() + "\"}";
			PushUtil.pushNote(pushInfo.getSourceFrom(), pushInfo.getTitle(), pushInfo.getContent2(), param, pushInfo.getClientIds());
		}
		boolean sendNote = pushInfo.isSendNote();
		String tempCode = pushInfo.getTemplateCode();
		if (sendNote && StringUtils.isNotBlank(tempCode)) {
			String mobiles = pushInfo.getMobiles();
			SmsUtil.sendNote(mobiles, pushInfo.getTemplateCode(), pushInfo.getTemplateParam2());
		}
	}

	@Override
	public ApiResult messageList(QueryParam queryParam) {
		PageList<MessageDTO> messagePageList = this.commonFacade.getMessageListForPage(queryParam);
		List<MessageDTO> messages = messagePageList.getDataList();
		List<Map<String, Object>> res = new ArrayList<>();
		if (null != messages && ! messages.isEmpty()) {
			for (MessageDTO message : messages) {
				Map<String, Object> rec = new HashMap<>();
				CglibBeanUtils.addToMap(message, rec);
				rec.remove("userIds");
				rec.remove("userId");
				rec.remove("status");
				rec.remove("id");
				rec.put("messageId", message.getId());
				res.add(rec);
			}
		}
		ApiResult apiResult = new ApiResult();
		apiResult.setData(res);
		PageInfo pageInfo = new PageInfo(messagePageList.getPageNo(), messagePageList.getPageSize(), messagePageList.getTotalRecord());
		apiResult.setPageInfo(pageInfo);
		return apiResult;
	}

	@Override
	public Map<String, Object> message(Long loginUserId, Long messageId) throws ArgsException {
		MessageDTO messageDTO = this.commonFacade.getMessage(messageId);
		if (null == messageDTO) {
			throw new ArgsException(FailureCode.ERR_002.getCode(), "消息不存在");
		}
		MessageReadDTO messageReadDTO = new MessageReadDTO();
		messageReadDTO.setMessageId(messageId);
		messageReadDTO.setStatus(1);
		messageReadDTO.setUserId(loginUserId);
		messageReadDTO = this.commonFacade.getMessageRead(messageReadDTO);
		if (null == messageReadDTO || null == messageReadDTO.getId()) {
			messageReadDTO = new MessageReadDTO();
			messageReadDTO.setCreateTime(new Date());
			messageReadDTO.setMessageId(messageId);
			messageReadDTO.setUserId(loginUserId);
			messageReadDTO.setStatus(1);
			this.commonFacade.saveMessageRead(messageReadDTO);
		}
		
		Map<String, Object> rec = new HashMap<>();
		CglibBeanUtils.addToMap(messageDTO, rec);
		rec.remove("userIds");
		rec.remove("userId");
		rec.remove("status");
		rec.remove("id");
		rec.put("messageId", messageDTO.getId());
		rec.put("read", 1);
		return rec;
	}

	@Override
	public void delMessage(Long loginUserId, String messageIds) throws ArgsException {
		if (StringUtils.isBlank(messageIds)) {
			throw new ArgsException(FailureCode.ERR_002);
		}
		String[] ids = messageIds.split(",");
		for (String id : ids) {
			if (StringUtils.isNotBlank(id)) {
				Long messageId = Long.valueOf(id);
				MessageDTO messageDTO = this.commonFacade.getMessage(messageId);
				if (null == messageDTO) {
					throw new ArgsException(FailureCode.ERR_002.getCode(), "消息不存在");
				}
				MessageReadDTO messageReadDTO = new MessageReadDTO();
				messageReadDTO.setMessageId(messageId);
				messageReadDTO.setStatus(2);
				messageReadDTO = this.commonFacade.getMessageRead(messageReadDTO);
				if (null == messageReadDTO || null == messageReadDTO.getId()) {
					messageReadDTO = new MessageReadDTO();
					messageReadDTO.setCreateTime(new Date());
					messageReadDTO.setMessageId(messageId);
					messageReadDTO.setUserId(loginUserId);
					messageReadDTO.setStatus(2);
					this.commonFacade.saveMessageRead(messageReadDTO);
				}
			}
		}
		
	}

	@Override
	public Map<String, Object> mineMessage(Long loginUserId) {
		Map<String, Object> res = new HashMap<>();
		res.put("type" + MessageType.system.getType(), getMessage(loginUserId, MessageType.system.getType()));
		res.put("type" + MessageType.game.getType(), getMessage(loginUserId, MessageType.game.getType()));
		res.put("type" + MessageType.team.getType(), getMessage(loginUserId, MessageType.team.getType()));
		res.put("type" + MessageType.court.getType(), getMessage(loginUserId, MessageType.court.getType()));
		return res;
	}
	
	private Map<String, Object> getMessage(Long userId, Integer messageType){
		QueryParam queryParam = new QueryParam();
		queryParam.setPageNo(1);
		queryParam.setPageSize(1);
		MessageDTO messageDTO = new MessageDTO();
		messageDTO.setMessageType(messageType);
		messageDTO.setUserId(userId);
		queryParam.setParam(messageDTO);
		PageList<MessageDTO> messagePageList = this.commonFacade.getMessageListForPage(queryParam);
		List<MessageDTO> messages = messagePageList.getDataList();
		Map<String, Object> rec = new HashMap<>();
		if (null != messages && messages.size() > 0) {
			MessageDTO message = messages.get(0);
			CglibBeanUtils.addToMap(message, rec);
			rec.remove("userIds");
			rec.remove("userId");
			rec.remove("status");
			rec.remove("id");
			rec.put("messageId", message.getId());
			messageDTO.setRead(0);
			rec.put("noReadNum", this.commonFacade.getMessageCount(messageDTO));
		}
		return rec;
	}

	private static List<AreaDTO> areas;
	
	@Override
	public List<AreaDTO> getAreas() {
		if (null != areas) {
			return areas;
		}
		AreaDTO areaDTO = new AreaDTO();
		areaDTO.setTypes("1,2,3");
		areaDTO.setStatus(1);
		List<AreaDTO> provinces = this.commonFacade.getAreaList(areaDTO);
		List<AreaDTO> zxs = new ArrayList<>();
		List<AreaDTO> gat = new ArrayList<>();
		for (AreaDTO province : provinces) {
			if (province.getType() == 1) {
				zxs.add(province);
				continue;
			}
			if (province.getType() == 2) {
				gat.add(province);
				continue;
			}
			AreaDTO cityDTO = new AreaDTO();
			cityDTO.setParentId(province.getAreaId());
			cityDTO.setStatus(1);
			List<AreaDTO> citys = this.commonFacade.getAreaList(cityDTO);
			province.setChildren(citys);
		}
		AreaDTO zx = new AreaDTO();
		zx.setShortName("直辖市");
		zx.setType(3);
		zx.setChildren(zxs);
		AreaDTO ga = new AreaDTO();
		ga.setShortName("港澳台");
		ga.setType(3);
		ga.setChildren(gat);
		provinces.add(0, zx);
		provinces.add(ga);
		areas = provinces;
		return provinces;
	}

	@Override
	public void bindMobile(MemberDTO memberDTO) throws ArgsException {
		if (null == memberDTO || null == memberDTO.getTag() || StringUtils.isBlank(memberDTO.getMobile()) || StringUtils.isBlank(memberDTO.getCode())) {
			throw new ArgsException(FailureCode.ERR_002);
		}
		SmsIdentityDTO dto = getSmsIdentity(memberDTO.getMobile(), SmsTag.bindMobile.getTag());
		if (null == dto || ! memberDTO.getCode().equals(dto.getCode())) {
			throw new ArgsException("102", "验证码错误");
		}
		smsIdentityService.delete(dto.getId());
		MemberDTO member = getMember(memberDTO.getOpenId());
		if (null == member) {
			memberDTO.setCreateTime(new Date());
			memberFacade.saveMember(memberDTO);
		}else{
			member.setMobile(memberDTO.getMobile());
			memberFacade.updateMember(member);
		}
	}
	
	public SmsIdentityDTO getSmsIdentity(String phone, Integer tag){
		SmsIdentityDTO dto = new SmsIdentityDTO();
		dto.setPhone(phone);
		dto.setTag(tag);
		List<SmsIdentityDTO> list = smsIdentityService.getSimpleList(dto);
		if (null != list && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

	@Override
	public MemberDTO getMemberInfo(MemberDTO memberDTO) throws ArgsException {
		if (null == memberDTO || (StringUtils.isBlank(memberDTO.getMobile()) && StringUtils.isBlank(memberDTO.getOpenId()))) {
			throw new ArgsException(FailureCode.ERR_002);
		}
		MemberDTO member = memberFacade.getMember(memberDTO);
		return member;
	}
	
	@Override
	public ImportResult importExcel(byte[] bs, String filename, String handle, String errorImport) throws ArgsException {
		AbstractDataImport<?> dataImport = (AbstractDataImport<?>)ClassUtils.getInstance(handle, new Class<?>[]{byte[].class, String.class}, new Object[]{bs, filename});
		ImportResult importResult = dataImport.importFirstSheetData();
		if ("0".equals(errorImport) || importResult.getErrorRecords().isEmpty()) {
			dataImport.save(importResult);
		}
		return importResult;
	}
	
	protected MemberDTO getMember(String openId){
		MemberDTO params = new MemberDTO();
		params.setOpenId(openId);
		MemberDTO member = memberFacade.getMember(params);
		return member;
	}
}
