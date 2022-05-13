package com.vncarca;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class VncarcaApplication {
	public static void main(String[] args) {
		SpringApplication.run(VncarcaApplication.class, args);
		// String password = "admin";
		// PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		//     String encodedPassword = passwordEncoder.encode(password);
		//     System.out.print("--------> \n"+encodedPassword);
	}
	
	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**").allowedOriginPatterns("*")
						.allowedMethods("*")
						.allowedHeaders("*")
						.allowedOrigins("http://localhost:4200", "http://localhost:9898/**").maxAge(3600);
			}
		};
	}
}
