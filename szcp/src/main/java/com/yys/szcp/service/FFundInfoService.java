package com.yys.szcp.service;

import com.yys.szcp.entity.TFundInfo;

import java.util.List;
import java.util.Map;

/**
 * Describe:
 * -------------------
 * User: yangyongsheng
 * Date: 2019/06/25 19:34:16
 * Email: 1095737364@qq.com
 */
public interface FFundInfoService {

    /**
     * 添加基金类型
     *
     * @param fundInfo
     * @return
     */
    int addFundInfo(TFundInfo fundInfo);


    /**
     * 更新基金类型
     *
     * @param fundInfo
     * @return
     */
    int updateFundInfo(TFundInfo fundInfo);

    /**
     * 删除基金类型
     *
     * @param fundInfo
     * @return
     */
    int deleteFundInfo(TFundInfo fundInfo);

    /**
     * 查询所有的基金类型
     *
     * @return
     */
    List<Map> findFundInfoList(Map map);

    List<Map> findFundInfoInvestmentList(Map map);

    int addFundInfoList(String maxData,String maxDataDate, String fundCode, List<List<String>> fundValue);


}
