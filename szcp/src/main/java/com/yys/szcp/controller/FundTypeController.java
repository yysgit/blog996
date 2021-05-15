package com.yys.szcp.controller;

import com.alibaba.druid.support.json.JSONUtils;
import com.yys.szcp.constant.ExceptionConstant;
import com.yys.szcp.entity.DbAdminUser;
import com.yys.szcp.entity.TFundType;
import com.yys.szcp.service.TFundTypeService;
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
@RequestMapping("sys/fundType")
public class FundTypeController {

    private static final Logger logger = LoggerFactory.getLogger(FundTypeController.class);

    @Autowired
    private TFundTypeService fundTypeService;

    /**
     * 添加基金类型
     *
     * @param request
     * @param fundType
     * @return
     */
    @RequestMapping("/addFundType")
    @ResponseBody
    public ResultUtil addFundType(HttpServletRequest request, String fundType) {
        try {

            //封装数据
            Map fundTypeParam =(Map) JSONUtils.parse(fundType);
            TFundType fundTypeMy=new TFundType();
            DbAdminUser adminUser = (DbAdminUser) request.getAttribute("adminUser");

            fundTypeMy.setTitle(StringISNULLUtil.mapToString(fundTypeParam.get("title")));
            fundTypeMy.setType(StringISNULLUtil.mapToInteger(fundTypeParam.get("type")));
            fundTypeMy.setSorting(StringISNULLUtil.mapToInteger(fundTypeParam.get("sorting")));
            fundTypeMy.setRemark(StringISNULLUtil.mapToString(fundTypeParam.get("remark")));
            fundTypeMy.setCreateUserId(adminUser.getId());
            fundTypeMy.setUpdateUserId(adminUser.getId());


           //判断名称是否重复
            List<TFundType> list=fundTypeService.findFundTypeByName(null,fundTypeMy.getTitle());
            if(list!=null&& list.size()>0){
                return ResultUtil.error("添加失败,名称重复!");
            }
            fundTypeService.addFundType(fundTypeMy);
            return ResultUtil.success("添加成功!");
        } catch (Exception e) {
            logger.error("添加基金类型错误: " + e.getMessage());
            return ResultUtil.error("添加失败!");
        }
    }





    /**
     * 更新文章菜单
     * @param request
     * @param fundType
     * @return
     */
    @RequestMapping("/updateFundType")
    @ResponseBody
    public ResultUtil updateFundType(HttpServletRequest request, String fundType) {
        try {
            //封装数据
            Map fundTypeParam =(Map) JSONUtils.parse(fundType);
            TFundType fundTypeMy=new TFundType();
            DbAdminUser adminUser = (DbAdminUser) request.getAttribute("adminUser");


            fundTypeMy.setTitle(StringISNULLUtil.mapToString(fundTypeParam.get("title")));
            fundTypeMy.setType(StringISNULLUtil.mapToInteger(fundTypeParam.get("type")));
            fundTypeMy.setRemark(StringISNULLUtil.mapToString(fundTypeParam.get("remark")));
            fundTypeMy.setSorting(StringISNULLUtil.mapToInteger(fundTypeParam.get("sorting")));
            fundTypeMy.setCreateUserId(adminUser.getId());
            fundTypeMy.setUpdateUserId(adminUser.getId());
            fundTypeMy.setId(StringISNULLUtil.mapToInteger(fundTypeParam.get("id")));

            //判断名称是否重复
            List<TFundType> list=fundTypeService.findFundTypeByName(fundTypeMy.getId(),fundTypeMy.getTitle());
            if(list!=null&& list.size()>0){
                return ResultUtil.error("添加失败,名称重复!");
            }
            fundTypeService.updateFundType(fundTypeMy);
            return ResultUtil.success("更新成功!");
        } catch (Exception e) {
            logger.error("更新基金类型错误: " + e.getMessage());
            return ResultUtil.error("更新失败!");
        }
    }


    /**
     * 删除基金类型
     * @param request
     * @param fundTypeId
     * @return
     */
    @RequestMapping("/deleteFundType")
    @ResponseBody
    public ResultUtil deleteFundType(HttpServletRequest request, Integer fundTypeId) {
        try {
            DbAdminUser adminUser = (DbAdminUser) request.getAttribute("adminUser");

            TFundType fundType=new TFundType();
            fundType.setId(fundTypeId);
            fundType.setDeleteStatus(1);
            fundTypeService.deleteFundType(fundType);
            return ResultUtil.success("删除成功!");
        } catch (Exception e) {
            logger.error("删除基金类型错误: " + e.getMessage());
            return ResultUtil.error("删除失败!");
        }
    }




    /**
     * 基金类型管理页面
     * @param request
     * @return
     */
    @RequestMapping("/findFundTypeList")
    @ResponseBody
    public ResultUtil findFundTypeList(HttpServletRequest request,String searchPream) {
        try {
            ResultUtil resultUtil = new ResultUtil();
            //封装数据
            Map search = (Map) JSONUtils.parse(searchPream);
            DbAdminUser adminUser = (DbAdminUser) request.getAttribute("adminUser");
            Map map = new HashMap();
            map.put("page", (Integer.valueOf(search.get("page").toString()) - 1) * 10);
            map.put("limit", search.get("limit"));
            map.put("title", search.get("titleSearch"));
            map.put("type", search.get("typeSearch"));


            resultUtil.setData(fundTypeService.findFundTypeList(map));
            resultUtil.setCount(fundTypeService.findFundTypeListCount(map));
            resultUtil.setMsg("查询成功!");
            resultUtil.setCode(ExceptionConstant.SUCCESS_HTTPREUQEST);

            return resultUtil;
        } catch (Exception e) {
            logger.error("查询基金类型错误: " + e.getMessage());
            return ResultUtil.error("查询失败!");
        }
    }

    /**
     * 基金类型查询
     * @param request
     * @return
     */
    @RequestMapping("/findFundTypeListByType")
    @ResponseBody
    public ResultUtil findFundTypeListByType(HttpServletRequest request,String searchPream) {
        try {
            ResultUtil resultUtil = new ResultUtil();
            //封装数据
            Map search = (Map) JSONUtils.parse(searchPream);
            DbAdminUser adminUser = (DbAdminUser) request.getAttribute("adminUser");
            Map map = new HashMap();
            map.put("type", search.get("typeSearch"));
            return ResultUtil.success(fundTypeService.findFundTypeListByType(map));
        } catch (Exception e) {
            logger.error("查询基金类型错误: " + e.getMessage());
            return ResultUtil.error("查询失败!");
        }
    }





}
