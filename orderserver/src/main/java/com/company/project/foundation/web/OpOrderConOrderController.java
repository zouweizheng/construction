package com.company.project.foundation.web;
import com.company.project.foundation.core.Result;
import com.company.project.foundation.core.ResultGenerator;
import com.company.project.foundation.model.OpOrderConOrder;
import com.company.project.foundation.service.OpOrderConOrderService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
* Created by CodeGenerator on 2018/07/10.
*/
@RestController
@RequestMapping("/oporderconorder")
public class OpOrderConOrderController {
    @Resource
    private OpOrderConOrderService opOrderConOrderService;

    @PostMapping("/add")
    public Result add(OpOrderConOrder opOrderConOrder) {
        opOrderConOrderService.save(opOrderConOrder);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/delete")
    public Result delete(@RequestParam Integer id) {
        opOrderConOrderService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/update")
    public Result update(OpOrderConOrder opOrderConOrder) {
        opOrderConOrderService.update(opOrderConOrder);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/detail")
    public Result detail(@RequestParam Integer id) {
        OpOrderConOrder opOrderConOrder = opOrderConOrderService.findById(id);
        return ResultGenerator.genSuccessResult(opOrderConOrder);
    }

    @PostMapping("/list")
    public Result list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        PageHelper.startPage(page, size);
        List<OpOrderConOrder> list = opOrderConOrderService.findAll();
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }
}
