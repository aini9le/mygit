package com.lii.cloud.admin.services;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.lii.cloud.common.base.services.BaseServiceImpl;
import com.lii.cloud.common.entity.admin.po.User;
import com.lii.cloud.db.mysql.mapper.imapper.admin.UserMapper;

@Service
public class UserService extends BaseServiceImpl<User>{
    @Resource
    private UserMapper userMapper;

}