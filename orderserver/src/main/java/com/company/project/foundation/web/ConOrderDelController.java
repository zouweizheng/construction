package com.company.project.foundation.web;
import com.company.project.foundation.core.Result;
import com.company.project.foundation.core.ResultGenerator;
import com.company.project.foundation.model.ConOrderDel;
import com.company.project.foundation.service.ConOrderDelService;
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
@RequestMapping("/conorderdel")
public class ConOrderDelController {
    @Resource
    private ConOrderDelService conOrderDelService;

    @PostMapping("/add")
    public Result add(ConOrderDel conOrderDel) {
        conOrderDelService.save(conOrderDel);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/delete")
    public Result delete(@RequestParam Integer id) {
        conOrderDelService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/update")
    public Result update(ConOrderDel conOrderDel) {
        conOrderDelService.update(conOrderDel);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/detail")
    public Result detail(@RequestParam Integer id) {
        ConOrderDel conOrderDel = conOrderDelService.findById(id);
        return ResultGenerator.genSuccessResult(conOrderDel);
    }

    @PostMapping("/list")
    public Result list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        PageHelper.startPage(page, size);
        List<ConOrderDel> list = conOrderDelService.findAll();
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }
}
