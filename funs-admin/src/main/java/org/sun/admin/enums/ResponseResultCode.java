package org.sun.admin.enums;
/**
* @author sun 
* @date Jan 23, 2018 4:57:11 PM
* 
*/
public enum ResponseResultCode {
	
	SUCCESS(0, "请求成功"),
    WEAK_NET_WORK(-1, "网络异常，请稍后重试"),
    AUTH_NOT(2, "没有认证"),
    PERMISSION_NOT(3, "无权限");
	
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

}
