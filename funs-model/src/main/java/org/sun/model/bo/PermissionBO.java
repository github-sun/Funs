package org.sun.model.bo;

import java.io.Serializable;

/**
 * @author sun
 * @date Jan 16, 2018 11:18:45 AM
 * 
 */
public class PermissionBO implements Serializable {

	

	/**
	 * 
	 */
	private static final long serialVersionUID = -3150024309424396314L;

	// 权限码
	private String code;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

}
