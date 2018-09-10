package org.xuxi.codex.db.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.validator.constraints.Length;
import org.xuxi.codex.common.valid.VG;
import org.xuxi.codex.common.valid.VM;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

/**
 * 数据源链接信息
 *
 * @author xuxi
 * @email 461720498@qq.com
 * @date 2018-09-07 13:07:48
 */
@TableName("data_source")
public class DataSourceEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 数据源ID
     */
    @NotNull(message = VM.NotNull, groups = {VG.Update.class, VG.Delete.class, VG.Get.class})
    @TableId(type = IdType.INPUT)
    private Long id;

    /**
     * 用户ID
     */
    @JsonIgnore
    private Long userId;

    /**
     * 连接名
     */
    @NotBlank(message = VM.NotBlank, groups = VG.Add.class)
    @Length(max = 20, message = VM.Max, groups = {VG.Add.class, VG.Update.class})
    private String name;
    /**
     * 链接URL
     */
    @NotBlank(message = VM.NotBlank, groups = VG.Add.class)
    @Length(max = 200, message = VM.Max, groups = {VG.Add.class, VG.Update.class})
    private String url;
    /**
     * 用户名
     */
    @NotBlank(message = VM.NotBlank, groups = VG.Add.class)
    @Length(max = 50, message = VM.Max, groups = {VG.Add.class, VG.Update.class})
    private String username;
    /**
     * 密码
     */
    @NotBlank(message = VM.NotBlank, groups = VG.Add.class)
    @Length(max = 50, message = VM.Max, groups = {VG.Add.class, VG.Update.class})
    private String passwd;
    /**
     * 是否扰乱的 1：正常的 2:扰乱的
     */
    private Integer isConfused;
    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    public void setId(Long id) {
        this.id = id;
    }


    public Long getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setUrl(String url) {
        this.url = url;
    }


    public String getUrl() {
        return url;
    }


    public void setUsername(String username) {
        this.username = username;
    }


    public String getUsername() {
        return username;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }


    public void setIsConfused(Integer isConfused) {
        this.isConfused = isConfused;
    }


    public Integer getIsConfused() {
        return isConfused;
    }


    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }


    public Date getCreateTime() {
        return createTime;
    }


    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }


    public Date getUpdateTime() {
        return updateTime;
    }


    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
