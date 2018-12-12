/**create by liuhua at 2017年9月3日 上午11:40:19**/
package com.booting.h5.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IntroController {
	
	@RequestMapping("/intro")
	public String index(){
		
		return "h5/intro";
	}

	@RequestMapping("/policy")
	public String policy(){
		
		return "h5/policy";
	}
}
