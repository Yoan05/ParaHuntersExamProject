package com.softuni.projectForExam.techStore.config;

import com.softuni.projectForExam.techStore.interceptors.AfterCompletionInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    private final AfterCompletionInterceptor afterCompletionInterceptor;

    public WebConfig(AfterCompletionInterceptor afterCompletionInterceptor) {
        this.afterCompletionInterceptor = afterCompletionInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry){
        registry.addInterceptor(afterCompletionInterceptor);
    }
}
