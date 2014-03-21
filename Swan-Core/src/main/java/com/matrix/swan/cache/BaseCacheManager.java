package com.matrix.swan.cache;

import com.matrix.swan.support.ApplicationSupport;

/**
 * 
 * @author 刘飞 E-mail:liufei_it@126.com
 * @version 1.0
 * @since 2013年12月30日 下午3:19:59
 */
public class BaseCacheManager extends ApplicationSupport {

	protected boolean openCache = false;

	public boolean isOpenCache() {
		return openCache;
	}

	public void setOpenCache(boolean openCache) {
		this.openCache = openCache;
	}
}