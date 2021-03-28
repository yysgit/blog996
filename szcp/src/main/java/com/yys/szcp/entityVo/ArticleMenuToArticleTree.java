package com.yys.szcp.entityVo;

import java.io.Serializable;
import java.util.List;

/**
 * Describe:
 * -------------------
 * User: yangyongsheng
 * Date: 2019/07/04 13:18:03
 * Email: 1095737364@qq.com
 */
public class ArticleMenuToArticleTree implements Serializable {

    private static final long serialVersionUID = 1L;
    private String id;
    private String title;
    private String url;
    private Boolean expand;
    private Boolean checked;
    private List<ArticleMenuToArticleTree> children;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Boolean getExpand() {
        return expand;
    }

    public void setExpand(Boolean expand) {
        this.expand = expand;
    }

    public Boolean getChecked() {
        return checked;
    }

    public void setChecked(Boolean checked) {
        this.checked = checked;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<ArticleMenuToArticleTree> getChildren() {
        return children;
    }

    public void setChildren(List<ArticleMenuToArticleTree> children) {
        this.children = children;
    }
    /*
     {
            title: 'parent 1',
            id:1,
            expand: true,
            children: [
              {
                title: 'parent 1-1',
                expand: true,
                id:11,
                children: [
                  {
                    id:111,
                    title: 'leaf 1-1-1'
                  },
     */

}
