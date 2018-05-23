package com.ef.srv.campaigns.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.ef.srv.campaigns.api.CampaignsController;
import com.ef.srv.campaigns.model.CampaingData;
import com.ef.srv.campaigns.model.Finality;
import com.ef.srv.campaigns.model.Product;
import com.ef.srv.campaigns.service.CampaignsService;

import lombok.extern.slf4j.Slf4j;

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

		UriComponentsBuilder builder = UriComponentsBuilder
				.fromHttpUrl("https://test.salesforce.com/services/oauth2/token")
				.queryParam("client_secret", "5533177683449434149")
				.queryParam("client_id",
						"3MVG9w8uXui2aB_rlAPrAgWPrr3g20tNLVPg9ov9lBaO4n5o8irqj8TpFMoiaHBhySCFO5uAwu8Ud8CuB9ZtS")
				.queryParam("grant_type", "password").queryParam("username", "evfapiuser@evofinance.com.atmira")
				.queryParam("password", "DigitalAtmOne_2018!M7NxZ7XtIyBpFvzuFtx12bXp");

		HttpHeaders headers = new HttpHeaders();
		// headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_FORM_URLENCODED));

		HttpEntity<String> entity = new HttpEntity<String>("", headers);

		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<?> oAuth = restTemplate.exchange(builder.build().encode().toUri(), HttpMethod.POST, entity,
				String.class);
		
		System.out.println(getToken( oAuth.getBody().toString()));
		
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

	private String getToken(String responseOAuth2) {
		String response = "";
		
		final String regexType = "token_type=(\\w*)";
		final Pattern patternType = Pattern.compile(regexType);
		final Matcher matcherType = patternType.matcher(responseOAuth2);
		
		final String regexToken = "access_token\\=(.*?)\\&";
		final Pattern patternToken = Pattern.compile(regexToken);
		final Matcher matcherToken = patternToken.matcher(responseOAuth2);

		if (matcherToken.find() && matcherType.find()) {
			//Devolvemos el indice 1 por que el indice 0 incluye los delimitadores
			response = matcherType.group(1) + " " + matcherToken.group(1);
		}
		
		return response;
	}
	
}
