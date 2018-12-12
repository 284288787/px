/**create by liuhua at 2017年6月2日 上午9:16:47**/
package com.booting.service;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import com.booting.common.PushInfo;
import com.booting.pub.dto.AreaDTO;
import com.booting.pub.dto.BusinessDTO;
import com.star.framework.importdata.ImportResult;
import com.star.framework.jdbc.dao.result.QueryParam;
import com.star.framework.specification.exception.ArgsException;
import com.star.framework.specification.result.v2.ApiResult;

public interface CommonWebService {

	public void sendSms(String mobile, Integer tag) throws ArgsException;

	public void verifySms(String mobile, String code, Integer tag) throws ArgsException;

	public Map<String, Object> latestVersion(Integer type, String version) throws ArgsException;

	public void feedback(String title, String content, String contact) throws ArgsException;

	public List<Map<String, String>> banner();

	public ApiResult articleList(QueryParam queryParam);

	public Map<String, Object> article(Long artId) throws ArgsException;

	public void refreshJson(Integer tag) throws IOException;

	public void updateBusiness(BusinessDTO businessDTO) throws ArgsException, IOException;

	public void enabledBusiness(String ids) throws ArgsException, IOException;

	public void disabledBusiness(String ids) throws ArgsException, IOException;

	public void saveBusiness(BusinessDTO businessDTO) throws ArgsException, IOException;

	public void writeMessage(PushInfo pushInfo);

	public ApiResult messageList(QueryParam queryParam);

	public Map<String, Object> message(Long loginUserId, Long messageId) throws ArgsException;

	public void delMessage(Long loginUserId, String messageIds) throws ArgsException;

	public Map<String, Object> mineMessage(Long loginUserId);

	public List<AreaDTO> getAreas();

	public ImportResult importExcel(byte[] bs, String filename, String handle, String errorImport) throws ArgsException;
}
