/**
 * 
 */
package com.zonekey.test.entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;

/**
 * @author admin
 * 
 */
@Entity
@Table(name = "User", catalog = "testspring")
public class User implements java.io.Serializable {
	private static final long serialVersionUID = -956206797526777167L;
	private Integer id;
	private String name;
	private String username;
	private String password;
	private String email;
	private String salt;
	private boolean locked;
	private String createDate;
	private Integer createUser;
	private String modifyDate;
	private String modifyUser;
	private boolean rememberMe;
	private String token;
	// 多对多关联
	private Set<Role> roles;
	private Set<Permission> permissions;

	@Id
	@GenericGenerator(name = "generator", strategy = "increment")
	@GeneratedValue(generator = "generator")
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "name", length = 20)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "loginname", length = 100)
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Column(name = "password", length = 100)
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Column(name = "email", length = 30)
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "create_date")
	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	@Column(name = "create_user")
	public Integer getCreateUser() {
		return createUser;
	}

	public void setCreateUser(Integer createUser) {
		this.createUser = createUser;
	}

	@Column(name = "modify_date")
	public String getModifyDate() {
		return modifyDate;
	}

	public void setModifyDate(String modifyDate) {
		this.modifyDate = modifyDate;
	}

	@Column(name = "modify_user")
	public String getModifyUser() {
		return modifyUser;
	}

	public void setModifyUser(String modifyUser) {
		this.modifyUser = modifyUser;
	}

	/**
	 * 被控方使用mapredby<br>
	 * 1）只有OneToOne,OneToMany,ManyToMany上才有mappedBy属性，ManyToOne不存在该属性；<br>
	 * 2) MappedBy标签一定定义在the owned side(被拥有方)，他指向the owning side(拥有方)；<br>
	 * 3) MappedBy的含义，应理解为，拥有方能够自动维护跟被拥有方的关系。<br>
	 * 4）MappedBy跟JoinColumn/JoinTable总是处于互斥的一方。
	 * 
	 * 主控方
	 * 
	 * @joinColumns 指向自己的外键
	 * @inverseJoinColumn 指向关联属性的外键
	 * @return
	 */
	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinTable(name = "user_role", joinColumns = { @JoinColumn(name = "user_id") }, inverseJoinColumns = { @JoinColumn(name = "role_id") })
	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinTable(name = "user_permission", joinColumns = { @JoinColumn(name = "user_id") }, inverseJoinColumns = { @JoinColumn(name = "permission_id") })
	public Set<Permission> getPermissions() {
		return permissions;
	}

	public void setPermissions(Set<Permission> permissions) {
		this.permissions = permissions;
	}

	@Column(name = "salt")
	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	@Column(name = "locked")
	public boolean isLocked() {
		return locked;
	}

	public void setLocked(boolean locked) {
		this.locked = locked;
	}

	/**
	 * 获取用户登录名和盐值
	 * 
	 * @return
	 */
	@Transient
	public String getCredentialsSalt() {
		return username + salt;
	}

	@Transient
	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	/**
	 * 
	 * @return
	 */
	@Transient
	public boolean isRememberMe() {
		return rememberMe;
	}

	public void setRememberMe(boolean rememberMe) {
		this.rememberMe = rememberMe;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", username=" + username + ", password=" + password + ", email=" + email + ", salt=" + salt + ", locked=" + locked + ", createDate=" + createDate
				+ ", createUser=" + createUser + ", modifyDate=" + modifyDate + ", modifyUser=" + modifyUser + ", rememberMe=" + rememberMe + ", token=" + token + ", roles=" + roles
				+ ", permissions=" + permissions + "]";
	}

}
