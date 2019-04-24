package com.sd;

import com.sd.util.StorageProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(StorageProperties.class)
public class Project3BkApplication {

    public static void main(String[] args) {
        SpringApplication.run(Project3BkApplication.class, args);
    }

}
