package org.sun.admin.enums;
/**
* @author sun 
* @date Jan 23, 2018 4:57:11 PM
* 
*/
public enum ResponseResultCode {
	
	SUCCESS(000000, "请求成功"),
    WEAK_NET_WORK(100000, "网络异常，请稍后重试"),
    AUTH_NOT(100001, "没有认证"),
    PERMISSION_NOT(100002, "无权限"),
	FAILED(100003, "请求失败"),
	ADMIN_ROLE_EXIST(100004, "无对照关系已存在!"),
	UNKNOWN_ERROR(100005, "未知错误!"),
	LOGIN_ERROR(100006, "用户名或密码有误");
	private int code;
    private String msg;

    ResponseResultCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

	public void setCode(int code) {
		this.code = code;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

}
