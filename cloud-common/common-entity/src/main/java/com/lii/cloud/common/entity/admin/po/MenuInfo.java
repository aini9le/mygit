package com.lii.cloud.common.entity.admin.po;

import javax.persistence.Column;
import javax.persistence.Table;

import com.lii.cloud.common.entity.base.BaseEntityIdAndDateEnable;

@Table(name = "basis_menu_info")
public class MenuInfo extends BaseEntityIdAndDateEnable{

	private static final long serialVersionUID = 7285672674361638702L;

	/**
     * 菜单名称
     */
    @Column(name = "menu_name")
    private String menuName;

    /**
     * 菜单路径
     */
    @Column(name = "menu_url")
    private String menuUrl;

    /**
     * 菜单权限路径
     */
    @Column(name = "menu_authority")
    private String menuAuthority;

    /**
     * 菜单类型 D: 文件夹      U:路径
     */
    @Column(name = "menu_type")
    private String menuType;

    /**
     * 菜单说明
     */
    @Column(name = "menu_info")
    private String menuInfo;
    /**
     * 上级菜单id 默认0  既为第一级菜单
     */
    @Column(name = "sup_id")
    private String supId;
    /**
     * 图标
     */
    @Column(name = "iconcls")
    private String iconcls; 
    /**
     * 排序
     */
    @Column(name = "menu_sort")
    private Integer menuSort;
    /**
     * 请求打开方式
     */
    @Column(name = "target_type")
    private String targetType;

    /**
     * 获取菜单名称
     *
     * @return menu_name - 菜单名称
     */
    public String getMenuName() {
        return menuName;
    }

    /**
     * 设置菜单名称
     *
     * @param menuName 菜单名称
     */
    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    /**
     * 获取菜单路径
     *
     * @return menu_url - 菜单路径
     */
    public String getMenuUrl() {
        return menuUrl;
    }

    /**
     * 设置菜单路径
     *
     * @param menuUrl 菜单路径
     */
    public void setMenuUrl(String menuUrl) {
        this.menuUrl = menuUrl;
    }

    /**
     * 获取菜单权限路径
     *
     * @return menu_authority - 菜单权限路径
     */
    public String getMenuAuthority() {
        return menuAuthority;
    }

    /**
     * 设置菜单权限路径
     *
     * @param menuAuthority 菜单权限路径
     */
    public void setMenuAuthority(String menuAuthority) {
        this.menuAuthority = menuAuthority;
    }

    /**
     * 获取菜单类型 D: 文件夹      U:路径
     *
     * @return menu_type - 菜单类型 D: 文件夹      U:路径
     */
    public String getMenuType() {
        return menuType;
    }

    /**
     * 设置菜单类型 D: 文件夹      U:路径
     *
     * @param menuType 菜单类型 D: 文件夹      U:路径
     */
    public void setMenuType(String menuType) {
        this.menuType = menuType;
    }

    /**
     * 获取菜单说明
     *
     * @return menu_info - 菜单说明
     */
    public String getMenuInfo() {
        return menuInfo;
    }

    /**
     * 设置菜单说明
     *
     * @param menuInfo 菜单说明
     */
    public void setMenuInfo(String menuInfo) {
        this.menuInfo = menuInfo;
    }

	public String getSupId() {
		return supId;
	}

	public void setSupId(String supId) {
		this.supId = supId;
	}

	public String getIconcls() {
		return iconcls;
	}

	public void setIconcls(String iconcls) {
		this.iconcls = iconcls;
	}

	public Integer getMenuSort() {
		return menuSort;
	}

	public void setMenuSort(Integer menuSort) {
		this.menuSort = menuSort;
	}

	public String getTargetType() {
		return targetType;
	}

	public void setTargetType(String targetType) {
		this.targetType = targetType;
	}
}