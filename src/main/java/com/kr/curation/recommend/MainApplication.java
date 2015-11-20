package com.kr.curation.recommend;

import java.util.EnumSet;

import javax.servlet.DispatcherType;
import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration.Dynamic;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.kr.curation.recommend.base.filter.CORSFilter;

@Configuration
@ComponentScan(basePackages = "com.kr.curation.recommend")
@EnableWebMvc
@EnableAutoConfiguration(exclude = { DataSourceAutoConfiguration.class })
public class MainApplication extends SpringBootServletInitializer implements
									WebApplicationInitializer {

	@Override
	protected SpringApplicationBuilder configure(
								SpringApplicationBuilder builder) {
		return builder.sources(MainApplication.class);
	}

	@Override
	public void onStartup(ServletContext servletContext)
			throws ServletException {
		AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
		Dynamic servlet = servletContext.addServlet("DispatcherServlet", new DispatcherServlet(context));
		servlet.setLoadOnStartup(1);
		servlet.addMapping("/*");

		CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
		characterEncodingFilter.setEncoding("UTF-8");
		characterEncodingFilter.setForceEncoding(true);
		FilterRegistration.Dynamic characterEncoding = servletContext
				.addFilter("characterEncoding", characterEncodingFilter);
		EnumSet<DispatcherType> dispatcherTypes = EnumSet.of(
				DispatcherType.REQUEST, DispatcherType.FORWARD);
		characterEncoding.addMappingForUrlPatterns(dispatcherTypes, true, "/");

		 FilterRegistration.Dynamic corsFilter = servletContext.addFilter("corsFilter", CORSFilter.class);
		 corsFilter.addMappingForUrlPatterns(null, false, "/*");

		super.onStartup(servletContext);
	}

	public static void main(String[] args) {
		SpringApplication.run(MainApplication.class, args);
	}
}
