package org.swan.web;

import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

/**
 * 
 * @author 刘飞 E-mail:liufei_it@126.com
 * @version 1.0
 * @since 2014年3月17日 下午5:14:54
 */
@Controller
@RequestMapping("/user")
public class UserController {

	@RequestMapping("/{id}")
	public String view(@PathVariable("id") Long id) {
		return "view";
	}

	@RequestMapping("/{id}")
	public ModelAndView getUser(@PathVariable("id") Long id) {
		return new ModelAndView();
	}

	public static void main(String[] args) {
		MockHttpServletRequest req = new MockHttpServletRequest();
		RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(req));
		// MvcUriComponentsBuilder类似于ServletUriComponentsBuilder，但是直接从控制器获取
		// 类级别的
		System.out.println(MvcUriComponentsBuilder.fromController(UserController.class).build().toString());
		// 方法级别的
		System.out.println(MvcUriComponentsBuilder.fromMethodName(UserController.class, "view", 1L).build().toString());
		// 通过Mock方法调用得到
		System.out.println(MvcUriComponentsBuilder.fromMethodCall(MvcUriComponentsBuilder.on(UserController.class).getUser(2L)).build());
	}
}
