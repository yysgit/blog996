package com.yys.szcp.config.druid;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Describe:数据库连接池Druid配置
 * -------------------
 * User: yangyongsheng
 * Date: 2019/06/22 12:07:28
 * Email: 1095737364@qq.com
 */
@Configuration
public class DruidConfig {

    @ConfigurationProperties(prefix = "spring.datasource")
    @Bean(destroyMethod = "close", initMethod = "init")
    public DataSource druid() {
        return new DruidDataSource();
    }

    // 配置Druid的监控
    // 1、配置一个管理后台的Servlet
    @Bean
    public ServletRegistrationBean statViewServlet() {
        ServletRegistrationBean bean = new ServletRegistrationBean(new StatViewServlet(), "/druid/*");

        Map<String, String> initParams = new HashMap<>();

        // 监控页面登录用户名
        initParams.put("loginUsername", "admin");
        // 监控页面登录用户密码
        initParams.put("loginPassword", "123456");
        // ip白名单（没有配置或者为空，则允许所有访问）
        initParams.put("allow", "");
        // ip黑名单（如果某个ip同时存在，deny优先于allow）
        initParams.put("deny", "");

        bean.setInitParameters(initParams);
        return bean;
    }

    // 2、配置一个web监控的filter
    @Bean
    public FilterRegistrationBean webStatFilter() {
        FilterRegistrationBean bean = new FilterRegistrationBean();
        bean.setFilter(new WebStatFilter());

        Map<String, String> initParams = new HashMap<>();

        // 不拦截的静态资源
        initParams.put("exclusions", "*.js,*.gif,*.jpg,*.bmp,*.png,*.css,*.ico,/druid/*");
        bean.setInitParameters(initParams);

        // 拦截所有的请求
        bean.setUrlPatterns(Arrays.asList("/*"));
        return bean;
    }


}
