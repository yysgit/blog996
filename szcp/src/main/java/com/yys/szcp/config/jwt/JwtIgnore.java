package com.yys.szcp.config.jwt;

import java.lang.annotation.*;

/**
 * JWT请求忽略注解
 * Created by Hilox on 2018/11/20 0020.
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface JwtIgnore {
}
