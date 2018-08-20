package com.company.project.foundation.web;
import com.company.project.foundation.core.Result;
import com.company.project.foundation.core.ResultGenerator;
import com.company.project.foundation.model.OpOrder;
import com.company.project.foundation.service.OpOrderService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
* Created by CodeGenerator on 2018/08/18.
*/
@RestController
@RequestMapping("/oporder")
public class OpOrderController {
    @Resource
    private OpOrderService opOrderService;

    @PostMapping("/add")
    public Result add(OpOrder opOrder) {
        opOrderService.save(opOrder);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/delete")
    public Result delete(@RequestParam Integer id) {
        opOrderService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/update")
    public Result update(OpOrder opOrder) {
        opOrderService.update(opOrder);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/detail")
    public Result detail(@RequestParam Integer id) {
        OpOrder opOrder = opOrderService.findById(id);
        return ResultGenerator.genSuccessResult(opOrder);
    }

    @PostMapping("/list")
    public Result list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        PageHelper.startPage(page, size);
        List<OpOrder> list = opOrderService.findAll();
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }
}
