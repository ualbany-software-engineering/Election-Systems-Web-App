package com.example.loginVersion2;



import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MyConfig implements WebMvcConfigurer {

	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/homepage").setViewName("homepage");
		registry.addViewController("/").setViewName("index");
		//registry.addViewController("/hello").setViewName("hello");
		registry.addViewController("/login").setViewName("login");
		registry.addViewController("/voting").setViewName("voting");
		registry.addViewController("/results").setViewName("results");
		registry.addViewController("/submitted").setViewName("submitted");
		registry.addViewController("/signup").setViewName("signup");
		registry.addViewController("/index").setViewName("index");
		registry.addViewController("/start").setViewName("start");
		registry.addViewController("/register_success").setViewName("register_success");
		registry.addViewController("/process_registrater").setViewName("register_success");
	}

}