package org.sun.admin.shiro;

import java.io.IOException;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.springframework.http.MediaType;
import org.sun.admin.enums.ResponseResultCode;
import org.sun.admin.util.ResponseResultUtils;
import org.sun.model.admin.vo.ResponseResult;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author sun
 * @date Jan 16, 2018 2:32:55 PM
 * 
 */
public class AdminFormAuthenticationFilter extends FormAuthenticationFilter {

	@Override
	protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
		if (isLoginRequest(request, response)) {
			return true;
		} else {
			response.setContentType(MediaType.APPLICATION_JSON_VALUE); // 设置ContentType
			// ,返回json数据
			response.setCharacterEncoding("UTF-8"); // 避免乱码
			try { 
				ResponseResult result = ResponseResultUtils.warn(ResponseResultCode.AUTH_NOT);
				ObjectMapper mapper = new ObjectMapper();  
				response.getWriter().write(mapper.writeValueAsString(result));
			} catch (IOException e) {
				System.out.println("===onAccessDenied IOException " + e.getMessage());
				e.printStackTrace();
			}
			return false;
		}

	}

}
