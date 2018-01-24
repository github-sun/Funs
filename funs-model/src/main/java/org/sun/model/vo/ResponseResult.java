package org.sun.model.vo;
/**
* @author sun 
* @date Jan 23, 2018 4:47:55 PM
* rest返回对象
*/
public class ResponseResult{
	
	//返回的状态码
	private int code;
	
	//返回的描述
	private String msg;
	
	//返回的数据
	private Object data;

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "ResponseResult [code=" + code + ", msg=" + msg + ", data=" + data + "]";
	}
	
}
