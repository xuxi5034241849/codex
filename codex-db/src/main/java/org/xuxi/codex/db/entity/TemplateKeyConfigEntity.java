package org.xuxi.codex.db.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

import java.io.Serializable;

/**
 * 模板字段自定义
 *
 * @author xuxi
 * @email 461720498@qq.com
 * @date 20180907164132
 */
@TableName("template_key_config")
public class TemplateKeyConfigEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    @TableId(type = IdType.INPUT)
    private String id;
    /**
     * 用户ID
     */
    private String userId;

    /**
     * 模板ID
     */
    private String templateId;

    /**
     * 标题
     */
    private String title;
    /**
     * 键
     */
    private String key;
    /**
     * 值
     */
    private String value;
    /**
     * 描述
     */
    private String desc;


    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserId() {
        return userId;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }

    public String getTemplateId() {
        return templateId;
    }

    public void setTemplateId(String templateId) {
        this.templateId = templateId;
    }


    public TemplateKeyConfigEntity(){}

    public TemplateKeyConfigEntity(String key, String value) {
        this.key = key;
        this.value = value;
    }
}
