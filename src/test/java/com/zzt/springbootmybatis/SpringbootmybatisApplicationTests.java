package com.zzt.springbootmybatis;

import com.zzt.dao.mapper.PersonMapper;
import com.zzt.service.impl.Testimpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;

@SpringBootTest
class SpringbootmybatisApplicationTests {

    @Autowired
    RedisTemplate redisTemplate;

    @Test
    public void  test1(){
        redisTemplate.opsForValue().append("msg","helo");
    }

}
