package com.data.generator.config;

import com.data.generator.interceptor.RequestLogInterceptor;
import com.data.generator.service.NumberService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class AppConfig implements WebMvcConfigurer {

    @Bean
    public NumberService numberService() {
        return new NumberService();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new RequestLogInterceptor())
            .addPathPatterns("/number/**");
    }
}
