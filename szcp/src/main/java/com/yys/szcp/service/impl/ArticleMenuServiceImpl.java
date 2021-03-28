package com.yys.szcp.service.impl;

import com.yys.szcp.entity.TArticle;
import com.yys.szcp.entity.TArticleMenu;
import com.yys.szcp.mapper.TArticleMenuMapper;
import com.yys.szcp.service.TArticleMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Describe:
 * -------------------
 * User: yangyongsheng
 * Date: 2019/06/25 19:34:53
 * Email: 1095737364@qq.com
 */
@Service
public class ArticleMenuServiceImpl implements TArticleMenuService {
    @Autowired
    private TArticleMenuMapper articleMenuMapper;

    @Override
    public int addArticleMenu(TArticleMenu articleMenu) {
        return articleMenuMapper.addArticleMenu(articleMenu);
    }

    @Override
    public TArticleMenu findArticleMenuById(Integer articleMenuId) {
        return articleMenuMapper.findArticleMenuById(articleMenuId);
    }

    @Override
    public List<TArticleMenu> findArticleMenuByParentId(Integer parentId) {
        return articleMenuMapper.findArticleMenuByParentId(parentId);
    }

    @Override
    public List<TArticleMenu> findArticleMenuByName(Integer articleMenuId, Integer parentId, String title) {
        return articleMenuMapper.findArticleMenuByName(articleMenuId,parentId,title);
    }

    @Override
    public int updateArticleMenu(TArticleMenu articleMenu) {
        return articleMenuMapper.updateArticleMenu(articleMenu);
    }

    @Override
    public int deleteArticleMenu(TArticleMenu articleMenu) {
        return articleMenuMapper.deleteArticleMenu(articleMenu);
    }

    @Override
    public List<TArticleMenu> findArticleMenuList() {
        return articleMenuMapper.findArticleMenuList();
    }

    @Override
    public List<TArticle> findArticleByArticleMenuId(Integer articleMenuId) {
        return articleMenuMapper.findArticleByArticleMenuId(articleMenuId);
    }
}
