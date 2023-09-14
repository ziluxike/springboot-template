package com.ziluxike.springboottemplate.config;


import com.ziluxike.springboottemplate.annotation.UserIdResolver;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

/**
 * @author ziluxike
 * @since 2023/8/16
 */
@Configuration
public class ResolverConfig implements WebMvcConfigurer {
    final UserIdResolver userIdResolver;

    public ResolverConfig(UserIdResolver userIdResolver) {
        this.userIdResolver = userIdResolver;
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(userIdResolver);
    }
}
