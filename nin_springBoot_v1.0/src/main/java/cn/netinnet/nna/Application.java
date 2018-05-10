package cn.netinnet.nna;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 *
 * @ClassName:    Application
 * @Description:  入口类
 *
 * @author         chen.wb
 * @version        V1.0
 * @Date           2017-07-24
 *
 **/
@EnableAutoConfiguration
@EnableTransactionManagement(order = 10) //开启事务，并设置order值，默认是Integer的最大值
@ComponentScan(basePackages={"cn.netinnet.nna"})
@SpringBootApplication
public class Application extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(Application.class);
    }

    public static void main(String[] args) {

        SpringApplication.run(Application.class, args);
    }

}
