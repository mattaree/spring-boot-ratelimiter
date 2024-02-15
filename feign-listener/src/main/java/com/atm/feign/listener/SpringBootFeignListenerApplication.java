package com.atm.feign.listener;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class SpringBootFeignListenerApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootFeignListenerApplication.class, args);
	}
}