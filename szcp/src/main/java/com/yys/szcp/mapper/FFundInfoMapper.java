package com.yys.szcp.mapper;

import com.yys.szcp.entity.TFundInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface FFundInfoMapper {


    /**
     * 添加基金信息
     * @param fundInfo
     * @return
     */
    int addFundInfo(TFundInfo fundInfo);


    /**
     * 更新基金信息
     * @param fundInfo
     * @return
     */
    int updateFundInfo(TFundInfo fundInfo);

    /**
     * 删除基金信息
     * @param fundInfo
     * @return
     */
    int deleteFundInfo(TFundInfo fundInfo);
    /**
     * 查询所有的基金信息
     * @return
     */
    List<Map> findFundInfoList(Map map);

    Map findFundInfoByCode(Map map);


    Integer addFundNetWorth(Map map);
    Integer updateFundNetWorth(Map map);
    Integer updateFundInfoByCode(Map map);
    Integer updateFundInfoNetWorthByCode(Map map);
    Integer addFundDay(Map map);
    Integer updateFundDay(Map map);




    List<Map> findFundInfoInvestmentList(Map map);

   Integer addFundLevel(Map map);

   Integer updateFundLevel(Map map);


}