package org.xuxi.codex.shiro;

import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
public class ShiroConfiguration {


    @Bean
    public RedisShiroSessionManager redisShiroSessionManager() {
        return new RedisShiroSessionManager();
    }

    @Bean
    public UserRealm userRear(){
        return new UserRealm();
    }


    @Bean
    public SessionManager sessionManager(RedisShiroSessionManager redisShiroSessionManager) {
        DefaultWebSessionManager sessionManager = new DefaultWebSessionManager();
        //设置session过期时间为1小时(单位：毫秒)，默认为30分钟
        sessionManager.setGlobalSessionTimeout(60 * 60 * 1000); // todo 这是不要魔术字符
        sessionManager.setSessionValidationSchedulerEnabled(true);
        sessionManager.setSessionIdUrlRewritingEnabled(false);
        sessionManager.setSessionDAO(redisShiroSessionManager);
        return sessionManager;
    }



    @Bean
    public SecurityManager securityManager(UserRealm userRealm, SessionManager sessionManager) {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(userRealm);
        securityManager.setSessionManager(sessionManager);
        return securityManager;
    }


    @Bean("shiroFilter")
    public ShiroFilterFactoryBean shiroFilter(SecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilter = new ShiroFilterFactoryBean();
        shiroFilter.setSecurityManager(securityManager);
//        shiroFilter.setLoginUrl("/login.html");
        shiroFilter.setUnauthorizedUrl("/");

        Map<String, String> filterMap = new LinkedHashMap<>();
//        filterMap.put("/swagger/**", "anon");
//        filterMap.put("/v2/api-docs", "anon");
//        filterMap.put("/swagger-ui.html", "anon");
//        filterMap.put("/webjars/**", "anon");
//        filterMap.put("/swagger-resources/**", "anon");
//
//        filterMap.put("/statics/**", "anon");
//        filterMap.put("/login.html", "anon");
//        filterMap.put("/sys/login", "anon");
//        filterMap.put("/favicon.ico", "anon");
//        filterMap.put("/captcha.jpg", "anon");
//        filterMap.put("/**", "authc");
        shiroFilter.setFilterChainDefinitionMap(filterMap);

        return shiroFilter;
    }



}
