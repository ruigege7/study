package com.yangr.study.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
import org.springframework.session.web.http.CookieSerializer;
import org.springframework.session.web.http.DefaultCookieSerializer;

/**
 * http session配置.
 *
 * @author: watson
 */
@Configuration
@EnableRedisHttpSession(
        maxInactiveIntervalInSeconds = 60 * 60 * 8,
        redisNamespace = "http-session-namespace")
public class WebSessionConfig {

    /**
     * Session名称前缀.
     */
    static final String SESSION_NAME_PREFIX = "QADMIN_SESSION";

    /**
     * 设定浏览器cookie名称.
     *
     * @return FilterRegistrationBean
     */
    @Bean
    public CookieSerializer cookieSerializer() {
        DefaultCookieSerializer serializer = new DefaultCookieSerializer();
        serializer.setCookieName(SESSION_NAME_PREFIX);
        serializer.setCookiePath("/");
        return serializer;
    }
}
