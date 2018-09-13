package com.ef.srv.campaigns.components;

import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.guava.GuavaCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.ef.srv.campaigns.model.CampaignData;
import com.ef.srv.campaigns.util.Utils;
import com.google.common.cache.CacheBuilder;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

//@EnableCaching
@Component
public class HttpCall {
	
	@Value("${campaignsServiceImpl.url.sf.auth}")
	private String auth;
	
	@Value("${campaignsServiceImpl.url.sf.authtest}")
	private String authtest;
	
	@Value("${campaignsServiceImpl.url.sf.campaign}")
	private String campaign;
	
	@Value("${campaignsServiceImpl.username}")
	private String username;
	
	@Value("${campaignsServiceImpl.grant_type}")
	private String grant_type;
	
	@Value("${campaignsServiceImpl.client_id}")
	private String client_id;
	
	@Value("${campaignsServiceImpl.client_secret}")
	private String client_secret;
	
	@Value("${campaignsServiceImpl.password}")
	private String password;
	
	@Value("${campaignsServiceImpl.authorization}")
	private String authorization;
	
	@Value("${campaignsServiceImpl.client_idValue}")
	private String client_idValue;
	
	@Value("${campaignsServiceImpl.client_secretValue}")
	private String client_secretValue;
	
	@Value("${campaignsServiceImpl.grant_typeValue}")
	private String grant_typeValue;
	
	@Value("${campaignsServiceImpl.passwordValue}")
	private String passwordValue;
	
	@Value("${campaignsServiceImpl.usernameValue}")
	private String usernameValue;
	
	@Value("${campaignsServiceImpl.empty}")
	private String empty;
	
	
	//Properties prop = new Properties();
	InputStream is = null;
	
	
	//@Cacheable(value = "response", cacheManager = "cacheManager")
	public ArrayList<CampaignData> getData() throws UnsupportedEncodingException {
		// System.out.println("Llamo a salesforce");

		/*
		is = getClass().getClassLoader().getResourceAsStream("application.properties");
		 
		if (is != null) {
			try {
				prop.load(is);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			
		}*/
		
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.getMessageConverters()
		        .add(0, new StringHttpMessageConverter(Charset.forName("UTF-8")));
		HttpHeaders headers = new HttpHeaders();
		
		headers = new HttpHeaders();
		
		headers.add(authorization, getToken());

		//@Value("${string:default text}")
		String sfURL = campaign;

		UriComponentsBuilder sfBuilder = UriComponentsBuilder.fromHttpUrl(sfURL);

		HttpEntity<String> sfEntity = new HttpEntity<String>(empty, headers);

		ResponseEntity<String> sfResponse = restTemplate.exchange(sfBuilder.build().encode().toUri(), HttpMethod.GET,
				sfEntity, String.class);

		ArrayList<CampaignData> campaignDataArray = new Gson().fromJson(sfResponse.getBody().toString(),
				new TypeToken<ArrayList<CampaignData>>() {
				}.getType());
		// Utils.simulateSlowService();
		return campaignDataArray;
	}

	private String getToken() {
		
		String token = "";
        
		String authURLTest = authtest;
		UriComponentsBuilder authBuilder = UriComponentsBuilder.fromHttpUrl(authURLTest)
				.queryParam(client_secret,client_secretValue)
				.queryParam(client_id,client_idValue)
				.queryParam(grant_type,grant_typeValue)
				.queryParam(username,usernameValue)
				.queryParam(password,passwordValue);

		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_FORM_URLENCODED));

		HttpEntity<String> authEntity = new HttpEntity<String>(empty,
				headers);

		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<?> oAuthResponse = restTemplate.exchange(authBuilder.build().encode().toUri(), HttpMethod.POST,
				authEntity, String.class);

		try {
			token = Utils.getTokenFromRaw(oAuthResponse.getBody().toString());
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return token;
	}
	
	
}
