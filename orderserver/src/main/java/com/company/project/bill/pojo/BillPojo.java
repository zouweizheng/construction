package com.company.project.bill.pojo;

import com.company.project.core.pojo.OrderPojo;
import com.company.project.foundation.model.BiOrder;
import com.company.project.foundation.model.OpOperation;

import java.util.List;

/**
 * Created by Administrator on 2017/10/29.
 */
public class BillPojo extends OrderPojo {
    private BiOrder biOrder;
    private List<OpOperation> opOperationList;

    public BiOrder getBiOrder() {
        return biOrder;
    }

    public void setBiOrder(BiOrder biOrder) {
        this.biOrder = biOrder;
    }

    public List<OpOperation> getOpOperationList() {
        return opOperationList;
    }

    public void setOpOperationList(List<OpOperation> opOperationList) {
        this.opOperationList = opOperationList;
    }
}
