package com.yys.szcp.service.impl;

import com.yys.szcp.entity.TFundInfo;
import com.yys.szcp.mapper.TFundInfoMapper;
import com.yys.szcp.service.TFundInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Describe:
 * -------------------
 * User: yangyongsheng
 * Date: 2019/06/25 19:34:53
 * Email: 1095737364@qq.com
 */
@Service
public class FundInfoServiceImpl implements TFundInfoService {
    @Autowired
    private TFundInfoMapper fundInfoMapper;

    @Override
    public int addFundInfo(TFundInfo fundInfo) {
        return fundInfoMapper.addFundInfo(fundInfo);
    }
    


    @Override
    public int updateFundInfo(TFundInfo fundInfo) {
        return fundInfoMapper.updateFundInfo(fundInfo);
    }

    @Override
    public int deleteFundInfo(TFundInfo fundInfo) {
        return fundInfoMapper.deleteFundInfo(fundInfo);
    }

    @Override
    public List<TFundInfo> findFundInfoList(Map map) {
        return fundInfoMapper.findFundInfoList(map);
    }

    @Override
    public Integer findFundInfoListCount(Map map) {
        return fundInfoMapper.findFundInfoListCount(map);
    }

}
