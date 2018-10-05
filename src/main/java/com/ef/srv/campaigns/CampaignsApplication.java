package com.ef.srv.campaigns;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Configuration
@SpringBootApplication
public class CampaignsApplication {
	
	
	private static Logger logger = LogManager.getLogger();

	public static void main(String[] args) {
		SpringApplication.run(CampaignsApplication.class, args);
		logger.info("The campaign is started");
	}
	
}
