package com.zzt.service;/*
 *  @author: G_night
 *  转载请申明作者
 *  Reprint please state the author
 */

import com.zzt.dao.entity.Person;

import java.util.List;

public interface Ipersonservice {
    void addPerson(Person person);
    public Person findPerson(Integer id);
    public List<Person> selectAllPerson();
    public void updatePerson(Person person);
}
