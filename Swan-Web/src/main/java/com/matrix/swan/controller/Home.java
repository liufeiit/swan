package com.matrix.swan.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.matrix.swan.common.BaseController;
import com.matrix.swan.common.ModelViewResolver;

/**
 * Web首页。
 * 
 * @author 刘飞 E-mail:liufei_it@126.com
 * @version 1.0
 * @since 2013年12月30日 下午10:26:04
 */
@Controller
public class Home extends BaseController {

	@RequestMapping(value = "/index.htm")
	public ModelAndView index(HttpServletRequest request) {
		ModelViewResolver resolver = modelViewService.getModelViewResolver(request);
		resolver.addAttribute("name", "刘飞00 : " + resolver.isSuccess());
		return resolver.build("welcome");
	}
}