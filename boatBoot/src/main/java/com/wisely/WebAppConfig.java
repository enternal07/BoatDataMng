package com.wisely;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.wisely.intercept.InterceptorConfig;

@Configuration
public class WebAppConfig extends WebMvcConfigurerAdapter {  
  
    @Override  
    public void addInterceptors(InterceptorRegistry registry) {  
        //注册自定义拦截器，添加拦截路径和排除拦截路径  
        registry.addInterceptor(new InterceptorConfig()).addPathPatterns("/**").excludePathPatterns("/user/login");  
    }  
}
