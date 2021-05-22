package com.zzt;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
//@ComponentScan("com.zzt.controller") //扫描controller
//@ComponentScan("com.zzt.service.impl")
@MapperScan("com.zzt.dao.mapper")//扫描到接口
@ServletComponentScan
@EnableCaching
public class SpringbootmybatisApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootmybatisApplication.class, args);
    }

}
