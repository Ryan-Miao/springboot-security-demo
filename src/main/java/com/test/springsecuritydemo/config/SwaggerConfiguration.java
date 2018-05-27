package com.test.springsecuritydemo.config;

import io.swagger.annotations.Api;
import java.util.ArrayList;
import java.util.List;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Created by Ryan Miao on 12/14/17.
 */
@EnableSwagger2
@Configuration
public class SwaggerConfiguration {

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
            .title("API")
            .description("查询")
            .termsOfServiceUrl("")
            .version("1.0.0")
            .build();
    }

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
            .select()
            .apis(RequestHandlerSelectors.withClassAnnotation(Api.class))
            .build()
            .globalOperationParameters(globalOperationParameters())
            .apiInfo(apiInfo());
    }

    private List<Parameter> globalOperationParameters() {
        final List<Parameter> parametersList = new ArrayList<>();

        final Parameter authorization = new ParameterBuilder()
            .name("Authorization")
            .description("除了登陆接口外，都需要通过此heaer来传递token, Bearer xxx")
            .modelRef(new ModelRef("string"))
            .parameterType("header")
            .required(false)
            .build();

        parametersList.add(authorization);

        return parametersList;
    }
}
