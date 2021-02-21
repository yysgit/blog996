package com.yys.szcp.controller;

import com.alibaba.druid.support.json.JSONUtils;
import com.yys.szcp.constant.ExceptionConstant;
import com.yys.szcp.entity.DbAdminUser;
import com.yys.szcp.entity.DbMarkdown;
import com.yys.szcp.service.DbMarkdownService;
import com.yys.szcp.utils.ResultUtil;
import com.yys.szcp.utils.StringISNULLUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @Author: 徐万金·JimXu
 * @email: xuwanjin@jimxu.top
 * @Description:
 * @Date: Create in 21:21 2020-10-14
 */
@RequestMapping("/sys/article")
@RestController
public class MarkdownController {

    private static final Logger logger = LoggerFactory.getLogger(RoleController.class);

    @Autowired
    private DbMarkdownService markdownService;

    @GetMapping("/test")
    public ResultUtil test(HttpServletRequest request){
        DbAdminUser adminUser = (DbAdminUser) request.getAttribute("adminUser");
        System.out.println(adminUser.getAdminName());
        return ResultUtil.success("1");
    }
    @RequestMapping("/findMarkdownList")
    public ResultUtil findMarkdownList(String searchPream){
        try {
            Map searchPreamMy =(Map) JSONUtils.parse(searchPream);
            ResultUtil resultUtil = new ResultUtil();
            Integer page = (StringISNULLUtil.mapToInteger(searchPreamMy.get("page").toString()) - 1) * 10;
            Integer limit = StringISNULLUtil.mapToInteger(searchPreamMy.get("limit"));
            resultUtil.setCode(ExceptionConstant.SUCCESS_HTTPREUQEST);
            resultUtil.setMsg("查询成功!");
            resultUtil.setCount(markdownService.findMarkdownListCount(page,limit));
            resultUtil.setData(markdownService.findMarkdownList(page,limit));
            return resultUtil;
        }catch (Exception e){
            logger.error("查询markdown失败: " + e.getMessage());
            return ResultUtil.error("查询失败!");
        }
    }
    @PostMapping("/addMarkdown")
    public ResultUtil addMarkdown(@RequestBody DbMarkdown dbMarkdown,String content,HttpServletRequest request){
        DbAdminUser adminUser = (DbAdminUser) request.getAttribute("adminUser");
        dbMarkdown.setCreateUser(adminUser.getAdminFullname());
        if(markdownService.findMarkdownByMenuid(dbMarkdown.getMenuId())!=0) return ResultUtil.error("保存失败，该菜单已存在文章");
        markdownService.addMarkdown(dbMarkdown,content);
        return ResultUtil.success("添加成功!");
    }
    @PutMapping("/editMarkdown")
    public ResultUtil editMarkdown(@RequestBody DbMarkdown dbMarkdown,String content,HttpServletRequest request){
        DbAdminUser adminUser = (DbAdminUser) request.getAttribute("adminUser");
        dbMarkdown.setUpdateUser(adminUser.getAdminFullname());
        if(markdownService.findMarkdownByMenuid(dbMarkdown.getMenuId())!=1) return ResultUtil.error("修改失败，菜单编号无。。");
        markdownService.updateMarkdown(dbMarkdown,content);
        return ResultUtil.success("添加成功!");
    }
    @DeleteMapping("/delMarkdown")
    public ResultUtil delMarkdown(Integer id){
        markdownService.delMarkdown(id);
        return ResultUtil.success("删除成功");
    }
    @GetMapping("/getMarkdownByid")
    public ResultUtil getMarkdownByid(Integer id){
        ResultUtil resultUtil = new ResultUtil();
        resultUtil.setCode(ExceptionConstant.SUCCESS_HTTPREUQEST);
        resultUtil.setData(markdownService.findMarkdownByid(id));
        resultUtil.setMsg("查询成功");
        return resultUtil;
    }
}
