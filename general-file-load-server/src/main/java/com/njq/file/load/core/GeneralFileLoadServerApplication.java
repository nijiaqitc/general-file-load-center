package com.njq.file.load.core;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;

@ImportResource({"classpath:applicationContext-dubbo.xml"})
@ComponentScan(basePackages={"com.njq"})
@EnableCaching
@SpringBootApplication
public class GeneralFileLoadServerApplication extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(GeneralFileLoadServerApplication.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(GeneralFileLoadServerApplication.class, args);
    }

}
