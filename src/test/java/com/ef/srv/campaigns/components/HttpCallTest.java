package com.ef.srv.campaigns.components;

import java.util.logging.Logger;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.HttpHeaders;

import com.ef.srv.campaigns.service.impl.CampaignsServiceImpl;

public class HttpCallTest {

	public static Logger logger = Logger.getLogger(HttpCallTest.class.getName());
	@Before
	public void beforeLoads() {
		logger.info("BEFORE");
	}

	@Test
	public void contextLoads() {
		HttpCall call = new HttpCall();
		String token = call.getToken("client_secret", "477912851283700266", "client_id", "3MVG9sSN_PMn8tjTirRmr.Fe_wcnFi6OaUC63GZIYr.bWw7LKE5etKrL0z6ViWynOHV1V6XESl__LEfXStlUS", "grant_type",
				"password", "username", "evfapiuser@evofinance.com.atmiraint", "password", "DigitalAtmOne_2018!wmBRnYQ21n2NVnqvGefSzv4Ef", "","https://test.salesforce.com/services/oauth2/token");
		
		call.getData("Authorization", token,"https://evobanco--atmiraint.cs89.my.salesforce.com/services/apexrest/getCampaignData/v1/","");
		call.getData("", token,"https://evobanco--atmiraint.cs89.my.salesforce.com/services/apexrest/getCampaignData/v1/","");
		
		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", token);

		
		call.getResponse(headers, "https://evobanco--atmiraint.cs89.my.salesforce.com/services/apexrest/getCampaignData/v1/","");
		
		CampaignsServiceImpl csi = new CampaignsServiceImpl();
		csi.v1CampaignsCampaignCodeGet("",  new HttpCall(), "","","");
		csi.v1CampaignsCampaignCodeGet("default",  null,"Authorization",token,"https://evobanco--atmiraint.cs89.my.salesforce.com/services/apexrest/getCampaignData/v1/");
		
			
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
