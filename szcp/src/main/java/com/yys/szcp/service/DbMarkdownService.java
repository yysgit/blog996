package com.yys.szcp.service;

import com.yys.szcp.entity.DbMarkdown;

import java.util.List;
import java.util.Map;

/**
 * @Author: 徐万金·JimXu
 * @email: xuwanjin@jimxu.top
 * @Description:
 * @Date: Create in 20:14 2020-10-14
 */
public interface DbMarkdownService {
    String getMarkdown(String path);
    String saveMarkdown(String content);
    String editMarkdown(String content,String url);
    List<Map> findMarkdownList(Integer pageIndex, Integer pageSize);
    int findMarkdownListCount(Integer pageIndex, Integer pageSize);
    int delMarkdown(Integer id);
    int updateMarkdown(DbMarkdown dbMarkdown,String content);
    int addMarkdown(DbMarkdown dbMarkdown,String content);
    DbMarkdown findMarkdownByid(Integer id);
    int findMarkdownByMenuid(Integer meniId);
}
