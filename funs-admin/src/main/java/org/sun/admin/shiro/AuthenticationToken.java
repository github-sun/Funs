package org.sun.admin.shiro;

import org.apache.shiro.authc.UsernamePasswordToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
* @author sun 
* @date Jan 16, 2018 2:32:55 PM
* 
*/
public class AuthenticationToken extends UsernamePasswordToken {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private String captcha;
    /**
     * 用来区分前后台登录的标记
     */
    private String loginType;
    /**
     * 用来区分登录用户的渠道
     */
    private String loginForm;

    public AuthenticationToken(String username, String password, boolean rememberMe) {
        super(username, password, rememberMe);
    }

    public String getCaptcha() {
        return captcha;
    }

    public void setCaptcha(String captcha) {
        this.captcha = captcha;
    }

    public String getLoginType() {
        return loginType;
    }

    public void setLoginType(String loginType) {
        this.loginType = loginType;
    }

    public String getLoginForm() {
        return loginForm;
    }

    public void setLoginForm(String loginForm) {
        this.loginForm = loginForm;
    }
}
