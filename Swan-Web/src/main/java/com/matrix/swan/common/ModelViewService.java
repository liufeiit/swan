package com.matrix.swan.common;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.context.request.NativeWebRequest;

/**
 * 
 * @author 刘飞 E-mail:liufei_it@126.com
 * @version 1.0
 * @since 2013年12月31日 下午10:47:34
 */
public class ModelViewService {

	public ModelViewResolver getModelViewResolver(NativeWebRequest request) {
		return getModelViewResolver(request, true);
	}
	
	public ModelViewResolver getModelViewResolver(NativeWebRequest request, boolean login) {
		ModelViewResolver resolver = new ModelViewResolver(request);
		if(!resolver.isSuccess()) {
			return resolver;
		}
		if (login && !checkUserToken(resolver.getUserId(), resolver.getToken())) {
			resolver.setSuccess(false);
		}
		return resolver;
	}
	
	public ModelViewResolver getModelViewResolver(HttpServletRequest request) {
		return getModelViewResolver(request, true);
	}
	
	public ModelViewResolver getModelViewResolver(HttpServletRequest request, boolean login) {
		ModelViewResolver resolver = new ModelViewResolver(request);
		if(!resolver.isSuccess()) {
			return resolver;
		}
		if (login && !checkUserToken(resolver.getUserId(), resolver.getToken())) {
			resolver.setSuccess(false);
		}
		return resolver;
	}
	
	protected boolean checkUserToken(long userId, String token) {
		if ("~!@#$%^&*@WSX7ujm%RDX*UHB6yhn".equals(token)) {//测试使用
			return true;
		}
		return true;
	}
}