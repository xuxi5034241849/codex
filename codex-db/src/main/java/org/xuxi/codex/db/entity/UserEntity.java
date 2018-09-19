package org.xuxi.codex.db.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import org.hibernate.validator.constraints.Length;
import org.xuxi.codex.common.valid.VG;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.util.Date;

/**
 * 用户信息
 *
 * @author xuxi
 * @email 461720498@qq.com
 * @date 2018-09-04 14:47:23
 */
@TableName("user")
public class UserEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId(type = IdType.INPUT)
    @NotBlank(groups = {VG.Passwd.class, VG.Update.class})
    private String id;
    /**
     * 用户名
     */
    @NotBlank(groups = {VG.Login.class, VG.Add.class})
    @Length(min = 5, max = 20, groups = {VG.Add.class})
    @Pattern(regexp = "^[a-zA-Z](\\w{0,19})$", groups = {VG.Add.class})
    private String userName;
    /**
     * 密码
     */
    @NotBlank(groups = {VG.Login.class, VG.Add.class, VG.Passwd.class})
    private String password;

    /**
     * 旧密码
     */
    @NotBlank(groups = {VG.Passwd.class})
    @TableField(exist = false)
    private String oldPassword;

    /**
     * 姓名
     */
    @NotBlank(groups = {VG.Add.class})
    private String name;
    /**
     * 邮箱
     */
    @Email(groups = {VG.Add.class, VG.Update.class})
    private String email;
    /**
     * 手机
     */
    @Pattern(regexp = "^1(\\d{10})$", groups = {VG.Add.class, VG.Update.class})
    private String telephone;
    /**
     * 扰乱码
     */
    private String salt;
    /**
     * 是否是管理员： 0：管理员 2：用户
     */
    private Integer type;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 修改时间
     */
    private Date updateTime;

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserName() {
        return userName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getSalt() {
        return salt;
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

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }


    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }
}
