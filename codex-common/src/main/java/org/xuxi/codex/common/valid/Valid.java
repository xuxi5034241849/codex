package org.xuxi.codex.common.valid;

import java.io.Serializable;

/**
 * jsr自定义校验bean
 */

@SuppressWarnings(value = "all")
public class Valid implements Serializable {
    private static final long serialVersionUID = 982912973564659307L;

    // 字段名称
    private String field;

    // 错误提示
    private String message;


    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
