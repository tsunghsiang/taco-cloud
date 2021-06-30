package tacos.controller;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

	public void addViewControllers(ViewControllerRegistry registry) {
		/*
		 * Realize the view mapping between a controller and a view
		 * In the example, when user gets into URI/ -> URL/home
		 *                 when user gets into URI/login -> URL/login
		 * */
		registry.addViewController("/").setViewName("home");
		registry.addViewController("/login");
	}
	
}
