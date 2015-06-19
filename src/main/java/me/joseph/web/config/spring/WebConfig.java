package me.joseph.web.config.spring;

import me.joseph.web.app.handler.RequestInterceptors;
import me.joseph.web.app.handler.TimeBasedAccessInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
@EnableWebMvc
public class WebConfig extends WebMvcConfigurerAdapter{

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new RequestInterceptors());
//        registry.addInterceptor(new TimeBasedAccessInterceptor());
    }


}
