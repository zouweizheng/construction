package com.company.project.foundation.service.impl;

import com.company.project.foundation.dao.OpOrderMapper;
import com.company.project.foundation.model.OpOrder;
import com.company.project.foundation.service.OpOrderService;
import com.company.project.foundation.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * Created by CodeGenerator on 2018/03/30.
 */
@Service
@Transactional
public class OpOrderServiceImpl extends AbstractService<OpOrder> implements OpOrderService {
    @Resource
    private OpOrderMapper opOrderMapper;

}
