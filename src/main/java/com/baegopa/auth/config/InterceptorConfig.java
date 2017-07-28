package com.baegopa.auth.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.baegopa.auth.interceptor.AuthInterceptor;
import com.baegopa.auth.interceptor.LoggerInterceptor;

/**
 * 
 * @author kimsehwan
 *
 */
@Configuration
@EnableWebMvc
public class InterceptorConfig extends WebMvcConfigurerAdapter {

    @Autowired
    private LoggerInterceptor loggerInterceptor;
    @Autowired
    private AuthInterceptor authInterceptor;
    
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry
        	.addInterceptor(authInterceptor)
        	.addPathPatterns("/users/**");
    	registry
        	.addInterceptor(loggerInterceptor)
        	.addPathPatterns("/**");
    }
}
