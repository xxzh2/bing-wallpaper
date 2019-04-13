package com.ginkgo.bing.wallpaper.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
public class MvcConfigurer extends WebMvcConfigurerAdapter {

//	@Bean 
//	PropertyPlaceholderConfigurer PropertyPlaceholderConfigurer() {
//		PropertyPlaceholderConfigurer configurer = new PropertyPlaceholderConfigurer();
//		
//		configurer.setLocations(new ClassPathResource[]{new ClassPathResource("config.properties")});
//		configurer.setIgnoreUnresolvablePlaceholders(true);
//		return configurer;
//	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/WEB-INF/**").addResourceLocations("classpath:/");
		registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
		super.addResourceHandlers(registry);
	}

	@Override
	public void configureViewResolvers(ViewResolverRegistry registry) {
		registry.viewResolver(new InternalResourceViewResolver("/WEB-INF/templates/", ".html"));
	}

}
