package com.zzt.servlet;/*
 *  @author: G_night
 *  转载请申明作者
 *  Reprint please state the author
 */

import com.google.gson.Gson;
import com.zzt.dao.entity.Person;
import com.zzt.service.impl.Personservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/SfindPerson")
public class PersonServlet extends HttpServlet {

    @Autowired
    private Personservice personservice;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("进入到ajax请求啦");
        //接受传来的id值
        Integer id = Integer.parseInt(req.getParameter("pid"));
        Person person=this.personservice.findPerson(id);
        Gson gson=new Gson();
        String personJsonString=gson.toJson(person);
        //直接输出打印即是返回(加上一段话防止乱码)
        resp.setContentType("text/html; charset=UTF-8");
        resp.getWriter().write(personJsonString);
    }
}
