package com.yys.szcp.controller;

import com.alibaba.druid.support.json.JSONUtils;
import com.yys.szcp.constant.ExceptionConstant;
import com.yys.szcp.entity.DbAdminUser;
import com.yys.szcp.entity.TArticle;
import com.yys.szcp.service.TArticleMenuService;
import com.yys.szcp.service.TArticleService;
import com.yys.szcp.utils.ResultUtil;
import com.yys.szcp.utils.StringISNULLUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("sys/article")
public class ArticleController {
    private static final Logger logger = LoggerFactory.getLogger(ArticleController.class);

    @Value("${markdown-path}")
    private String markdownPath;

    @Autowired
    private TArticleService articleService;

    @Autowired
    private TArticleMenuService articleMenuService;

    @RequestMapping("/markdown")
    @ResponseBody
    public ResultUtil getMarkdown(HttpServletRequest request, String url) {
        ResultUtil result = new ResultUtil();
        result.setCode(ExceptionConstant.SUCCESS_HTTPREUQEST);
        Map map=new HashMap();
        map.put("markdownData",articleService.getMarkdown(url));
        articleService.updateArticleViewsNumber(url);
        map.put("article",articleService.findArticleByUrl(url));
        result.setData(map);

        return result;
    }

    /**
     * 添加文章
     *
     * @param request
     * @return
     */
    @RequestMapping("/addArticle")
    @ResponseBody
    public ResultUtil addArticle(HttpServletRequest request, String article) {
        try {
            DbAdminUser adminUser = (DbAdminUser) request.getAttribute("adminUser");

            //封装数据
            Map adminUserParam = (Map) JSONUtils.parse(article);
            TArticle articleMy = new TArticle();
            articleMy.setArticleMenuId(StringISNULLUtil.mapToInteger(adminUserParam.get("articleMenuId")));
            articleMy.setTitle(StringISNULLUtil.mapToString(adminUserParam.get("title")));
            articleMy.setUrl(StringISNULLUtil.mapToString(adminUserParam.get("url")));
            articleMy.setSorting(StringISNULLUtil.mapToInteger(adminUserParam.get("sorting")));
            articleMy.setRemark(StringISNULLUtil.mapToString(adminUserParam.get("remark")));
            String localDate = LocalDate.now().toString();
            String name = String.valueOf(System.currentTimeMillis());
            String dirName = localDate.replaceAll("-", "/");

            File dir = new File(markdownPath + dirName);
            if (!dir.exists()) {
                dir.mkdirs();
            }
            String filePath =markdownPath+ dirName + "/" + name + ".md";
            File file = new File(filePath);
            file.createNewFile();
            //2020-10-14-1602681071986.md
            articleMy.setUrl(localDate+'-'+name+".md");

            articleMy.setCreateUserId(adminUser.getId());
            articleMy.setUpdateUserId(adminUser.getId());

            //验证名称是否重复
            List<TArticle> articleList = articleService.findArticleByTitle(null, articleMy.getTitle());
            if (articleList != null && articleList.size() > 0) {
                return ResultUtil.error("添加失败,名称重复!");
            }
            articleService.addArticle(articleMy);
            return ResultUtil.success("添加成功!");
        } catch (Exception e) {

            logger.error("添加文章错误: " + e);
            return ResultUtil.error("添加失败!");
        }
    }

    /**
     * 查询文章
     *
     * @param request
     * @return
     */
    @RequestMapping("/findArticleList")
    @ResponseBody
    public ResultUtil findArticleList(HttpServletRequest request, String searchPream) {
        try {
            ResultUtil resultUtil = new ResultUtil();
            //封装数据
            Map search = (Map) JSONUtils.parse(searchPream);
            DbAdminUser adminUser = (DbAdminUser) request.getAttribute("adminUser");
            Map map = new HashMap();
            map.put("page", (Integer.valueOf(search.get("page").toString()) - 1) * 10);
            map.put("limit", search.get("limit"));
            map.put("articleMenuId", StringISNULLUtil.mapToInteger(search.get("articleMenuId")));

            resultUtil.setData(articleService.findArticleListByArticleMenuId(map));
            resultUtil.setCount(articleService.findArticleListByArticleMenuIdCount(map));
            resultUtil.setMsg("查询成功!");
            resultUtil.setCode(ExceptionConstant.SUCCESS_HTTPREUQEST);
            return resultUtil;
        } catch (Exception e) {

            logger.error("查询管理用户列表错误: " + e);
            return ResultUtil.error("查询失败!");
        }
    }

    /**
     * 搜索文章标题
     *
     * @param request
     * @return
     */
    @RequestMapping("/findArticleListByName")
    @ResponseBody
    public ResultUtil findArticleListByName(HttpServletRequest request, String searchPream) {
        try {
            ResultUtil resultUtil = new ResultUtil();
            //封装数据
            Map search = (Map) JSONUtils.parse(searchPream);
            DbAdminUser adminUser = (DbAdminUser) request.getAttribute("adminUser");
            Map map = new HashMap();

            map.put("articleNameSearch", StringISNULLUtil.mapToString(search.get("articleNameSearch")));
           List<Map> articleList= articleService.findArticleListByArticleNameSearch(map);

            return ResultUtil.success(articleList);
        } catch (Exception e) {

            logger.error("查询管理用户列表错误: " + e);
            return ResultUtil.error("查询失败!");
        }
    }


    /**
     * 更新文章信息
     *
     * @return
     * @par后台管理am request
     */
    @RequestMapping("/updateArticle")
    @ResponseBody
    public ResultUtil updateArticle(HttpServletRequest request, String article) {
        try {
            DbAdminUser adminUser = (DbAdminUser) request.getAttribute("adminUser");
            //封装数据
            Map adminUserParam = (Map) JSONUtils.parse(article);
            TArticle articleMy = new TArticle();
            articleMy.setArticleMenuId(StringISNULLUtil.mapToInteger(adminUserParam.get("articleMenuId")));
            articleMy.setTitle(StringISNULLUtil.mapToString(adminUserParam.get("title")));
            articleMy.setUrl(StringISNULLUtil.mapToString(adminUserParam.get("url")));
            articleMy.setSorting(StringISNULLUtil.mapToInteger(adminUserParam.get("sorting")));
            articleMy.setRemark(StringISNULLUtil.mapToString(adminUserParam.get("remark")));
            articleMy.setId(StringISNULLUtil.mapToInteger(adminUserParam.get("id")));
            articleMy.setCreateUserId(adminUser.getId());
            articleMy.setUpdateUserId(adminUser.getId());

            //验证名称是否重复
            List<TArticle> articleList = articleService.findArticleByTitle(articleMy.getId(), articleMy.getTitle());
            if (articleList != null && articleList.size() > 0) {
                return ResultUtil.error("更新失败,名称重复!");
            }

            articleService.updateArticle(articleMy);
            return ResultUtil.success("更新成功!");
        } catch (Exception e) {

            logger.error("更新文章错误: " + e);
            return ResultUtil.error("更新失败!");
        }
    }

    /**
     * 更新文章内容
     *
     * @return
     * @par后台管理am request
     */
    @RequestMapping("/updateArticleContent")
    @ResponseBody
    public ResultUtil updateArticleContent(HttpServletRequest request,
                                           @RequestParam(value = "articleContent", required = false) String articleContent,
                                           @RequestParam(value = "num", required = false) String num,
                                           @RequestParam(value = "url", required = false) String url
    ) {
        try {
            String articleContentList= articleContent;
            Integer number= StringISNULLUtil.mapToInteger(num);
            articleContentList= articleContentList.replaceAll("#1#","[");
            articleContentList= articleContentList.replaceAll("#2#","]");
            articleService.saveMarkdown(articleContentList, url,number);
            return ResultUtil.success("更新成功!");
        } catch (Exception e) {

            logger.error("更新文章错误: " + e);
            return ResultUtil.error("更新失败!"+e);
        }
    }


    /**
     * 删除文章
     *
     * @param request
     * @return
     */
    @RequestMapping("/deleteArticle")
    @ResponseBody
    public ResultUtil deleteArticle(HttpServletRequest request, Integer articleId) {
        try {
            TArticle article = new TArticle();
            article.setId(articleId);
            article.setDeleteStatus(1);
            articleService.deleteArticle(article);
            return ResultUtil.success("删除成功!");
        } catch (Exception e) {

            logger.error("删除管理用户错误: " + e);
            return ResultUtil.error("删除失败!");
        }
    }

}
