package org.sun.admin.shiro;


import javax.annotation.Resource;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.SessionListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.sun.admin.config.RedisConfig;

/**
* @author sun 
* @date Jan 16, 2018 2:32:55 PM
* 
*/
@Configuration
public class CustomSessionListener implements SessionListener {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Resource
    private RedisConfig redisConfig;

    /**
     * 一个回话的生命周期开始
     */
    @Override
    public void onStart(Session session) {
        logger.info("===onStart:{}", session.getId());
    }
    /**
     * 一个回话的生命周期结束
     */
    @Override
    public void onStop(Session session) {
        logger.info("===onStop:{}", session.getId());
    }

    @Override
    public void onExpiration(Session session) {
        logger.info("===onExpiration:{}", session.getId());
        redisTemplate.delete(redisConfig.getSessionPrefix() + session.getId().toString());
    }

}

