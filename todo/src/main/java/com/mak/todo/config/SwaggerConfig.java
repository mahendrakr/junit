package com.mak.todo.config;

import org.springframework.context.annotation.Bean;

import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


@EnableSwagger2
public class SwaggerConfig {
	private static final ApiInfo DEFAULT_API_INFO = new ApiInfo("Todo Api title", "Todo API Documentation", "1.0", "urn:tos",
	          "mahendrakumar@gmail.com", "Apache 2.0", "http://www.apache.org/licenses/LICENSE-2.0");

	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2).apiInfo(DEFAULT_API_INFO);
	}
}
