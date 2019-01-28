//package com.lii.cloud.db.cache;
//
//import com.lii.cloud.common.entity.base.dto.BaseParameterIdDTO;
//import com.lii.cloud.common.entity.basis.dto.organization.BasisGroupInfoByUserTypeDto;
//import com.lii.cloud.common.entity.basis.dto.organization.BasisGroupServicesDto;
//import com.lii.cloud.common.entity.basis.dto.shiros.ShiropermissionRoleDto;
//import com.lii.cloud.common.entity.basis.po.organization.BasisGroupServices;
//import com.lii.cloud.common.entity.basis.po.organization.BasisGroupUser;
//import com.lii.cloud.common.entity.basis.po.organization.BasisUser;
//import com.lii.cloud.common.entity.basis.po.organization.MenuInfo;
//import com.lii.cloud.common.entity.basis.po.organization.RoleInfo;
//import com.lii.cloud.common.entity.basis.po.organization.UserInfo;
//import com.lii.cloud.common.entity.basis.po.shiros.BasisShiropermission;
//import com.lii.cloud.common.entity.basis.vo.UserInfoVO;
//import com.lii.cloud.common.entity.basis.vo.shiros.ShiroPermissionVo;
//import com.lii.cloud.common.entity.enums.organization.GroupServiceTenantEnum;
//import com.lii.cloud.db.mysql.mapper.iMapper.basis.organization.*;
//import com.lii.cloud.db.mysql.mapper.iMapper.basis.shiros.BasisShiropermissionMapper;
//import org.apache.commons.collections.CollectionUtils;
//import org.apache.commons.lang.StringUtils;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.cache.annotation.CacheEvict;
//import org.springframework.cache.annotation.CachePut;
//import org.springframework.cache.annotation.Cacheable;
//import org.springframework.stereotype.Service;
//
//import javax.annotation.Resource;
//import java.util.ArrayList;
//import java.util.HashSet;
//import java.util.List;
//import java.util.Set;
//
///**
// * @CacheEvict 支持如下几个参数：
// * value：缓存位置名称，不能为空，同上
// * key：缓存的key，默认为空，同上
// * condition：触发条件，只有满足条件的情况才会清除缓存，默认为空，支持SpEL
// * allEntries：true表示清除value中的全部缓存，默认为false
// * <p>
// * 除了上述使用方法参数作为key之外，Spring还为我们提供了一个root对象可以用来生成key。通过该root对象我们可以获取到以下信息。
// * 属性名称             描述                                                 示例
// * methodName   当前方法名                                      #root.methodName
// * method       当前方法                                         #root.method.name
// * target       当前被调用的对象                           #root.target
// * targetClass  当前被调用的对象的class       #root.targetClass
// * args         当前方法参数组成的数组                 #root.args[0]
// * caches       当前被调用的方法使用的Cache    #root.caches[0].name
// */
//@Service
//public class CacheService {
//
//    @Autowired
//    protected UserInfoMapper userMapper;
//    @Autowired
//    protected MenuInfoMapper menuMapper;
//    @Autowired
//    protected BasisUserMapper basisUserMapper;
//    @Resource
//    protected BasisGroupUserMapper groupUserMapper;
//    @Resource
//    protected BasisGroupInfoMapper groupInfoMapper;
//    @Resource
//    protected RoleInfoMapper roleMapper;
//    @Resource
//    protected DeptInfoMapper deptMapper;
//    @Resource
//    protected BasisShiropermissionMapper spMapper;
//    @Resource
//    protected BasisGroupServicesMapper servicesMapper;
//
//    @Cacheable(value = "usercache", key = "'selectUserById:id_'+#id")
//    public UserInfo selectUserById(String id) {
//        UserInfo user = userMapper.selectByPrimaryKey(id);
//        return user;
//    }
//
////    @Cacheable(value = "usercache", key = "'selectUserByAccout:account_'+#account")
//    public UserInfoVO selectUserByAccout(String account) {
//        UserInfoVO user = new UserInfoVO();
//        BasisUser bauser = new BasisUser();
//        bauser.setUserid(account);
//        BasisUser basisUser = basisUserMapper.selectOne(bauser);
//        if (null != basisUser) {
//            BasisGroupInfoByUserTypeDto groupDto = new BasisGroupInfoByUserTypeDto();
//            groupDto.setUserId(basisUser.getId());
//            user.setGroupUsers(groupUserMapper.getUserManagesById(groupDto));
//            BaseParameterIdDTO idDTO = new BaseParameterIdDTO();
//            idDTO.setId(basisUser.getId());
//            List<RoleInfo> roleIds = roleMapper.getRoleInfoByUserId(idDTO);
//            user.setRoleIds(roleIds);
////        	user.setRoleIds(roleMapper.selectAll());  //测试用
//            user.setDepts(deptMapper.getDeptByUserId(idDTO));
//            // 查询用户所在所有组服务对象信息
//            BasisGroupServicesDto serDto = new BasisGroupServicesDto();
//            serDto.setTenantId(GroupServiceTenantEnum.RENT_TENANT.getCode());
//            serDto.setId(basisUser.getId());
//            List<BasisGroupServices> service = servicesMapper.getAllServiceByGroupUserId(serDto);
//            if (CollectionUtils.isNotEmpty(service)) {
//                user.setServices(service);
//            }
//
//            ShiropermissionRoleDto roleDtos = new ShiropermissionRoleDto();
//            List<String> roles = new ArrayList<>();
//            for (RoleInfo r : roleIds) {
//                roles.add(r.getId());
//            }
//            if (CollectionUtils.isNotEmpty(roles)){
//                roleDtos.setRoles(roles);
//                List<BasisShiropermission> splist = spMapper.getShiropermissionByRoles(roleDtos);
//                Set<String> pers = new HashSet<String>();
//                for (BasisShiropermission s : splist) {
//                    pers.add(s.getApplyCode() + ":" + s.getModuleCode() + ":" + s.getPathCode());
//                }
//                if (!CollectionUtils.isEmpty(pers))
//                    user.setPermissions(pers);
//            }
//        }
//        user.setBasisUser(basisUser);
//        return user;
//    }
//    
//    /**
//     * 根据账户  获取 账户对应的权限信息
//     * @param userId
//     * @return
//     */
//    public Set<String> getPathPermission(String userId){
//    	Set<String> pers = new HashSet<String>();
//    	BaseParameterIdDTO idDTO = new BaseParameterIdDTO();
//        idDTO.setId(userId);
//        List<RoleInfo> roleIds = roleMapper.getRoleInfoByUserId(idDTO);
//        if(CollectionUtils.isNotEmpty(roleIds)){
//        	ShiropermissionRoleDto roleDtos = new ShiropermissionRoleDto();
//            List<String> roles = new ArrayList<>();
//            for (RoleInfo r : roleIds) {
//                roles.add(r.getId());
//            }
//            if (CollectionUtils.isNotEmpty(roles)){
//                roleDtos.setRoles(roles);
//                List<BasisShiropermission> splist = spMapper.getShiropermissionByRoles(roleDtos);
//                for (BasisShiropermission s : splist) {
//                    pers.add(s.getApplyCode() + ":" + s.getModuleCode() + ":" + s.getPathCode());
//                }
//            }
//        }
//    	return pers;
//    }
//
//    /**
//     * 清除用户缓存新
//     *
//     * @param account
//     */
//    @CacheEvict(value = "usercache", key = "'selectUserByAccout:account_'+#account")
//    public void removeUserByAccout(String account) {
//
//    }
//
//    /**
//     * 更新缓存
//     *
//     * @param account
//     * @return
//     */
//    @CachePut(value = "usercache", key = "'selectUserByAccout:account_'+#account")
//    public UserInfoVO selectUserByAccoutUpdate(String account) {
////    	UserInfoVO user = userMapper.selectUserByUserName(account);
////        return user;
//        return null;
//    }
//
//    /**
//     * 缓存通过用户id 查询权限信息
//     *
//     * @param userId
//     * @return
//     */
//    @Cacheable(value = "usercache", key = "'getShiroPermissionVoByUserId:id_'+#userId")
//    public ShiroPermissionVo getShiroPermissionVoByUserId(String userId) {
//        ShiroPermissionVo vo = new ShiroPermissionVo();
//        BaseParameterIdDTO idDto = new BaseParameterIdDTO();
//        idDto.setId(userId);
//        // TODO 自动生成的方法存根
//        List<RoleInfo> list = roleMapper.getRoleInfoByUserId(idDto);
//        List<String> roleIds = null;
//        Set<String> roleNames = null;
//        if (!CollectionUtils.isEmpty(list)) {
//            roleIds = new ArrayList<>();
//            roleNames = new HashSet<String>();
//            for (RoleInfo r : list) {
//                roleIds.add(r.getId());
//                roleNames.add(r.getRoleName());
//            }
//        }
//        Set<String> permissions = null;
//        if (!CollectionUtils.isEmpty(roleIds)) {
//            ShiropermissionRoleDto roleDto = new ShiropermissionRoleDto();
//            roleDto.setRoles(roleIds);
//            // 获取角色权限
//            List<BasisShiropermission> sps = spMapper.getShiropermissionByRoles(roleDto);
//            if (!CollectionUtils.isEmpty(sps)) {
//                permissions = new HashSet<String>();
//                for (BasisShiropermission s : sps) {
//                    permissions.add(s.getApplyCode() + ":" + s.getModuleCode() + ":" + s.getPathCode());
//                    vo.getPaths().add(s.getApplyCode() + ":" + s.getModuleCode() + ":" + s.getPathCode());
//                }
//            }
//        }
//
//        vo.setRoleNames(roleNames);
//        vo.setPermissions(permissions);
//        return vo;
//    }
//
//
//    /**
//     * 通过角色id 查询菜单信息
//     *
//     * @param roleId
//     * @return
//     */
//    @Cacheable(value = "usercache", key = "'selectMenuByRoleId:roleId_'+#roleId")
//    public List<MenuInfo> selectMenuByRoleId(String roleId) {
//        if (StringUtils.isNotBlank(roleId)) {
//            return menuMapper.selectByRoleId(roleId);
//        }
//        return null;
//    }
//
//    /**
//     * 通过角色id 查询更新缓存菜单信息
//     *
//     * @param roleId
//     * @return
//     */
//    @CachePut(value = "usercache", key = "'selectMenuByRoleId:roleId_'+#roleId")
//    public List<MenuInfo> selectMenuByRoleIdUpdate(String roleId) {
//        if (StringUtils.isNotBlank(roleId)) {
//            return menuMapper.selectByRoleId(roleId);
//        }
//        return null;
//    }
//}
