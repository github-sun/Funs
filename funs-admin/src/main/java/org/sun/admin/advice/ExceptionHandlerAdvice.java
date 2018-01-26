package org.sun.admin.advice;

import org.apache.shiro.authz.AuthorizationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;
import org.sun.admin.enums.ResponseResultCode;
import org.sun.admin.util.ResponseResultUtils;
import org.sun.model.admin.vo.ResponseResult;

/**
 * @author sun
 * @date Jan 23, 2018 4:37:23 PM
 * 
 */
@ControllerAdvice
@ResponseBody
public class ExceptionHandlerAdvice implements ResponseBodyAdvice {

	private ThreadLocal<Object> modelHolder = new ThreadLocal<>();

	private Logger logger = LoggerFactory.getLogger(ExceptionHandlerAdvice.class);

	@ExceptionHandler(AuthorizationException.class)
	public ResponseResult handleAuthorizationException(AuthorizationException e) {
		return ResponseResultUtils.warn(ResponseResultCode.PERMISSION_NOT);
	}

	@InitBinder
	public void initBinder(WebDataBinder webDataBinder) {
		modelHolder.set(webDataBinder.getTarget());
	}

	@Override
	public boolean supports(MethodParameter returnType, Class converterType) {
		return true;
	}

	@Override
	public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType,
			Class selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
		modelHolder.remove();
		return body;
	}

}
