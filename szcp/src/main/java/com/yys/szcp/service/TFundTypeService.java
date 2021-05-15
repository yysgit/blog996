package com.yys.szcp.service;

import com.yys.szcp.entity.TFundType;

import java.util.List;
import java.util.Map;

/**
 * Describe:
 * -------------------
 * User: yangyongsheng
 * Date: 2019/06/25 19:34:16
 * Email: 1095737364@qq.com
 */
public interface TFundTypeService {

    /**
     * 添加基金类型
     * @param fundType
     * @return
     */
    int addFundType(TFundType fundType);


    /**
     *  通过菜单名称 得到的菜单
     * @param fundTypeId
     * @return
     */
    List<TFundType> findFundTypeByName(Integer fundTypeId,String title);

    /**
     * 更新基金类型
     * @param fundType
     * @return
     */
    int updateFundType(TFundType fundType);

    /**
     * 删除基金类型
     * @param fundType
     * @return
     */
    int deleteFundType(TFundType fundType);

    /**
     * 查询所有的基金类型
     * @return
     */
    List<TFundType> findFundTypeList(Map map);
    Integer findFundTypeListCount(Map map);

    /**
     * 查询
     * @param map
     * @return
     */
    List<TFundType> findFundTypeListByType(Map map);

}
