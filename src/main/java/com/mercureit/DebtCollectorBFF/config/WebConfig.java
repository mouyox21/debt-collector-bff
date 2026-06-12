package com.mercureit.DebtCollectorBFF.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {


//	private final StringToOptionalDateConverter stringToOptionalDateConverter;
//
//	public WebConfig(StringToOptionalDateConverter stringToOptionalDateConverter) {
//		this.stringToOptionalDateConverter = stringToOptionalDateConverter;
//	}

//	@Override
//	public void addFormatters(FormatterRegistry registry) {
//		registry.addConverter(stringToOptionalDateConverter);

//	}









	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**").allowedMethods("OPTIONS", "GET", "PUT", "POST", "PATCH", "DELETE")
				.allowedOrigins("*").allowedHeaders("*");

	}

}