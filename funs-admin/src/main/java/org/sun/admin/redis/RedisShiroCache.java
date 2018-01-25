package org.sun.admin.redis;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.BoundValueOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.sun.admin.config.RedisConfig;

/**
* @author sun 
* @date Jan 16, 2018 2:32:55 PM
* 
*/

@SuppressWarnings("unchecked")
public class RedisShiroCache<K, V> implements Cache<K, V> {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private RedisConfig redisConfig;

    private String cacheKey;

    private RedisTemplate<K, V> redisTemplate;

    @SuppressWarnings("rawtypes")
    public RedisShiroCache(String name, RedisTemplate client, RedisConfig redisConfig) {
        this.redisConfig = redisConfig;
        this.cacheKey = this.redisConfig.getCachePrefix();
        this.redisTemplate = client;
    }

    @Override
    public V get(K key) throws CacheException {
        logger.info("根据key从Redis中获取对象 key [" + key + "]");
        try {
            if(key == null){
                return null;
            }else {
                //redisTemplate.boundValueOps(getCacheKey(key)).expire(redisConfig.getCacheTime(), TimeUnit.MINUTES);
                V v = redisTemplate.boundValueOps(getCacheKey(key)).get();
                return v;
            }
        }catch (Throwable t){
            throw new CacheException(t);
        }
    }

    @Override
    public V put(K key, V value) throws CacheException {
        logger.info("根据key从存储 key [" + key + "]");
        try {
            V v = get(key);
            BoundValueOperations<K, V> operations = redisTemplate.boundValueOps(getCacheKey(key));
            operations.set(value, redisConfig.getCacheTime(), TimeUnit.MINUTES);
            System.out.println(redisTemplate.boundValueOps(getCacheKey(key)).get().toString());
            return v;
        }catch (Throwable t){
            throw  new CacheException(t);
        }
    }

    @Override
    public V remove(K key) throws CacheException {
        logger.info("从redis中删除 key [" + key + "]");
        try {
            V v = get(key);
            redisTemplate.delete(getCacheKey(key));
            return v;
        }catch (Throwable t){
            throw new CacheException(t);
        }

    }

    @Override
    public void clear() throws CacheException {
        try {
            redisTemplate.delete(keys());
        }catch (Throwable t){
            throw new CacheException(t);
        }
    }

    @Override
    public int size() {
        return keys().size();
    }

    @Override
    public Set<K> keys() {
        return redisTemplate.keys(getCacheKey("*"));
    }

    @Override
    public Collection<V> values() {
        Set<K> set = keys();
        List<V> list = new ArrayList<>();
        for (K s : set) {
            list.add(get(s));
        }
        return list;
    }

    private K getCacheKey(Object k) {
        K key = (K) (this.cacheKey + k);
        logger.info("===getCacheKey key "+key);
        return key;
    }
}