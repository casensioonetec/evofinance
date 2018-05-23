package com.ef.srv.campaigns.service.impl;

import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.ef.srv.campaigns.api.CampaignsController;
import com.ef.srv.campaigns.model.CampaingData;
import com.ef.srv.campaigns.model.Finality;
import com.ef.srv.campaigns.model.OAuth2;
import com.ef.srv.campaigns.model.Product;
import com.ef.srv.campaigns.service.CampaignsService;
import com.ev.arq.srv.api.exception.EntityNotFoundException;

import lombok.extern.slf4j.Slf4j;
import net.minidev.json.JSONValue;

/**
 * A delegate to be called by the {@link CampaignsController}}. Implement this
 * interface with a {@link org.springframework.stereotype.Service} annotated
 * class.
 */

@Service
@Slf4j
public class CampaignsServiceImpl implements CampaignsService {

	@Override
	public CampaingData v1CampaignsCampaignCodeGet(String campaignCode) {

		try {
		
			RestTemplate restTemplate = new RestTemplate();
			String authURLTest = "https://test.salesforce.com/services/oauth2/token";
			
			MultiValueMap<String, String> body = new LinkedMultiValueMap<String, String>();
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
			
			body.add("grant_type", "password");
			body.add("client_id", "3MVG9w8uXui2aB_rlAPrAgWPrr3g20tNLVPg9ov9lBaO4n5o8irqj8TpFMoiaHBhySCFO5uAwu8Ud8CuB9ZtS");
			body.add("client_secret", "5533177683449434149");
			body.add("username", "evfapiuser@evofinance.com.atmira");
			body.add("password", "DigitalAtmOne_2018!M7NxZ7XtIyBpFvzuFtx12bXp");

//			String a = "grant_type:password\r\n" + 
//					"client_id:3MVG9w8uXui2aB_rlAPrAgWPrr3g20tNLVPg9ov9lBaO4n5o8irqj8TpFMoiaHBhySCFO5uAwu8Ud8CuB9ZtS\r\n" + 
//					"client_secret:5533177683449434149\r\n" + 
//					"username:evfapiuser@evofinance.com.atmira\r\n" + 
//					"password:DigitalAtmOne_2018!M7NxZ7XtIyBpFvzuFtx12bXp";
			
			HttpEntity<Object> httpEntity = new HttpEntity<Object>(body, headers);

			ResponseEntity<OAuth2> oAuth = restTemplate.exchange(authURLTest, HttpMethod.POST, httpEntity,
					OAuth2.class);

			String token = oAuth.getBody().getAccess_token();
			
		} catch (HttpClientErrorException e) {
			e.printStackTrace();
		}
		
		//Construcción del objeto de respuesta
		Product product = Product.builder()
				.TIN(new BigDecimal(12341))
				.codProd("adsfasdfa")
				.TAE(new BigDecimal(123))
				.optionalInsurance(false)
				.minMonths("1")
				.maxMonths("25")
				.minAmount("1")
				.maxAmount("1232323")
				.insuranceAmount(new BigDecimal(12312312))
				.formalisationFee(new BigDecimal(12312312))
				.earlyRepayFirst12Months("asdfas")
				.earlyRepayAfter12Months("aasdfasd")
				.build();
		
		List<Product> listaProductos = new ArrayList<Product>();
		listaProductos.add(product);
		
		product = Product.builder()
				.TIN(new BigDecimal(12341))
				.codProd("adsfasdfa")
				.TAE(new BigDecimal(123))
				.optionalInsurance(false)
				.minMonths("1")
				.maxMonths("25")
				.minAmount("1")
				.maxAmount("1232323")
				.insuranceAmount(new BigDecimal(12312312))
				.formalisationFee(new BigDecimal(12312312))
				.earlyRepayFirst12Months("asdfas")
				.earlyRepayAfter12Months("aasdfasd")
				.build();
		
		listaProductos.add(product);

		Finality finality = Finality.builder()
				.name("Coche")
				.products(listaProductos)
				.build();

		List<Finality> listaFinalidades = new ArrayList<Finality>();
		listaFinalidades.add(finality);
		
		finality = Finality.builder()
				.name("Coche")
				.products(listaProductos)
				.build();
		
		listaFinalidades.add(finality);

		CampaingData campaingData = CampaingData.builder().id("12").vendor(false).finalities(listaFinalidades).build();

		return campaingData;
	}

}
