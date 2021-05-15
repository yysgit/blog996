package com.yys.szcp.mapper;

import com.yys.szcp.entity.TFundInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface TFundInfoMapper {


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
    List<TFundInfo> findFundInfoList(Map map);
    Integer findFundInfoListCount(Map map);
}