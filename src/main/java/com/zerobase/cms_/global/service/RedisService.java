package com.zerobase.cms_.global.service;


import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RedisService {

    private final RedisTemplate<String, Object> redisTemplate;


    public String getData(String key) {

        ValueOperations<String, Object> value = redisTemplate.opsForValue();

        return (String) value.get(key);

    }

    public void setDataExpire(String key, String value, Long expiredTime) {

        ValueOperations<String, Object> values = redisTemplate.opsForValue();

        values.set(key, value, expiredTime);

    }

    public void deleteData(String key) {
        redisTemplate.delete(key);
    }

}
