package com.nsrtm.contribuyente.microservice;

import com.nsrtm.contribuyente.microservice.config.CORS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.time.Duration;
import java.util.Arrays;

@SpringBootApplication
@EnableCaching
public class Application {

	@Autowired
	private CORS cors;

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	CorsFilter corsFilter() {
		CorsConfiguration config = new CorsConfiguration();
		config.setAllowCredentials(cors.allowCredentials); // you USUALLY want this
		config.setAllowedOrigins(Arrays.asList(cors.allowOrigins.split(",")));
		config.setAllowedHeaders(Arrays.asList(cors.allowHeaders.split(",")));
		config.setAllowedMethods(Arrays.asList(cors.allowMethods.split(",")));
		config.setMaxAge(Duration.ofSeconds(cors.getMaxAge()));

		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration(cors.getMapping(), config);

		return new CorsFilter(source);
	}
}
