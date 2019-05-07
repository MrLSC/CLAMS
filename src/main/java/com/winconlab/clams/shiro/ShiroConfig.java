package com.winconlab.clams.shiro;

import com.winconlab.clams.utils.LogBackUtil;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
public class ShiroConfig {
    /**
     * shiro拦截器注入
     */
    @Bean
    public ShiroFilterFactoryBean shiroFilter(SecurityManager securityManager) {
        ShiroFilterFactoryBean filterFactoryBean = new ShiroFilterFactoryBean();
        // 必须设置 SecurityManager
        filterFactoryBean.setSecurityManager(securityManager);
        // setLoginUrl 如果不设置值，默认会自动寻找Web工程根目录下的"/login.jsp"页面 或 "/login" 映射
        filterFactoryBean.setLoginUrl("/page/login");
        // 设置无权限时跳转的 url;
        filterFactoryBean.setUnauthorizedUrl("/page/notAuth");

        //RolesAuthorizationFilter
        //PermissionsAuthorizationFilter;
        //添加自定义拦截器
        Map<String, Filter> filterMap = new HashMap<>();
        filterMap.put("roleOr", new RoleOrAuthorizationFilter());
        filterMap.put("permOr", new PermOrAuthorizationFilter());

        //添加拦截器拦截规则
        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<>();
        //游客权限
        filterChainDefinitionMap.put("/guest/**", "anon");
        //开放的接口
        filterChainDefinitionMap.put("/sysuser/login", "anon");
        filterChainDefinitionMap.put("/sysuser/register/**", "anon");
        filterChainDefinitionMap.put("/**/error_page/**", "anon");

        //需要角色访问 例如 roles[admin,user] : 配置多个角色需要拥有全部角色才能访问
        //                rolesOr[admin,user] : 配置多个角色只需其中一个满足即可访问
        filterChainDefinitionMap.put("/page/need_role", "roles[admin]");
        filterChainDefinitionMap.put("/page/need_role_or", "roleOr[admin,user]");

        //需要权限访问
        filterChainDefinitionMap.put("/page/need_permission", "perms[admin:kefangwen]");
        filterChainDefinitionMap.put("/page/need_permission_or", "permOr[admin:kefangwen,admin:rd]");


        //退出操作
        filterChainDefinitionMap.put("/sysuser/logout", "logout");
        //其余接口一律拦截
        //主要这行代码必须放在所有权限设置的最后，不然会导致所有 url 都被拦截
        filterChainDefinitionMap.put("/**", "authc");


        filterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        filterFactoryBean.setFilters(filterMap);
        LogBackUtil.info("Shiro拦截器工厂类注入成功");
        return filterFactoryBean;
    }


    /**
     * 自定义角色访问控制拦截器
     *
     * @return
     */
    @Bean
    public RoleOrAuthorizationFilter customRolesAuthorizationFilter() {
        return new RoleOrAuthorizationFilter();
    }

    /**
     * 注入shiro的SecurityManager
     *
     * @param clamsRealm
     * @return
     */
    @Bean
    public SecurityManager securityManager(ClamsRealm clamsRealm) {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(clamsRealm);
        return securityManager;
    }

    /**
     * realm
     *
     * @return
     */
    @Bean
    public ClamsRealm clamsRealm(
            @Qualifier("hashedCredentialsMatcher") HashedCredentialsMatcher matcher,
            @Qualifier("ehCacheManager") EhCacheManager ehCacheManager) {
        ClamsRealm myAuthorizingRealm = new ClamsRealm();
        // 设置密码凭证匹配器
        myAuthorizingRealm.setCredentialsMatcher(matcher); // myShiroRealm.setCredentialsMatcher(hashedCredentialsMatcher());
        // 设置缓存管理器
        myAuthorizingRealm.setCacheManager(ehCacheManager);
        return myAuthorizingRealm;
    }

    @Bean
    public HashedCredentialsMatcher hashedCredentialsMatcher() {
        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
        hashedCredentialsMatcher.setHashAlgorithmName("MD5");// 散列算法:这里使用MD5算法;
        hashedCredentialsMatcher.setHashIterations(2);// 散列的次数
        return hashedCredentialsMatcher;
    }

    /**
     * 开启shiro aop注解支持. 使用代理方式;所以需要开启代码支持; Controller才能使用@RequiresPermissions
     *
     * @param securityManager
     * @return
     */
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(
            @Qualifier("securityManager") SecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }


    /**
     * 缓存管理器
     *
     * @return
     */
    @Bean
    public EhCacheManager ehCacheManager() {
        EhCacheManager cacheManager = new EhCacheManager();
        cacheManager.setCacheManagerConfigFile("classpath:shiro-ehcache.xml");
        return cacheManager;
    }

    /**
     * cookie对象;
     *
     * @return
     */
    @Bean
    public SimpleCookie rememberMeCookie() {
        // 这个参数是cookie的名称，对应前端的checkbox 的name = rememberMe
        SimpleCookie simpleCookie = new SimpleCookie("rememberMe");
        // <!-- 记住我cookie生效时间30天（259200） ,单位秒;-->
        simpleCookie.setMaxAge(259200);
        return simpleCookie;
    }

    /**
     * 记住我管理器 cookie管理对象;
     *
     * @return
     */
    @Bean(name = "cookieRememberMeManager")
    public CookieRememberMeManager rememberMeManager() {
        CookieRememberMeManager cookieRememberMeManager = new CookieRememberMeManager();
        cookieRememberMeManager.setCookie(rememberMeCookie());
        return cookieRememberMeManager;
    }
}
