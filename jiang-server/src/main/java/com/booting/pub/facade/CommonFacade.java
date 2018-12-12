/** create by auto at 2017-06-02 09:14:49**/
package com.booting.pub.facade;

import java.io.Serializable;
import java.util.List;

import com.booting.pub.dto.AppVersionDTO;
import com.booting.pub.dto.AreaDTO;
import com.booting.pub.dto.BusinessDTO;
import com.booting.pub.dto.CompanyDTO;
import com.booting.pub.dto.DocumentDTO;
import com.booting.pub.dto.FeedbackDTO;
import com.booting.pub.dto.MessageDTO;
import com.booting.pub.dto.MessageReadDTO;
import com.booting.pub.dto.SmsIdentityDTO;
import com.booting.pub.entity.AppVersionEntity;
import com.booting.pub.entity.AreaEntity;
import com.booting.pub.entity.BusinessEntity;
import com.booting.pub.entity.CompanyEntity;
import com.booting.pub.entity.DocumentEntity;
import com.booting.pub.entity.FeedbackEntity;
import com.booting.pub.entity.MessageEntity;
import com.booting.pub.entity.MessageReadEntity;
import com.booting.pub.entity.SmsIdentityEntity;
import com.star.framework.jdbc.dao.result.PageList;
import com.star.framework.jdbc.dao.result.QueryParam;
import com.star.framework.specification.exception.ArgsException;

public interface CommonFacade extends Serializable {

	/**
	 * 新增 APP版本
	 */
	public Long saveAppVersion(AppVersionDTO appVersionDTO) throws ArgsException;

	/**
	 * 批量新增 APP版本
	 */
	public void batchSaveAppVersion(List<AppVersionDTO> dtos);

	/**
	 * 更新 APP版本
	 */
	public int updateAppVersion(AppVersionDTO appVersionDTO) throws ArgsException;

	/**
	 * 批量 APP版本
	 */
	public void batchUpdateAppVersion(List<AppVersionDTO> dtos);

	/**
	 * 删除 APP版本
	 */
	public int deleteAppVersion(long id);

	/**
	 * 根据主键获取 APP版本
	 */
	public AppVersionDTO getAppVersion(long id);

	/**
	 * 根据条件获取一条 APP版本
	 */
	public AppVersionDTO getAppVersion(AppVersionDTO appVersionDTO);

	/**
	 * 查询满足条件的 APP版本 列表(单表)
	 */
	public List<AppVersionDTO> getAppVersionList(AppVersionDTO appVersionDTO);

	/**
	 * 查询满足条件的 APP版本 列表(分页)(单表)
	 */
	public PageList<AppVersionDTO> getAppVersionListForPage(AppVersionDTO appVersionDTO, int pageNumber, int pageSize);

	/**
	 * 查询满足条件的 APP版本 列表(分页)(单表)
	 */
	public PageList<AppVersionDTO> getAppVersionListForPage(QueryParam queryParam);

	/**
	 * APP版本DTO 转换成 Entity
	 */
	public AppVersionEntity toAppVersionEntity(AppVersionDTO appVersionDTO);

	/**
	 * APP版本DTOs 转换成 Entities
	 */
	public List<AppVersionEntity> toAppVersionEntities(List<AppVersionDTO> dtoes);

	/**
	 * 新增 文档
	 */
	public Long saveDocument(DocumentDTO documentDTO) throws ArgsException;

	/**
	 * 批量新增 文档
	 */
	public void batchSaveDocument(List<DocumentDTO> dtos);

	/**
	 * 更新 文档
	 */
	public int updateDocument(DocumentDTO documentDTO) throws ArgsException;

	/**
	 * 批量 文档
	 */
	public void batchUpdateDocument(List<DocumentDTO> dtos);

	/**
	 * 删除 文档
	 */
	public int deleteDocument(long docId);

	/**
	 * 根据主键获取 文档
	 */
	public DocumentDTO getDocument(long docId);

	/**
	 * 根据条件获取一条 文档
	 */
	public DocumentDTO getDocument(DocumentDTO documentDTO);

	/**
	 * 查询满足条件的 文档 列表(单表)
	 */
	public List<DocumentDTO> getDocumentList(DocumentDTO documentDTO);

	/**
	 * 查询满足条件的 文档 列表(分页)(单表)
	 */
	public PageList<DocumentDTO> getDocumentListForPage(DocumentDTO documentDTO, int pageNumber, int pageSize);

	/**
	 * 查询满足条件的 文档 列表(分页)(单表)
	 */
	public PageList<DocumentDTO> getDocumentListForPage(QueryParam queryParam);

	/**
	 * 文档DTO 转换成 Entity
	 */
	public DocumentEntity toDocumentEntity(DocumentDTO documentDTO);

	/**
	 * 文档DTOs 转换成 Entities
	 */
	public List<DocumentEntity> toDocumentEntities(List<DocumentDTO> dtoes);

	/**
	 * 新增 意见与反馈
	 */
	public Long saveFeedback(FeedbackDTO feedbackDTO);

	/**
	 * 批量新增 意见与反馈
	 */
	public void batchSaveFeedback(List<FeedbackDTO> dtos);

	/**
	 * 更新 意见与反馈
	 */
	public int updateFeedback(FeedbackDTO feedbackDTO);

	/**
	 * 批量 意见与反馈
	 */
	public void batchUpdateFeedback(List<FeedbackDTO> dtos);

	/**
	 * 删除 意见与反馈
	 */
	public int deleteFeedback(long id);

	/**
	 * 根据主键获取 意见与反馈
	 */
	public FeedbackDTO getFeedback(long id);

	/**
	 * 根据条件获取一条 意见与反馈
	 */
	public FeedbackDTO getFeedback(FeedbackDTO feedbackDTO);

	/**
	 * 查询满足条件的 意见与反馈 列表(单表)
	 */
	public List<FeedbackDTO> getFeedbackList(FeedbackDTO feedbackDTO);

	/**
	 * 查询满足条件的 意见与反馈 列表(分页)(单表)
	 */
	public PageList<FeedbackDTO> getFeedbackListForPage(FeedbackDTO feedbackDTO, int pageNumber, int pageSize);

	/**
	 * 查询满足条件的 意见与反馈 列表(分页)(单表)
	 */
	public PageList<FeedbackDTO> getFeedbackListForPage(QueryParam queryParam);

	/**
	 * 意见与反馈DTO 转换成 Entity
	 */
	public FeedbackEntity toFeedbackEntity(FeedbackDTO feedbackDTO);

	/**
	 * 意见与反馈DTOs 转换成 Entities
	 */
	public List<FeedbackEntity> toFeedbackEntities(List<FeedbackDTO> dtoes);

	/**
	 * 新增 验证短信
	 */
	public Long saveSmsIdentity(SmsIdentityDTO smsIdentityDTO);

	/**
	 * 批量新增 验证短信
	 */
	public void batchSaveSmsIdentity(List<SmsIdentityDTO> dtos);

	/**
	 * 更新 验证短信
	 */
	public int updateSmsIdentity(SmsIdentityDTO smsIdentityDTO);

	/**
	 * 批量 验证短信
	 */
	public void batchUpdateSmsIdentity(List<SmsIdentityDTO> dtos);

	/**
	 * 删除 验证短信
	 */
	public int deleteSmsIdentity(long id);

	/**
	 * 根据主键获取 验证短信
	 */
	public SmsIdentityDTO getSmsIdentity(long id);

	/**
	 * 根据条件获取一条 验证短信
	 */
	public SmsIdentityDTO getSmsIdentity(SmsIdentityDTO smsIdentityDTO);

	/**
	 * 查询满足条件的 验证短信 列表(单表)
	 */
	public List<SmsIdentityDTO> getSmsIdentityList(SmsIdentityDTO smsIdentityDTO);

	/**
	 * 查询满足条件的 验证短信 列表(分页)(单表)
	 */
	public PageList<SmsIdentityDTO> getSmsIdentityListForPage(SmsIdentityDTO smsIdentityDTO, int pageNumber, int pageSize);

	/**
	 * 查询满足条件的 验证短信 列表(分页)(单表)
	 */
	public PageList<SmsIdentityDTO> getSmsIdentityListForPage(QueryParam queryParam);

	/**
	 * 验证短信DTO 转换成 Entity
	 */
	public SmsIdentityEntity toSmsIdentityEntity(SmsIdentityDTO smsIdentityDTO);

	/**
	 * 验证短信DTOs 转换成 Entities
	 */
	public List<SmsIdentityEntity> toSmsIdentityEntities(List<SmsIdentityDTO> dtoes);

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

	public void disabledDocument(String docIds) throws ArgsException;

	public void enabledDocument(String docIds) throws ArgsException;

	public void disabledAppVersion(String ids) throws ArgsException;

	public void enabledAppVersion(String ids) throws ArgsException;

	/**
	 * 新增 地区
	 */
	public Long saveArea(AreaDTO areaDTO);

	/**
	 * 批量新增 地区
	 */
	public void batchSaveArea(List<AreaDTO> dtos);

	/**
	 * 更新 地区
	 */
	public int updateArea(AreaDTO areaDTO);

	/**
	 * 批量 地区
	 */
	public void batchUpdateArea(List<AreaDTO> dtos);

	/**
	 * 删除 地区
	 */
	public int deleteArea(long areaId);

	/**
	 * 根据主键获取 地区
	 */
	public AreaDTO getArea(long areaId);

	/**
	 * 根据条件获取一条 地区
	 */
	public AreaDTO getArea(AreaDTO areaDTO);

	/**
	 * 查询满足条件的 地区 列表(单表)
	 */
	public List<AreaDTO> getAreaList(AreaDTO areaDTO);

	/**
	 * 查询满足条件的 地区 列表(分页)(单表)
	 */
	public PageList<AreaDTO> getAreaListForPage(AreaDTO areaDTO, int pageNumber, int pageSize);

	/**
	 * 查询满足条件的 地区 列表(分页)(单表)
	 */
	public PageList<AreaDTO> getAreaListForPage(QueryParam queryParam);

	/**
	 * 地区DTO 转换成 Entity
	 */
	public AreaEntity toAreaEntity(AreaDTO areaDTO);

	/**
	 * 地区DTOs 转换成 Entities
	 */
	public List<AreaEntity> toAreaEntities(List<AreaDTO> dtoes);

	/**
	 * 新增 企业
	 */
	public Long saveCompany(CompanyDTO companyDTO) throws ArgsException;

	/**
	 * 批量新增 企业
	 */
	public void batchSaveCompany(List<CompanyDTO> dtos);

	/**
	 * 更新 企业
	 */
	public int updateCompany(CompanyDTO companyDTO) throws ArgsException;

	/**
	 * 批量 企业
	 */
	public void batchUpdateCompany(List<CompanyDTO> dtos);

	/**
	 * 删除 企业
	 */
	public int deleteCompany(long companyId);

	/**
	 * 根据主键获取 企业
	 */
	public CompanyDTO getCompany(long companyId);

	/**
	 * 根据条件获取一条 企业
	 */
	public CompanyDTO getCompany(CompanyDTO companyDTO);

	/**
	 * 查询满足条件的 企业 列表(单表)
	 */
	public List<CompanyDTO> getCompanyList(CompanyDTO companyDTO);

	/**
	 * 查询满足条件的 企业 列表(分页)(单表)
	 */
	public PageList<CompanyDTO> getCompanyListForPage(CompanyDTO companyDTO, int pageNumber, int pageSize);

	/**
	 * 查询满足条件的 企业 列表(分页)(单表)
	 */
	public PageList<CompanyDTO> getCompanyListForPage(QueryParam queryParam);

	/**
	 * 企业DTO 转换成 Entity
	 */
	public CompanyEntity toCompanyEntity(CompanyDTO companyDTO);

	/**
	 * 企业DTOs 转换成 Entities
	 */
	public List<CompanyEntity> toCompanyEntities(List<CompanyDTO> dtoes);
	
	/**
	 * 新增 行业
	 */
	public Long saveBusiness(BusinessDTO businessDTO) throws ArgsException;

	/**
	 * 批量新增 行业
	 */
	public void batchSaveBusiness(List<BusinessDTO> dtos);

	/**
	 * 更新 行业
	 */
	public int updateBusiness(BusinessDTO businessDTO) throws ArgsException;

	/**
	 * 批量 行业
	 */
	public void batchUpdateBusiness(List<BusinessDTO> dtos);

	/**
	 * 删除 行业
	 */
	public int deleteBusiness(long businessId);

	/**
	 * 根据主键获取 行业
	 */
	public BusinessDTO getBusiness(long businessId);

	/**
	 * 根据条件获取一条 行业
	 */
	public BusinessDTO getBusiness(BusinessDTO businessDTO);

	/**
	 * 查询满足条件的 行业 列表(单表)
	 */
	public List<BusinessDTO> getBusinessList(BusinessDTO businessDTO);

	/**
	 * 查询满足条件的 行业 列表(分页)(单表)
	 */
	public PageList<BusinessDTO> getBusinessListForPage(BusinessDTO businessDTO, int pageNumber, int pageSize);

	/**
	 * 查询满足条件的 行业 列表(分页)(单表)
	 */
	public PageList<BusinessDTO> getBusinessListForPage(QueryParam queryParam);

	/**
	 * 行业DTO 转换成 Entity
	 */
	public BusinessEntity toBusinessEntity(BusinessDTO businessDTO);

	/**
	 * 行业DTOs 转换成 Entities
	 */
	public List<BusinessEntity> toBusinessEntities(List<BusinessDTO> dtoes);

	public void enabledBusiness(String ids) throws ArgsException;

	public void disabledBusiness(String ids) throws ArgsException;

	public void disabledCompany(String ids) throws ArgsException;

	public void enabledCompany(String ids) throws ArgsException;

	/**
	 * 新增 消息
	 */
	public Long saveMessage(MessageDTO messageDTO);

	/**
	 * 批量新增 消息
	 */
	public void batchSaveMessage(List<MessageDTO> dtos);

	/**
	 * 更新 消息
	 */
	public int updateMessage(MessageDTO messageDTO);

	/**
	 * 批量 消息
	 */
	public void batchUpdateMessage(List<MessageDTO> dtos);

	/**
	 * 删除 消息
	 */
	public int deleteMessage(long id);

	/**
	 * 根据主键获取 消息
	 */
	public MessageDTO getMessage(long id);

	/**
	 * 根据条件获取一条 消息
	 */
	public MessageDTO getMessage(MessageDTO messageDTO);

	/**
	 * 查询满足条件的 消息 列表(单表)
	 */
	public List<MessageDTO> getMessageList(MessageDTO messageDTO);

	/**
	 * 查询满足条件的 消息 列表(分页)(单表)
	 */
	public PageList<MessageDTO> getMessageListForPage(MessageDTO messageDTO, int pageNumber, int pageSize);

	/**
	 * 查询满足条件的 消息 列表(分页)(单表)
	 */
	public PageList<MessageDTO> getMessageListForPage(QueryParam queryParam);

	/**
	 * 消息DTO 转换成 Entity
	 */
	public MessageEntity toMessageEntity(MessageDTO messageDTO);

	/**
	 * 消息DTOs 转换成 Entities
	 */
	public List<MessageEntity> toMessageEntities(List<MessageDTO> dtoes);

	/**
	 * 新增 已读消息
	 */
	public Long saveMessageRead(MessageReadDTO messageReadDTO);

	/**
	 * 批量新增 已读消息
	 */
	public void batchSaveMessageRead(List<MessageReadDTO> dtos);

	/**
	 * 更新 已读消息
	 */
	public int updateMessageRead(MessageReadDTO messageReadDTO);

	/**
	 * 批量 已读消息
	 */
	public void batchUpdateMessageRead(List<MessageReadDTO> dtos);

	/**
	 * 删除 已读消息
	 */
	public int deleteMessageRead(long id);

	/**
	 * 根据主键获取 已读消息
	 */
	public MessageReadDTO getMessageRead(long id);

	/**
	 * 根据条件获取一条 已读消息
	 */
	public MessageReadDTO getMessageRead(MessageReadDTO messageReadDTO);

	/**
	 * 查询满足条件的 已读消息 列表(单表)
	 */
	public List<MessageReadDTO> getMessageReadList(MessageReadDTO messageReadDTO);

	/**
	 * 查询满足条件的 已读消息 列表(分页)(单表)
	 */
	public PageList<MessageReadDTO> getMessageReadListForPage(MessageReadDTO messageReadDTO, int pageNumber, int pageSize);

	/**
	 * 查询满足条件的 已读消息 列表(分页)(单表)
	 */
	public PageList<MessageReadDTO> getMessageReadListForPage(QueryParam queryParam);

	/**
	 * 已读消息DTO 转换成 Entity
	 */
	public MessageReadEntity toMessageReadEntity(MessageReadDTO messageReadDTO);

	/**
	 * 已读消息DTOs 转换成 Entities
	 */
	public List<MessageReadEntity> toMessageReadEntities(List<MessageReadDTO> dtoes);

	public Integer getMessageCount(MessageDTO messageDTO);

}