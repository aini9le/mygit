package com.lii.cloud.common.entity.admin.po;

import javax.persistence.*;

@Table(name = "basis_user")
public class User {
    /**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    /**
     * 成员UserID。对应管理端的帐号
     */
    private String userid;

    /**
     * 成员名称
     */
    private String name;

    /**
     * 手机号码，第三方仅通讯录套件可获取
     */
    private String mobile;

    /**
     * 职位信息；第三方仅通讯录套件可获取
     */
    private String position;

    /**
     * 性别。0表示未定义，1表示男性，2表示女性
     */
    private String gender;

    /**
     * 邮箱，第三方仅通讯录套件可获取
     */
    private String email;

    /**
     * 上级字段，标识是否为上级；第三方仅通讯录套件可获取
     */
    private String isleader;

    /**
     * 头像url。注：如果要获取小图将url最后的”/0”改成”/100”即可
     */
    private String avatar;

    /**
     * 座机。第三方仅通讯录套件可获取
     */
    private String telephone;

    /**
     * 英文名；第三方仅通讯录套件可获取
     */
    @Column(name = "english_name")
    private String englishName;

    /**
     * 激活状态: 1=已激活，2=已禁用，4=未激活。
     */
    private String status;

    /**
     * 员工个人二维码，扫描可添加为外部联系人；第三方暂不可获取
     */
    @Column(name = "qr_code")
    private String qrCode;

    /**
     * 密码
     */
    private String password;

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
     * 获取成员UserID。对应管理端的帐号
     *
     * @return userid - 成员UserID。对应管理端的帐号
     */
    public String getUserid() {
        return userid;
    }

    /**
     * 设置成员UserID。对应管理端的帐号
     *
     * @param userid 成员UserID。对应管理端的帐号
     */
    public void setUserid(String userid) {
        this.userid = userid;
    }

    /**
     * 获取成员名称
     *
     * @return name - 成员名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置成员名称
     *
     * @param name 成员名称
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取手机号码，第三方仅通讯录套件可获取
     *
     * @return mobile - 手机号码，第三方仅通讯录套件可获取
     */
    public String getMobile() {
        return mobile;
    }

    /**
     * 设置手机号码，第三方仅通讯录套件可获取
     *
     * @param mobile 手机号码，第三方仅通讯录套件可获取
     */
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    /**
     * 获取职位信息；第三方仅通讯录套件可获取
     *
     * @return position - 职位信息；第三方仅通讯录套件可获取
     */
    public String getPosition() {
        return position;
    }

    /**
     * 设置职位信息；第三方仅通讯录套件可获取
     *
     * @param position 职位信息；第三方仅通讯录套件可获取
     */
    public void setPosition(String position) {
        this.position = position;
    }

    /**
     * 获取性别。0表示未定义，1表示男性，2表示女性
     *
     * @return gender - 性别。0表示未定义，1表示男性，2表示女性
     */
    public String getGender() {
        return gender;
    }

    /**
     * 设置性别。0表示未定义，1表示男性，2表示女性
     *
     * @param gender 性别。0表示未定义，1表示男性，2表示女性
     */
    public void setGender(String gender) {
        this.gender = gender;
    }

    /**
     * 获取邮箱，第三方仅通讯录套件可获取
     *
     * @return email - 邮箱，第三方仅通讯录套件可获取
     */
    public String getEmail() {
        return email;
    }

    /**
     * 设置邮箱，第三方仅通讯录套件可获取
     *
     * @param email 邮箱，第三方仅通讯录套件可获取
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * 获取上级字段，标识是否为上级；第三方仅通讯录套件可获取
     *
     * @return isleader - 上级字段，标识是否为上级；第三方仅通讯录套件可获取
     */
    public String getIsleader() {
        return isleader;
    }

    /**
     * 设置上级字段，标识是否为上级；第三方仅通讯录套件可获取
     *
     * @param isleader 上级字段，标识是否为上级；第三方仅通讯录套件可获取
     */
    public void setIsleader(String isleader) {
        this.isleader = isleader;
    }

    /**
     * 获取头像url。注：如果要获取小图将url最后的”/0”改成”/100”即可
     *
     * @return avatar - 头像url。注：如果要获取小图将url最后的”/0”改成”/100”即可
     */
    public String getAvatar() {
        return avatar;
    }

    /**
     * 设置头像url。注：如果要获取小图将url最后的”/0”改成”/100”即可
     *
     * @param avatar 头像url。注：如果要获取小图将url最后的”/0”改成”/100”即可
     */
    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    /**
     * 获取座机。第三方仅通讯录套件可获取
     *
     * @return telephone - 座机。第三方仅通讯录套件可获取
     */
    public String getTelephone() {
        return telephone;
    }

    /**
     * 设置座机。第三方仅通讯录套件可获取
     *
     * @param telephone 座机。第三方仅通讯录套件可获取
     */
    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    /**
     * 获取英文名；第三方仅通讯录套件可获取
     *
     * @return english_name - 英文名；第三方仅通讯录套件可获取
     */
    public String getEnglishName() {
        return englishName;
    }

    /**
     * 设置英文名；第三方仅通讯录套件可获取
     *
     * @param englishName 英文名；第三方仅通讯录套件可获取
     */
    public void setEnglishName(String englishName) {
        this.englishName = englishName;
    }

    /**
     * 获取激活状态: 1=已激活，2=已禁用，4=未激活。
     *
     * @return status - 激活状态: 1=已激活，2=已禁用，4=未激活。
     */
    public String getStatus() {
        return status;
    }

    /**
     * 设置激活状态: 1=已激活，2=已禁用，4=未激活。
     *
     * @param status 激活状态: 1=已激活，2=已禁用，4=未激活。
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * 获取员工个人二维码，扫描可添加为外部联系人；第三方暂不可获取
     *
     * @return qr_code - 员工个人二维码，扫描可添加为外部联系人；第三方暂不可获取
     */
    public String getQrCode() {
        return qrCode;
    }

    /**
     * 设置员工个人二维码，扫描可添加为外部联系人；第三方暂不可获取
     *
     * @param qrCode 员工个人二维码，扫描可添加为外部联系人；第三方暂不可获取
     */
    public void setQrCode(String qrCode) {
        this.qrCode = qrCode;
    }

    /**
     * 获取密码
     *
     * @return password - 密码
     */
    public String getPassword() {
        return password;
    }

    /**
     * 设置密码
     *
     * @param password 密码
     */
    public void setPassword(String password) {
        this.password = password;
    }
}