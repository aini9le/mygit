package com.lii.cloud.admin.services;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lii.cloud.common.base.services.BaseServiceImpl;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 */
@Service("rentUserService")
public class UserService extends BaseServiceImpl<BasisUser>{

    @Autowired
    private CacheService cacheService;
    @Resource
    private BasisUserMapper userMapper;
    

    /**
     * 根据用户名称查询用户信息
     * @param account
     * @return
     */
    public UserInfoVO selectUserByAccout(String account) {
        return cacheService.selectUserByAccout(account);
    }

    /**
     * 获取用户信息集合
     * 所有用户
     * SYSTEM : 系统管理员
     * GRADING : 分级管理员
     *
     * @param type
     * @return
     */
    public List<BasisUserVo> getUsersAll(String type){
        List<BasisUserVo> list = new ArrayList<BasisUserVo>();
        List<BasisUser> users = null;
        // 查询全部用户信息
        if (StringUtils.isBlank(type)){
            users = userMapper.selectAll();
        }
        BasisGroupUserTypeDto typeDto = new BasisGroupUserTypeDto();
        if ("SYSTEM".equals(type)){
            typeDto.setType(1);
            users = userMapper.getUserTypeAll(typeDto);
        }
        if ("GRADING".equals(type)){
            typeDto.setType(2);
            users = userMapper.getUserTypeAll(typeDto);
        }
        if (CollectionUtils.isNotEmpty(users)){
            BasisUserVo userVo = null;
            for (BasisUser user: users) {
                userVo = new BasisUserVo();
                list.add(userVo.toVo(user));
            }
        }
        return list;
    }


}