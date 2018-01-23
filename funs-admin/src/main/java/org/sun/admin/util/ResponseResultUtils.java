package org.sun.admin.util;

import org.sun.admin.enums.ResponseResultCode;
import org.sun.model.vo.ResponseResult;

/**
 * @author sun
 * @date Jan 23, 2018 5:00:40 PM
 * 
 */
public class ResponseResultUtils {

	public static ResponseResult success(Object data) {
		ResponseResult result = new ResponseResult();
		result.setCode(ResponseResultCode.SUCCESS.getCode());
		result.setMsg(ResponseResultCode.SUCCESS.getMsg());
		result.setData(data);
		return result;
	}

	public static ResponseResult warn(ResponseResultCode resultCode, Object data) {
		ResponseResult result = new ResponseResult();
		result.setCode(resultCode.getCode());
		result.setMsg(resultCode.getMsg());
		result.setData(data);
		return result;
	}

	public static ResponseResult warn(ResponseResultCode resultCode) {
		ResponseResult result = new ResponseResult();
		result.setCode(resultCode.getCode());
		result.setMsg(resultCode.getMsg());
		return result;
	}
}
