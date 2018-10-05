package com.ef.srv.campaigns;

import java.util.logging.Logger;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class CampaignsApplicationTest {
	public static Logger logger = Logger.getLogger(CampaignsApplicationTest.class.getName());

	@Before
	public void beforeLoads() {
		logger.info("BEFORE");
	}

	@Test
	public void mainTest() {
		logger.info("TEST");
		CampaignsApplication.main(new String[0]);
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
