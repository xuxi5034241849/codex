package org.xuxi.codex.db.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import org.xuxi.codex.common.valid.ValidGroup;
import org.xuxi.codex.common.valid.ValidateMessage;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * 用户信息
 * 
 * @author xuxi
 * @email 461720498@qq.com
 * @date 2018-09-03 16:54:09
 */
@TableName("user")
public class UserEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * id
	 */
	@TableId
	private Integer id;
	/**
	 * 用户名
	 */
	@NotBlank(groups = ValidGroup.Login.class)
	private String userName;
	/**
	 * 密码
	 */
	@NotBlank(groups = ValidGroup.Login.class)
	private String password;
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
	/**
	 * 创建时间
	 */
	private Integer createTime;
	/**
	 * 修改时间
	 */
	private Integer updateTime;

	/**
	 * 设置：id
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	/**
	 * 获取：id
	 */
	public Integer getId() {
		return id;
	}
	/**
	 * 设置：用户名
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}
	/**
	 * 获取：用户名
	 */
	public String getUserName() {
		return userName;
	}
	/**
	 * 设置：密码
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	/**
	 * 获取：密码
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * 设置：姓名
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * 获取：姓名
	 */
	public String getName() {
		return name;
	}
	/**
	 * 设置：邮箱
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	/**
	 * 获取：邮箱
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * 设置：手机
	 */
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	/**
	 * 获取：手机
	 */
	public String getTelephone() {
		return telephone;
	}
	/**
	 * 设置：创建时间
	 */
	public void setCreateTime(Integer createTime) {
		this.createTime = createTime;
	}
	/**
	 * 获取：创建时间
	 */
	public Integer getCreateTime() {
		return createTime;
	}
	/**
	 * 设置：修改时间
	 */
	public void setUpdateTime(Integer updateTime) {
		this.updateTime = updateTime;
	}
	/**
	 * 获取：修改时间
	 */
	public Integer getUpdateTime() {
		return updateTime;
	}
}
