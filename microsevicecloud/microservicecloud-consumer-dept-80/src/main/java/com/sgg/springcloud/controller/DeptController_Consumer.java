package com.sgg.springcloud.controller;

import com.sgg.springcloud.entity.Dept;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
public class DeptController_Consumer {

    //private static final String PERFIX = "http://localhost:8001";
    private static final String PERFIX = "http://MICROSERVICECLOUD-DEPT"; //通过配置eureka中的服务名
    @Autowired
    private RestTemplate restTemplate;

    @PostMapping(value = "/consumer/dept/add")
    public boolean add(Dept dept) {
        return restTemplate.postForObject(PERFIX + "/dept/add", dept, Boolean.class);
    }

    @GetMapping(value = "/consumer/dept/list")
    public List<Dept> list() {
        return restTemplate.getForObject(PERFIX + "/dept/list", List.class);
    }

    @GetMapping(value = "consumer/dept/get/{id}")
    public Dept get(@PathVariable("id") Long id) {
        return restTemplate.getForObject(PERFIX + "/dept/get/" + id, Dept.class);
    }
    //测试@EnableDiscoveryClient,消费端可以调用服务发现
    @RequestMapping(value="/consumer/dept/discovery")
    public Object discovery()
    {
        return restTemplate.getForObject(PERFIX+"/dept/discovery", Object.class);
    }

}
