package com.shgbit.cloudhealth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class})
@SpringBootApplication
//@MapperScan("com.shgbit.cloudhealth.dao")
@EnableSwagger2
public class CloudhealthApplication {

    public static void main(String[] args) {
        SpringApplication.run(CloudhealthApplication.class, args);
    }


}
