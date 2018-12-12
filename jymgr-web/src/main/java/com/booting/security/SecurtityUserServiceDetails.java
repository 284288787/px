/**create by liuhua at 2017年5月19日 下午2:07:11**/
package com.booting.security;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.booting.business.dto.MemberDTO;
import com.booting.business.facade.BusinessFacade;
import com.booting.common.CommonConstants.UserIdentity;
import com.booting.common.CommonConstants.UserType;
import com.booting.system.dto.RoleSourceRelationDTO;
import com.booting.system.dto.UserAccountDTO;
import com.booting.system.dto.UserInfoDTO;
import com.booting.system.dto.UserRoleRelationDTO;
import com.booting.system.facade.SystemFacade;
import com.star.framework.jdbc.dao.result.PageList;
import com.star.framework.jdbc.dao.result.QueryParam;
import com.star.framework.security.core.StarUserInfo;

@Service("securtityUserServiceDetails")
public class SecurtityUserServiceDetails implements UserDetailsService {
	
//	Md5PasswordEncoder encode = new Md5PasswordEncoder();
//	
//	List<SimpleGrantedAuthority> nullAuth = new ArrayList<>();
//	private Map<String, User> users = new HashMap<String, User>(){
//		{
//			put("admin", new User("admin", encode.encodePassword("111", null), Collections.singletonList(new SimpleGrantedAuthority("ROLE_ADMIN"))));
//			put("test1", new User("test1", encode.encodePassword("111", null), Collections.singletonList(new SimpleGrantedAuthority("ROLE_TEST"))));
//			put("test2", new User("test2", encode.encodePassword("111", null), nullAuth));
//		}
//	};
	private static final Long this_system_menu_id = 1l;
	
	@Autowired
	private SystemFacade systemFacade;
	@Autowired
	private BusinessFacade businessFacade;
	
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//		List<SimpleGrantedAuthority> authorities = new ArrayList<SimpleGrantedAuthority>();
//		authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
//		authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
		
		UserAccountDTO userAccountDTO = new UserAccountDTO();
		userAccountDTO.setAccount(username);
		userAccountDTO = systemFacade.getUserAccount(userAccountDTO);
		if (null != userAccountDTO) {
			List<SimpleGrantedAuthority> authorities = new ArrayList<>();
			List<RoleSourceRelationDTO> resources = new ArrayList<>();
			List<Map<String, Object>> navMenus = new ArrayList<>();
			List<Map<String, Object>> menus = new ArrayList<>();
			List<Map<String, Object>> packages = new ArrayList<>();
			List<Map<String, Object>> services = new ArrayList<>();
			
			Integer userType = userAccountDTO.getUserType();
			if (null == userType || userType == UserType.system.getUserType()) {
			  UserInfoDTO userInfo = systemFacade.getUserInfo(userAccountDTO.getUserId());
			  userAccountDTO.setName(userInfo.getName());
			  userAccountDTO.setAddress(userInfo.getAddress());
			  userAccountDTO.setIdentity(userInfo.getIdentity());
            } else {
              MemberDTO memberDTO = businessFacade.getMember(userAccountDTO.getUserId());
              userAccountDTO.setName(memberDTO.getName());
              userAccountDTO.setIdentity(UserIdentity.background.getIdentity());
            }
			if (userAccountDTO.getIdentity().intValue() == UserIdentity.background.getIdentity()) {
				QueryParam queryParam = new QueryParam();
				queryParam.setPageNo(1);
				queryParam.setPageSize(10000);
				queryParam.setParam(userAccountDTO);
				PageList<UserRoleRelationDTO> pageList = systemFacade.getUserRoleForPage(queryParam);
				List<UserRoleRelationDTO> roles = pageList.getDataList();
				String roleIds = "";
				for (UserRoleRelationDTO role : roles) {
					authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getRoleName().toUpperCase()));
					roleIds += "," + role.getRoleId();
				}
				if (roleIds.length() > 0) {
					roleIds = roleIds.substring(1);
					resources = systemFacade.getUserResources(roleIds);
				}
				Map<Long, List<RoleSourceRelationDTO>> temp = new LinkedHashMap<>();
				if (resources.size() > 0) {
					for (RoleSourceRelationDTO rsr : resources) {
						Long parentId = rsr.getParentId();
						List<RoleSourceRelationDTO> res = temp.get(parentId);
						if (null == res) {
							res = new ArrayList<>();
							temp.put(parentId, res);
						}
						res.add(rsr);
					}
					List<RoleSourceRelationDTO> res = temp.get(this_system_menu_id);
					if (null != res) {
						for (RoleSourceRelationDTO roleSourceRelationDTO : res) {
							Map<String, Object> nav = new LinkedHashMap<>();
							nav.put("name", roleSourceRelationDTO.getSourceName());
							nav.put("icoCls", roleSourceRelationDTO.getSourceIcoCls());
							nav.put("selected", true);
							navMenus.add(nav);
							Map<String, Object> menu = new LinkedHashMap<>();
							menu.put("id", "menu_" + roleSourceRelationDTO.getSourceId());
							List<RoleSourceRelationDTO> res2 = temp.get(roleSourceRelationDTO.getSourceId());
							if (null != res2) {
								List<Map<String, Object>> menuDetails = new ArrayList<>();
								for (RoleSourceRelationDTO roleSourceRelationDTO2 : res2) {
									Map<String, Object> item = new LinkedHashMap<>();
									item.put("text", roleSourceRelationDTO2.getSourceName());
									List<RoleSourceRelationDTO> res3 = temp.get(roleSourceRelationDTO2.getSourceId());
									if (null != res3) {
										List<Map<String, Object>> details = new ArrayList<>();
										for (RoleSourceRelationDTO roleSourceRelationDTO3 : res3) {
											Map<String, Object> ele = new LinkedHashMap<>();
											ele.put("id", "item_" + roleSourceRelationDTO3.getSourceId());
											ele.put("text", roleSourceRelationDTO3.getSourceName());
											ele.put("href", roleSourceRelationDTO3.getSourceUrl());
											details.add(ele);
										}
										item.put("items", details);
									}
									menuDetails.add(item);
								}
								menu.put("menu", menuDetails);
							}
							menus.add(menu);
						}
					}
				}
			}else{}
			
			StarUserInfo user = new StarUserInfo(userAccountDTO.getIdentity(),
				userAccountDTO.getUserId(), 
				userAccountDTO.getName(), 
				username, userAccountDTO.getPassword(), 
				userAccountDTO.getEnabled() == 1,
				userAccountDTO.getNonExpired() == 1, 
				true,
				userAccountDTO.getNonLocked() == 1, authorities, resources, menus, navMenus, packages, services);
			return user;
		}
		return null;
	}

}
