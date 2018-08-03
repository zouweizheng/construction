package com.company.project.foundation.service.impl;

import com.company.project.foundation.dao.BiOrderMapper;
import com.company.project.foundation.model.BiOrder;
import com.company.project.foundation.service.BiOrderService;
import com.company.project.foundation.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * Created by CodeGenerator on 2018/07/10.
 */
@Service
@Transactional
public class BiOrderServiceImpl extends AbstractService<BiOrder> implements BiOrderService {
    @Resource
    private BiOrderMapper biOrderMapper;

}
