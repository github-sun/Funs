package org.sun.admin.shiro;

import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
* @author sun 
* @date Jan 16, 2018 2:32:55 PM
* 
*/
public class AdminFormAuthenticationFilter extends FormAuthenticationFilter {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
    @Override
    public void setLoginUrl(String loginUrl) {
    	//logger.info("===setLoginUrl()");
    	System.out.println("===setLoginUrl()");
        super.setLoginUrl("/console/login");
    }

    @Override
    public void setSuccessUrl(String successUrl) {
        super.setSuccessUrl("/console/index");
    }

}
