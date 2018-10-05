package com.ef.srv.campaigns.model;

import java.math.BigDecimal;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ProductTest {

	private static Logger logger = LogManager.getLogger();

	@Before
	public void beforeLoads() {
		logger.info("BEFORE");
	}

	@Test
	public void contextLoads() {
		Product data = new Product(null, null, null, null, null, null, null, null, null, null, null, null, null, null);
		
		data.setCodProd("as!");
		data.setEarlyRepayAfter12Months("asa");
		data.setEarlyRepayFirst12Months("asdas");
		data.setFormalisationFee(new BigDecimal("1231231237584.12312"));
		data.setInsuranceAmount(new BigDecimal("1231231237584.12312"));
		data.setLoanInsurance("loan");
		data.setMaxAmount("1222");
		data.setMaxMonths("12");
		data.setMinAmount("123");
		data.setMinMonths("1");
		data.setPercentage(new BigDecimal("0.444444444444444"));
		data.setSecondHolder("no");
		data.setTAE(new BigDecimal("0.000099"));
		data.setTIN(new BigDecimal("0.444444444444444"));
		
		data.getCodProd();
		data.getEarlyRepayAfter12Months();
		data.getEarlyRepayFirst12Months();
		data.getFormalisationFee();
		data.getInsuranceAmount();
		data.getLoanInsurance();
		data.getMaxAmount();
		data.getMaxMonths();
		data.getMinAmount();
		data.getMinMonths();
		data.getPercentage();
		data.getSecondHolder();
		data.getTAE();
		data.getTIN();
		
			
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
