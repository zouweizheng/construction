package com.conpany.project.construction;

import com.alibaba.fastjson.JSONObject;
import com.company.project.Application;
import com.company.project.construction.pojo.ConstructionPojo;
import com.company.project.core.pojo.ApiResult;
import com.company.project.core.pojo.TaskAndOrder;
import com.company.project.foundation.core.Result;
import com.company.project.foundation.model.ConOrder;
import com.conpany.project.util.PostUtil;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.text.ParseException;
import java.util.Map;

/**
 * Created by ZouWeizheng on 2017-12-13.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class ConstructionTest {

    private int port = 10079;
    private String testUrl="http://localhost:"+port;
    private String tokenUrl = "?token=42A1ANUMxMTdEODA2NDhCNzFBMjI1MjE4OTE3em91d2VpemhlbmdAZ2QuY21jYw==";

    @Test
    public void testInsertOrder() throws ParseException {
        ConstructionPojo constructionPojo = new ConstructionPojo();
        ConOrder conOrder = new ConOrder();
        conOrder.setBelongPersonId("zouweizheng");
        conOrder.setBelongPersonName("邹伟政");
        conOrder.setCreatePersonId("");
        conOrder.setCreatePersonName("");
        conOrder.setDestinationId("23233");
        conOrder.setDestinationName("B地");
        conOrder.setFeeType("车辆");
        constructionPojo.setConOrder(conOrder);
        PostUtil postUtil = new PostUtil<ConstructionPojo>();
        ApiResult<TaskAndOrder> result=  postUtil.postData(constructionPojo,testUrl+"con/insertOrder"+tokenUrl);
        System.out.println("插入工单信息："+result);
        //Assert.assertTrue("0".equals(result.getCode()));

    }
}
