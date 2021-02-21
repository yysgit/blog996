package com.yys.szcp.service.impl;

import com.yys.szcp.controller.AdminController;
import com.yys.szcp.entity.DbMarkdown;
import com.yys.szcp.mapper.DbMarkdownMapper;
import com.yys.szcp.service.DbMarkdownService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: 徐万金·JimXu
 * @email: xuwanjin@jimxu.top
 * @Description:
 * @Date: Create in 20:27 2020-10-14
 */
@Service
@Transactional
public class MarkdownServiceImpl implements DbMarkdownService {

    @Value("${markdown-path}")
    private String markdownPath;

    @Value("${projectHttp}")
    private String projectHttp;

    @Autowired
    private DbMarkdownMapper dbMarkdownMapper;

    private static final Logger logger = LoggerFactory.getLogger(AdminController.class);

    @Override
    public String getMarkdown(String path) {
        path = path.replaceAll("-","/");
        FileInputStream fileInputStream = null;
        StringBuilder builder = new StringBuilder();
        if (path==null || "".equals(path)){
            return null;
        }
        try {
            fileInputStream = new FileInputStream(new File(markdownPath+path));
            byte[] buf = new byte[1024]; //数据中转站 临时缓冲区
            int length = 0;
            //循环读取文件内容，输入流中将最多buf.length个字节的数据读入一个buf数组中,返回类型是读取到的字节数。
            //当文件读取到结尾时返回 -1,循环结束。
            while((length = fileInputStream.read(buf)) != -1){
                builder.append(new String(buf, 0, length));
            }

        } catch (Exception e) {
            //e.printStackTrace();
            logger.error("markdown读取失败:"+e);
        }finally {
            try {
                fileInputStream.close();
            }catch (IOException e){
                //e.printStackTrace();
                logger.error("markdown读取失败:"+e);
            }
        }
        return builder.toString();
    }

    public String saveMarkdown(String content){
        String localDate = LocalDate.now().toString();
        String name = String.valueOf(System.currentTimeMillis());
        String dirName = localDate.replaceAll("-","/");
        dirName = markdownPath + dirName;
        File dir = new File(dirName);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        String filePath = dirName+"/"+name+".md";
        File markdownFile = new File(filePath);
        FileOutputStream fos = null;
        try {
            byte[] bytes = content.getBytes();
            fos = new FileOutputStream(markdownFile);
            fos.write(bytes);
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                fos.close();
            }catch (IOException e){
                logger.error("markdown写入失败:"+e);
            }
        }
        return projectHttp+"/markdown/"+localDate+"-"+name+".md";
    }

    public String editMarkdown(String content,String url){
        String[] split = url.split("/");
        String filePath = split[split.length-1];
        filePath = markdownPath + filePath.replaceAll("-","/");
        File markdownFile = new File(filePath);
        FileOutputStream fos = null;
        try {
            byte[] bytes = content.getBytes();
            fos = new FileOutputStream(markdownFile);
            fos.write(bytes);
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                fos.close();
            }catch (IOException e){
                logger.error("markdown写入失败:"+e);
            }
        }
        return url;
    }

    @Override
    public List<Map> findMarkdownList(Integer page, Integer limit) {
        Map<String,Integer> map = new HashMap<>();
        map.put("page",page);
        map.put("limit",limit);
        return dbMarkdownMapper.findMarkdownList(map);
    }

    @Override
    public int findMarkdownListCount(Integer page, Integer limit) {
        Map<String,Integer> map = new HashMap<>();
        map.put("page",page);
        map.put("limit",limit);
        return dbMarkdownMapper.findMarkdownListCount(map);
    }

    @Override
    public int delMarkdown(Integer id) {
        return dbMarkdownMapper.delMarkdown(id);
    }

    @Override
    public int updateMarkdown(DbMarkdown dbMarkdown,String content) {
        this.editMarkdown(content,dbMarkdown.getUrl());
        return dbMarkdownMapper.updateMarkdown(dbMarkdown);
    }

    @Override
    public int addMarkdown(DbMarkdown dbMarkdown,String content) {
        String url = this.saveMarkdown(content);
        dbMarkdown.setUrl(url);
        return dbMarkdownMapper.addMarkdown(dbMarkdown);
    }

    @Override
    public DbMarkdown findMarkdownByid(Integer id) {
        return dbMarkdownMapper.findMarkdownByid(id);
    }

    @Override
    public int findMarkdownByMenuid(Integer meniId) {
        return dbMarkdownMapper.findMarkdownByMenuid(meniId);
    }
}
