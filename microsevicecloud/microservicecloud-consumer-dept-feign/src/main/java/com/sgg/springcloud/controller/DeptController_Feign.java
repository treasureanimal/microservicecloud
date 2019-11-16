package com.sgg.springcloud.controller;

import com.sgg.springcloud.entity.Dept;
import com.sgg.springcloud.services.DeptClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DeptController_Feign {

    @Autowired
    private DeptClientService deptClientService;

    //private static final String PERFIX = "http://localhost:8001";
    //private static final String PERFIX = "http://MICROSERVICECLOUD-DEPT"; //通过配置eureka中的服务名
    @RequestMapping(value = "/consumer/dept/get/{id}")
    public Dept get(@PathVariable("id") Long id)
    {
        return this.deptClientService.get(id);
    }

    @RequestMapping(value = "/consumer/dept/list")
    public List<Dept> list()
    {
        return this.deptClientService.list();
    }

    @RequestMapping(value = "/consumer/dept/add")
    public Object add(Dept dept)
    {
        return this.deptClientService.add(dept);
    }
}
