package com.lii.cloud.admin.services;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.lii.cloud.common.base.services.BaseServiceImpl;
import com.lii.cloud.common.entity.admin.po.Role;
import com.lii.cloud.db.mysql.mapper.imapper.admin.RoleMapper;

@Service
public class RoleService extends BaseServiceImpl<Role>{

    @Resource
    private RoleMapper roleMapper;

}
