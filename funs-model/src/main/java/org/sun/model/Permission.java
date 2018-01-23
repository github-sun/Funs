package org.sun.model;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
* @author sun 
* @date Jan 12, 2018 5:20:08 PM
* 权限表
*/
public class Permission implements Serializable{
	


	/**
	 * 
	 */
	private static final long serialVersionUID = -1425173129596133702L;

	//主键
	private int id;
	
	//权限名称
	private String name;
	
	//权限码
	private String code;
	
	//创建日期
	@JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
	private Date createDate;
	
	//修改日期
	@JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
	private Date updateDate;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	@Override
	public String toString() {
		return "Permission [id=" + id + ", name=" + name + ", code=" + code + ", createDate=" + createDate
				+ ", updateDate=" + updateDate + "]";
	}
}
