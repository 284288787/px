/**create by liuhua at 2017年9月3日 上午11:40:19**/
package com.booting.h5.controller;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IntroController {
  @Autowired
  private MessageSource messageSource;

  @RequestMapping("/intro")
  public String index() {

    return "h5/intro";
  }

  @RequestMapping("/policy")
  public String policy(Model model) {
    Locale locale = LocaleContextHolder.getLocale();
    String name = messageSource.getMessage("name", null, locale);
    model.addAttribute("name", name);
    return "h5/policy";
  }
}
