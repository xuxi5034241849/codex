package org.xuxi.codex.shiro.token;

import java.io.Serializable;


/**
 * Token 实体
 */
public class UserContextInfo implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * oauth2 token
     */
    private String token;

    /**
     * 用户主体信息
     */
    private User user;

    class User {

        /**
         * 用户ID
         */
        private Long userId;

        /**
         * 用户名
         */
        private String userName;
        /**
         * 姓名
         */
        private String name;
        /**
         * 邮箱
         */
        private String email;
        /**
         * 手机
         */
        private String telephone;

    }


    /**
     * ------------------------下面是  get & set -------------------
     *
     * @return
     */

    public Long getUserId() {
        return user.userId;
    }

    public void setUserId(Long userId) {
        this.user.userId = userId;
    }


    public String getUserName() {
        return user.userName;
    }

    public void setUserName(String userName) {
        this.user.userName = userName;
    }

    public String getName() {
        return user.name;
    }

    public void setName(String name) {
        this.user.name = name;
    }

    public String getEmail() {
        return user.email;
    }

    public void setEmail(String email) {
        this.user.email = email;
    }

    public String getTelephone() {
        return user.telephone;
    }

    public void setTelephone(String telephone) {
        this.user.telephone = telephone;
    }


    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }


    public User getUser() {
        return user;
    }


    public UserContextInfo() {
        this.user = new User();
    }

}
