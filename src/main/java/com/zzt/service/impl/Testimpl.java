package com.zzt.service.impl;/*
 *  @author: G_night
 *  转载请申明作者
 *  Reprint please state the author
 */

import com.zzt.dao.mapper.impl.TestImplDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Testimpl {

    @Autowired
    private TestImplDao testImplDao;

    public void testSout(){
        this.testImplDao.testSout();
    }

}
