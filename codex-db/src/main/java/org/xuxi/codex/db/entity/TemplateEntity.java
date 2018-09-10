package org.xuxi.codex.db.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.xuxi.codex.common.valid.VG;
import org.xuxi.codex.common.valid.VM;

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
    @NotNull(message = VM.NotNull, groups = VG.Get.class)
    @TableId(type = IdType.INPUT)
    private Long templateId;
    /**
     * 用户ID
     */
    @JsonIgnore
    private Long userId;
    /**
     * 模板名称
     */
    @NotNull(message = VM.NotNull, groups = Template1.class)
    private String templateName;
    /**
     * 模板类型 1：CRUD
     */
    @NotNull(message = VM.NotNull, groups = VG.List.class)
    private Integer type;
    /**
     * 创建时间
     */
    private Date createTime;


    //---------------以下为 扩展 字段------------------

    @NotBlank(message = VM.NotBlank, groups = Template1.class)
    @TableField(exist = false)
    private String entity;

    @NotBlank(message = VM.NotBlank, groups = Template1.class)
    @TableField(exist = false)
    private String mapper;

    @NotBlank(message = VM.NotBlank, groups = Template1.class)
    @TableField(exist = false)
    private String service;

    @NotBlank(message = VM.NotBlank, groups = Template1.class)
    @TableField(exist = false)
    private String serviceImpl;

    @NotBlank(message = VM.NotBlank, groups = Template1.class)
    @TableField(exist = false)
    private String mapperXML;


    public interface Template1 {
    }

    public void setTemplateId(Long templateId) {
        this.templateId = templateId;
    }

    public Long getTemplateId() {
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

    public String getEntity() {
        return entity;
    }

    public void setEntity(String entity) {
        this.entity = entity;
    }

    public String getMapper() {
        return mapper;
    }

    public void setMapper(String mapper) {
        this.mapper = mapper;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public String getServiceImpl() {
        return serviceImpl;
    }

    public void setServiceImpl(String serviceImpl) {
        this.serviceImpl = serviceImpl;
    }

    public String getMapperXML() {
        return mapperXML;
    }

    public void setMapperXML(String mapperXML) {
        this.mapperXML = mapperXML;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}


