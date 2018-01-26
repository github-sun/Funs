package org.sun.model.admin;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
* @author sun 
* @date Jan 12, 2018 5:20:08 PM
* 用户表
*/
public class Admin implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6885295259916111599L;

	//主键
	private int id;
	
	//用户名
	private String username;
	
	//密码
	private String password;
	
	//锁定状态
	private int state;
	
	//加盐
	private String salt;
	
	private int isSuper;
	
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

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	public int getIsSuper() {
		return isSuper;
	}

	public void setIsSuper(int isSuper) {
		this.isSuper = isSuper;
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
		return "Admin [id=" + id + ", username=" + username + ", password=" + password + ", state=" + state + ", salt="
				+ salt + ", isSuper=" + isSuper + ", createDate=" + createDate + ", updateDate=" + updateDate + "]";
	}
}
