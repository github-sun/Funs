package org.sun.admin.shiro;

import java.io.IOException;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.springframework.http.MediaType;

/**
 * @author sun
 * @date Jan 16, 2018 2:32:55 PM
 * 
 */
public class AdminFormAuthenticationFilter extends FormAuthenticationFilter {

	@Override
	public void setLoginUrl(String loginUrl) {
		super.setLoginUrl("/index");
	}

	@Override
	public void setSuccessUrl(String successUrl) {
		super.setSuccessUrl("/console/index");
	}

	@Override
	protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
		if (isLoginRequest(request, response)) {
			if (isLoginSubmission(request, response)) {
				return executeLogin(request, response);
			} else {
				// allow them to see the login page ;)
				return true;
			}
		} else {
			response.setContentType(MediaType.APPLICATION_JSON_VALUE); // 设置ContentType
			// ,返回json数据
			response.setCharacterEncoding("UTF-8"); // 避免乱码
			try { 
				response.getWriter().write("2");
			} catch (IOException e) {
				System.out.println("===onAccessDenied IOException " + e.getMessage());
				e.printStackTrace();
			}
			return false;
		}

	}

}
