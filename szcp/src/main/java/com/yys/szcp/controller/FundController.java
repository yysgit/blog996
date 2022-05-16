package com.yys.szcp.controller;


import com.alibaba.druid.support.json.JSONUtils;
import com.yys.szcp.constant.ExceptionConstant;
import com.yys.szcp.entity.DbAdminUser;
import com.yys.szcp.service.FFundInfoService;
import com.yys.szcp.service.TFundInfoService;
import com.yys.szcp.utils.DateUtil;
import com.yys.szcp.utils.ResultUtil;
import com.yys.szcp.utils.SendRequest;
import com.yys.szcp.utils.StringISNULLUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("fund")
public class FundController {
    @Autowired
    private FFundInfoService fFundInfoService;
    private static final Logger logger = LoggerFactory.getLogger(ArticleController.class);

    @RequestMapping("index")
    public String index(HttpServletRequest request) {
        return "index";
    }
    @RequestMapping("fund")
    public String fund(HttpServletRequest request) {
        return "fund";
    }


    @RequestMapping("addFundData")
    @ResponseBody
    public ResultUtil addFundData(@RequestBody Map<String, String> stringParam){
        System.out.println(stringParam);
        return ResultUtil.success("添加成功!");
    }


    @RequestMapping("getFundDataListOne")
    @ResponseBody
    public String getFundDataListOne(HttpServletRequest request,String fundId) {
        long timestamp=System.currentTimeMillis();
        return SendRequest.getFundDataListOne(fundId,timestamp);
    }


    @RequestMapping("findFundInfoListType")
    @ResponseBody
    public ResultUtil findFundInfoListType(HttpServletRequest request) {
        try {
            ResultUtil resultUtil = new ResultUtil();
            //封装数据

            Map map = new HashMap();
            resultUtil.setData(fFundInfoService.findFundInfoList(map));
            resultUtil.setMsg("查询成功!");
            resultUtil.setCode(ExceptionConstant.SUCCESS_HTTPREUQEST);

            return resultUtil;
        } catch (Exception e) {
            logger.error("查询基金信息错误: " + e.getMessage());
            return ResultUtil.error("查询失败!");
        }
    }


    @RequestMapping("findFundInfoInvestmentList")
    @ResponseBody
    public ResultUtil findFundInfoInvestmentList(HttpServletRequest request) {
        try {
            ResultUtil resultUtil = new ResultUtil();
            //封装数据
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            Map map = new HashMap();
            map.put("fundday1",DateUtil.getPastDate(0));
            map.put("fundday2",DateUtil.getPastDate(1));
            map.put("fundday3",DateUtil.getPastDate(2));
            map.put("fundday4",DateUtil.getPastDate(3));
            map.put("fundday5",DateUtil.getPastDate(4));
            map.put("fundday6",DateUtil.getPastDate(5));
            map.put("fundday7",DateUtil.getPastDate(6));
            map.put("fundday8",DateUtil.getPastDate(7));
            map.put("fundday9",DateUtil.getPastDate(8));
            map.put("fundday10",DateUtil.getPastDate(9));

            resultUtil.setData(fFundInfoService.findFundInfoInvestmentList(map));
            resultUtil.setMsg("查询成功!");
            resultUtil.setCode(ExceptionConstant.SUCCESS_HTTPREUQEST);

            return resultUtil;
        } catch (Exception e) {
            logger.error("查询基金信息错误: " + e.getMessage());
            return ResultUtil.error("查询失败!");
        }
    }
    @RequestMapping("addFundInfoList")
    @ResponseBody
    public ResultUtil addFundInfoList( @RequestBody Map<String, String> stringParam) {

        try {
            ResultUtil resultUtil = new ResultUtil();

           String maxData=stringParam.get("maxData");
           String fundCode=stringParam.get("fundCode");
           String chartData=stringParam.get("chartData");
           String maxDataDate=stringParam.get("maxDataDate");
           List<List<String>> fundValue=  (List<List<String>>)JSONUtils.parse(chartData);


          fFundInfoService.addFundInfoList(maxData,maxDataDate,fundCode,fundValue);

           resultUtil.setMsg("查询成功!");
           resultUtil.setCode(ExceptionConstant.SUCCESS_HTTPREUQEST);

            return resultUtil;
        } catch (Exception e) {
            logger.error("查询基金信息错误: " + e.getMessage());
            return ResultUtil.error("查询失败!");
        }
    }



}
