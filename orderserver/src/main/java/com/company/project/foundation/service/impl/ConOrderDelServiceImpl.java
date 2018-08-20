package com.company.project.foundation.service.impl;

import com.company.project.foundation.dao.ConOrderDelMapper;
import com.company.project.foundation.model.ConOrderDel;
import com.company.project.foundation.service.ConOrderDelService;
import com.company.project.foundation.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * Created by CodeGenerator on 2018/08/18.
 */
@Service
@Transactional
public class ConOrderDelServiceImpl extends AbstractService<ConOrderDel> implements ConOrderDelService {
    @Resource
    private ConOrderDelMapper conOrderDelMapper;

}
