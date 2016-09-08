package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EnableEurekaClient
public class ServiceApplication {
	public static void main(String[] args) {
		SpringApplication.run(ServiceApplication.class, args);
	}
}

@RestController
class Service {
    private static int counter;

    @RequestMapping(value = "/service", method = RequestMethod.GET)
    public String getValue() {
        return String.valueOf(counter ++);
    }
}
