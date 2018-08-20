package com.company.project.foundation.service.impl;

import com.company.project.foundation.dao.ConOrderDelRecordMapper;
import com.company.project.foundation.model.ConOrderDelRecord;
import com.company.project.foundation.service.ConOrderDelRecordService;
import com.company.project.foundation.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * Created by CodeGenerator on 2018/08/18.
 */
@Service
@Transactional
public class ConOrderDelRecordServiceImpl extends AbstractService<ConOrderDelRecord> implements ConOrderDelRecordService {
    @Resource
    private ConOrderDelRecordMapper conOrderDelRecordMapper;

}
