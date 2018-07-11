package com.ef.srv.campaigns.components;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

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
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.ef.srv.campaigns.model.CampaignData;
import com.ef.srv.campaigns.util.Utils;
import com.google.common.cache.CacheBuilder;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

@EnableCaching
@Component
public class HttpCall {
	
	Properties prop = new Properties();
	InputStream is = null;
	

	@Cacheable(value = "response", cacheManager="cacheManager")
	public ArrayList<CampaignData> getData() throws UnsupportedEncodingException {
		System.out.println("Llamo a salesforce");

		
		is = getClass().getClassLoader().getResourceAsStream("config.properties");
		 
		if (is != null) {
			try {
				prop.load(is);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			
		}
		
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		
		headers = new HttpHeaders();
		
		headers.add(prop.getProperty("campaignsServiceImpl.authorization"), getToken());

		//@Value("${string:default text}")
		String sfURL = prop.getProperty("campaignsServiceImpl.url.sf.campaign");

		UriComponentsBuilder sfBuilder = UriComponentsBuilder.fromHttpUrl(sfURL);

		HttpEntity<String> sfEntity = new HttpEntity<String>(prop.getProperty("campaignsServiceImpl.empty"), headers);

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
        
		String authURLTest = prop.getProperty("campaignsServiceImpl.url.sf.auth.test");
		UriComponentsBuilder authBuilder = UriComponentsBuilder.fromHttpUrl(authURLTest)
				.queryParam(prop.getProperty("campaignsServiceImpl.client_secret"),
						prop.getProperty("campaignsServiceImpl.client_secret.value"))
				.queryParam(prop.getProperty("campaignsServiceImpl.client_id"),
						prop.getProperty("campaignsServiceImpl.client_id.value"))
				.queryParam(prop.getProperty("campaignsServiceImpl.grant_type"),
						prop.getProperty("campaignsServiceImpl.grant_type.value"))
				.queryParam(prop.getProperty("campaignsServiceImpl.username"),
						prop.getProperty("campaignsServiceImpl.username.value"))
				.queryParam(prop.getProperty("campaignsServiceImpl.password"),
						prop.getProperty("campaignsServiceImpl.password.value"));

		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_FORM_URLENCODED));

		HttpEntity<String> authEntity = new HttpEntity<String>(prop.getProperty("campaignsServiceImpl.empty"),
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

	@Bean
	public CacheManager cacheManager() {

		GuavaCacheManager cacheManager = new GuavaCacheManager();
		CacheBuilder<Object, Object> cacheBuilder = CacheBuilder.newBuilder().maximumSize(100).expireAfterWrite(1,
				TimeUnit.DAYS);
		cacheManager.setCacheBuilder(cacheBuilder);

		/*
		 * SimpleCacheManager cacheManager = new SimpleCacheManager(); Cache cache = new
		 * ConcurrentMapCache("mycache"); cacheManager.setCaches(Arrays.asList(cache));
		 */
		return cacheManager;

	}
}
