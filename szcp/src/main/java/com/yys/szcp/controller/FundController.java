package com.yys.szcp.controller;


import com.yys.szcp.utils.ResultUtil;
import com.yys.szcp.utils.SendRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("fund")
public class FundController {

    private static final Logger logger = LoggerFactory.getLogger(ArticleController.class);

    @RequestMapping("index")
    public String index(HttpServletRequest request) {
        return "index";
    }


    @RequestMapping("getFundDataListOne")
    @ResponseBody
    public String getFundDataListOne(HttpServletRequest request,String fundId) {
        long timestamp=System.currentTimeMillis();
        return SendRequest.getFundDataListOne(fundId,timestamp);
    }
}
