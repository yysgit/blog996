package com.yys.szcp.controller;

import com.yys.szcp.config.jwt.JwtIgnore;
import com.yys.szcp.entityVo.ArticleTree;
import com.yys.szcp.service.DbArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author: 徐万金·JimXu
 * @email: xuwanjin@jimxu.top
 * @Description:
 * @Date: Create in 19:22 2020-12-15
 */
@RestController
@RequestMapping("/article")
public class ArticleController {
    @Autowired
    private DbArticleService dbArticleService;

    @GetMapping("/getArticleTree")
    @JwtIgnore
    public List<ArticleTree> getArticleTree(){
        return dbArticleService.findListTree();
    }
}
