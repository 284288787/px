/** create by auto at 2018-03-02 10:12:43**/
package com.booting.lottery.service;

import com.star.framework.jdbc.service.BaseService;

import java.util.List;
import java.util.Map;

import com.booting.lottery.dto.InvitationDetailDTO;
import com.booting.lottery.entity.InvitationDetailEntity;

/**
 * 邀请明细服务
 *
 * @author auto
 *
 */
public interface InvitationDetailService extends BaseService<InvitationDetailEntity, InvitationDetailDTO> {

	public List<InvitationDetailDTO> getInvitationDetailList(Map<String, Object> params);

}