package com.zzt.dao.mapper;

import com.zzt.dao.entity.Person;

import java.util.List;

public interface PersonMapper {
    int deleteByPrimaryKey(Integer pid);

    int insert(Person record);

    int insertSelective(Person record);

    Person selectByPrimaryKey(Integer pid);

    int updateByPrimaryKeySelective(Person record);

    int updateByPrimaryKey(Person record);

    List<Person> selectAllPerson();
}