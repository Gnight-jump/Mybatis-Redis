package com.zzt.service.impl;/*
 *  @author: G_night
 *  转载请申明作者
 *  Reprint please state the author
 */

import com.zzt.dao.entity.Person;
import com.zzt.dao.mapper.PersonMapper;
import com.zzt.service.Ipersonservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.Cache;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Personservice implements Ipersonservice {
    @Autowired
    private PersonMapper personMapper;

    @Override
    public void addPerson(Person person) {
        this.personMapper.insert(person);
    }

    @Override
    public Person findPerson(Integer id) {
        return this.personMapper.selectByPrimaryKey(id);
    }


    /**
     * 采用缓存的方式，以后查询相同数据就可以直接使用
     *
     * cacheNames/value-----指定缓存组件的名字，可以指定为数组---{"",""},保存多个缓存
     * key：使用SqEL设置key值，默认使用传入的参数作为key值
     * keyGenerator：指定key生成器，该属性和上面的key二选一适用
     * condition：指定符合条件才缓存，例如cadition("#id>0")，id>0才生效
     * unless：与上面的condition相反，符合条件就 不生效F
     * sync：是否使用异步
     * cacheManager：指定缓存管理器
     *
    **/
    @Cacheable(cacheNames = "person")
    //其实还可以用@CacheConfig(value="person")注解整个类,这样以后就不用在方法前的注解设置value（cacheNames）
    public Person selectPerson_cache(Integer id){
        System.out.println("进入Cache");
        Person person=this.personMapper.selectByPrimaryKey(id);
        return person;
    }

    @Override
    public List<Person> selectAllPerson() {
        return this.personMapper.selectAllPerson();
    }

    @Override
    public void updatePerson(Person person) {
        this.personMapper.updateByPrimaryKey(person);
    }


    /**
     * 手动获取缓存管理器并处理操作
    **/
    @Qualifier("myCacheManager")  //指定管理器
    @Autowired  // 注入自己的缓存管理器
    RedisCacheManager myCacheManager;
    public  Person getPersonById(Integer id){
        Person person=personMapper.selectByPrimaryKey(id);
        //获取缓存
        Cache cache=myCacheManager.getCache("person");
        cache.put("Pperson",person);
        return person;
    }


}
