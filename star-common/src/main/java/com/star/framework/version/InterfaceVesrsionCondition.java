/**create by liuhua at 2016年7月14日 下午5:24:39**/
package com.star.framework.version;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.servlet.mvc.condition.RequestCondition;

public class InterfaceVesrsionCondition implements RequestCondition<InterfaceVesrsionCondition> {
	private final static Pattern VERSION_PREFIX_PATTERN = Pattern.compile("/(\\d+(\\.\\d+)*)/");
	private String[] version;

	public InterfaceVesrsionCondition(String[] apiVersion) {
		this.version = apiVersion;
	}

	public InterfaceVesrsionCondition combine(InterfaceVesrsionCondition other) {
		return new InterfaceVesrsionCondition(other.getVersion());

	}

	public InterfaceVesrsionCondition getMatchingCondition(HttpServletRequest request) {
		Matcher m = VERSION_PREFIX_PATTERN.matcher(request.getRequestURI());
		if (m.find()) {
			List<String> vs = Arrays.asList(this.version);
			String version = m.group(1);
			if (vs.contains(version)){
				return this;
			}
		}
		return null;
	}

	public int compareTo(InterfaceVesrsionCondition other, HttpServletRequest request) {
		String[] version1 = other.getVersion();
		String[] version2 = this.version;
		
		return version1.length - version2.length;
	}
	
	public String[] getVersion(){
		return version;
	}
}
