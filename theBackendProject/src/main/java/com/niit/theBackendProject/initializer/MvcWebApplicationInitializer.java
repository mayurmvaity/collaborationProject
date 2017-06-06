package com.niit.theBackendProject.initializer;

import javax.servlet.Filter;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import com.niit.theBackendProject.config.CORSFilter;
import com.niit.theBackendProject.config.EmailConfig;
import com.niit.theBackendProject.config.HibernateConfig;
import com.niit.theBackendProject.config.MvcConfig;
import com.niit.theBackendProject.config.RootConfig;
public class MvcWebApplicationInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class[] {HibernateConfig.class, RootConfig.class, EmailConfig.class};
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		return new Class[] {MvcConfig.class};
	}

	@Override
	protected String[] getServletMappings() {
		return new String[] {"/"};
	}

	@Override
    protected Filter[] getServletFilters() {
   	 Filter [] singleton = { new CORSFilter() };
   	 return singleton;
    }
}