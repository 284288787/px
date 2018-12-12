/** create by auto at 2017-06-13 14:16:44**/
package com.booting.pub.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.booting.pub.dto.DocumentDTO;
import com.booting.pub.entity.DocumentEntity;
import com.booting.pub.service.DocumentService;

import com.star.framework.jdbc.dao.JDBCSupport;

import com.star.framework.jdbc.dao.result.PageList;

import com.star.framework.jdbc.dao.result.QueryParam;

@Service("documentService")
public class DocumentServiceImpl extends JDBCSupport<DocumentEntity, DocumentDTO> implements DocumentService{

	private static final long serialVersionUID = 1L;

	@Override
	public DocumentDTO save(DocumentEntity documentEntity) {
		long id = this.persist(documentEntity);
		return get(id);
	}

	@Override
	public DocumentDTO update(DocumentEntity documentEntity) {
		this.dynamicMerge(documentEntity);
		return get(documentEntity.getDocId());
	}

	@Override
	public DocumentDTO updateAll(DocumentEntity documentEntity) {
		this.merge(documentEntity);
		return get(documentEntity.getDocId());
	}

	@Override
	public DocumentDTO updateBySql(DocumentDTO documentDTO) {
		if(null == documentDTO) return null;
		this.execute("document.updateDocument", toMap(documentDTO, "yyyy-MM-dd HH:mm:ss"));
		if(null == documentDTO.getDocId()) return null;
		return get(documentDTO.getDocId());
	}

	@Override
	public int delete(long id) {
		return this.del(id);
	}

	@Override
	public void batchSave(List<DocumentEntity> entities) {
		if(null == entities || entities.isEmpty()){
			return;
		}
		Map<String, Object>[] params = toMap(entities);
		this.batch("document.insertDocument", params);
	}

	@Override
	public void batchUpdate(List<DocumentDTO> dtos) {
		if(null == dtos || dtos.isEmpty()){
			return;
		}
		Map<String, Object>[] params = toMap(dtos);
		this.batch("document.updateDocument", params);
	}

	@Override
	public DocumentDTO get(long docId) {
		return getById(docId);
	}

	@Override
	public DocumentDTO get(DocumentDTO documentDTO) {
		if(null == documentDTO) {
			return null;
		}
		Map<String, Object> param = toMap(documentDTO, "yyyy-MM-dd HH:mm:ss");
		return this.queryForObject("document.getSimpleDocumentList", param);
	}

	@Override
	public List<DocumentDTO> getSimpleList(DocumentDTO documentDTO) {
		Map<String, Object> param = toMap(documentDTO, "yyyy-MM-dd HH:mm:ss");
		return this.queryForList("document.getSimpleDocumentList", param);
	}

	@Override
	public PageList<DocumentDTO> getSimpleListForPage(DocumentDTO documentDTO, int pageNo, int pageSize) {
		QueryParam queryParam = new QueryParam();
		queryParam.setPageNo(pageNo);
		queryParam.setPageSize(pageSize);
		queryParam.setParam(documentDTO);
		return this.queryForPage("document.getSimpleDocumentListCount", "document.getSimpleDocumentList", queryParam);
	}

	@Override
	public PageList<DocumentDTO> getSimpleListForPage(QueryParam queryParam) {
		return this.queryForPage("document.getSimpleDocumentListCount", "document.getSimpleDocumentList", queryParam);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> List<T> getList(T dto) {
		Map<String, Object> param = toMap(dto, "yyyy-MM-dd HH:mm:ss");
		return (List<T>)this.queryForList("document.getDocumentList", param);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> PageList<T> getListForPage(T dto, int pageNo, int pageSize) {
		QueryParam queryParam = new QueryParam();
		queryParam.setPageNo(pageNo);
		queryParam.setPageSize(pageSize);
		queryParam.setParam(dto);
		return (PageList<T>) this.queryForPage("document.getDocumentListCount", "document.getDocumentList", queryParam, dto.getClass());
	}

	@Override
	public <T> PageList<T> getListForPage(QueryParam queryParam, Class<T> clazz) {
		return (PageList<T>) this.queryForPage("document.getDocumentListCount", "document.getDocumentList", queryParam, clazz);
	}

}