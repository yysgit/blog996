package com.yys.szcp.entity;

import java.util.Date;

/**
 * <p>
 * 
 * </p>
 *
 * @author yys
 * @since 2021-03-27
 */
public class TArticle  {

    /**
     * id
     */
	private Integer id;
    /**
     * 菜单ID
     */
	private Integer articleMenuId;
    /**
     * 标题
     */
	private String title;
    /**
     * url链接
     */
	private String url;
    /**
     * 备注
     */
	private String remark;
    /**
     * 排序
     */
	private Integer sorting;
    /**
     * 创建人
     */
	private Integer createUserId;
    /**
     * 修改人
     */
	private Integer updateUserId;
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

	public Integer getArticleMenuId() {
		return articleMenuId;
	}

	public void setArticleMenuId(Integer articleMenuId) {
		this.articleMenuId = articleMenuId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getRemark() {
		return remark;
	}

    public Integer getSorting() {
        return sorting;
    }

    public void setSorting(Integer sorting) {
        this.sorting = sorting;
    }

    public void setRemark(String remark) {
		this.remark = remark;
	}

	public Integer getCreateUserId() {
		return createUserId;
	}

	public void setCreateUserId(Integer createUserId) {
		this.createUserId = createUserId;
	}

	public Integer getUpdateUserId() {
		return updateUserId;
	}

	public void setUpdateUserId(Integer updateUserId) {
		this.updateUserId = updateUserId;
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
