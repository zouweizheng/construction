package com.company.project.foundation.service.impl;

import com.company.project.foundation.dao.ConOrderMapper;
import com.company.project.foundation.model.ConOrder;
import com.company.project.foundation.service.ConOrderService;
import com.company.project.foundation.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * Created by CodeGenerator on 2018/03/30.
 */
@Service
@Transactional
public class ConOrderServiceImpl extends AbstractService<ConOrder> implements ConOrderService {
    @Resource
    private ConOrderMapper conOrderMapper;

}
