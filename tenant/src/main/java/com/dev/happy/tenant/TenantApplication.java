package com.dev.happy.tenant;

import com.dev.happy.tenant.utils.RedisStandaloneUtils;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@MapperScan(basePackages = {"com.dev.happy.tenant.mapper"})
public class TenantApplication {
    @Value("${spring.redis.host}")
    private String host;
    @Value("${spring.redis.port}")
    private Integer port;
    public static void main(String[] args) {
        SpringApplication.run(TenantApplication.class, args);
    }
    @Bean
    public RedisStandaloneUtils redisStandaloneUtils() {
       return new RedisStandaloneUtils(host,port);
    }
}
