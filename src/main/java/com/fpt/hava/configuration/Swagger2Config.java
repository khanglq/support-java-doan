package com.fpt.hava.configuration;

import java.util.Collections;
import java.util.List;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class Swagger2Config {

  private final Swagger2Properties swagger2Properties;

  public Swagger2Config(Swagger2Properties swagger2Properties) {
    this.swagger2Properties = swagger2Properties;
  }

  @Bean
  public Docket api() {
    return new Docket(DocumentationType.SWAGGER_2).select()
        .apis(RequestHandlerSelectors.basePackage("com.fpt.hava.web.api"))
        .apis(RequestHandlerSelectors.any())
        .paths(PathSelectors.any())
        .build()
        .apiInfo(apiEndPointsInfo())
        .securitySchemes(Collections.singletonList(apiKey()))
        .securityContexts(Collections.singletonList(securityContext()));
  }

  private ApiInfo apiEndPointsInfo() {
    return new ApiInfoBuilder().title(swagger2Properties.getTitle())
        .description(swagger2Properties.getDescription())
        .version(swagger2Properties.getVersion())
        .build();
  }

  private SecurityContext securityContext() {
    return SecurityContext.builder().securityReferences(defaultAuth())
        .forPaths(PathSelectors.regex("/api/.*")).build();
  }

  private List<SecurityReference> defaultAuth() {
    AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
    return Collections.singletonList(
        new SecurityReference("Bearer", new AuthorizationScope[]{authorizationScope}));
  }

  private ApiKey apiKey() {
    return new ApiKey("Bearer", HttpHeaders.AUTHORIZATION, "header");
  }
}
