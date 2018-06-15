package com.company.project.foundation.web;
import com.company.project.foundation.core.Result;
import com.company.project.foundation.core.ResultGenerator;
import com.company.project.foundation.model.OpOperationConOrder;
import com.company.project.foundation.service.OpOperationConOrderService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
* Created by CodeGenerator on 2017/12/19.
*/
@RestController
@RequestMapping("/opoperationconorder")
public class OpOperationConOrderController {
    @Resource
    private OpOperationConOrderService opOperationConOrderService;

    @PostMapping("/add")
    public Result add(OpOperationConOrder opOperationConOrder) {
        opOperationConOrderService.save(opOperationConOrder);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/delete")
    public Result delete(@RequestParam Integer id) {
        opOperationConOrderService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/update")
    public Result update(OpOperationConOrder opOperationConOrder) {
        opOperationConOrderService.update(opOperationConOrder);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/detail")
    public Result detail(@RequestParam Integer id) {
        OpOperationConOrder opOperationConOrder = opOperationConOrderService.findById(id);
        return ResultGenerator.genSuccessResult(opOperationConOrder);
    }

    @PostMapping("/list")
    public Result list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        PageHelper.startPage(page, size);
        List<OpOperationConOrder> list = opOperationConOrderService.findAll();
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }
}
