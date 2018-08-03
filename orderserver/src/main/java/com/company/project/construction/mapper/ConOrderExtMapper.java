package com.company.project.construction.mapper;

import org.apache.ibatis.annotations.Select;
import org.mybatis.spring.annotation.MapperScan;

@MapperScan
public interface ConOrderExtMapper{

    @Select("SELECT sum(money) FROM con_order limit 10")
    double getTotalNum(Long id);
}