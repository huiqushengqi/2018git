package cn.netinnet.nna.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.async.DeferredResult;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * FileName: SwaggerConfig
 *
 * @version v1.0
 * @Description: 用于生成、描述、调用和可视化 RESTful 风格的 Web 服务
 * @author: chenwb
 * @date: 2017.8.30
 * <p>
 * Modification  History:
 * Date         Author        Version        Discription
 * -----------------------------------------------------------------------------------
 * ${date}      ${user}         1.0             1.0
 * Why & What is modified: <修改原因描述>
 */

/**
 * Swagger2默认将所有的Controller中的RequestMapping方法都会暴露，然而在实际开发中，我们并不一定需要把所有API都提现在文档中查看，这种情况下，使用注解@ApiIgnore来解决，如果应用在Controller范围上，则当前Controller中的所有方法都会被忽略，如果应用在方法上，则对应用的方法忽略暴露API。

 注解@ApiOperation和@ApiParam可以理解为API说明，多动手尝试就很容易理解了。
 如果我们不使用这样注解进行说明，Swagger2也是有默认值的，没什么可说的试试就知道了。

 在 http://localhost:8080/swagger-ui.html 显示页面的右上角有api_key ，springfox-swagger 2.2.2 版本并没有进行处理，我们可以自己添加拦截器拦截 /v2/api-docs来处理我们API文档的访问权限，如果要更严格更灵活的控制，可能需要修改源码来实现了。相信 springfox-swagger 的后期版本应该会支持更全面的应用需求的。
 *
 * @author Angel(QQ:412887952)
 * @version v.0.1
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {
    /**
     * 可以定义多个组，比如本类中定义把test和demo区分开了
     * （访问页面就可以看到效果了）
     *
     */
    @Bean
    public Docket testApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("test")
                .genericModelSubstitutes(DeferredResult.class)
//                .genericModelSubstitutes(ResponseEntity.class)
                .useDefaultResponseMessages(false)
                .forCodeGeneration(true)
                .pathMapping("/")// base，最终调用接口后会和paths拼接在一起
                .select()
                // .paths(or(regex("/api/.*")))//过滤的接口
                .build()
                .apiInfo(testApiInfo());
    }

    @Bean
    public Docket demoApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("demo")
                .genericModelSubstitutes(DeferredResult.class)
//              .genericModelSubstitutes(ResponseEntity.class)
                .useDefaultResponseMessages(false)
                .forCodeGeneration(false)
                .pathMapping("/")
                .select()
                // .paths(or(regex("/demo/.*")))//过滤的接口
                .build()
                .apiInfo(demoApiInfo());
    }

    private ApiInfo testApiInfo() {
        return new ApiInfoBuilder()
                .title("Electronic Health Record(EHR) Platform API")//大标题
                .description("EHR Platform's REST API, all the applications could access the Object model data via JSON.")//详细描述
                .version("1.0")//版本
                .termsOfServiceUrl("NO terms of service")
                // .contact(new Contact("小单", "http://blog.csdn.net/catoop", "365384722@qq.com"))//作者
                .license("The Apache License, Version 2.0")
                .licenseUrl("http://www.apache.org/licenses/LICENSE-2.0.thymeleaf")
                .build();
    }

    private ApiInfo demoApiInfo() {
        return new ApiInfoBuilder()
                .title("Electronic Health Record(EHR) Platform API")//大标题
                .description("EHR Platform's REST API, all the applications could access the Object model data via JSON.")//详细描述
                .version("1.0")//版本
                .termsOfServiceUrl("NO terms of service")
                // .contact(new Contact("小单", "http://blog.csdn.net/catoop", "365384722@qq.com"))//作者
                .license("The Apache License, Version 2.0")
                .licenseUrl("http://www.apache.org/licenses/LICENSE-2.0.thymeleaf")
                .build();
    }
}
