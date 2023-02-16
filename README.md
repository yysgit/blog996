# Blog996 网站介绍

blog996网址: https://www.blog996.com/md

![image.png](https://api.blog996.com/sys/image/downLoadImage/20221112000647985_763433.png)
![image.png](https://api.blog996.com/sys/image/downLoadImage/20221112000740862_962978.png)

## 技术

- Springboot(jwt,druid)
- Vue(IviewUI)

## 环境：
- jdk v1.8
- node v14.4.0
- maven v3.2.5
- mysql 5.7.26

## 工具：
- Intellij idea 2021.2
- VSCode v1.45.1
- Navicat for Mysql 11.1.20.0
- phpStudy  8.1.1.3版本

## 运行：
### 后台程序
1. 下载zip程序包,然后使用idea 打开szcp;使用VSCode打开szcpvue:
   ![image.png](https://api.blog996.com/sys/image/downLoadImage/20221112001428852_219761.png)
2. 在Navicat中新建fullstackblog数据库，执行下载的fullstackblog.sql脚本， 注意数据库编码为utf8。
![image.png](https://api.blog996.com/sys/image/downLoadImage/20221112001308027_100874.png)


3. 打开项目,找到application.yml文件,将下载的数据包解压到指定位置,然后更改md文档目录和md中图片的目录
```yaml
pathDirImg: E:\MyGithubProject\markdownImage\
markdown-path: E:\MyGithubProject\markdown\
```
![image.png](https://api.blog996.com/sys/image/downLoadImage/20221112001026224_585549.png)

4. 直接运行springboot的SzcpApplication.java的main主类方法,启动后端服务.

### 前端程序
1. 安装VSCode和node后，先在szcpvue目录下执行模块安装： 
```shell
npm instal
```
3. 运行程序： 
```shell
npm run dev 
```


### 访问
1. 后端登录: [http://localhost:9000/sys/login](http://localhost:9000/sys/login)  登录用户名和密码  admin 123456
3. 前端访问: [http://localhost:9000/md](http://localhost:9000/md)  

### 页面浏览
![1.png](https://api.blog996.com/sys/image/downLoadImage/20211219221044896_196602.png)
![2.png](https://api.blog996.com/sys/image/downLoadImage/20211219221344064_258028.png)
![3.png](https://api.blog996.com/sys/image/downLoadImage/20211219221350374_229502.png)
![4.png](https://api.blog996.com/sys/image/downLoadImage/20211219221358097_525452.png)
![5.png](https://api.blog996.com/sys/image/downLoadImage/20211219221406050_345036.png)
![6.png](https://api.blog996.com/sys/image/downLoadImage/20211219221411644_830607.png)




### 技术支持
1. 免服QQ群：647474923
2. 付费QQ群：123300273
3. 作者QQ号：1095737364
