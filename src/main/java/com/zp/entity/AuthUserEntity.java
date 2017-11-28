/**
 * Copyright © 2016郑州金色马甲电子商务有限公司. All rights reserved.
 *
 * @Title: AuthUserEntity
 * @Prject: llmj-authority
 * @Package: com.llmj.client
 * @author: qrc
 * @date: 2017-02-17 11:24
 * @version: V1.0
 */
package com.zp.entity;

import java.util.Date;

/**
 * @Title:
 * @Description: <后台管理用户实体>
 * @author: qrc
 * @date: 2017-02-17 11:24
 * @version: V1.0
 */
public class AuthUserEntity {

    /**
     * 用户id
     */
    private int userId;

    /**
     *用户名，登录名
     */
    private String userName;

    /**
     * 角色名称
     */
    private String roleName;
    
    /**
     * 角色id
     */
    private int roleId;
    
    /**
     *密码
     */
    private String userPassWord;

    /**
     *昵称
     */
    private String userNickName;

    /**
     * 真实姓名
     */
    private String userRealname;
    
    /**
     *年龄
     */
    private int userAge;

    /**
     *性别
     */
    private int userSex;

    /**
     *地址
     */
    private String userAddress;

    /**
     *电话
     */
    private String userPhone;

    /**
     * 注册时间
     */
    private Date regtime;
    
    /**
     * 最后一次登录时间
     */
    private Date lastLogintime;
    
    /**
     *级别
     */
    private int level;

    /**
     *
     */
    private int status;

    /**
     *是否删除，0表示未删除，1表示已删除
     */
    private int isDel = 0;

    /**
     *所属工厂
     */
    private int department;
    /**
     *所属工厂名称
     */
    private String departmentName;
    
    
    /**
     *1代表普通管理员2代表超级管理员
     */
    private int userType;
    /**
     *工厂
     */
    private int factoryCode;
    
    /**
     *
     */
    private String code;
    
    public int getFactoryCode() {
		return factoryCode;
	}

	public void setFactoryCode(int factoryCode) {
		this.factoryCode = factoryCode;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPassWord() {
        return userPassWord;
    }

    public void setUserPassWord(String userPassWord) {
        this.userPassWord = userPassWord;
    }

    public String getUserNickName() {
        return userNickName;
    }

    public void setUserNickName(String userNickName) {
        this.userNickName = userNickName;
    }

    public int getUserAge() {
        return userAge;
    }

    public void setUserAge(int userAge) {
        this.userAge = userAge;
    }

    public int getUserSex() {
        return userSex;
    }

    public void setUserSex(int userSex) {
        this.userSex = userSex;
    }

    public String getUserAddress() {
        return userAddress;
    }

    public void setUserAddress(String userAddress) {
        this.userAddress = userAddress;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getIsDel() {
        return isDel;
    }

    public void setIsDel(int isDel) {
        this.isDel = isDel;
    }

    public int getDepartment() {
        return department;
    }

    public void setDepartment(int department) {
        this.department = department;
    }

    public int getUserType() {
        return userType;
    }

    public void setUserType(int userType) {
        this.userType = userType;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public int getRoleId() {
		return roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

	public String getUserRealname() {
		return userRealname;
	}

	public void setUserRealname(String userRealname) {
		this.userRealname = userRealname;
	}

	public Date getRegtime() {
		return regtime;
	}

	public void setRegtime(Date regtime) {
		this.regtime = regtime;
	}

	public Date getLastLogintime() {
		return lastLogintime;
	}

	public void setLastLogintime(Date lastLogintime) {
		this.lastLogintime = lastLogintime;
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	@Override
	public String toString() {
		return "AuthUserEntity [userId=" + userId + ", userName=" + userName + ", roleName=" + roleName + ", roleId="
				+ roleId + ", userPassWord=" + userPassWord + ", userNickName=" + userNickName + ", userRealname="
				+ userRealname + ", userAge=" + userAge + ", userSex=" + userSex + ", userAddress=" + userAddress
				+ ", userPhone=" + userPhone + ", regtime=" + regtime + ", lastLogintime=" + lastLogintime + ", level="
				+ level + ", status=" + status + ", isDel=" + isDel + ", department=" + department + ", departmentName="
				+ departmentName + ", userType=" + userType + ", code=" + code + "]";
	}

}
