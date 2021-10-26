package com.spproject.xdclass.config;

import com.spproject.xdclass.interceptor.LoginInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author xiaolongzhao
 */
@Configuration
public class InterceptorConfig implements WebMvcConfigurer {

   @Autowired
   private  LoginInterceptor loginInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {


        registry.addInterceptor(loginInterceptor).addPathPatterns("/api/v1/pri/*/*/**")
                //不拦截哪些路径   斜杠一定要加
                .excludePathPatterns("/api/v1/pri/user/login","/api/v1/pri/user/register");

        WebMvcConfigurer.super.addInterceptors(registry);

    }

}
