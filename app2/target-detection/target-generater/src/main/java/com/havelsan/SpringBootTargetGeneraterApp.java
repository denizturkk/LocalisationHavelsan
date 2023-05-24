package com.havelsan;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.admin.SpringApplicationAdminJmxAutoConfiguration;


@SpringBootApplication(exclude = SpringApplicationAdminJmxAutoConfiguration.class)

public class SpringBootTargetGeneraterApp
{
    public static void main( String[] args )
    {
        SpringApplication.run(SpringBootTargetGeneraterApp.class);
    }
}
