package com.yys.szcp.controller;

import com.alibaba.druid.support.json.JSONUtils;
import com.yys.szcp.entity.DbAdminUser;
import com.yys.szcp.entity.DbOrgan;
import com.yys.szcp.entityVo.OrganManageTree;
import com.yys.szcp.entityVo.OrganToUserTree;
import com.yys.szcp.service.DbOrganService;
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
@RequestMapping("sys/organ/")
public class OrganController {

    private static final Logger logger = LoggerFactory.getLogger(RoleController.class);

    @Autowired
    private DbOrganService organService;


    /**
     * 添加角色
     *
     * @param request
     * @param organ
     * @return
     */
    @RequestMapping("addOrgan")
    @ResponseBody
    public ResultUtil addOrgan(HttpServletRequest request, String organ) {
        try {

            //封装数据
            Map organ1 =(Map) JSONUtils.parse(organ);
            DbOrgan organMy=new DbOrgan();
            DbAdminUser adminUser = (DbAdminUser) request.getAttribute("adminUser");
            organMy.setParentId(StringISNULLUtil.mapToInteger(organ1.get("parentId")));
            organMy.setOrganName(StringISNULLUtil.mapToString(organ1.get("organName")));
            organMy.setRemake(StringISNULLUtil.mapToString(organ1.get("organRemake")));
            organMy.setSorting(StringISNULLUtil.mapToInteger(organ1.get("sorting")));



           //判断名称是否重复
            List<DbOrgan> list=organService.findOrganByName(null,organMy.getParentId(),organMy.getOrganName());
            if(list!=null&& list.size()>0){
                return ResultUtil.error("添加失败,名称重复!");
            }
            organService.addOrgan(organMy);
            return ResultUtil.success("添加成功!");
        } catch (Exception e) {
            logger.error("添加机构错误: " + e.getMessage());
            return ResultUtil.error("添加失败!");
        }
    }


    /**
     * 通过机构id 获取机构
     * @param request
     * @param organId
     * @return
     */
    @RequestMapping("findOrganById")
    @ResponseBody
    public ResultUtil findOrganById(HttpServletRequest request, Integer organId) {
        try {
            DbOrgan organ=organService.findOrganById(organId);
            return ResultUtil.success(organ);
        } catch (Exception e) {
            logger.error("查询机构错误: " + e.getMessage());
            return ResultUtil.error("查询失败!");
        }
    }


    /**
     * 更新机构
     * @param request
     * @param organ
     * @return
     */
    @RequestMapping("updateOrgan")
    @ResponseBody
    public ResultUtil updateOrgan(HttpServletRequest request, String organ) {
        try {
            //封装数据
            Map organ1 =(Map) JSONUtils.parse(organ);
            DbOrgan organMy=new DbOrgan();
            DbAdminUser adminUser = (DbAdminUser) request.getAttribute("adminUser");

            organMy.setParentId(StringISNULLUtil.mapToInteger(organ1.get("parentId")));
            organMy.setOrganName(StringISNULLUtil.mapToString(organ1.get("organName")));
            organMy.setRemake(StringISNULLUtil.mapToString(organ1.get("organRemake")));
            organMy.setSorting(StringISNULLUtil.mapToInteger(organ1.get("sorting")));
            organMy.setId(StringISNULLUtil.mapToInteger(organ1.get("id")));

            //判断名称是否重复
            List<DbOrgan> list=organService.findOrganByName(organMy.getId(),organMy.getParentId(),organMy.getOrganName());
            if(list!=null&& list.size()>0){
                return ResultUtil.error("添加失败,名称重复!");
            }
            organService.updateOrgan(organMy);
            return ResultUtil.success("更新成功!");
        } catch (Exception e) {
            logger.error("更新机构错误: " + e.getMessage());
            return ResultUtil.error("更新失败!");
        }
    }


    /**
     * 删除机构
     * @param request
     * @param organId
     * @return
     */
    @RequestMapping("deleteOrgan")
    @ResponseBody
    public ResultUtil deleteOrgan(HttpServletRequest request, Integer organId) {
        try {
            DbAdminUser adminUser = (DbAdminUser) request.getAttribute("adminUser");
           if(organId.equals(adminUser.getOrganId())){
               return ResultUtil.success("不能删除自己的机构");
           }
            DbOrgan organ=new DbOrgan();
            organ.setId(organId);
            organ.setDeleteStatus(1);
            organService.deleteOrgan(organ);
            return ResultUtil.success("删除成功!");
        } catch (Exception e) {
            logger.error("删除机构错误: " + e.getMessage());
            return ResultUtil.error("删除失败!");
        }
    }


    /**
     * 用户页面的机构树
     * @param request
     * @return
     */
    @RequestMapping("findOrganUserTree")
    @ResponseBody
    public ResultUtil findOrganUserTree(HttpServletRequest request) {
        try {
            DbAdminUser adminUser = (DbAdminUser) request.getAttribute("adminUser");
            //添加本级
            DbOrgan organ=organService.findOrganById(adminUser.getOrganId());



            //-----特殊处理开始--------
            //运营员特殊处理 level 等于2 表示运营部
            if(organ!=null&&organ.getLevel()==2){
                //查询父级下面的所有机构
                List<DbOrgan> organChildList= organService.findOrganByParentId(organ.getParentId());
                for(DbOrgan organ1:organChildList){
                    if(organ1.getLevel()==3){
                        organ=organ1;
                    }
                }
            }
            //-----特殊处理结束--------


            OrganToUserTree organToUserTree=new OrganToUserTree();
            organToUserTree.setId(organ.getId().toString());
            organToUserTree.setExpand(true);
            organToUserTree.setChecked(true);
            organToUserTree.setTitle(organ.getOrganName());
            organToUserTree.setChildren(getOrganUserTreeList (organ.getId()));
            List<OrganToUserTree> organManageTreeList=new ArrayList<>();
            organManageTreeList.add(organToUserTree);

            return ResultUtil.success(organManageTreeList);
        } catch (Exception e) {
            logger.error("查询机构树错误: " + e.getMessage());
            return ResultUtil.error("查询失败!");
        }
    }



    /**
     * 得到某个机构下面一个机构树
     * @param request
     * @return
     */
    @RequestMapping("findOrganTree")
    @ResponseBody
    public ResultUtil findOrganTreeById(HttpServletRequest request) {
        try {
            DbAdminUser adminUser = (DbAdminUser) request.getAttribute("adminUser");
            //添加本级
            DbOrgan organ=organService.findOrganById(adminUser.getOrganId());
            OrganManageTree organManageTree=new OrganManageTree();
            organManageTree.setId(organ.getId());
            organManageTree.setOrganName(organ.getOrganName());
            organManageTree.setOrganRemake(organ.getRemake());
            organManageTree.setSorting(organ.getSorting());
            organManageTree.setParentId(organ.getParentId());
            organManageTree.setChildren(getOrganMangeTreeList (organ.getId()));

            List<OrganManageTree> organManageTrees=new ArrayList<>();
            organManageTrees.add(organManageTree);

            return ResultUtil.success(organManageTrees);
        } catch (Exception e) {
            logger.error("查询机构错误: " + e.getMessage());
            return ResultUtil.error("查询失败!");
        }
    }

    private List<OrganManageTree> getOrganMangeTreeList(Integer organParentId) throws Exception{
        List<OrganManageTree> organManageTrees=new ArrayList<>();

        //查询下级
        List<DbOrgan> organChildList= organService.findOrganByParentId(organParentId);
        if(organChildList!=null &&organChildList.size()>0){
            //封装数据
            for(DbOrgan organ:organChildList){
                OrganManageTree organManageTree=new OrganManageTree();
                organManageTree.setId(organ.getId());
                organManageTree.setOrganName(organ.getOrganName());
                organManageTree.setOrganRemake(organ.getRemake());
                organManageTree.setSorting(organ.getSorting());
                organManageTree.setParentId(organ.getParentId());
                organManageTree.setChildren(getOrganMangeTreeList (organ.getId()));
                organManageTrees.add(organManageTree);
            }
        }
      return organManageTrees;

    }

    private List<OrganToUserTree> getOrganUserTreeList(Integer organParentId) throws Exception{
        List<OrganToUserTree> organToUserTreeList=new ArrayList<>();

        //查询下级
        List<DbOrgan> organChildList= organService.findOrganByParentId(organParentId);
        if(organChildList!=null &&organChildList.size()>0){
            //封装数据
            for(DbOrgan organ:organChildList){
                OrganToUserTree organToUserTree=new OrganToUserTree();
                organToUserTree.setId(organ.getId().toString());
                organToUserTree.setExpand(true);
                organToUserTree.setChecked(true);
                organToUserTree.setTitle(organ.getOrganName());
                organToUserTree.setChildren(getOrganUserTreeList (organ.getId()));
                organToUserTreeList.add(organToUserTree);
            }
        }
        return organToUserTreeList;

    }


}
