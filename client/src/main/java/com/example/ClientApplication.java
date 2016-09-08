package com.example;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableCircuitBreaker
@EnableEurekaClient
public class ClientApplication {
	public static void main(String[] args) {
		SpringApplication.run(ClientApplication.class, args);
	}
}

@RestController
class Client {

    @Autowired
    private ClientService clientService;

    @HystrixCommand(fallbackMethod = "fallback")
    @RequestMapping(value = "/service", method = RequestMethod.GET)
    public String getValue() {
        return clientService.getValue();
    }

    public String fallback() {
        return "24";
    }
}

@Service
class ClientService {

    private RestTemplate restTemplate = new RestTemplate();

    public String getValue() {
        return restTemplate.getForObject("http://localhost:8081/service", String.class);
    }
}
