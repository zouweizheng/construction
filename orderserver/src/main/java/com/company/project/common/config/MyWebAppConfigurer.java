package com.company.project.common.config;

import com.company.project.common.aspect.AccessFilter;
import com.company.project.common.aspect.TokenFilter;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Created by ZouWeizheng on 2017-12-07.
 */
@Configuration
public class MyWebAppConfigurer
        extends WebMvcConfigurerAdapter {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 多个拦截器组成一个拦截器链
        // addPathPatterns 用于添加拦截规则
        // excludePathPatterns 用户排除拦截
        registry.addInterceptor(new AccessFilter()).addPathPatterns("/**");
        registry.addInterceptor(new TokenFilter()).addPathPatterns("/**").excludePathPatterns("/token/**");
        super.addInterceptors(registry);
    }

}
