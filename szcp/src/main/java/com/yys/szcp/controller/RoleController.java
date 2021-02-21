package com.yys.szcp.controller;

import com.alibaba.druid.support.json.JSONUtils;
import com.yys.szcp.constant.ExceptionConstant;
import com.yys.szcp.entity.DbAdminUser;
import com.yys.szcp.entity.DbRole;
import com.yys.szcp.service.DbRoleService;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Describe: 角色操作
 * -------------------
 * User: yangyongsheng
 * Date: 2019/06/25 16:37:30
 * Email: 1095737364@qq.com
 */
@Controller
@RequestMapping("sys/role/")
public class RoleController {
    private static final Logger logger = LoggerFactory.getLogger(RoleController.class);


    @Autowired
    private DbRoleService roleService;


    /**
     * 添加角色
     *
     * @param request
     * @param role
     * @return
     */
    @RequestMapping("addRole")
    @ResponseBody
    public ResultUtil addRole(HttpServletRequest request, String role) {

        try {
            //封装数据
            Map role1 =(Map) JSONUtils.parse(role);
            DbRole roleMy=new DbRole();
            roleMy.setRoleName(StringISNULLUtil.mapToString(role1.get("roleName")));
            roleMy.setRemark(StringISNULLUtil.mapToString(role1.get("roleRemark")));
            DbAdminUser adminUser = (DbAdminUser) request.getAttribute("adminUser");

            //判断名称是否重复
            List<DbRole> list=roleService.findRoleByName(null,roleMy.getRoleName());
            if(list!=null&& list.size()>0){
                return ResultUtil.error("添加失败,名称重复!");
            }
            roleService.addRole(roleMy);
            return ResultUtil.success("添加成功!");
        } catch (Exception e) {
            logger.error("添加角色错误: " + e.getMessage());
            return ResultUtil.error("添加失败!");
        }
    }


    /**
     * 根据id  查询角色
     *
     * @param request
     * @param roleId
     * @return
     */
    @RequestMapping("findRoleById")
    @ResponseBody
    public ResultUtil findRoleById(HttpServletRequest request, Integer roleId) {
        try {
            DbRole role = roleService.findRoleById(roleId);
            return ResultUtil.success(role);
        } catch (Exception e) {
            logger.error("根据id  查询角色错误: " + e.getMessage());
            return ResultUtil.error("根据id查询角色失败!");
        }
    }


    /**
     * 更新角色
     *
     * @param request
     * @param role
     * @return
     */
    @RequestMapping("updateRole")
    @ResponseBody
    public ResultUtil updateRole(HttpServletRequest request, String  role) {
        try {

            //封装数据
            Map role1 =(Map) JSONUtils.parse(role);
            DbRole roleMy=new DbRole();
            roleMy.setId(StringISNULLUtil.mapToInteger(role1.get("id").toString()));
            roleMy.setRoleName(StringISNULLUtil.mapToString(role1.get("roleName")));
            roleMy.setRemark(StringISNULLUtil.mapToString(role1.get("roleRemark")));

            //判断名称是否重复
            List<DbRole> list=roleService.findRoleByName(roleMy.getId(),roleMy.getRoleName());
            if(list!=null&& list.size()>0){
                return ResultUtil.error("修改失败,名称重复!");
            }
            roleService.updateRole(roleMy);
            return ResultUtil.success("修改成功!");
        } catch (Exception e) {
            logger.error("修改角色错误: " + e.getMessage());
            return ResultUtil.error("修改失败!");
        }
    }

    /**
     * 删除角色
     *
     * @param request
     * @param roleId
     * @return
     */
    @RequestMapping("deleteRole")
    @ResponseBody
    public ResultUtil deleteRole(HttpServletRequest request, Integer roleId) {
        try {
            DbRole role=new DbRole();
            role.setId(roleId);
            role.setDeleteStatus(1);
            roleService.deleteRole(role);
            return ResultUtil.success("删除成功!");
        } catch (Exception e) {
            logger.error("删除角色错误: " + e.getMessage());
            return ResultUtil.error("删除失败!");
        }
    }



    /**
     * 分页查询角色列表
     * @param request
     * @param searchPream
     * @return
     */
    @RequestMapping("findRoleList")
    @ResponseBody
    public ResultUtil findRoleList(HttpServletRequest request, String searchPream) {
        try {

            //封装数据
            Map searchPreamMy =(Map) JSONUtils.parse(searchPream);

            ResultUtil resultUtil = new ResultUtil();

            DbAdminUser adminUser = (DbAdminUser) request.getAttribute("adminUser");
            Map map = new HashMap();
            map.put("page", (StringISNULLUtil.mapToInteger(searchPreamMy.get("page").toString()) - 1) * 10);
            map.put("limit", StringISNULLUtil.mapToInteger(searchPreamMy.get("limit")));

            resultUtil.setCode(ExceptionConstant.SUCCESS_HTTPREUQEST);
            resultUtil.setMsg("查询成功!");
            resultUtil.setCount(roleService.findRoleListCount(map));
            resultUtil.setData(roleService.findRoleList(map));
            return resultUtil;
        } catch (Exception e) {
            logger.error("添加角色错误: " + e.getMessage());
            return ResultUtil.error("查询失败!");
        }
    }

    /**
     * 查询所有角色列表
     * @param request
     * @return
     */
    @RequestMapping("findRoleAllList")
    @ResponseBody
    public ResultUtil findRoleAllList(HttpServletRequest request) {
        try {

            DbAdminUser adminUser = (DbAdminUser) request.getAttribute("adminUser");
            Map map = new HashMap();
            map.put("adminUserId", adminUser.getId());
            List<Map> roleList=roleService.findRoleAllList(map);

            //-----特殊处理开始--------
            //运营员特殊处理 level 等于2 表示运营部
            if(adminUser!=null&&adminUser.getLevel()==2){
                List<Map> myRole=new ArrayList<>();
                for(Map map1 : roleList){
                    if(StringISNULLUtil.mapToInteger(map1.get("id"))==5){
                        myRole.add(map1);
                        return ResultUtil.success(myRole);
                    }
                }
            }
            //-----特殊处理结束--------

            return ResultUtil.success(roleList);
        } catch (Exception e) {
            logger.error("添加角色错误: " + e.getMessage());
            return ResultUtil.error("查询失败!");
        }
    }

}
