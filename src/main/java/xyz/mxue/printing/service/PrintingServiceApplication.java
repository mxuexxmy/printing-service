package xyz.mxue.printing.service;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableTransactionManagement
@MapperScan("xyz.mxue.printing.service.mapper")
@EnableScheduling
@SpringBootApplication
public class PrintingServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(PrintingServiceApplication.class, args);
    }

}
