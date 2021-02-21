package com.yys.szcp.entityVo;

import java.io.Serializable;
import java.util.List;

/**
 * Describe:
 * -------------------
 * User: yangyongsheng
 * Date: 2019/06/30 16:23:19
 * Email: 1095737364@qq.com
 */
public class MenuManageTree implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer id;
    private String title;
    private String icon;
    private String href;
    private String perms;
    private Integer parentId;
    private Integer sorting;
    private Integer grade;//等级, 目前严格按照3级来做
    private List<MenuManageTree> children;

    public Integer getGrade() {
        return grade;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public Integer getSorting() {
        return sorting;
    }

    public void setSorting(Integer sorting) {
        this.sorting = sorting;
    }

    public List<MenuManageTree> getChildren() {
        return children;
    }

    public void setChildren(List<MenuManageTree> children) {
        this.children = children;
    }
}
