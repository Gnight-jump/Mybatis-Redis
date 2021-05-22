package com.zzt.controller;/*
 *  @author: G_night
 *  转载请申明作者
 *  Reprint please state the author
 */

import com.google.gson.Gson;
import com.zzt.dao.entity.Person;
import com.zzt.service.impl.Personservice;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Controller
public class PersonController {

    @Autowired
    private Personservice personservice;

    @RequestMapping("/hello")
    // 有redirect: 就是跳转到Static目录下的html，否则是templates的
    public String sayHello(){
        return "redirect:/HelloWorld.html";
    }

    /**
     * 添加成员
    **/
    @RequestMapping("/CaddPerson")
    public String addPerson(HttpServletRequest request){
        System.out.println("进入到CaddPerson");
        try{
            request.setCharacterEncoding("UTF-8");
            String name=request.getParameter("pname");
            String sex=request.getParameter("psex");
            Person person=new Person();
            person.setPname(name);
            person.setPsex(sex);
            System.out.println(person);
            this.personservice.addPerson(person);
        }catch (Exception e){
            e.printStackTrace();
            return "error";
        }
        return "hello";
    }

    /**
     * 修改成员
    **/
    @RequestMapping("/updatePerson")
    public String update(HttpServletRequest request){
        try{
            int id = Integer.parseInt(request.getParameter("id"));
            Person person=new Person();
            person.setPid(id);
            person.setPname("修改人");
            person.setPsex("无");
            this.personservice.updatePerson(person);
        }catch (Exception e){
            return "redirect:/error.html";
        }
        return "redirect:/success.html";
    }

    /**
     * 使用缓存查看结果
    **/
    @RequestMapping("/selectAllpersoncache")
    @ResponseBody
    public Person selectAllPerson(@Param("id")Integer id){
        Person person=null;
        try{
            person=personservice.selectPerson_cache(id);
        }catch (Exception e){
            e.printStackTrace();
        }
        return person;
    }


    @RequestMapping("/selectById")
    @ResponseBody
    public Person selectById(@Param("id")Integer id){
        Person person=null;
        try{
            person=personservice.getPersonById(id);
        }catch (Exception e){
            e.printStackTrace();
        }
        return person;
    }


}
