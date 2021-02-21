package com.yys.szcp.service;

import com.yys.szcp.entityVo.ArticleTree;

import java.util.List;

/**
 * @Author: 徐万金·JimXu
 * @email: xuwanjin@jimxu.top
 * @Description:
 * @Date: Create in 17:38 2020-12-15
 */
public interface DbArticleService {
    List<ArticleTree> findListTree();
}
