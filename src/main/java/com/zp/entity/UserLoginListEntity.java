/**
 * Copyright © 2016郑州金色马甲电子商务有限公司. All rights reserved.
 *
 * @Title: UserLoginListEntity
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
 * @Description: <用户登录记录实体>
 * @author: qrc
 * @date: 2017-02-17 11:24
 * @version: V1.0
 */
public class UserLoginListEntity {

    /**
     *
     */
    private int loginId;

    /**
     *user_id
     */
    private int userId;

    /**
     *
     */
    private Date loginTime;

    /**
     *
     */
    private String loginIp;

    public int getLoginId() {
        return loginId;
    }

    public void setLoginId(int loginId) {
        this.loginId = loginId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Date getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(Date loginTime) {
        this.loginTime = loginTime;
    }

    public String getLoginIp() {
        return loginIp;
    }

    public void setLoginIp(String loginIp) {
        this.loginIp = loginIp;
    }

    @Override
    public String toString() {
        return "UserLoginListEntity{" +
                "loginId=" + loginId +
                ", userId=" + userId +
                ", loginTime=" + loginTime +
                ", loginIp='" + loginIp + '\'' +
                '}';
    }
}
