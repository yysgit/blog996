package com.yys.szcp.entityVo;

import java.io.Serializable;
import java.util.List;

/**
 * Describe:
 * -------------------
 * User: yangyongsheng
 * Date: 2019/07/04 16:52:48
 * Email: 1095737364@qq.com
 */
public class ArticleMenuManageTree implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer id;
    private Integer parentId;
    private String title;
    private String remark;
    private Integer sorting;
    private List<ArticleMenuManageTree> children;

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

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getSorting() {
        return sorting;
    }

    public void setSorting(Integer sorting) {
        this.sorting = sorting;
    }

    public List<ArticleMenuManageTree> getChildren() {
        return children;
    }

    public void setChildren(List<ArticleMenuManageTree> children) {
        this.children = children;
    }
}


