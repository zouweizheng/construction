package com.company.project.test;

import com.company.project.foundation.model.ConOrder;
import org.springframework.stereotype.Service;

/**
 * Created by ZouWeizheng on 2017-11-03.
 */
@Service
public class TestService {

    public ConOrder getConOrder(String fieldName , String value){
        ConOrder conOrder = new ConOrder();
        conOrder.setBelongPersonId("2323");
        return conOrder;
    }
}
