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
public class ServiceMiyoApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServiceMiyoApplication.class, args);
	}

	private static final Logger LOG = LoggerFactory.getLogger(ServiceMiyoApplication.class);


	@Autowired
	private RestTemplate restTemplate;

	@Bean
	public RestTemplate getRestTemplate(){
		return new RestTemplate();
	}

	@RequestMapping("/miyo")
	public String callHome(){
		LOG.info("calling trace service-miyo  ");
		return restTemplate.getForObject("http://localhost:8988/info", String.class);
	}
	@RequestMapping("/info")
	public String info(){
		LOG.info("calling trace service-miyo ");

		return "i'm service-miyo";

	}

	@Bean
	public AlwaysSampler defaultSampler(){
		return new AlwaysSampler();
	}
}
