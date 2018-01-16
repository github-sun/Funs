package org.sun.admin.redis;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import javax.annotation.Resource;

/**
* @author sun 
* @date Jan 16, 2018 2:32:55 PM
* 
*/

import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.apache.shiro.cache.CacheManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;
import org.sun.admin.config.RedisConfig;

@Repository("redisCacheManager")
public class RedisCacheManager implements CacheManager {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private final ConcurrentMap<String, Cache> caches = new ConcurrentHashMap<String, Cache>();

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    @Autowired
    private RedisConfig redisConfig;

    @Override
    public <K, V> Cache<K, V> getCache(String name) throws CacheException {
        logger.debug("获取名称为: " + name + " 的RedisCache实例");
        Cache c = caches.get(name);

        if(c == null){
            c =  new RedisShiroCache<K, V>(name, redisTemplate, redisConfig);
            caches.put(name, c);
        }
        return  c;
    }
}
