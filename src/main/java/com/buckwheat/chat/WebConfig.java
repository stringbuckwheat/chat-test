package com.buckwheat.chat;

import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

public class WebConfig implements WebMvcConfigurer {
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
	    // registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
		
		registry.addResourceHandler("/webjars/**")
        .addResourceLocations("/webjars/")
        .resourceChain(false);
		registry.setOrder(1);
	}
}
