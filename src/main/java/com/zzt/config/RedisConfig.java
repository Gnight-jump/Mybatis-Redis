package com.zzt.config;/*
 *  @author: G_night
 *  转载请申明作者
 *  Reprint please state the author
 */

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.jsontype.impl.LaissezFaireSubTypeValidator;
import com.zzt.dao.entity.Person;
import org.springframework.boot.autoconfigure.cache.RedisCacheManagerBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.cache.RedisCacheWriter;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.net.UnknownHostException;
import java.time.Duration;

@Configuration
public class RedisConfig {
    /**
     * 这个模板可以直接照搬
     *
     * 编写自己的RedisTemplate
     *
     * 因为Redis默认序列化时会使用JDK序列化器，使得Redis中的key和value值不可读。
     * 所以我们可以改造RedisTemplate，配置自定义序列化器取代默认。
     **/
    @Bean
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory)
            throws UnknownHostException {
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(redisConnectionFactory);

        // 设置key和hash的field
        // 设置键（key）的序列化采用StringRedisSerializer。
        StringRedisSerializer stringRedisSerializer=new StringRedisSerializer();
        redisTemplate.setKeySerializer(stringRedisSerializer);
        redisTemplate.setHashKeySerializer(stringRedisSerializer);

        //下面这个效率低，不过可以反序列化带泛型的list数据
        //GenericJackson2JsonRedisSerializer jackson2JsonRedisSerializer = new GenericJackson2JsonRedisSerializer();

        //而这个方法，效率高，不过也要做一定的设定
        Jackson2JsonRedisSerializer jackson2JsonRedisSerializer=new Jackson2JsonRedisSerializer(Object.class);
        ObjectMapper objectMapper=new ObjectMapper();
        objectMapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        objectMapper.activateDefaultTyping(LaissezFaireSubTypeValidator.instance ,
                ObjectMapper.DefaultTyping.NON_FINAL, JsonTypeInfo.As.PROPERTY);
        jackson2JsonRedisSerializer.setObjectMapper(objectMapper);

        // 设置值（value）的序列化采用FastJsonRedisSerializer。
        redisTemplate.setValueSerializer(jackson2JsonRedisSerializer);
        redisTemplate.afterPropertiesSet();
        return redisTemplate;
    }


    @Bean
    //这个是Springboot2.x的版本
    public RedisCacheManager myCacheManager(RedisConnectionFactory redisConnectionFactory){
        RedisCacheConfiguration cacheConfiguration =
                RedisCacheConfiguration.defaultCacheConfig()
                        .entryTtl(Duration.ofDays(1))   // 设置缓存过期时间为一天
                        .disableCachingNullValues()     // 禁用缓存空值，不缓存null校验
                        .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(new
                                GenericJackson2JsonRedisSerializer()));   // 设置CacheManager的值序列化方式为json序列化，可加入@Class属性
        // 设置默认的cache组件
        return RedisCacheManager.builder(redisConnectionFactory).cacheDefaults(cacheConfiguration).build();

    }


    /**
     *  单纯设置某个pojo的序列化
    **/
    //以Person这个pojo为例
    @Bean
    public RedisTemplate<String, Person> personRedisTemolate(RedisConnectionFactory redisConnectionFactory)
    throws UnknownHostException{
        RedisTemplate<String,Person> template=new RedisTemplate<String, Person>();
        template.setConnectionFactory(redisConnectionFactory);
        Jackson2JsonRedisSerializer<Person> serializer=new Jackson2JsonRedisSerializer<Person>(Person.class);
        template.setDefaultSerializer(serializer);
        return template;
    }


}