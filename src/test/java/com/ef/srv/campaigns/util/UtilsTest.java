package com.ef.srv.campaigns.util;

import java.io.UnsupportedEncodingException;
import java.util.logging.Logger;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class UtilsTest {
	public static Logger logger = Logger.getLogger(UtilsTest.class.getName());
	@Before
	public void beforeLoads() {
		logger.info("BEFORE");
	}

	@Test
	public void getTokenFromRawTest() throws UnsupportedEncodingException {
		logger.info("TEST");
		Utils.getTokenFromRaw("pruebaToken");
		Utils.getTokenFromRaw("access_toke=asdasd&token_type=tokenasdas");
	}


	@After
	public void afterLoads() {
		logger.info("AFTER");
	}
}
