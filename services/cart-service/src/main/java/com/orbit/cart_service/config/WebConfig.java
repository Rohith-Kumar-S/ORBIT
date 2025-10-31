package com.orbit.cart_service.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig {

	@Bean
	public FilterRegistrationBean<JwtAuthenticationFilter> jwtFilter(JwtAuthenticationFilter filter) {
		FilterRegistrationBean<JwtAuthenticationFilter> registrationBean = new FilterRegistrationBean<>();
		registrationBean.setFilter(filter);
		registrationBean.addUrlPatterns("/api/*"); // Apply to all /api/* endpoints
		return registrationBean;
	}

	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**").allowedOrigins("http://localhost:3000", "http://3.211.201.146:3000")
						.allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS").allowCredentials(true);
			}
		};
	}
}
