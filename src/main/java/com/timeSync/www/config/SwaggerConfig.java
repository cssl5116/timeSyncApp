package com.timeSync.www.config;

import io.swagger.annotations.ApiOperation;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.ApiSelectorBuilder;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

/**
 * @author fishx
 * @version 1.0
 * @description: Swagger配置类
 * @date 2023/8/23 15:24
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {
  @Bean
  public Docket createRestApi() {
    Docket docket = new Docket(DocumentationType.SWAGGER_2);
    // ApiInfoBuilder 用于在Swagger界面上添加各种信息
    ApiInfoBuilder builder = new ApiInfoBuilder();
    builder.title("时刻协同在线办公系统");
    ApiInfo info = builder.build();
    docket.apiInfo(info);

    // ApiSelectorBuilder 用来设置哪些类中的方法会生成到REST API中
    ApiSelectorBuilder selectorBuilder = docket.select();
    // 选择所有包所有类
    selectorBuilder.paths(PathSelectors.any());
    // 使用@ApiOperation的方法会被提取到REST API中
    selectorBuilder.apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class));
    docket = selectorBuilder.build();

    /*
     * 下面的语句是开启对JWT的支持，当用户用Swagger调用受JWT认证保护的方法，
     * 必须要先提交参数（例如令牌）
     */
    // 存储用户必须提交的参数
    List<ApiKey> apiKeyList = new ArrayList<>();
    // 规定用户需要输入什么参数
    ApiKey apiKey = new ApiKey("token", "token", "header");
    apiKeyList.add(apiKey);
    docket.securitySchemes(apiKeyList);
    // 如果用户JWT认证通过，则在Swagger中全局有效
    AuthorizationScope scope = new AuthorizationScope("global", "accessEverything");
    AuthorizationScope[] scopes = {scope};
    SecurityReference reference = new SecurityReference("token", scopes);

    List<SecurityReference> refList = new ArrayList<>();
    refList.add(reference);
    SecurityContext context = SecurityContext.builder().securityReferences(refList).build();

    List<SecurityContext> cxtList = new ArrayList<>();
    cxtList.add(context);
    docket.securityContexts(cxtList);
    return docket;
  }
}
