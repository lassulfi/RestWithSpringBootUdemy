package br.com.lassulfi.config;

import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors.basePackage("br.com.lassulfi"))
				.paths(PathSelectors.any())
				.build()
				.apiInfo(apiInfo());
	}

	private ApiInfo apiInfo() {
		return new ApiInfo(
				"RESTful API With Spring Boot 2.1.3 - AWS", 
				"RESTful API developed in Java using Spring Boot Framework version 2.1.3 - AWS", 
				"v1", 
				"Terms of Service URL", 
				new Contact(
						"Luis Daniel Assulfi", 
						"https://lassulfi.github.io/ProjetoPortifolio/", 
						"lassulfi@gmail.com"), 
				"API License", 
				"API License URL", 
				Collections.EMPTY_LIST);
	}
}
