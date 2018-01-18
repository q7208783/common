package group.zhangcc.login.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Created by ZhangChicheng on 2018/1/18.
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {
	@Bean
	public Docket api(){
		return new Docket(DocumentationType.SWAGGER_2)
			.apiInfo(apiInfo())
			.select()
			.apis(RequestHandlerSelectors.basePackage("group.zhangcc.login"))
			.paths(PathSelectors.any())
			.build();
	}

	private ApiInfo apiInfo() {
		return new ApiInfoBuilder()
			.title("ZCC WEB API")
			.description("This is a private API Server of Zcc.")
			.contact(new Contact("ZhangChiCheng","", "576926338@qq.com"))
			.version("1.0")
			.build();
	}
}
