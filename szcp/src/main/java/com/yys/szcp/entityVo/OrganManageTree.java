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
public class OrganManageTree implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer id;
    private Integer parentId;
    private String organName;
    private String organRemake;
    private Integer sorting;
    private List<OrganManageTree> children;

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

    public String getOrganName() {
        return organName;
    }

    public void setOrganName(String organName) {
        this.organName = organName;
    }

    public String getOrganRemake() {
        return organRemake;
    }

    public void setOrganRemake(String organRemake) {
        this.organRemake = organRemake;
    }

    public Integer getSorting() {
        return sorting;
    }

    public void setSorting(Integer sorting) {
        this.sorting = sorting;
    }

    public List<OrganManageTree> getChildren() {
        return children;
    }

    public void setChildren(List<OrganManageTree> children) {
        this.children = children;
    }
}


