package com.xmu.haruki;

import com.xmu.haruki.environment.BedRoom;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
@MapperScan("com.xmu.haruki.mapper")
public class Application{
    public static void main(String[] args) {
        ApplicationContext context=SpringApplication.run(Application.class,args);
    }
}
