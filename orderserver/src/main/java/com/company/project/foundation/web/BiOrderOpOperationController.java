package com.company.project.foundation.web;
import com.company.project.foundation.core.Result;
import com.company.project.foundation.core.ResultGenerator;
import com.company.project.foundation.model.BiOrderOpOperation;
import com.company.project.foundation.service.BiOrderOpOperationService;
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
@RequestMapping("/biorderopoperation")
public class BiOrderOpOperationController {
    @Resource
    private BiOrderOpOperationService biOrderOpOperationService;

    @PostMapping("/add")
    public Result add(BiOrderOpOperation biOrderOpOperation) {
        biOrderOpOperationService.save(biOrderOpOperation);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/delete")
    public Result delete(@RequestParam Integer id) {
        biOrderOpOperationService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/update")
    public Result update(BiOrderOpOperation biOrderOpOperation) {
        biOrderOpOperationService.update(biOrderOpOperation);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/detail")
    public Result detail(@RequestParam Integer id) {
        BiOrderOpOperation biOrderOpOperation = biOrderOpOperationService.findById(id);
        return ResultGenerator.genSuccessResult(biOrderOpOperation);
    }

    @PostMapping("/list")
    public Result list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        PageHelper.startPage(page, size);
        List<BiOrderOpOperation> list = biOrderOpOperationService.findAll();
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }
}
