package com.sgg.springcloud.services.impl;

import com.sgg.springcloud.dao.DeptDao;
import com.sgg.springcloud.entity.Dept;
import com.sgg.springcloud.services.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by ZeroV on 2018/11/6.
 */
@Service
public class DeptServiceImpl implements DeptService {

    @Autowired
    private DeptDao dao;


    @Override
    public boolean add(Dept dept) {
        return dao.addDept(dept);
    }

    @Override
    public Dept get(Long id) {
        return dao.findById(id);
    }

    @Override
    public List<Dept> list() {
        return dao.findAll();
    }

}
