package com.matrix.swan.common;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

/**
 * 
 * @author 刘飞 E-mail:liufei_it@126.com
 * @version 1.0
 * @since 2013年12月30日 下午10:30:27
 */
public class BaseController {

	protected final Log log = LogFactory.getLog(getClass());

	@Resource(name = "commonsMultipartResolver")
	protected CommonsMultipartResolver commonsMultipartResolver;
	@Resource(name = "modelViewService")
	protected ModelViewService modelViewService;

}