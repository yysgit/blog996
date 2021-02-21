package com.yys.szcp.entity;

import java.util.Date;

/**
 * <p>
 * 
 * </p>
 *
 * @author yys
 * @since 2020-02-02
 */

public class DbMenu {

    private static final long serialVersionUID = 1L;


	private Integer id;
    /**
     * 父节点
     */
	private Integer parentId;
    /**
     * 菜单名
     */
	private String title;
    /**
     * 图标
     */
	private String icon;
    /**
     * 资源地址
     */
	private String href;
    /**
     * 权限
     */
	private String perms;
    /**
     * true：展开，false：不展开
     */
	private String spread;
    /**
     * 排序
     */
	private Integer sorting;
    /**
     * 创建时间
     */
	private Date createTime;
    /**
     * 修改时间
     */
	private Date updateTime;
    /**
     * 是否删除
     */
	private Integer deleteStatus;


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getHref() {
		return href;
	}

	public void setHref(String href) {
		this.href = href;
	}

	public String getPerms() {
		return perms;
	}

	public void setPerms(String perms) {
		this.perms = perms;
	}

	public String getSpread() {
		return spread;
	}

	public void setSpread(String spread) {
		this.spread = spread;
	}

	public Integer getSorting() {
		return sorting;
	}

	public void setSorting(Integer sorting) {
		this.sorting = sorting;
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

	public Integer getDeleteStatus() {
		return deleteStatus;
	}

	public void setDeleteStatus(Integer deleteStatus) {
		this.deleteStatus = deleteStatus;
	}


}
