package com.company.project.foundation.web;
import com.company.project.foundation.core.Result;
import com.company.project.foundation.core.ResultGenerator;
import com.company.project.foundation.model.ConOrderDelRecord;
import com.company.project.foundation.service.ConOrderDelRecordService;
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
@RequestMapping("/conorderdelrecord")
public class ConOrderDelRecordController {
    @Resource
    private ConOrderDelRecordService conOrderDelRecordService;

    @PostMapping("/add")
    public Result add(ConOrderDelRecord conOrderDelRecord) {
        conOrderDelRecordService.save(conOrderDelRecord);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/delete")
    public Result delete(@RequestParam Integer id) {
        conOrderDelRecordService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/update")
    public Result update(ConOrderDelRecord conOrderDelRecord) {
        conOrderDelRecordService.update(conOrderDelRecord);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/detail")
    public Result detail(@RequestParam Integer id) {
        ConOrderDelRecord conOrderDelRecord = conOrderDelRecordService.findById(id);
        return ResultGenerator.genSuccessResult(conOrderDelRecord);
    }

    @PostMapping("/list")
    public Result list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        PageHelper.startPage(page, size);
        List<ConOrderDelRecord> list = conOrderDelRecordService.findAll();
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }
}
