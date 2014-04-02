package com.matrix.swan.common;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.servlet.ModelAndView;

/**
 * 
 * @author 刘飞 E-mail:liufei_it@126.com
 * @version 1.0
 * @since 2013年12月31日 下午11:02:53
 */
public class ModelViewResolver {
	private static final String VERSION_NAME = "version";
	private static final String DEVICE_NAME = "device";
	public static final String TOKEN_NAME = "token";
	public static final String USER_ID_NAME = "userId";

	private final int version;// 应用版本
	private final String device;// 设备类型，如IPhone、Android等等
	private final String token;// 该用户设备的访问token
	private final long userId;// 用户ID
	private final Map<String, Object> data = new HashMap<String, Object>();
	
	private boolean success = false;

	public ModelViewResolver(NativeWebRequest request) {
		this(NumberUtils.toInt(request.getParameter(VERSION_NAME), -1), StringUtils.defaultString(
				request.getParameter(DEVICE_NAME), ""),
				StringUtils.defaultString(request.getParameter(TOKEN_NAME), ""), NumberUtils.toLong(
						request.getParameter(USER_ID_NAME), -1L));
	}

	public ModelViewResolver(HttpServletRequest request) {
		this(NumberUtils.toInt(request.getParameter(VERSION_NAME), -1), StringUtils.defaultString(
				request.getParameter(DEVICE_NAME), ""),
				StringUtils.defaultString(request.getParameter(TOKEN_NAME), ""), NumberUtils.toLong(
						request.getParameter(USER_ID_NAME), -1L));
	}

	public ModelViewResolver(int version, String device, String token, long userId) {
		super();
		this.version = version;
		this.device = device;
		this.token = token;
		this.userId = userId;
		this.success = checkRequest();
	}
	
	public ModelViewResolver addAttribute(String attributeName, Object attributeValue) {
		data.put(attributeName, attributeValue);
		return this;
	}
	
	public ModelViewResolver addAttribute(Map<String, ?> attributes) {
		data.putAll(attributes);
		return this;
	}
	
	public ModelAndView build(String viewName) {
		return new ModelAndView(viewName, data);
	}

	/**
	 * 校验请求数据是否正确，对于错误请求，填充错误代码。
	 */
	protected boolean checkRequest() {
		return true;
	}

	/**
	 * @return the success
	 */
	public boolean isSuccess() {
		return success;
	}

	/**
	 * @param success the success to set
	 */
	public void setSuccess(boolean success) {
		this.success = success;
	}

	/**
	 * @return the version
	 */
	public int getVersion() {
		return version;
	}

	/**
	 * @return the device
	 */
	public String getDevice() {
		return device;
	}

	/**
	 * @return the token
	 */
	public String getToken() {
		return token;
	}

	/**
	 * @return the userId
	 */
	public long getUserId() {
		return userId;
	}

	/**
	 * @return the data
	 */
	public Map<String, Object> getData() {
		return data;
	}
}