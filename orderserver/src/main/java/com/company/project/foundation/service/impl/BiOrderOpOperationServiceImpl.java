package com.company.project.foundation.service.impl;

import com.company.project.foundation.dao.BiOrderOpOperationMapper;
import com.company.project.foundation.model.BiOrderOpOperation;
import com.company.project.foundation.service.BiOrderOpOperationService;
import com.company.project.foundation.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * Created by CodeGenerator on 2018/08/18.
 */
@Service
@Transactional
public class BiOrderOpOperationServiceImpl extends AbstractService<BiOrderOpOperation> implements BiOrderOpOperationService {
    @Resource
    private BiOrderOpOperationMapper biOrderOpOperationMapper;

}
