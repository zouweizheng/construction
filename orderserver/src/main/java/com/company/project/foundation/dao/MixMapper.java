package com.company.project.foundation.dao;

import com.company.project.foundation.core.mapper;
import com.company.project.foundation.dao.ConOrderMapper;
import com.company.project.foundation.model.BiOrder;
import com.company.project.foundation.model.ConOrder;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.mybatis.spring.annotation.MapperScan;

import java.sql.Date;
import java.util.Map;

@Mapper
public interface MixMapper{

    //@Select("SELECT sum(num) as num,sum(money) as money FROM con_order where project_name = #{projectName} limit 10")
    //Map getTotalNum(String projectName, String motorcadeId, String motorcadeName, String workType, String createPerson, Date startTime, Date endTime);
    Map getTotalNum(Map map);
}