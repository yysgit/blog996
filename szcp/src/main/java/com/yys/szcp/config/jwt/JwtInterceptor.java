package com.yys.szcp.config.jwt;

import com.alibaba.fastjson.JSON;
import com.yys.szcp.constant.ExceptionConstant;
import com.yys.szcp.entity.DbAdminUser;
import com.yys.szcp.entity.DbMenu;
import com.yys.szcp.service.DbAdminUserService;
import com.yys.szcp.service.DbMenuService;
import com.yys.szcp.utils.ResultUtil;
import io.jsonwebtoken.Claims;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.List;


public class JwtInterceptor implements HandlerInterceptor {
    private Logger logger = LoggerFactory.getLogger(JwtInterceptor.class);

    @Autowired
    private JwtEntity jwtEntity;

    @Autowired
    private DbAdminUserService adminUserService;

    @Autowired
    private DbMenuService menuService;

    //返回方法
    private static void printJson(HttpServletResponse response, Integer code, String msg) {
        ResultUtil resultUtil = new ResultUtil();
        resultUtil.setCode(code);
        resultUtil.setMsg(msg);
        String content = JSON.toJSONString(resultUtil);
        printContent(response, content);
    }

    private static void printContent(HttpServletResponse response, String content) {
        try {
//            response.reset();
            response.setContentType("application/json");
            response.setHeader("Cache-Control", "no-store");
            response.setCharacterEncoding("UTF-8");
            PrintWriter pw = response.getWriter();
            pw.write(content);
            pw.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
                             Object handler) throws Exception {
        //权限验证
        String url = request.getServletPath();
        String myUrl = url.substring(1, url.length());
        logger.info("访问路径:" + url);
        if (myUrl.contains("static/")) {
            return true;
        }
        if (myUrl.contains("fund/")) {
            return true;
        }
        if (myUrl.contains("findArticleMenuToArticleListForHome")
                || myUrl.contains("markdown")
                || myUrl.contains("sys/article/findArticleListByName")
                || myUrl.contains("sys/uploadImg/downLoadBanner")
        ) {
            return true;
        }



		if (myUrl.contains("swagger")) {
            System.out.println("---Joe---swagger");
            return true;
        }
		
        // 忽略带JwtIgnore注解的请求, 不做后续token认证校验
        if (handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            JwtIgnore jwtIgnore = handlerMethod.getMethodAnnotation(JwtIgnore.class);
            if (jwtIgnore != null) {
                return true;
            }
        }


        if (HttpMethod.OPTIONS.equals(request.getMethod())) {
            response.setStatus(HttpServletResponse.SC_OK);
            return true;
        }

        final String authHeader = request.getHeader(JwtConstant.AUTH_HEADER_KEY);

        if (StringUtils.isEmpty(authHeader)) {
            // TODO 这里自行抛出异常
            logger.info("===== 用户未登录, 请先登录:" + url);
            response.setStatus(200);
            printJson(response, ExceptionConstant.TOKEN_EXCEPTIONCODE, "用户未登录, 请先登录!");
            return false;
        }

        // 校验头格式校验
        if (!JwtUtils.validate(authHeader)) {
            // TODO 这里自行抛出异常
            logger.info("===== token格式异常 =====");
            return false;
        }

        // token解析
        final String authToken = JwtUtils.getRawToken(authHeader);
        Claims claims = JwtUtils.parseToken(authToken, jwtEntity.getBase64Secret());
        if (claims == null) {
            response.setStatus(200);
            printJson(response, ExceptionConstant.TOKEN_EXCEPTIONCODE, "登录信息过时,请重新登录!");
            logger.info("===== token解析异常 =====");
            return false;
        }

        //控制后台管理权限信息
        if("/sys/".equals(url.substring(0,5))) {
            // 传递所需信息  userId 保存在claims 里面
            request.setAttribute("adminUserId", claims);

            Integer integer = Integer.valueOf(claims.get("userId").toString());
            //权限验证
            DbAdminUser adminUser = adminUserService.findAdminUserById(integer);
            request.setAttribute("adminUser", adminUser);

            if ("sys/admin/login".equals(myUrl)
                    || "sys/admin/findAdminUserInfo".equals(myUrl)
                    || "sys/menu/findAllIcon".equals(myUrl)
                    || myUrl.contains("static/")
            ) {
                return true;
            }


            List<DbMenu> list = menuService.findMenuByRoleId(adminUser.getRoleId());
            boolean purview = false;
            for (DbMenu menu : list) {
                if (myUrl.equals(menu.getHref())) {
                    purview = true;
                    return true;
                }
            }
            if (!purview) {
                logger.info("无权限的访问路径:" + url);
            }
        }

        //控制微信小程序权限信息
        if("/weixin/".equals(url.substring(0,8))){
            return true;
        }

        //控制Android App 权限信息
        if("/android/".equals(url.substring(0,9))){
            return true;
        }

        //ios App 权限信息
        if("/ios/".equals(url.substring(0,5))){
            return true;
        }
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response,
                           Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response,
                                Object handler, Exception ex) throws Exception {

    }
}
