package com.yys.szcp.service.impl;

import com.yys.szcp.entity.DbOrgan;
import com.yys.szcp.mapper.DbOrganMapper;
import com.yys.szcp.service.DbOrganService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Describe:
 * -------------------
 * User: yangyongsheng
 * Date: 2019/06/25 17:34:16
 * Email: 1095737364@qq.com
 */
@Service
public class OrganServiceImpl implements DbOrganService {
    @Autowired
    private DbOrganMapper organMapper;

    @Override
    public List<DbOrgan> findOrganByName(Integer organId, Integer parentId, String organName) {
        return organMapper.findOrganByName(organId,parentId,organName);
    }

    @Override
    public int addOrgan(DbOrgan organ) {
        return organMapper.addOrgan(organ);
    }

    @Override
    public DbOrgan findOrganById(Integer organId) {
        return organMapper.findOrganById(organId);
    }

    @Override
    public List<DbOrgan> findOrganByParentId(Integer parentId) {
        return organMapper.findOrganByParentId(parentId);
    }

    @Override
    public int updateOrgan(DbOrgan organ) {
        return organMapper.updateOrgan(organ);
    }

    @Override
    public int deleteOrgan(DbOrgan organ) {
        return organMapper.deleteOrgan(organ);
    }

    @Override
    public DbOrgan findOrganByAdminUserId(Integer organId) {
        return organMapper.findOrganByAdminUserId(organId);
    }
}
