package org.xuxi.codex.db.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.xuxi.codex.common.valid.VG;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

/**
 * @author xuxi
 * @email 461720498@qq.com
 * @date 20180907165600
 */
@TableName("template")
@JsonInclude(value=JsonInclude.Include.NON_NULL)
public class TemplateEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    @NotBlank( groups = VG.Get.class)
    @TableId(type = IdType.INPUT)
    private String templateId;
    /**
     * 用户ID
     */
    @JsonIgnore
    private String userId;
    /**
     * 模板名称
     */
    @NotNull(groups = Template1.class)
    private String templateName;
    /**
     * 模板类型 1：CRUD
     */
    @NotNull(groups = VG.List.class)
    private Integer type;
    /**
     * 创建时间
     */
    private Date createTime;


    //---------------以下为 扩展 字段------------------

    @NotBlank( groups = Template1.class)
    @TableField(exist = false)
    private String entityPackagePath;

    @NotBlank( groups = Template1.class)
    @TableField(exist = false)
    private String mapperPackagePath;

    @NotBlank( groups = Template1.class)
    @TableField(exist = false)
    private String servicePackagePath;

    @NotBlank( groups = Template1.class)
    @TableField(exist = false)
    private String serviceImplPackagePath;

    @NotBlank( groups = Template1.class)
    @TableField(exist = false)
    private String mapperXmlPackagePath;


    public interface Template1 {
    }

    public void setTemplateId(String templateId) {
        this.templateId = templateId;
    }

    public String getTemplateId() {
        return templateId;
    }

    public void setTemplateName(String templateName) {
        this.templateName = templateName;
    }

    public String getTemplateName() {
        return templateName;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getType() {
        return type;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public String getEntityPackagePath() {
        return entityPackagePath;
    }

    public void setEntityPackagePath(String entityPackagePath) {
        this.entityPackagePath = entityPackagePath;
    }

    public String getMapperPackagePath() {
        return mapperPackagePath;
    }

    public void setMapperPackagePath(String mapperPackagePath) {
        this.mapperPackagePath = mapperPackagePath;
    }

    public String getServicePackagePath() {
        return servicePackagePath;
    }

    public void setServicePackagePath(String servicePackagePath) {
        this.servicePackagePath = servicePackagePath;
    }

    public String getServiceImplPackagePath() {
        return serviceImplPackagePath;
    }

    public void setServiceImplPackagePath(String serviceImplPackagePath) {
        this.serviceImplPackagePath = serviceImplPackagePath;
    }

    public String getMapperXmlPackagePath() {
        return mapperXmlPackagePath;
    }

    public void setMapperXmlPackagePath(String mapperXmlPackagePath) {
        this.mapperXmlPackagePath = mapperXmlPackagePath;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}


