package com.yys.szcp.entity;

import java.util.Date;


/**
 * <p>
 * 管理用户信息表
 * </p>
 *
 * @author yys
 * @since 2020-02-02
 */

public class DbAdminUser{

    private static final long serialVersionUID = 1L;

	private Integer id;
    /**
     * 所属角色（外键）
     */
	private Integer roleId;
    /**
     * 机构(外键)
     */
	private Integer organId;
    /**
     * 所属父级
     */
	private Integer parentId;
    /**
     * 管理员名称
     */
	private String adminName;
    /**
     * 真实姓名
     */
    private String adminFullname;
    /**
     * 管理员手机号
     */
	private String adminPhone;
    /**
     * 管理员密码
     */
	private String adminPassword;
    /**
     * 创建时间
     */
	private Date createTime;
    /**
     * 更新时间
     */
	private Date updateTime;
    /**
     * 最后登录时间
     */
	private Date lastLoginTime;
    /**
     * 是否删除0: 未删除, 1表示删除
     */
	private Integer deleteStatus;
    /**
     * 机构登级
     */
	private Integer level;


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public Integer getOrganId() {
		return organId;
	}

	public void setOrganId(Integer organId) {
		this.organId = organId;
	}

	public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	public String getAdminName() {
		return adminName;
	}

	public void setAdminName(String adminName) {
		this.adminName = adminName;
	}

	public String getAdminPhone() {
		return adminPhone;
	}

	public void setAdminPhone(String adminPhone) {
		this.adminPhone = adminPhone;
	}

	public String getAdminPassword() {
		return adminPassword;
	}

	public void setAdminPassword(String adminPassword) {
		this.adminPassword = adminPassword;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public Date getLastLoginTime() {
		return lastLoginTime;
	}

	public void setLastLoginTime(Date lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}

	public Integer getDeleteStatus() {
		return deleteStatus;
	}

	public void setDeleteStatus(Integer deleteStatus) {
		this.deleteStatus = deleteStatus;
	}

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public String getAdminFullname() {
        return adminFullname;
    }

    public void setAdminFullname(String adminFullname) {
        this.adminFullname = adminFullname;
    }
}
