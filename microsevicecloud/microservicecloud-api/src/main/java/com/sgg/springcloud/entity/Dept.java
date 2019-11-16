package com.sgg.springcloud.entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors; 

import java.io.Serializable;

@SuppressWarnings("serial")
@NoArgsConstructor
@Data
@Accessors(chain=true)
//必须序列化
public class Dept implements Serializable {

    private Long deptno;
    private String dname;
    private String db_source;

    public Dept(String dname) {
        super();
        this.dname = dname;
    }


}