package com.zonekey.test.entity;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "role")
public class Role implements Serializable {

	private static final long serialVersionUID = -3786600674416484607L;

	private Integer id;
	private String name;
	private String description;
	// 角色代号
	private String code;
	private String createDate;
	private Integer createUser;
	private String modifyDate;
	private Integer modifyUser;
	private Set<User> uses;
	private Set<Permission> permissions;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Column
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
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
	public Integer getModifyUser() {
		return modifyUser;
	}

	public void setModifyUser(Integer modifyUser) {
		this.modifyUser = modifyUser;
	}

	/**
	 * 被控方
	 * 
	 * @return
	 */
	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "roles")
	// @JoinTable(name = "user_role")
	public Set<User> getUses() {
		return uses;
	}

	public void setUses(Set<User> uses) {
		this.uses = uses;
	}

	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	// @JoinTable(name = "role_permission")
	public Set<Permission> getPermissions() {
		return permissions;
	}

	public void setPermissions(Set<Permission> permissions) {
		this.permissions = permissions;
	}

	@Override
	public String toString() {
		return "Role [id=" + id + ", name=" + name + ", description=" + description + ", code=" + code + ", createDate=" + createDate + ", createUser=" + createUser + ", modifyDate=" + modifyDate
				+ ", modifyUser=" + modifyUser + "]";
	}

}
