package com.lii.cloud.common.entity.admin.po;

import java.util.Date;
import javax.persistence.*;

@Table(name = "basis_role_info")
public class RoleInfo {
    /**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    /**
     * 角色名称
     */
    @Column(name = "role_name")
    private String roleName;

    /**
     * 所有者
     */
    private String owner;

    /**
     * 是否启用  Y：启用   N:不启用
     */
    @Column(name = "is_enable")
    private String isEnable;

    /**
     * 是否公开   0：不公开   1：公开
     */
    @Column(name = "is_public")
    private Integer isPublic;

    /**
     * 创建时间
     */
    @Column(name = "create_date")
    private Date createDate;

    /**
     * 启用/暂停 时间
     */
    @Column(name = "enable_date")
    private Date enableDate;

    /**
     * 角色说明
     */
    @Column(name = "role_info")
    private String roleInfo;

    /**
     * 获取主键
     *
     * @return id - 主键
     */
    public String getId() {
        return id;
    }

    /**
     * 设置主键
     *
     * @param id 主键
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * 获取角色名称
     *
     * @return role_name - 角色名称
     */
    public String getRoleName() {
        return roleName;
    }

    /**
     * 设置角色名称
     *
     * @param roleName 角色名称
     */
    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    /**
     * 获取所有者
     *
     * @return owner - 所有者
     */
    public String getOwner() {
        return owner;
    }

    /**
     * 设置所有者
     *
     * @param owner 所有者
     */
    public void setOwner(String owner) {
        this.owner = owner;
    }

    /**
     * 获取是否启用  Y：启用   N:不启用
     *
     * @return is_enable - 是否启用  Y：启用   N:不启用
     */
    public String getIsEnable() {
        return isEnable;
    }

    /**
     * 设置是否启用  Y：启用   N:不启用
     *
     * @param isEnable 是否启用  Y：启用   N:不启用
     */
    public void setIsEnable(String isEnable) {
        this.isEnable = isEnable;
    }

    /**
     * 获取是否公开   0：不公开   1：公开
     *
     * @return is_public - 是否公开   0：不公开   1：公开
     */
    public Integer getIsPublic() {
        return isPublic;
    }

    /**
     * 设置是否公开   0：不公开   1：公开
     *
     * @param isPublic 是否公开   0：不公开   1：公开
     */
    public void setIsPublic(Integer isPublic) {
        this.isPublic = isPublic;
    }

    /**
     * 获取创建时间
     *
     * @return create_date - 创建时间
     */
    public Date getCreateDate() {
        return createDate;
    }

    /**
     * 设置创建时间
     *
     * @param createDate 创建时间
     */
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    /**
     * 获取启用/暂停 时间
     *
     * @return enable_date - 启用/暂停 时间
     */
    public Date getEnableDate() {
        return enableDate;
    }

    /**
     * 设置启用/暂停 时间
     *
     * @param enableDate 启用/暂停 时间
     */
    public void setEnableDate(Date enableDate) {
        this.enableDate = enableDate;
    }

    /**
     * 获取角色说明
     *
     * @return role_info - 角色说明
     */
    public String getRoleInfo() {
        return roleInfo;
    }

    /**
     * 设置角色说明
     *
     * @param roleInfo 角色说明
     */
    public void setRoleInfo(String roleInfo) {
        this.roleInfo = roleInfo;
    }
}