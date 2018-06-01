package com.ef.srv.campaigns;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Configuration;

@Configuration
@SpringBootApplication
@EnableCaching
public class CampaignsApplication {

	public static void main(String[] args) {
		SpringApplication.run(CampaignsApplication.class, args);
	}
	
}
