package com.matrix.swan.dataobject;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * 
 * @author 刘飞 E-mail:liufei_it@126.com
 * @version 1.0
 * @since 2013年12月30日 下午4:56:44
 */
public class BaseDataObject implements Serializable {

	private static final long serialVersionUID = 1L;

	private long id;
	private Date gmtCreated;
	private Date gmtModified;

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * @return the gmtCreated
	 */
	public Date getGmtCreated() {
		return gmtCreated;
	}

	/**
	 * @param gmtCreated
	 *            the gmtCreated to set
	 */
	public void setGmtCreated(Date gmtCreated) {
		this.gmtCreated = gmtCreated;
	}

	/**
	 * @return the gmtModified
	 */
	public Date getGmtModified() {
		return gmtModified;
	}

	/**
	 * @param gmtModified
	 *            the gmtModified to set
	 */
	public void setGmtModified(Date gmtModified) {
		this.gmtModified = gmtModified;
	}
}