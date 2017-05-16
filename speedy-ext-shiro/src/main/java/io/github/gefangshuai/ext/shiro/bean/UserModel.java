package io.github.gefangshuai.ext.shiro.bean;

import io.github.gefangshuai.ext.persistence.SupportModel;
import io.github.gefangshuai.ext.shiro.utils.HashUtils;

import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotNull;

/**
 * 用于Shiro登录校验的用户封装，自定义User Model可以继承此类
 */
@MappedSuperclass
public class UserModel extends SupportModel {

    @NotNull
    private String username;        // 登录名
    @NotNull
    private String password;        // 密码
    private String salt;            // 密码盐值

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    /**
     * 加密用户，赋予用户salt值及md5 password
     */
    public void encryptUser(){
        String salt = HashUtils.generateSalt();
        this.setSalt(salt);
        String password = HashUtils.toMd5(this.getPassword(), salt);
        this.setPassword(password);
    }
}
