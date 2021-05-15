package com.yys.szcp.service.impl;

import com.yys.szcp.entity.TFundType;
import com.yys.szcp.mapper.TFundTypeMapper;
import com.yys.szcp.service.TFundTypeService;
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
public class FundTypeServiceImpl implements TFundTypeService {
    @Autowired
    private TFundTypeMapper fundTypeMapper;

    @Override
    public int addFundType(TFundType fundType) {
        return fundTypeMapper.addFundType(fundType);
    }
    
    @Override
    public List<TFundType> findFundTypeByName(Integer fundTypeId, String title) {
        return fundTypeMapper.findFundTypeByName(fundTypeId,title);
    }

    @Override
    public int updateFundType(TFundType fundType) {
        return fundTypeMapper.updateFundType(fundType);
    }

    @Override
    public int deleteFundType(TFundType fundType) {
        return fundTypeMapper.deleteFundType(fundType);
    }

    @Override
    public List<TFundType> findFundTypeList(Map map) {
        return fundTypeMapper.findFundTypeList(map);
    }

    @Override
    public Integer findFundTypeListCount(Map map) {
        return fundTypeMapper.findFundTypeListCount(map);
    }

    @Override
    public List<TFundType> findFundTypeListByType(Map map) {
       return fundTypeMapper.findFundTypeListByType(map);
    }
}
