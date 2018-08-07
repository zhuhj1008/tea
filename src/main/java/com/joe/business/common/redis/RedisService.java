package com.joe.business.common.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.concurrent.TimeUnit;

/**
 * 缓存服务
 * create by Joe on 2018-08-07 18:30
 **/
@Service
public class RedisService {

    @Autowired
    RedisTemplate redisTemplate;

    public boolean isHasKey(Object cacheKey){
        boolean isHasKey = redisTemplate.hasKey(cacheKey);
        return isHasKey;
    }

    public <T> T getCache(Object cacheKey){
        if(isHasKey(cacheKey)){
            ValueOperations valueOperations = redisTemplate.opsForValue();
            return (T)valueOperations.get(cacheKey);
        }
        return null;
    }

    public void putCache(Object cacheKey,Object data, long timeOutMillSeconds){
        ValueOperations valueOperations = redisTemplate.opsForValue();
        valueOperations.set(cacheKey,data);
        if(timeOutMillSeconds > 0){//小于0则不过期
            expireCache(cacheKey,timeOutMillSeconds);
        }
    }

    /**
     * 不存在才设置值，存在就不设置值
     */
    public Boolean putCacheIfAbsent(Object cacheKey,Object data, long timeOutMillSeconds){
        ValueOperations valueOperations = redisTemplate.opsForValue();
        Boolean obj = valueOperations.setIfAbsent(cacheKey,data);
        if(obj && timeOutMillSeconds > 0){//小于0则不过期
            expireCache(cacheKey,timeOutMillSeconds);
        }
        return obj;
    }


    public void putCache(Object cacheKey,Object data){
        putCache(cacheKey,data,-1);
    }



    public void expireCache(Object cacheKey,long timeOutMillSeconds){
        redisTemplate.expire(cacheKey,timeOutMillSeconds, TimeUnit.MILLISECONDS);
    }

    public void deleteCache(String... cacheKey){
        if(cacheKey.length == 1){
            redisTemplate.delete(cacheKey[0]);
        }else{
            redisTemplate.delete(CollectionUtils.arrayToList(cacheKey));
        }
    }


}
