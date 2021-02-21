package com.yys.szcp.service.impl;

import com.yys.szcp.entity.DbArticle;
import com.yys.szcp.entityVo.ArticleTree;
import com.yys.szcp.entityVo.ArticleTreeUtil;
import com.yys.szcp.mapper.DbArticleMapper;
import com.yys.szcp.service.DbArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: 徐万金·JimXu
 * @email: xuwanjin@jimxu.top
 * @Description:
 * @Date: Create in 17:40 2020-12-15
 */
@Service
public class DbArticleServiceImpl implements DbArticleService {
    @Autowired
    private DbArticleMapper mapper;

    @Override
    public List<ArticleTree> findListTree() {
        return ArticleTreeUtil.getArticleTree(mapper.findListTree());
    }
}
