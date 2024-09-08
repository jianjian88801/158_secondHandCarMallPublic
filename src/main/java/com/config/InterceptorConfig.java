package com.config;

import com.interceptor.AuthorizationInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

@Configuration
public class InterceptorConfig extends WebMvcConfigurationSupport{

	@Bean
    public AuthorizationInterceptor getAuthorizationInterceptor() {
        return new AuthorizationInterceptor();
    }

	@Override
    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(getAuthorizationInterceptor()).addPathPatterns("/**")
//				.excludePathPatterns("/static/**","/**/*.css","/**/*.html","/**/*.js","/upload/*");
		registry.addInterceptor(getAuthorizationInterceptor()).addPathPatterns("/**").excludePathPatterns("/static/**");
		super.addInterceptors(registry);
	}

	/**
	 * springboot 2.0配置WebMvcConfigurationSupport之后，会导致默认配置被覆盖，要访问静态资源需要重写addResourceHandlers方法
	 */
	@Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/**")
				.addResourceLocations("classpath:/META-INF/resources/");
		registry.addResourceHandler("/upload/**")
				.addResourceLocations("file:" + "F:\\educationProject\\汽车租赁系统\\二手车交易\\secondHandCarMall\\src\\main\\webapp\\upload\\");
//		registry.addResourceHandler("/**")
//        .addResourceLocations("classpath:/resources/**")
//        .addResourceLocations("classpath:/static/**")
//        .addResourceLocations("classpath:/admin/**")
//        .addResourceLocations("classpath:/front/**")
//        .addResourceLocations("classpath:/public/");
		super.addResourceHandlers(registry);
    }
}
