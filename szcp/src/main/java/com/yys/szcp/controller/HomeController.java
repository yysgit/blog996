package com.yys.szcp.controller;

import com.yys.szcp.service.DbMarkdownService;
import com.yys.szcp.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: 徐万金·JimXu
 * @email: xuwanjin@jimxu.top
 * @Description:
 * @Date: Create in 19:56 2020-10-14
 */
@RestController
@RequestMapping
public class HomeController {

    @Autowired
    private DbMarkdownService markdownService;

    @GetMapping("/markdown/{path}")
    public ResultUtil getMarkdown(@PathVariable String path){
        return ResultUtil.success(markdownService.getMarkdown(path));
    }
}
