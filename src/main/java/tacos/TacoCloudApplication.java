package tacos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author andy1
 * Boostrap class
 */
@SpringBootApplication
public class TacoCloudApplication implements WebMvcConfigurer{

	public static void main(String[] args) {
		SpringApplication.run(TacoCloudApplication.class, args);
	}

	/*
	 * If uncomment the snippet, do the following 2 steps:
	 * [1] Remove 'HomeController' class from the source
	 * [2] Comment @WebMvcTest(HomeController.class) from HomeControllerTest class
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/").setViewName("home");
	}
	*/
}
