package br.com.lassulfi.config;

import java.util.List;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import br.com.lassulfi.serialization.converter.YAMLJackson2HttpMessageConverter;

@Configuration
//@EnableWebMvc
public class WebConfig implements WebMvcConfigurer {
	
	private static final MediaType MEDIA_TYPE_YAML = MediaType.valueOf("application/x-yaml");
	
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**")
			.allowedMethods("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS", "HEAD", "TRACE", "CONNECT");
	}

	@Override
	public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
		/*
		 * CONTENT NEGOTIATION VIA EXTENSION
		 * 
		 * ex.: http://localhost:8080/api/person/v1/1.json
		 * 
		 * configurer.favorParameter(false)
		 * .ignoreAcceptHeader(false)
		 * .defaultContentType(MediaType.APPLICATION_JSON)
		 * .mediaType("json",MediaType.APPLICATION_JSON)
		 * .mediaType("xml", MediaType.APPLICATION_XML);
		 */
		
		/*
		 * CONTENT NEGOTIATION VIA QUERY PARAMETER
		 * 
		 * ex.: http://localhost:8080/api/v1/1?mediaType=xml
		 * 
		 * configurer.favorPathExtension(false) .favorParameter(true)
		 * .parameterName("mediaType") .ignoreAcceptHeader(true)
		 * .useRegisteredExtensionsOnly(false)
		 * .defaultContentType(MediaType.APPLICATION_JSON) 
		 * .mediaType("json",MediaType.APPLICATION_JSON)
		 * .mediaType("xml", MediaType.APPLICATION_XML);
		 */
		
		/**
		 * CONTENT NEGOTIATION VIA HEADER
		 */
		configurer.favorPathExtension(false) 
		.favorParameter(false)
		.parameterName("mediaType")
		.ignoreAcceptHeader(false)
		.useRegisteredExtensionsOnly(false)
		.defaultContentType(MediaType.APPLICATION_JSON)
		.mediaType("json",MediaType.APPLICATION_JSON)
		.mediaType("xml", MediaType.APPLICATION_XML)
		.mediaType("x-yaml", MEDIA_TYPE_YAML);
	}

	@Override
	public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
		converters.add(new YAMLJackson2HttpMessageConverter());
	}
}
