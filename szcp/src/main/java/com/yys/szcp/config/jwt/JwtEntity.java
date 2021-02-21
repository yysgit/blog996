package com.yys.szcp.config.jwt;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "jwt")
public class JwtEntity {

    /**
     * 发行者名
     */
    private String name;

    /**
     * base64加密密钥
     */
    private String base64Secret;

    /**
     * jwt中过期时间设置(分)
     */
    private int jwtExpires;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBase64Secret() {
        return base64Secret;
    }

    public void setBase64Secret(String base64Secret) {
        this.base64Secret = base64Secret;
    }

    public int getJwtExpires() {
        return jwtExpires;
    }

    public void setJwtExpires(int jwtExpires) {
        this.jwtExpires = jwtExpires;
    }
}
