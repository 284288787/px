/** create by auto at 2017-06-02 09:14:49**/
package com.booting.pub.facade.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
import com.booting.pub.facade.CommonFacade;
import com.booting.pub.service.AppVersionService;
import com.booting.pub.service.AreaService;
import com.booting.pub.service.BusinessService;
import com.booting.pub.service.CompanyService;
import com.booting.pub.service.DocumentService;
import com.booting.pub.service.FeedbackService;
import com.booting.pub.service.MessageReadService;
import com.booting.pub.service.MessageService;
import com.booting.pub.service.SmsIdentityService;
import com.star.framework.jdbc.dao.result.PageList;
import com.star.framework.jdbc.dao.result.QueryParam;
import com.star.framework.specification.FailureCode;
import com.star.framework.specification.exception.ArgsException;
import com.star.framework.utils.CglibBeanUtils;

@Service("commonFacade")
public class CommonFacadeImpl implements CommonFacade {
	private static final long serialVersionUID = 1L;

	@Autowired
	private SmsIdentityService smsIdentityService;

	@Autowired
	private AppVersionService appVersionService;

	@Autowired
	private DocumentService documentService;

	@Autowired
	private FeedbackService feedbackService;
	
	@Autowired
	private AreaService areaService;

	@Autowired
	private CompanyService companyService;
	
	@Autowired
	private BusinessService businessService;
	
	@Autowired
	private MessageService messageService;
	
	@Autowired
	private MessageReadService messageReadService;
	
	@Override
	public Long saveAppVersion(AppVersionDTO appVersionDTO) throws ArgsException{
		if (null == appVersionDTO || StringUtils.isBlank(appVersionDTO.getVersion()) || StringUtils.isBlank(appVersionDTO.getUrl())) {
			throw new ArgsException(FailureCode.ERR_002);
		}
		AppVersionEntity entity = toAppVersionEntity(appVersionDTO);
		entity.setType(1);
		entity.setStatus(1);
		entity.setCreateTime(new Date());
		appVersionDTO = appVersionService.save(entity);
		return appVersionDTO.getId();
	}

	@Override
	public void batchSaveAppVersion(List<AppVersionDTO> dtos){
		if (null == dtos || dtos.isEmpty()) {
			return;
		}
		List<AppVersionEntity> entities = toAppVersionEntities(dtos);
		appVersionService.batchSave(entities);
	}

	@Override
	public int updateAppVersion(AppVersionDTO appVersionDTO) throws ArgsException{
		if (null == appVersionDTO || null == appVersionDTO.getId() || StringUtils.isBlank(appVersionDTO.getVersion()) || StringUtils.isBlank(appVersionDTO.getUrl())) {
			throw new ArgsException(FailureCode.ERR_002);
		}
		appVersionDTO = appVersionService.updateBySql(appVersionDTO);
		return 1;
	}

	@Override
	public void batchUpdateAppVersion(List<AppVersionDTO> dtos){
		if (null == dtos || dtos.isEmpty()) {
			return;
		}
		appVersionService.batchUpdate(dtos);
	}

	@Override
	public int deleteAppVersion(long id){
		return appVersionService.delete(id);
	}

	@Override
	public AppVersionDTO getAppVersion(long id){
		return appVersionService.get(id);
	}

	@Override
	public AppVersionDTO getAppVersion(AppVersionDTO appVersionDTO){
		return appVersionService.get(appVersionDTO);
	}

	@Override
	public List<AppVersionDTO> getAppVersionList(AppVersionDTO appVersionDTO){
		return appVersionService.getSimpleList(appVersionDTO);
	}

	@Override
	public PageList<AppVersionDTO> getAppVersionListForPage(AppVersionDTO appVersionDTO, int pageNumber, int pageSize){
		return appVersionService.getSimpleListForPage(appVersionDTO, pageNumber, pageSize);
	}

	@Override
	public PageList<AppVersionDTO> getAppVersionListForPage(QueryParam queryParam){
		return appVersionService.getSimpleListForPage(queryParam);
	}

	@Override
	public AppVersionEntity toAppVersionEntity(AppVersionDTO dto){
		AppVersionEntity entity = new AppVersionEntity();
		CglibBeanUtils.copy(dto, entity);
		return entity;
	}

	@Override
	public List<AppVersionEntity> toAppVersionEntities(List<AppVersionDTO> dtos){
		List<AppVersionEntity> entities = new ArrayList<>();
		for(AppVersionDTO dto : dtos){
			entities.add(toAppVersionEntity(dto));
		}
		return entities;
	}

	@Override
	public Long saveDocument(DocumentDTO documentDTO) throws ArgsException{
		if (null == documentDTO) {
			throw new ArgsException(FailureCode.ERR_002);
		}
		DocumentEntity entity = toDocumentEntity(documentDTO);
		entity.setEnabled(1);
		entity.setCreateTime(new Date());
		entity.setModifyTime(entity.getCreateTime());
		documentDTO = documentService.save(entity);
		return documentDTO.getDocId();
	}

	@Override
	public void batchSaveDocument(List<DocumentDTO> dtos){
		if (null == dtos || dtos.isEmpty()) {
			return;
		}
		List<DocumentEntity> entities = toDocumentEntities(dtos);
		documentService.batchSave(entities);
	}

	@Override
	public int updateDocument(DocumentDTO documentDTO) throws ArgsException{
		if (null == documentDTO) {
			throw new ArgsException(FailureCode.ERR_002);
		}
		documentDTO = documentService.updateBySql(documentDTO);
		return 1;
	}

	@Override
	public void batchUpdateDocument(List<DocumentDTO> dtos){
		if (null == dtos || dtos.isEmpty()) {
			return;
		}
		documentService.batchUpdate(dtos);
	}

	@Override
	public int deleteDocument(long docId){
		return documentService.delete(docId);
	}

	@Override
	public DocumentDTO getDocument(long docId){
		return documentService.get(docId);
	}

	@Override
	public DocumentDTO getDocument(DocumentDTO documentDTO){
		return documentService.get(documentDTO);
	}

	@Override
	public List<DocumentDTO> getDocumentList(DocumentDTO documentDTO){
		return documentService.getSimpleList(documentDTO);
	}

	@Override
	public PageList<DocumentDTO> getDocumentListForPage(DocumentDTO documentDTO, int pageNumber, int pageSize){
		return documentService.getSimpleListForPage(documentDTO, pageNumber, pageSize);
	}

	@Override
	public PageList<DocumentDTO> getDocumentListForPage(QueryParam queryParam){
		return documentService.getSimpleListForPage(queryParam);
	}

	@Override
	public DocumentEntity toDocumentEntity(DocumentDTO dto){
		DocumentEntity entity = new DocumentEntity();
		CglibBeanUtils.copy(dto, entity);
		return entity;
	}

	@Override
	public List<DocumentEntity> toDocumentEntities(List<DocumentDTO> dtos){
		List<DocumentEntity> entities = new ArrayList<>();
		for(DocumentDTO dto : dtos){
			entities.add(toDocumentEntity(dto));
		}
		return entities;
	}

	@Override
	public Long saveFeedback(FeedbackDTO feedbackDTO){
		if (null == feedbackDTO) {
			return null;
		}
		FeedbackEntity entity = toFeedbackEntity(feedbackDTO);
		feedbackDTO = feedbackService.save(entity);
		return feedbackDTO.getId();
	}

	@Override
	public void batchSaveFeedback(List<FeedbackDTO> dtos){
		if (null == dtos || dtos.isEmpty()) {
			return;
		}
		List<FeedbackEntity> entities = toFeedbackEntities(dtos);
		feedbackService.batchSave(entities);
	}

	@Override
	public int updateFeedback(FeedbackDTO feedbackDTO){
		feedbackDTO = feedbackService.updateBySql(feedbackDTO);
		return 1;
	}

	@Override
	public void batchUpdateFeedback(List<FeedbackDTO> dtos){
		if (null == dtos || dtos.isEmpty()) {
			return;
		}
		feedbackService.batchUpdate(dtos);
	}

	@Override
	public int deleteFeedback(long id){
		return feedbackService.delete(id);
	}

	@Override
	public FeedbackDTO getFeedback(long id){
		return feedbackService.get(id);
	}

	@Override
	public FeedbackDTO getFeedback(FeedbackDTO feedbackDTO){
		return feedbackService.get(feedbackDTO);
	}

	@Override
	public List<FeedbackDTO> getFeedbackList(FeedbackDTO feedbackDTO){
		return feedbackService.getSimpleList(feedbackDTO);
	}

	@Override
	public PageList<FeedbackDTO> getFeedbackListForPage(FeedbackDTO feedbackDTO, int pageNumber, int pageSize){
		return feedbackService.getSimpleListForPage(feedbackDTO, pageNumber, pageSize);
	}

	@Override
	public PageList<FeedbackDTO> getFeedbackListForPage(QueryParam queryParam){
		return feedbackService.getSimpleListForPage(queryParam);
	}

	@Override
	public FeedbackEntity toFeedbackEntity(FeedbackDTO dto){
		FeedbackEntity entity = new FeedbackEntity();
		CglibBeanUtils.copy(dto, entity);
		return entity;
	}

	@Override
	public List<FeedbackEntity> toFeedbackEntities(List<FeedbackDTO> dtos){
		List<FeedbackEntity> entities = new ArrayList<>();
		for(FeedbackDTO dto : dtos){
			entities.add(toFeedbackEntity(dto));
		}
		return entities;
	}
	
	@Override
	public Long saveSmsIdentity(SmsIdentityDTO smsIdentityDTO){
		if (null == smsIdentityDTO) {
			return null;
		}
		SmsIdentityEntity entity = toSmsIdentityEntity(smsIdentityDTO);
		smsIdentityDTO = smsIdentityService.save(entity);
		return smsIdentityDTO.getId();
	}

	@Override
	public void batchSaveSmsIdentity(List<SmsIdentityDTO> dtos){
		if (null == dtos || dtos.isEmpty()) {
			return;
		}
		List<SmsIdentityEntity> entities = toSmsIdentityEntities(dtos);
		smsIdentityService.batchSave(entities);
	}

	@Override
	public int updateSmsIdentity(SmsIdentityDTO smsIdentityDTO){
		smsIdentityDTO = smsIdentityService.updateBySql(smsIdentityDTO);
		return 1;
	}

	@Override
	public void batchUpdateSmsIdentity(List<SmsIdentityDTO> dtos){
		if (null == dtos || dtos.isEmpty()) {
			return;
		}
		smsIdentityService.batchUpdate(dtos);
	}

	@Override
	public int deleteSmsIdentity(long id){
		return smsIdentityService.delete(id);
	}

	@Override
	public SmsIdentityDTO getSmsIdentity(long id){
		return smsIdentityService.get(id);
	}

	@Override
	public SmsIdentityDTO getSmsIdentity(SmsIdentityDTO smsIdentityDTO){
		return smsIdentityService.get(smsIdentityDTO);
	}

	@Override
	public List<SmsIdentityDTO> getSmsIdentityList(SmsIdentityDTO smsIdentityDTO){
		return smsIdentityService.getSimpleList(smsIdentityDTO);
	}

	@Override
	public PageList<SmsIdentityDTO> getSmsIdentityListForPage(SmsIdentityDTO smsIdentityDTO, int pageNumber, int pageSize){
		return smsIdentityService.getSimpleListForPage(smsIdentityDTO, pageNumber, pageSize);
	}

	@Override
	public PageList<SmsIdentityDTO> getSmsIdentityListForPage(QueryParam queryParam){
		return smsIdentityService.getSimpleListForPage(queryParam);
	}

	@Override
	public SmsIdentityEntity toSmsIdentityEntity(SmsIdentityDTO dto){
		SmsIdentityEntity entity = new SmsIdentityEntity();
		CglibBeanUtils.copy(dto, entity);
		return entity;
	}

	@Override
	public List<SmsIdentityEntity> toSmsIdentityEntities(List<SmsIdentityDTO> dtos){
		List<SmsIdentityEntity> entities = new ArrayList<>();
		for(SmsIdentityDTO dto : dtos){
			entities.add(toSmsIdentityEntity(dto));
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

	@Override
	public void disabledDocument(String docIds) throws ArgsException {
		if (StringUtils.isBlank(docIds)) {
			throw new ArgsException(FailureCode.ERR_002);
		}
		DocumentDTO dto = new DocumentDTO();
		dto.setEnabled(0);
		dto.setDocIds(docIds);
		documentService.updateBySql(dto);
	}

	@Override
	public void enabledDocument(String docIds) throws ArgsException {
		if (StringUtils.isBlank(docIds)) {
			throw new ArgsException(FailureCode.ERR_002);
		}
		DocumentDTO dto = new DocumentDTO();
		dto.setEnabled(1);
		dto.setDocIds(docIds);
		documentService.updateBySql(dto);
	}

	@Override
	public void disabledAppVersion(String ids) throws ArgsException {
		if (StringUtils.isBlank(ids)) {
			throw new ArgsException(FailureCode.ERR_002);
		}
		AppVersionDTO dto = new AppVersionDTO();
		dto.setStatus(0);
		dto.setIds(ids);
		appVersionService.updateBySql(dto);
	}

	@Override
	public void enabledAppVersion(String ids) throws ArgsException {
		if (StringUtils.isBlank(ids)) {
			throw new ArgsException(FailureCode.ERR_002);
		}
		AppVersionDTO dto = new AppVersionDTO();
		dto.setStatus(1);
		dto.setIds(ids);
		appVersionService.updateBySql(dto);
	}

	@Override
	public Long saveArea(AreaDTO areaDTO){
		if (null == areaDTO) {
			return null;
		}
		AreaEntity entity = toAreaEntity(areaDTO);
		areaDTO = areaService.save(entity);
		return areaDTO.getAreaId();
	}

	@Override
	public void batchSaveArea(List<AreaDTO> dtos){
		if (null == dtos || dtos.isEmpty()) {
			return;
		}
		List<AreaEntity> entities = toAreaEntities(dtos);
		areaService.batchSave(entities);
	}

	@Override
	public int updateArea(AreaDTO areaDTO){
		areaDTO = areaService.updateBySql(areaDTO);
		return 1;
	}

	@Override
	public void batchUpdateArea(List<AreaDTO> dtos){
		if (null == dtos || dtos.isEmpty()) {
			return;
		}
		areaService.batchUpdate(dtos);
	}

	@Override
	public int deleteArea(long areaId){
		return areaService.delete(areaId);
	}

	@Override
	public AreaDTO getArea(long areaId){
		return areaService.get(areaId);
	}

	@Override
	public AreaDTO getArea(AreaDTO areaDTO){
		return areaService.get(areaDTO);
	}

	@Override
	public List<AreaDTO> getAreaList(AreaDTO areaDTO){
		return areaService.getSimpleList(areaDTO);
	}

	@Override
	public PageList<AreaDTO> getAreaListForPage(AreaDTO areaDTO, int pageNumber, int pageSize){
		return areaService.getSimpleListForPage(areaDTO, pageNumber, pageSize);
	}

	@Override
	public PageList<AreaDTO> getAreaListForPage(QueryParam queryParam){
		return areaService.getSimpleListForPage(queryParam);
	}

	@Override
	public AreaEntity toAreaEntity(AreaDTO dto){
		AreaEntity entity = new AreaEntity();
		CglibBeanUtils.copy(dto, entity);
		return entity;
	}

	@Override
	public List<AreaEntity> toAreaEntities(List<AreaDTO> dtos){
		List<AreaEntity> entities = new ArrayList<>();
		for(AreaDTO dto : dtos){
			entities.add(toAreaEntity(dto));
		}
		return entities;
	}

	@Override
	public Long saveCompany(CompanyDTO companyDTO){
		if (null == companyDTO) {
			return null;
		}
		CompanyEntity entity = toCompanyEntity(companyDTO);
		entity.setEnabled(1);
		entity.setCreateTime(new Date());
		entity.setModifyTime(entity.getCreateTime());
		companyDTO = companyService.save(entity);
		return companyDTO.getCompanyId();
	}

	@Override
	public void batchSaveCompany(List<CompanyDTO> dtos){
		if (null == dtos || dtos.isEmpty()) {
			return;
		}
		List<CompanyEntity> entities = toCompanyEntities(dtos);
		companyService.batchSave(entities);
	}

	@Override
	public int updateCompany(CompanyDTO companyDTO){
		companyDTO = companyService.updateBySql(companyDTO);
		return 1;
	}

	@Override
	public void batchUpdateCompany(List<CompanyDTO> dtos){
		if (null == dtos || dtos.isEmpty()) {
			return;
		}
		companyService.batchUpdate(dtos);
	}

	@Override
	public int deleteCompany(long companyId){
		return companyService.delete(companyId);
	}

	@Override
	public CompanyDTO getCompany(long companyId){
		return companyService.get(companyId);
	}

	@Override
	public CompanyDTO getCompany(CompanyDTO companyDTO){
		return companyService.get(companyDTO);
	}

	@Override
	public List<CompanyDTO> getCompanyList(CompanyDTO companyDTO){
		return companyService.getSimpleList(companyDTO);
	}

	@Override
	public PageList<CompanyDTO> getCompanyListForPage(CompanyDTO companyDTO, int pageNumber, int pageSize){
		return companyService.getSimpleListForPage(companyDTO, pageNumber, pageSize);
	}

	@Override
	public PageList<CompanyDTO> getCompanyListForPage(QueryParam queryParam){
		return companyService.getSimpleListForPage(queryParam);
	}

	@Override
	public CompanyEntity toCompanyEntity(CompanyDTO dto){
		CompanyEntity entity = new CompanyEntity();
		CglibBeanUtils.copy(dto, entity);
		return entity;
	}

	@Override
	public List<CompanyEntity> toCompanyEntities(List<CompanyDTO> dtos){
		List<CompanyEntity> entities = new ArrayList<>();
		for(CompanyDTO dto : dtos){
			entities.add(toCompanyEntity(dto));
		}
		return entities;
	}
	
	@Override
	public Long saveBusiness(BusinessDTO businessDTO){
		if (null == businessDTO) {
			return null;
		}
		BusinessEntity entity = toBusinessEntity(businessDTO);
		entity.setEnabled(1);
		entity.setCreateTime(new Date());
		entity.setModifyTime(entity.getCreateTime());
		businessDTO = businessService.save(entity);
		return businessDTO.getBusinessId();
	}

	@Override
	public void batchSaveBusiness(List<BusinessDTO> dtos){
		if (null == dtos || dtos.isEmpty()) {
			return;
		}
		List<BusinessEntity> entities = toBusinessEntities(dtos);
		businessService.batchSave(entities);
	}

	@Override
	public int updateBusiness(BusinessDTO businessDTO){
		businessDTO = businessService.updateBySql(businessDTO);
		return 1;
	}

	@Override
	public void batchUpdateBusiness(List<BusinessDTO> dtos){
		if (null == dtos || dtos.isEmpty()) {
			return;
		}
		businessService.batchUpdate(dtos);
	}

	@Override
	public int deleteBusiness(long businessId){
		return businessService.delete(businessId);
	}

	@Override
	public BusinessDTO getBusiness(long businessId){
		return businessService.get(businessId);
	}

	@Override
	public BusinessDTO getBusiness(BusinessDTO businessDTO){
		return businessService.get(businessDTO);
	}

	@Override
	public List<BusinessDTO> getBusinessList(BusinessDTO businessDTO){
		return businessService.getSimpleList(businessDTO);
	}

	@Override
	public PageList<BusinessDTO> getBusinessListForPage(BusinessDTO businessDTO, int pageNumber, int pageSize){
		return businessService.getSimpleListForPage(businessDTO, pageNumber, pageSize);
	}

	@Override
	public PageList<BusinessDTO> getBusinessListForPage(QueryParam queryParam){
		return businessService.getSimpleListForPage(queryParam);
	}

	@Override
	public BusinessEntity toBusinessEntity(BusinessDTO dto){
		BusinessEntity entity = new BusinessEntity();
		CglibBeanUtils.copy(dto, entity);
		return entity;
	}

	@Override
	public List<BusinessEntity> toBusinessEntities(List<BusinessDTO> dtos){
		List<BusinessEntity> entities = new ArrayList<>();
		for(BusinessDTO dto : dtos){
			entities.add(toBusinessEntity(dto));
		}
		return entities;
	}

	@Override
	public void enabledBusiness(String ids) throws ArgsException {
		if (StringUtils.isBlank(ids)) {
			throw new ArgsException(FailureCode.ERR_002);
		}
		BusinessDTO dto = new BusinessDTO();
		dto.setEnabled(1);
		dto.setBusinessIds(ids);
		businessService.updateBySql(dto);
	}

	@Override
	public void disabledBusiness(String ids) throws ArgsException {
		if (StringUtils.isBlank(ids)) {
			throw new ArgsException(FailureCode.ERR_002);
		}
		BusinessDTO dto = new BusinessDTO();
		dto.setEnabled(0);
		dto.setBusinessIds(ids);
		businessService.updateBySql(dto);
	}

	@Override
	public void enabledCompany(String ids) throws ArgsException {
		if (StringUtils.isBlank(ids)) {
			throw new ArgsException(FailureCode.ERR_002);
		}
		CompanyDTO dto = new CompanyDTO();
		dto.setEnabled(1);
		dto.setCompanyIds(ids);
		companyService.updateBySql(dto);
	}
	
	@Override
	public void disabledCompany(String ids) throws ArgsException {
		if (StringUtils.isBlank(ids)) {
			throw new ArgsException(FailureCode.ERR_002);
		}
		CompanyDTO dto = new CompanyDTO();
		dto.setEnabled(0);
		dto.setCompanyIds(ids);
		companyService.updateBySql(dto);
	}
	
	@Override
	public Long saveMessage(MessageDTO messageDTO){
		if (null == messageDTO) {
			return null;
		}
		MessageEntity entity = toMessageEntity(messageDTO);
		messageDTO = messageService.save(entity);
		return messageDTO.getId();
	}

	@Override
	public void batchSaveMessage(List<MessageDTO> dtos){
		if (null == dtos || dtos.isEmpty()) {
			return;
		}
		List<MessageEntity> entities = toMessageEntities(dtos);
		messageService.batchSave(entities);
	}

	@Override
	public int updateMessage(MessageDTO messageDTO){
		messageDTO = messageService.updateBySql(messageDTO);
		return 1;
	}

	@Override
	public void batchUpdateMessage(List<MessageDTO> dtos){
		if (null == dtos || dtos.isEmpty()) {
			return;
		}
		messageService.batchUpdate(dtos);
	}

	@Override
	public int deleteMessage(long id){
		return messageService.delete(id);
	}

	@Override
	public MessageDTO getMessage(long id){
		return messageService.get(id);
	}

	@Override
	public MessageDTO getMessage(MessageDTO messageDTO){
		return messageService.get(messageDTO);
	}

	@Override
	public List<MessageDTO> getMessageList(MessageDTO messageDTO){
		return messageService.getSimpleList(messageDTO);
	}

	@Override
	public PageList<MessageDTO> getMessageListForPage(MessageDTO messageDTO, int pageNumber, int pageSize){
		return messageService.getSimpleListForPage(messageDTO, pageNumber, pageSize);
	}

	@Override
	public PageList<MessageDTO> getMessageListForPage(QueryParam queryParam){
		return messageService.getSimpleListForPage(queryParam);
	}

	@Override
	public MessageEntity toMessageEntity(MessageDTO dto){
		MessageEntity entity = new MessageEntity();
		CglibBeanUtils.copy(dto, entity);
		return entity;
	}

	@Override
	public List<MessageEntity> toMessageEntities(List<MessageDTO> dtos){
		List<MessageEntity> entities = new ArrayList<>();
		for(MessageDTO dto : dtos){
			entities.add(toMessageEntity(dto));
		}
		return entities;
	}
	
	@Override
	public Long saveMessageRead(MessageReadDTO messageReadDTO){
		if (null == messageReadDTO) {
			return null;
		}
		MessageReadEntity entity = toMessageReadEntity(messageReadDTO);
		messageReadDTO = messageReadService.save(entity);
		return messageReadDTO.getId();
	}

	@Override
	public void batchSaveMessageRead(List<MessageReadDTO> dtos){
		if (null == dtos || dtos.isEmpty()) {
			return;
		}
		List<MessageReadEntity> entities = toMessageReadEntities(dtos);
		messageReadService.batchSave(entities);
	}

	@Override
	public int updateMessageRead(MessageReadDTO messageReadDTO){
		messageReadDTO = messageReadService.updateBySql(messageReadDTO);
		return 1;
	}

	@Override
	public void batchUpdateMessageRead(List<MessageReadDTO> dtos){
		if (null == dtos || dtos.isEmpty()) {
			return;
		}
		messageReadService.batchUpdate(dtos);
	}

	@Override
	public int deleteMessageRead(long id){
		return messageReadService.delete(id);
	}

	@Override
	public MessageReadDTO getMessageRead(long id){
		return messageReadService.get(id);
	}

	@Override
	public MessageReadDTO getMessageRead(MessageReadDTO messageReadDTO){
		return messageReadService.get(messageReadDTO);
	}

	@Override
	public List<MessageReadDTO> getMessageReadList(MessageReadDTO messageReadDTO){
		return messageReadService.getSimpleList(messageReadDTO);
	}

	@Override
	public PageList<MessageReadDTO> getMessageReadListForPage(MessageReadDTO messageReadDTO, int pageNumber, int pageSize){
		return messageReadService.getSimpleListForPage(messageReadDTO, pageNumber, pageSize);
	}

	@Override
	public PageList<MessageReadDTO> getMessageReadListForPage(QueryParam queryParam){
		return messageReadService.getSimpleListForPage(queryParam);
	}

	@Override
	public MessageReadEntity toMessageReadEntity(MessageReadDTO dto){
		MessageReadEntity entity = new MessageReadEntity();
		CglibBeanUtils.copy(dto, entity);
		return entity;
	}

	@Override
	public List<MessageReadEntity> toMessageReadEntities(List<MessageReadDTO> dtos){
		List<MessageReadEntity> entities = new ArrayList<>();
		for(MessageReadDTO dto : dtos){
			entities.add(toMessageReadEntity(dto));
		}
		return entities;
	}

	@Override
	public Integer getMessageCount(MessageDTO messageDTO) {
		return this.messageService.getMessageCount(messageDTO);
	}
}