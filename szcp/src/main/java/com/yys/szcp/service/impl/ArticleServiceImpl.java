package com.yys.szcp.service.impl;

import com.yys.szcp.entity.TArticle;
import com.yys.szcp.mapper.TArticleMapper;
import com.yys.szcp.service.TArticleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.*;
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
        BufferedReader bufferedReader =null;
        StringBuilder builder = new StringBuilder();
        if (path==null || "".equals(path)){
            return null;
        }
        try {
            fileInputStream = new FileInputStream(new File(markdownPath+path));
            bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream,"UTF-8"));
            String s = null;
            while((s = bufferedReader.readLine())!=null){
                builder.append(System.lineSeparator()+s);
            }
        } catch (Exception e) {
            logger.error("markdown读取失败:"+e);
        }finally {
            try {
                bufferedReader.close();
            }catch (IOException e){
                logger.error("markdown读取失败:"+e);
            }
        }
        return builder.toString();
    }
    @Override
    public void saveMarkdown(String content,String path,Integer num){
        path = path.replaceAll("-","/");
        File markdownFile = new File(markdownPath+path);
        FileOutputStream fos = null;
        OutputStreamWriter oStreamWriter =null;
        try {
            byte[] bytes = content.getBytes();
            if(num==0){
                fos = new FileOutputStream(markdownFile);
            }else{
                fos = new FileOutputStream(markdownFile,true);
            }
            oStreamWriter=new OutputStreamWriter(fos, "utf-8");
            oStreamWriter.write(content);
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                oStreamWriter.close();
            }catch (IOException e){
                logger.error("markdown写入失败:"+e);
            }
        }
        return ;
    }
}
