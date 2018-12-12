/**create by liuhua at 2017年5月26日 上午9:46:57**/
package com.booting.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.stereotype.Service;

import com.booting.system.dto.ResourceDTO;
import com.booting.system.dto.RoleSourceRelationDTO;
import com.booting.system.facade.SystemFacade;

@Service("starFilterInvocationSecurityMetadataSource")
public class StarFilterInvocationSecurityMetadataSource implements FilterInvocationSecurityMetadataSource {

	@Autowired
	private SystemFacade systemFacade;

//	private PathMatcher matcher = new AntPathMatcher();
	
	private static Map<RequestMatcher, Collection<ConfigAttribute>> resourceMap = null;

	public void loadResourceDefine() {
		resourceMap = new HashMap<>();
		ResourceDTO resourceDTO = new ResourceDTO();
		resourceDTO.setEnabled(1);
		List<RoleSourceRelationDTO> resources = systemFacade.getAllRoleSourceRelation();
		if (null != resources && !resources.isEmpty()) {
			for (RoleSourceRelationDTO resource : resources) {
				String url = resource.getSourceUrl();
				if (StringUtils.isNotBlank(url)) {
					String urls[] = url.split(",");
					for (String u : urls) {
						if (StringUtils.isBlank(u)) {
							continue;
						}
						RequestMatcher requestMatcher = new AntPathRequestMatcher(u);
						ConfigAttribute ca = new SecurityConfig("ROLE_" + resource.getRoleName().toUpperCase());
						Collection<ConfigAttribute> value = resourceMap.get(requestMatcher);
						if (null == value) {
							value = new ArrayList<ConfigAttribute>();
							resourceMap.put(requestMatcher, value);
						}
						value.add(ca);
					}
				}
			}
		}
	}

	@Override
	public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
		final HttpServletRequest request = ((FilterInvocation) object).getRequest();
		RequestMatcher requestMatcher = new AntPathRequestMatcher("/login");
		if (requestMatcher.matches(request)) {
			return null;
		}
		requestMatcher = new AntPathRequestMatcher("/getLoginInfo");
		if (requestMatcher.matches(request)) {
			return null;
		}
		requestMatcher = new AntPathRequestMatcher("/api/**/login");
		if (requestMatcher.matches(request)) {
			return null;
		}
		if (null == resourceMap) {
			loadResourceDefine();
		}
		for (Map.Entry<RequestMatcher, Collection<ConfigAttribute>> entry : resourceMap.entrySet()) {
			if (entry.getKey().matches(request)) {
				return entry.getValue();
			}
		}
		Collection<ConfigAttribute> list = new ArrayList<>();
		list.add(new SecurityConfig("ROLE_suoyourendoumeiyouzhegequanxian"));
		return list;
	}

	@Override
	public Collection<ConfigAttribute> getAllConfigAttributes() {
		if (null == resourceMap) {
			loadResourceDefine();
		}
		Set<ConfigAttribute> allAttributes = new HashSet<ConfigAttribute>();
		for (Map.Entry<RequestMatcher, Collection<ConfigAttribute>> entry : resourceMap.entrySet()) {
			allAttributes.addAll(entry.getValue());
		}

		return allAttributes;
	}

	@Override
	public boolean supports(Class<?> clazz) {
		return FilterInvocation.class.isAssignableFrom(clazz);
	}

}
