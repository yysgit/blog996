package com.yys.szcp.entityVo;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Describe:
 * -------------------
 * User: yangyongsheng
 * Date: 2019/06/26 19:08:35
 * Email: 1095737364@qq.com
 */

/*
{
        "icon":"md-cloud-upload",
        "name":"update",
        "meta":{
            "icon":"md-cloud-upload",
            "title":"数据上传"
        },
        "children":[
            {
                "icon":"ios-document",
                "name":"update_table_page",
                "meta":{
                    "icon":"ios-document",
                    "title":"上传Csv"
                }
            },
            {
                "icon":"md-clipboard",
                "name":"update_paste_page",
                "meta":{
                    "icon":"md-clipboard",
                    "title":"粘贴表格数据"
                }
            }
        ]
    },


 */
public class MenuTree  implements Serializable {
    private static final long serialVersionUID = 1L;
        private String icon;
        private String name;
        private Map meta;
        private List<MenuTree> children;

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Map getMeta() {
        return meta;
    }

    public void setMeta(Map meta) {
        this.meta = meta;
    }

    public List<MenuTree> getChildren() {
        return children;
    }

    public void setChildren(List<MenuTree> children) {
        this.children = children;
    }









}
