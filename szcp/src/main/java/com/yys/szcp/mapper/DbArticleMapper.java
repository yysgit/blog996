package com.yys.szcp.mapper;

import com.yys.szcp.entity.DbArticle;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Author: 徐万金·JimXu
 * @email: xuwanjin@jimxu.top
 * @Description:
 * @Date: Create in 17:35 2020-12-15
 */
@Mapper
public interface DbArticleMapper {
    List<DbArticle> findListTree();
}
