package com.company.project.foundation.service.impl;

import com.company.project.foundation.dao.OpOrderConOrderMapper;
import com.company.project.foundation.model.OpOrderConOrder;
import com.company.project.foundation.service.OpOrderConOrderService;
import com.company.project.foundation.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * Created by CodeGenerator on 2018/07/10.
 */
@Service
@Transactional
public class OpOrderConOrderServiceImpl extends AbstractService<OpOrderConOrder> implements OpOrderConOrderService {
    @Resource
    private OpOrderConOrderMapper opOrderConOrderMapper;

}
