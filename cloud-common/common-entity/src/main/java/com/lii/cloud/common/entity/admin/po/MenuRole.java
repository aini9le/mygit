package com.lii.cloud.common.entity.admin.po;

import javax.persistence.*;

@Table(name = "basis_menu_role")
public class MenuRole {
    /**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    /**
     * 菜单主键id （rent_menu_info表）
     */
    @Column(name = "menu_id")
    private String menuId;

    /**
     * 角色id（rent_role_info）
     */
    @Column(name = "role_id")
    private String roleId;

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
     * 获取菜单主键id （rent_menu_info表）
     *
     * @return menu_id - 菜单主键id （rent_menu_info表）
     */
    public String getMenuId() {
        return menuId;
    }

    /**
     * 设置菜单主键id （rent_menu_info表）
     *
     * @param menuId 菜单主键id （rent_menu_info表）
     */
    public void setMenuId(String menuId) {
        this.menuId = menuId;
    }

    /**
     * 获取角色id（rent_role_info）
     *
     * @return role_id - 角色id（rent_role_info）
     */
    public String getRoleId() {
        return roleId;
    }

    /**
     * 设置角色id（rent_role_info）
     *
     * @param roleId 角色id（rent_role_info）
     */
    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }
}