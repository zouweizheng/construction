package com.company.project.construction.pojo;

import com.company.project.core.pojo.OrderPojo;
import com.company.project.foundation.model.ConOrder;
import com.company.project.foundation.service.ConOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2017/10/29.
 */
@Service
public class ConstructionPojo extends OrderPojo {

    private ConOrder conOrder;

    public ConOrder getConOrder() {
        return conOrder;
    }

    public void setConOrder(ConOrder conOrder) {
        this.conOrder = conOrder;
    }
}
