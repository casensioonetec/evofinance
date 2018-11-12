package com.ef.srv.campaigns.model;

import java.util.ArrayList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class FinalityTest {
	
	private static Logger logger = LogManager.getLogger();
	@Before
	public void beforeLoads() {
		logger.info("BEFORE");
	}
	
	@Test
	public void test() {
		Finality finalty= new Finality("as", "asdasd","9", new ArrayList<Product>());
		finalty.setCode("code");
		finalty.setName("name");
		finalty.setOrder("order");
		finalty.setProducts(new ArrayList<Product>());
		
		finalty.setCode("");
		finalty.setName("");
		
		finalty.getCode();
		finalty.getName();
		finalty.getProducts();
		
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
