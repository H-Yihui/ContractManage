package com.ktriasia.contractmanager;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 项目启动入口
 * @author Ktriasia
 * @version 1.0.0
 * @since 2025-9-16
 */
@SpringBootApplication
@MapperScan("com.ktriasia.contractmanager.model.mapper")
public class Application {
    SpringApplication springApplication=new SpringApplication();
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}