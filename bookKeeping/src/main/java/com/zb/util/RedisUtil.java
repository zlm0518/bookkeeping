package com.zb.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
public class RedisUtil {

    @Autowired
    private RedisTemplate redisTemplate;

    public boolean set(String key,Object value){
        try {
            ValueOperations vo = redisTemplate.opsForValue();
            vo.set(key,value);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }

    public boolean set(String key,Object value,long time){
        try {
            ValueOperations vo = redisTemplate.opsForValue();
            vo.set(key,value,time, TimeUnit.SECONDS);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }

    public Object get(String key){
        ValueOperations vo = redisTemplate.opsForValue();
        return vo.get(key);
    }

    public boolean exists(String key){
        return this.get(key)!=null?true:false;
    }

    public void delete(String key){
        redisTemplate.delete(key);
    }

}
