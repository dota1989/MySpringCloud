package com.caad;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.sleuth.sampler.AlwaysSampler;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@RestController
public class ServiceMiyaApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServiceMiyaApplication.class, args);
	}

	private static final Logger LOG = LoggerFactory.getLogger(ServiceMiyaApplication.class);


	@Autowired
	private RestTemplate restTemplate;

	@Bean
	public RestTemplate getRestTemplate(){
		return new RestTemplate();
	}

	@RequestMapping("/miya")
	public String callHome(){
		LOG.info("calling trace service-miya  ");
		return restTemplate.getForObject("http://localhost:8989/miyo", String.class);
	}
	@RequestMapping("/info")
	public String info(){
		LOG.info("calling trace service-miya ");

		return "i'm service-miya";

	}

	@Bean
	public AlwaysSampler defaultSampler(){
		return new AlwaysSampler();
	}
}
