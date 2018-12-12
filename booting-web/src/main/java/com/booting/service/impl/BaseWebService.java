/**create by liuhua at 2017年7月7日 上午11:35:09**/
package com.booting.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.booting.common.PushInfo;
import com.booting.coupon.facade.CouponFacade;
import com.booting.court.facade.CourtFacade;
import com.booting.pkg.dto.AttributeDTO;
import com.booting.pkg.dto.PackageAttributeValueDTO;
import com.booting.pkg.dto.PackageDTO;
import com.booting.pkg.dto.PackageServiceRelationDTO;
import com.booting.pkg.dto.ServiceDTO;
import com.booting.pkg.facade.PackageFacade;
import com.booting.service.CommonWebService;
import com.booting.team.dto.TeamMemberDTO;
import com.booting.team.facade.TeamFacade;
import com.star.framework.utils.CglibBeanUtils;

public class BaseWebService {

	@Autowired
	protected CommonWebService commonWebService;
	@Autowired
	protected TeamFacade teamFacade;
	@Autowired
	protected CourtFacade courtFacade;
	@Autowired
	protected PackageFacade packageFacade;
	@Autowired
	protected CouponFacade couponFacade;
	
	protected void writeMessage(PushInfo pushInfo) {
		commonWebService.writeMessage(pushInfo);
	}
	
	protected Long getTeamIdByUserId(Long userId){
		TeamMemberDTO teamMemberDTO = new TeamMemberDTO();
		teamMemberDTO.setUserId(userId);
		teamMemberDTO.setStatus(2);
		TeamMemberDTO tmd = this.teamFacade.getTeamMember(teamMemberDTO);
		if (null != tmd) {
			return tmd.getTeamId();
		}
		return null;
	}
	
	protected PackageDTO getPackage(Long packageId){
		if (null == packageId) {
			return null;
		}
		PackageDTO packageDTO = new PackageDTO();
		packageDTO.setPackageId(packageId);
		List<PackageDTO> packages = getPackage(packageDTO);
		if (null == packages || packages.isEmpty()) {
			return null;
		}
		return packages.get(0);
	}
	
	protected List<PackageDTO> getPackage(PackageDTO packageDTO) {
		List<PackageDTO> list = packageFacade.getPackageList(packageDTO);
		List<Map<String, Object>> res = new ArrayList<>();
		for (PackageDTO pkg : list) {
			List<ServiceDTO> services = new ArrayList<>();
			PackageServiceRelationDTO packageServiceRelationDTO = new PackageServiceRelationDTO();
			packageServiceRelationDTO.setPackageId(pkg.getPackageId());
			List<PackageServiceRelationDTO> relations = packageFacade.getPackageServiceRelationList(packageServiceRelationDTO);
			for (PackageServiceRelationDTO packageServiceRelation : relations) {
				PackageAttributeValueDTO packageAttributeValueDTO = new PackageAttributeValueDTO();
				packageAttributeValueDTO.setPackageId(packageServiceRelation.getPackageId());
				packageAttributeValueDTO.setServiceId(packageServiceRelation.getServiceId());
				ServiceDTO service = packageFacade.getService(packageServiceRelation.getServiceId());
				List<AttributeDTO> attributes = new ArrayList<>();
				List<PackageAttributeValueDTO> values = packageFacade.getPackageAttributeValueList(packageAttributeValueDTO);
				for (PackageAttributeValueDTO packageAttributeValue : values) {
					AttributeDTO attribute = packageFacade.getAttribute(packageAttributeValue.getAttributeId());
					attribute.setAttributeValue(packageAttributeValue.getAttributeValue());
					attributes.add(attribute);
				}
				service.setAttributes(attributes);
				service.setCount(packageServiceRelation.getCount());
				services.add(service);
			}
			pkg.setServices(services);
			Map<String, Object> map = new HashMap<>();
			CglibBeanUtils.addToMap(pkg, map, "yyyy-MM-dd HH:mm:ss");
			map.remove("packageIds");
			res.add(map);
		}
		
		return list;
	}
}
