package com.yys.szcp.entityVo;

import java.util.List;

/**
 * @Author: 徐万金·JimXu
 * @email: xuwanjin@jimxu.top
 * @Description:
 * @Date: Create in 16:59 2020-12-15
 */
public class ArticleTree {
    private Integer id;
    private Integer articleId;
    private List<ArticleTree> child;
    private String title;
    private String updateUser;
    private String updateTime;
    private String deleteStatus;
    private String remark;
    private String level;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getArticleId() {
        return articleId;
    }

    public void setArticleId(Integer articleId) {
        this.articleId = articleId;
    }

    public List<ArticleTree> getChild() {
        return child;
    }

    public void setChild(List<ArticleTree> child) {
        this.child = child;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public String getDeleteStatus() {
        return deleteStatus;
    }

    public void setDeleteStatus(String deleteStatus) {
        this.deleteStatus = deleteStatus;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }
}
