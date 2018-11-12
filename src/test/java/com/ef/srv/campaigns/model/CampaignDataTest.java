package com.ef.srv.campaigns.model;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class CampaignDataTest<PlainOldJavaObject> {

	private static Logger logger = LogManager.getLogger();

	@Before
	public void beforeLoads() {
		logger.info("BEFORE");
	}

	@Test
	public void contextLoads() {
		CampaignData data = new CampaignData(null, null, null, null, null, null);
		CampaignData data2= new CampaignData("id","","","",false ,new ArrayList<Finality>()); 
		
		data.setDefaultCampaign(false);
		data.setFinalities(new ArrayList<Finality>());
		data.setForcedInsurance("si");
		data.setId("id");
		data.setPixel("pixel");
		data.setChannelType("");
		
		data.getDefaultCampaign();
		data.getFinalities();
		data.getForcedInsurance();
		data.getForcedInsurance();
		data.getId();
		data.getPixel();
		data2.getChannelType();
		
		List<Product>products= new ArrayList<>();
		products.add(new Product(null, null, null, null, null, null, null, null, null, null, null, null, null, null));
		
		
		List<Finality>finalities = new ArrayList<>();
		finalities.add(new Finality("code","name", "order", products));
		
		data.setFinalities(finalities);
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
