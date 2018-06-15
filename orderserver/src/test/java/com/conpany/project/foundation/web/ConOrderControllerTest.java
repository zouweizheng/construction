package com.conpany.project.foundation.web;

import com.alibaba.fastjson.JSONObject;
import com.company.project.construction.pojo.ConstructionPojo;
import com.company.project.foundation.model.ConOrder;
import com.company.project.foundation.web.ConOrderController;
import com.conpany.project.Tester;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.HashMap;
import java.util.Map;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by ZouWeizheng on 2017-11-17.
 */
public class ConOrderControllerTest extends Tester{

    private MockMvc mockMvc;
    @Autowired
    private ConOrderController conOrderController;
    @Autowired
    private WebApplicationContext wac; // 注入WebApplicationContext

    @Before // 在测试开始前初始化工作
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
    }

    @Test
    public void testQ1() throws Exception {
        ConstructionPojo constructionPojo = new ConstructionPojo();
        ConOrder conOrder = new ConOrder();
        conOrder.setBelongPersonId("zouweizheng");
        conOrder.setBelongPersonName("邹伟政2222");
        conOrder.setCreatePersonId("");
        conOrder.setCreatePersonName("");
        conOrder.setDestinationId("23233");
        conOrder.setDestinationName("B地");
        conOrder.setFeeType("车辆");
        constructionPojo.setConOrder(conOrder);

        MvcResult result = mockMvc.perform(post("/con/insertOrder?token=42A1ANUMxMTdEODA2NDhCNzFBMjI1MjE4OTE3em91d2VpemhlbmdAZ2QuY21jYw==").content(JSONObject.toJSONString(constructionPojo)))
                .andExpect(status().isOk())// 模拟向testRest发送get请求
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))// 预期返回值的媒体类型text/plain;charset=UTF-8
                .andReturn();// 返回执行请求的结果
        System.out.println(result.getResponse().getContentAsString());
    }
}
