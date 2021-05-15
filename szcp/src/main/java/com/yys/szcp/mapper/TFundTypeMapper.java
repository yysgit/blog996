package com.yys.szcp.mapper;

import com.yys.szcp.entity.TFundType;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface TFundTypeMapper {


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
    List<TFundType> findFundTypeByName(@Param("fundTypeId") Integer fundTypeId, @Param("title") String title);

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