package com.ef.srv.campaigns.service.impl;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
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
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.ef.srv.campaigns.api.CampaignsController;
import com.ef.srv.campaigns.model.CampaignData;
import com.ef.srv.campaigns.service.CampaignsService;
import com.ef.srv.campaigns.util.Messages;
import com.ef.srv.campaigns.util.Utils;
import com.google.common.cache.CacheBuilder;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

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
	public CampaignData v1CampaignsCampaignCodeGet(String campaignCode) {

		CampaignData response = null;
		for (CampaignData data : getCampaignsFromSF()) {
			if (data.getId().toString().equals(campaignCode)) {
				response = data;
			}
		}

		return response;
	}

	@Cacheable("response")
	public ArrayList<CampaignData> getCampaignsFromSF() {
		System.out.println("Response");
		ArrayList<CampaignData> response = null;

		try {
			response = getData();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return response;
	}

	@Cacheable("token")
	private String getToken() throws UnsupportedEncodingException {
		System.out.println("Llamo al token");
		String token = "";
		String authURLTest = Messages.getString("CampaignsServiceImpl.url.sf.auth.test");
		UriComponentsBuilder authBuilder = UriComponentsBuilder.fromHttpUrl(authURLTest)
				.queryParam(Messages.getString("CampaignsServiceImpl.client_secret"),
						Messages.getString("CampaignsServiceImpl.client_secret.value"))
				.queryParam(Messages.getString("CampaignsServiceImpl.client_id"),
						Messages.getString("CampaignsServiceImpl.client_id.value"))
				.queryParam(Messages.getString("CampaignsServiceImpl.grant_type"),
						Messages.getString("CampaignsServiceImpl.grant_type.value"))
				.queryParam(Messages.getString("CampaignsServiceImpl.username"),
						Messages.getString("CampaignsServiceImpl.username.value"))
				.queryParam(Messages.getString("CampaignsServiceImpl.password"),
						Messages.getString("CampaignsServiceImpl.password.value"));

		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_FORM_URLENCODED));

		HttpEntity<String> authEntity = new HttpEntity<String>(Messages.getString("CampaignsServiceImpl.empty"),
				headers);

		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<?> oAuthResponse = restTemplate.exchange(authBuilder.build().encode().toUri(), HttpMethod.POST,
				authEntity, String.class);

		token = Utils.getTokenFromRaw(oAuthResponse.getBody().toString());
		return token;
	}

	@Cacheable("campaignDataArray")
	private ArrayList<CampaignData> getData() throws UnsupportedEncodingException {
		System.out.println("Llamo a salesforce");
		// String authURL = Messages.getString("CampaignsServiceImpl.url.sf.auth");
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers = new HttpHeaders();
		headers.add(Messages.getString("CampaignsServiceImpl.authorization"), getToken());

		String sfURL = Messages.getString("CampaignsServiceImpl.url.sf.campaign");

		UriComponentsBuilder sfBuilder = UriComponentsBuilder.fromHttpUrl(sfURL);

		HttpEntity<String> sfEntity = new HttpEntity<String>(Messages.getString("CampaignsServiceImpl.empty"), headers);

		ResponseEntity<String> sfResponse = restTemplate.exchange(sfBuilder.build().encode().toUri(), HttpMethod.GET,
				sfEntity, String.class);

		ArrayList<CampaignData> campaignDataArray = new Gson().fromJson(sfResponse.getBody().toString(),
				new TypeToken<ArrayList<CampaignData>>() {
				}.getType());
		// Utils.simulateSlowService();
		return campaignDataArray;
	}

}
