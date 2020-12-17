package com.lxiaocode.redis;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author lixiaofeng
 * @date 2020/12/17 下午8:04
 * @blog http://www.lxiaocode.com/
 */
@SpringBootApplication
@ComponentScan(basePackages = {"com.lxiaocode.redis.**"})
@MapperScan(basePackages = {"com.lxiaocode.redis.mapper"})
public class DistributedLockApplication {
    public static void main(String[] args) {
        SpringApplication.run(DistributedLockApplication.class, args);
    }
}
