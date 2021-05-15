package com.yys.szcp.controller;

import com.alibaba.druid.support.json.JSONUtils;
import com.yys.szcp.constant.ExceptionConstant;
import com.yys.szcp.entity.DbAdminUser;
import com.yys.szcp.entity.TFundInfo;
import com.yys.szcp.service.TFundInfoService;
import com.yys.szcp.utils.ResultUtil;
import com.yys.szcp.utils.StringISNULLUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * Describe:
 * -------------------
 * User: yangyongsheng
 * Date: 2019/06/25 17:37:12
 * Email: 1095737364@qq.com
 */
@Controller
@RequestMapping("sys/fundInfo")
public class FundInfoController {

    private static final Logger logger = LoggerFactory.getLogger(FundInfoController.class);

    @Autowired
    private TFundInfoService fundInfoService;

    /**
     * 添加基金信息
     *
     * @param request
     * @param fundInfo
     * @return
     */
    @RequestMapping("/addFundInfo")
    @ResponseBody
    public ResultUtil addFundInfo(HttpServletRequest request, String fundInfo) {
        try {
            //封装数据
            Map fundInfoParam =(Map) JSONUtils.parse(fundInfo);
            TFundInfo fundInfoMy=new TFundInfo();
            DbAdminUser adminUser = (DbAdminUser) request.getAttribute("adminUser");

            fundInfoMy.setFundTypeCompanyId(StringISNULLUtil.mapToInteger(fundInfoParam.get("fundTypeCompanyId")));
            fundInfoMy.setFundTypeSectionId(StringISNULLUtil.mapToInteger(fundInfoParam.get("fundTypeSectionId")));
            fundInfoMy.setFundTypeTypeId(StringISNULLUtil.mapToInteger(fundInfoParam.get("fundTypeTypeId")));
            fundInfoMy.setFundTypePurchaseId(StringISNULLUtil.mapToInteger(fundInfoParam.get("fundTypePurchaseId")));
            fundInfoMy.setFundName(StringISNULLUtil.mapToString(fundInfoParam.get("fundName")));
            fundInfoMy.setFundCode(StringISNULLUtil.mapToString(fundInfoParam.get("fundCode")));
            fundInfoMy.setFundScale(StringISNULLUtil.mapToBigDecimal(fundInfoParam.get("fundScale")));
            fundInfoMy.setHoldingYield(StringISNULLUtil.mapToBigDecimal(fundInfoParam.get("holdingYield")));
            fundInfoMy.setPrincipal(StringISNULLUtil.mapToBigDecimal(fundInfoParam.get("principal")));
            fundInfoMy.setProfitLoss(StringISNULLUtil.mapToBigDecimal(fundInfoParam.get("profitLoss")));
            fundInfoMy.setRecommendationLevel(StringISNULLUtil.mapToInteger(fundInfoParam.get("recommendationLevel")));
            fundInfoMy.setRemark(StringISNULLUtil.mapToString(fundInfoParam.get("remark")));
            fundInfoMy.setCreateUserId(adminUser.getId());
            fundInfoMy.setUpdateUserId(adminUser.getId());

            fundInfoService.addFundInfo(fundInfoMy);
            return ResultUtil.success("添加成功!");
        } catch (Exception e) {
            logger.error("添加基金信息错误: " + e.getMessage());
            return ResultUtil.error("添加失败!");
        }
    }





    /**
     * 更新文章菜单
     * @param request
     * @param fundInfo
     * @return
     */
    @RequestMapping("/updateFundInfo")
    @ResponseBody
    public ResultUtil updateFundInfo(HttpServletRequest request, String fundInfo) {
        try {
            //封装数据
            Map fundInfoParam =(Map) JSONUtils.parse(fundInfo);
            TFundInfo fundInfoMy=new TFundInfo();
            DbAdminUser adminUser = (DbAdminUser) request.getAttribute("adminUser");

            fundInfoMy.setFundTypeCompanyId(StringISNULLUtil.mapToInteger(fundInfoParam.get("fundTypeCompanyId")));
            fundInfoMy.setFundTypeSectionId(StringISNULLUtil.mapToInteger(fundInfoParam.get("fundTypeSectionId")));
            fundInfoMy.setFundTypeTypeId(StringISNULLUtil.mapToInteger(fundInfoParam.get("fundTypeTypeId")));
            fundInfoMy.setFundTypePurchaseId(StringISNULLUtil.mapToInteger(fundInfoParam.get("fundTypePurchaseId")));
            fundInfoMy.setFundName(StringISNULLUtil.mapToString(fundInfoParam.get("fundName")));
            fundInfoMy.setFundCode(StringISNULLUtil.mapToString(fundInfoParam.get("fundCode")));
            fundInfoMy.setFundScale(StringISNULLUtil.mapToBigDecimal(fundInfoParam.get("fundScale")));
            fundInfoMy.setHoldingYield(StringISNULLUtil.mapToBigDecimal(fundInfoParam.get("holdingYield")));
            fundInfoMy.setPrincipal(StringISNULLUtil.mapToBigDecimal(fundInfoParam.get("principal")));
            fundInfoMy.setProfitLoss(StringISNULLUtil.mapToBigDecimal(fundInfoParam.get("profitLoss")));
            fundInfoMy.setRecommendationLevel(StringISNULLUtil.mapToInteger(fundInfoParam.get("recommendationLevel")));
            fundInfoMy.setRemark(StringISNULLUtil.mapToString(fundInfoParam.get("remark")));
            fundInfoMy.setCreateUserId(adminUser.getId());
            fundInfoMy.setUpdateUserId(adminUser.getId());
            fundInfoMy.setId(StringISNULLUtil.mapToInteger(fundInfoParam.get("id")));

            fundInfoService.updateFundInfo(fundInfoMy);
            return ResultUtil.success("更新成功!");
        } catch (Exception e) {
            logger.error("更新基金信息错误: " + e.getMessage());
            return ResultUtil.error("更新失败!");
        }
    }


    /**
     * 删除基金信息
     * @param request
     * @param fundInfoId
     * @return
     */
    @RequestMapping("/deleteFundInfo")
    @ResponseBody
    public ResultUtil deleteFundInfo(HttpServletRequest request, Integer fundInfoId) {
        try {
            DbAdminUser adminUser = (DbAdminUser) request.getAttribute("adminUser");

            TFundInfo fundInfo=new TFundInfo();
            fundInfo.setId(fundInfoId);
            fundInfo.setDeleteStatus(1);
            fundInfoService.deleteFundInfo(fundInfo);
            return ResultUtil.success("删除成功!");
        } catch (Exception e) {
            logger.error("删除基金信息错误: " + e.getMessage());
            return ResultUtil.error("删除失败!");
        }
    }




    /**
     * 基金信息管理页面
     * @param request
     * @return
     */
    @RequestMapping("/findFundInfoList")
    @ResponseBody
    public ResultUtil findFundInfoList(HttpServletRequest request,String searchPream) {
        try {
            ResultUtil resultUtil = new ResultUtil();
            //封装数据
            Map search = (Map) JSONUtils.parse(searchPream);
            DbAdminUser adminUser = (DbAdminUser) request.getAttribute("adminUser");
            Map map = new HashMap();
            map.put("page", (Integer.valueOf(search.get("page").toString()) - 1) * 10);
            map.put("limit", search.get("limit"));
            map.put("title", search.get("titleSearch"));
            map.put("fundTypeCompanyId", search.get("fundTypeCompanyIdSearch"));
            map.put("fundTypeSectionId", search.get("fundTypeSectionIdSearch"));
            map.put("fundTypeTypeId", search.get("fundTypeTypeIdSearch"));
            map.put("fundTypePurchaseId", search.get("fundTypePurchaseIdSearch"));
            map.put("fundName", search.get("fundNameSearch"));
            map.put("fundCode", search.get("fundCodeSearch"));
            map.put("principalOrderBy", search.get("principalOrderBySearch"));
            map.put("profitLossOrderBy", search.get("profitLossOrderBySearch"));
            map.put("recommendationLevelOrderBy", search.get("recommendationLevelOrderBySearch"));
            map.put("holdingYieldOrderBy", search.get("holdingYieldOrderBySearch"));



            resultUtil.setData(fundInfoService.findFundInfoList(map));
            resultUtil.setCount(fundInfoService.findFundInfoListCount(map));
            resultUtil.setMsg("查询成功!");
            resultUtil.setCode(ExceptionConstant.SUCCESS_HTTPREUQEST);

            return resultUtil;
        } catch (Exception e) {
            logger.error("查询基金信息错误: " + e.getMessage());
            return ResultUtil.error("查询失败!");
        }
    }




}
