package com.yys.szcp.controller;

import com.alibaba.druid.support.json.JSONUtils;
import com.yys.szcp.entity.DbAdminUser;
import com.yys.szcp.entity.TArticle;
import com.yys.szcp.entity.TArticleMenu;
import com.yys.szcp.entityVo.ArticleMenuManageTree;
import com.yys.szcp.entityVo.ArticleMenuToArticleTree;
import com.yys.szcp.service.TArticleMenuService;
import com.yys.szcp.utils.ResultUtil;
import com.yys.szcp.utils.StringISNULLUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Describe:
 * -------------------
 * User: yangyongsheng
 * Date: 2019/06/25 17:37:12
 * Email: 1095737364@qq.com
 */
@Controller
@RequestMapping("sys/articleMenu")
public class ArticleMenuController {

    private static final Logger logger = LoggerFactory.getLogger(ArticleMenuController.class);

    @Autowired
    private TArticleMenuService articleMenuService;

    /**
     * 添加菜单
     *
     * @param request
     * @param articleMenu
     * @return
     */
    @RequestMapping("/addArticleMenu")
    @ResponseBody
    public ResultUtil addArticleMenu(HttpServletRequest request, String articleMenu) {
        try {

            //封装数据
            Map articleMenuParam =(Map) JSONUtils.parse(articleMenu);
            TArticleMenu articleMenuMy=new TArticleMenu();
            DbAdminUser adminUser = (DbAdminUser) request.getAttribute("adminUser");

            articleMenuMy.setParentId(StringISNULLUtil.mapToInteger(articleMenuParam.get("parentId")));
            articleMenuMy.setTitle(StringISNULLUtil.mapToString(articleMenuParam.get("title")));
            articleMenuMy.setSorting(StringISNULLUtil.mapToInteger(articleMenuParam.get("sorting")));
            articleMenuMy.setRemark(StringISNULLUtil.mapToString(articleMenuParam.get("remark")));
            articleMenuMy.setCreateUserId(adminUser.getId());
            articleMenuMy.setUpdateUserId(adminUser.getId());


           //判断名称是否重复
            List<TArticleMenu> list=articleMenuService.findArticleMenuByName(null,articleMenuMy.getParentId(),articleMenuMy.getTitle());
            if(list!=null&& list.size()>0){
                return ResultUtil.error("添加失败,名称重复!");
            }
            articleMenuService.addArticleMenu(articleMenuMy);
            return ResultUtil.success("添加成功!");
        } catch (Exception e) {
            logger.error("添加文章菜单错误: " + e.getMessage());
            return ResultUtil.error("添加失败!");
        }
    }


    /**
     * 通过机构id 获取文章菜单
     * @param request
     * @param articleMenuId
     * @return
     */
    @RequestMapping("/findArticleMenuById")
    @ResponseBody
    public ResultUtil findArticleMenuById(HttpServletRequest request, Integer articleMenuId) {
        try {
            TArticleMenu articleMenu=articleMenuService.findArticleMenuById(articleMenuId);
            return ResultUtil.success(articleMenu);
        } catch (Exception e) {
            logger.error("查询机构错误: " + e.getMessage());
            return ResultUtil.error("查询失败!");
        }
    }


    /**
     * 更新文章菜单
     * @param request
     * @param articleMenu
     * @return
     */
    @RequestMapping("/updateArticleMenu")
    @ResponseBody
    public ResultUtil updateArticleMenu(HttpServletRequest request, String articleMenu) {
        try {
            //封装数据
            Map articleMenuParam =(Map) JSONUtils.parse(articleMenu);
            TArticleMenu articleMenuMy=new TArticleMenu();
            DbAdminUser adminUser = (DbAdminUser) request.getAttribute("adminUser");

            articleMenuMy.setParentId(StringISNULLUtil.mapToInteger(articleMenuParam.get("parentId")));
            articleMenuMy.setTitle(StringISNULLUtil.mapToString(articleMenuParam.get("title")));
            articleMenuMy.setRemark(StringISNULLUtil.mapToString(articleMenuParam.get("remark")));
            articleMenuMy.setSorting(StringISNULLUtil.mapToInteger(articleMenuParam.get("sorting")));
            articleMenuMy.setCreateUserId(adminUser.getId());
            articleMenuMy.setUpdateUserId(adminUser.getId());
            articleMenuMy.setId(StringISNULLUtil.mapToInteger(articleMenuParam.get("id")));

            //判断名称是否重复
            List<TArticleMenu> list=articleMenuService.findArticleMenuByName(articleMenuMy.getId(),articleMenuMy.getParentId(),articleMenuMy.getTitle());
            if(list!=null&& list.size()>0){
                return ResultUtil.error("添加失败,名称重复!");
            }
            articleMenuService.updateArticleMenu(articleMenuMy);
            return ResultUtil.success("更新成功!");
        } catch (Exception e) {
            logger.error("更新文章菜单错误: " + e.getMessage());
            return ResultUtil.error("更新失败!");
        }
    }


    /**
     * 删除文章菜单
     * @param request
     * @param articleMenuId
     * @return
     */
    @RequestMapping("/deleteArticleMenu")
    @ResponseBody
    public ResultUtil deleteArticleMenu(HttpServletRequest request, Integer articleMenuId) {
        try {
            DbAdminUser adminUser = (DbAdminUser) request.getAttribute("adminUser");

            TArticleMenu articleMenu=new TArticleMenu();
            articleMenu.setId(articleMenuId);
            articleMenu.setDeleteStatus(1);
            articleMenuService.deleteArticleMenu(articleMenu);
            return ResultUtil.success("删除成功!");
        } catch (Exception e) {
            logger.error("删除文章菜单错误: " + e.getMessage());
            return ResultUtil.error("删除失败!");
        }
    }


    /**
     * 文章页面
     * @param request
     * @return
     */
    @RequestMapping("/findArticleMenuToArticleList")
    @ResponseBody
    public ResultUtil findArticleMenuToArticleList(HttpServletRequest request) {
        try {
            DbAdminUser adminUser = (DbAdminUser) request.getAttribute("adminUser");
            //添加本级
            List<TArticleMenu> articleMenuList=articleMenuService.findArticleMenuList();


            List<ArticleMenuToArticleTree> articleMenuManageTreeList = new ArrayList<>();

            for (int i = 0; i < articleMenuList.size(); i++) {
                ArticleMenuToArticleTree articleMenuManageTree = new ArticleMenuToArticleTree();
                TArticleMenu articleMenu = articleMenuList.get(i);

                //判断是否存在父级, 如果没有, 则表示是一级菜单
                if (articleMenu.getParentId() == null || articleMenu.getParentId() == 0) {
                    articleMenuManageTree.setId(articleMenu.getId().toString());
                    articleMenuManageTree.setExpand(true);
                    articleMenuManageTree.setChecked(true);
                    articleMenuManageTree.setTitle(articleMenu.getTitle());
                    articleMenuManageTree.setChildren(this.getArticleMenuArticleTreeList (articleMenu.getId()));
                    articleMenuManageTreeList.add(articleMenuManageTree);
                }
            }
            return ResultUtil.success(articleMenuManageTreeList);

        } catch (Exception e) {
            logger.error("查询机构树错误: " + e.getMessage());
            return ResultUtil.error("查询失败!");
        }
    }

    private List<ArticleMenuToArticleTree> getArticleMenuArticleTreeList(Integer articleMenuParentId) throws Exception{
        List<ArticleMenuToArticleTree> articleMenuToArticleTreeList=new ArrayList<>();

        //查询下级
        List<TArticleMenu> articleMenuChildList= articleMenuService.findArticleMenuByParentId(articleMenuParentId);
        if(articleMenuChildList!=null &&articleMenuChildList.size()>0){
            //封装数据
            for(TArticleMenu articleMenu:articleMenuChildList){
                ArticleMenuToArticleTree articleMenuToArticleTree=new ArticleMenuToArticleTree();
                articleMenuToArticleTree.setId(articleMenu.getId().toString());
                articleMenuToArticleTree.setExpand(true);
                articleMenuToArticleTree.setChecked(true);
                articleMenuToArticleTree.setTitle(articleMenu.getTitle());
                articleMenuToArticleTree.setChildren(getArticleMenuArticleTreeList (articleMenu.getId()));
                articleMenuToArticleTreeList.add(articleMenuToArticleTree);
            }
        }
        return articleMenuToArticleTreeList;

    }


    /**
     * 首页的页面
     * @param request
     * @return
     */
    @RequestMapping("/findArticleMenuToArticleListForHome")
    @ResponseBody
    public ResultUtil findArticleMenuToArticleListForHome(HttpServletRequest request) {
        try {
            DbAdminUser adminUser = (DbAdminUser) request.getAttribute("adminUser");
            //添加本级
            List<TArticleMenu> articleMenuList=articleMenuService.findArticleMenuList();


            List<ArticleMenuToArticleTree> articleMenuManageTreeList = new ArrayList<>();

            for (int i = 0; i < articleMenuList.size(); i++) {
                ArticleMenuToArticleTree articleMenuManageTree = new ArticleMenuToArticleTree();
                TArticleMenu articleMenu = articleMenuList.get(i);

                //判断是否存在父级, 如果没有, 则表示是一级菜单
                if (articleMenu.getParentId() == null || articleMenu.getParentId() == 0) {
                    articleMenuManageTree.setId(articleMenu.getId().toString());
                    articleMenuManageTree.setExpand(true);
                    articleMenuManageTree.setChecked(true);
                    articleMenuManageTree.setTitle(articleMenu.getTitle());
                    articleMenuManageTree.setChildren(this.getArticleMenuArticleTreeListForHome (articleMenu.getId()));
                    articleMenuManageTreeList.add(articleMenuManageTree);
                }
            }
            return ResultUtil.success(articleMenuManageTreeList);

        } catch (Exception e) {
            logger.error("查询机构树错误: " + e.getMessage());
            return ResultUtil.error("查询失败!");
        }
    }

    private List<ArticleMenuToArticleTree> getArticleMenuArticleTreeListForHome(Integer articleMenuId) throws Exception{
        List<ArticleMenuToArticleTree> articleMenuToArticleTreeList=new ArrayList<>();

        //查询下级
        List<TArticle> articleList= articleMenuService.findArticleByArticleMenuId(articleMenuId);
        if(articleList!=null &&articleList.size()>0){
            //封装数据
            for(TArticle article:articleList){
                ArticleMenuToArticleTree articleMenuToArticleTree=new ArticleMenuToArticleTree();
                articleMenuToArticleTree.setId(article.getId().toString());
                articleMenuToArticleTree.setExpand(true);
                articleMenuToArticleTree.setUrl(article.getUrl());
                articleMenuToArticleTree.setChecked(true);
                articleMenuToArticleTree.setTitle(article.getTitle());
                articleMenuToArticleTree.setChildren(null);
                articleMenuToArticleTreeList.add(articleMenuToArticleTree);
            }
        }
        return articleMenuToArticleTreeList;

    }





    /**
     * 文章树管理页面
     * @param request
     * @return
     */
    @RequestMapping("/findArticleMenuList")
    @ResponseBody
    public ResultUtil findArticleMenuList(HttpServletRequest request) {
        try {
            DbAdminUser adminUser = (DbAdminUser) request.getAttribute("adminUser");
            //添加本级
            List<TArticleMenu> articleMenuList=articleMenuService.findArticleMenuList();

            List<ArticleMenuManageTree> articleMenuManageTreeList = new ArrayList<>();

            for (int i = 0; i < articleMenuList.size(); i++) {
                ArticleMenuManageTree articleMenuManageTree = new ArticleMenuManageTree();
                TArticleMenu articleMenu = articleMenuList.get(i);

                //判断是否存在父级, 如果没有, 则表示是一级菜单
                if (articleMenu.getParentId() == null || articleMenu.getParentId() == 0) {
                    articleMenuManageTree.setId(articleMenu.getId());
                    articleMenuManageTree.setTitle(articleMenu.getTitle());
                    articleMenuManageTree.setRemark(articleMenu.getRemark());
                    articleMenuManageTree.setSorting(articleMenu.getSorting());
                    articleMenuManageTree.setParentId(articleMenu.getParentId());
                    articleMenuManageTree.setChildren(getArticleMenuMangeTreeList (articleMenu.getId()));


                    articleMenuManageTreeList.add(articleMenuManageTree);
                }
            }
            return ResultUtil.success(articleMenuManageTreeList);
        } catch (Exception e) {
            logger.error("查询文章菜单错误: " + e.getMessage());
            return ResultUtil.error("查询失败!");
        }
    }

    private List<ArticleMenuManageTree> getArticleMenuMangeTreeList(Integer articleMenuParentId) throws Exception{
        List<ArticleMenuManageTree> articleMenuManageTrees=new ArrayList<>();

        //查询下级
        List<TArticleMenu> articleMenuChildList= articleMenuService.findArticleMenuByParentId(articleMenuParentId);
        if(articleMenuChildList!=null &&articleMenuChildList.size()>0){
            //封装数据
            for(TArticleMenu articleMenu:articleMenuChildList){
                ArticleMenuManageTree articleMenuManageTree=new ArticleMenuManageTree();
                articleMenuManageTree.setId(articleMenu.getId());
                articleMenuManageTree.setTitle(articleMenu.getTitle());
                articleMenuManageTree.setRemark(articleMenu.getRemark());
                articleMenuManageTree.setSorting(articleMenu.getSorting());
                articleMenuManageTree.setParentId(articleMenu.getParentId());
                articleMenuManageTree.setChildren(getArticleMenuMangeTreeList (articleMenu.getId()));
                articleMenuManageTrees.add(articleMenuManageTree);
            }
        }
      return articleMenuManageTrees;

    }




}
