package com.yys.szcp.service.impl;

import com.yys.szcp.entity.TArticle;
import com.yys.szcp.mapper.TArticleMapper;
import com.yys.szcp.service.TArticleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@Service
public class ArticleServiceImpl implements TArticleService {

    private static final Logger logger = LoggerFactory.getLogger(ArticleServiceImpl.class);


    @Autowired
    private TArticleMapper articleMapper;

    @Value("${markdown-path}")
    private String markdownPath;

    @Value("${projectHttp}")
    private String projectHttp;


    @Override
    public int addArticle(TArticle article) {
        return articleMapper.addArticle(article);
    }

    @Override
    public List<Map> findArticleListByArticleMenuId(Map map) {
        return articleMapper.findArticleListByArticleMenuId(map);
    }

    @Override
    public int findArticleListByArticleMenuIdCount(Map map) {
        return articleMapper.findArticleListByArticleMenuIdCount(map);
    }

    @Override
    public int updateArticle(TArticle article) {
        return articleMapper.updateArticle(article);
    }

    @Override
    public int deleteArticle(TArticle article) {
        return articleMapper.deleteArticle(article);
    }

    @Override
    public List<TArticle> findArticleByTitle(Integer articleId, String title) {
        return articleMapper.findArticleByTitle(articleId,title);
    }
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
    @Override
    public void saveMarkdown(String content,String path){
      /*  String localDate = LocalDate.now().toString();
        String name = String.valueOf(System.currentTimeMillis());
        String dirName = url.replaceAll("-","/");*/
        path = path.replaceAll("-","/");
       /* File dir = new File(markdownPath+path);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        String filePath = dirName+"/"+name+".md";*/
        File markdownFile = new File(markdownPath+path);
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
        return ;
    }
}
