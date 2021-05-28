package com.dev.happy.tenant;

import com.dev.happy.tenant.utils.RedisStandaloneUtils;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@MapperScan(basePackages = {"com.dev.happy.tenant.mapper"})
public class TenantApplication {

    public static void main(String[] args) {
        SpringApplication.run(TenantApplication.class, args);
    }
    @Bean
    public RedisStandaloneUtils redisStandaloneUtils() {
       return new RedisStandaloneUtils();
    }
}
