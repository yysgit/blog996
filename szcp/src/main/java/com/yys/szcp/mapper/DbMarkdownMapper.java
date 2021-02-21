package com.yys.szcp.mapper;

import com.yys.szcp.entity.DbMarkdown;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * @Author: 徐万金·JimXu
 * @email: xuwanjin@jimxu.top
 * @Description:
 * @Date: Create in 20:59 2020-10-16
 */
@Mapper
public interface DbMarkdownMapper {

    List<Map> findMarkdownList(Map map);
    int findMarkdownListCount(Map map);
    int delMarkdown(Integer id);
    int updateMarkdown(DbMarkdown dbMarkdown);
    int addMarkdown(DbMarkdown dbMarkdown);
    DbMarkdown findMarkdownByid(Integer id);
    int findMarkdownByMenuid(Integer meniId);
}
