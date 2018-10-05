package com.ef.srv.campaigns.api;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class CampaignsControllerTest {
	private static Logger logger = LogManager.getLogger();

	@Before
	public void beforeLoads() {
		logger.info("BEFORE");
	}

	@Test
	public void contextLoads() {
		logger.info("TEST");
	}

	@Test
	public void getData() {
		logger.info("TEST");
	}

	@After
	public void afterLoads() {
		logger.info("AFTER");
	}
}
