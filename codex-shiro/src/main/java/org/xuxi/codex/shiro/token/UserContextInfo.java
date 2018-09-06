package org.xuxi.codex.shiro.token;

import java.io.Serializable;


/**
 * Token 实体
 */
public class UserContextInfo implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long userId;

    private String token;


    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

}