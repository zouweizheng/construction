package com.company.project;

import com.company.project.common.aspect.AccessFilter;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages={"com.company.project","com.cmit.kapok.activiti.business.querytask","com.cmit.kapok.activiti.dao","com.cmit.kapok.activiti.config","com.cmit.kapok.activiti.service"})
@MapperScan("com.cmit.kapok.activiti.mappers")
@ServletComponentScan
@EnableCaching
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public AccessFilter accessFilter() {
        return new AccessFilter();
    }

}

