package com.niit.theBackendProject.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@EnableWebMvc
@ComponentScan("com.niit.theBackendProject")
public class MvcConfig extends WebMvcConfigurerAdapter {

	// Configuration to load the static resources	
	@Override
	 public void addResourceHandlers(ResourceHandlerRegistry registry) {
	    registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
	 }

	 // Configuration for view resolver
	 @Bean
	 public ViewResolver configureViewResolver() {
	     InternalResourceViewResolver viewResolve = new InternalResourceViewResolver();
	     viewResolve.setPrefix("/WEB-INF/views/");
	     viewResolve.setSuffix(".jsp");

	     return viewResolve;
	 }

	 // Use the DefaultServletHandlerConfigurer 
	 @Override
	 public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer){
	   configurer.enable();
	 }	
	 
	 @Bean
     public MultipartResolver multipartResolver() {
         return new CommonsMultipartResolver();
     }	
}