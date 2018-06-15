package com.company.project.operation.pojo;

import com.company.project.core.pojo.OrderConfigInfo;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * Created by ZouWeizheng on 2017-11-20.
 */
@ConfigurationProperties( prefix = "operation")
@Component
@PropertySource("classpath:application.properties")
@Service("OperationConfigInfo")
public class OperationConfigInfo extends OrderConfigInfo{

}
