package com.company.project.operation.pojo;

import com.company.project.core.pojo.OrderPojo;
import com.company.project.foundation.model.ConOrder;
import com.company.project.foundation.model.OpOperation;
import com.company.project.foundation.model.OpOrder;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2017/10/29.
 */
@Service
public class OperationPojo extends OrderPojo {

    private OpOrder opOrder;
    private List<OpOperation> opOperationList;
    private List<ConOrder> conOrderList;

    public OpOrder getOpOrder() {
        return opOrder;
    }

    public void setOpOrder(OpOrder opOrder) {
        this.opOrder = opOrder;
    }

    public List<OpOperation> getOpOperationList() {
        return opOperationList;
    }

    public void setOpOperationList(List<OpOperation> opOperationList) {
        this.opOperationList = opOperationList;
    }

    public List<ConOrder> getConOrderList() {
        return conOrderList;
    }

    public void setConOrderList(List<ConOrder> conOrderList) {
        this.conOrderList = conOrderList;
    }
}
