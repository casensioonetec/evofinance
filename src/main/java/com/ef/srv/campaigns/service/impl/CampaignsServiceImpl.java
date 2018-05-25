package com.ef.srv.campaigns.service.impl;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;

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
import com.ef.srv.campaigns.model.DataBody;
import com.ef.srv.campaigns.service.CampaignsService;
import com.ef.srv.campaigns.util.Messages;
import com.ef.srv.campaigns.util.Utils;
import com.google.gson.Gson;

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
	public ArrayList<DataBody> v1CampaignsCampaignCodeGet(String campaignCode) {

		// String authURL = Messages.getString("CampaignsServiceImpl.url.sf.auth");
		String authURLTest = Messages.getString("CampaignsServiceImpl.url.sf.auth.test");
		String sfURL = Messages.getString("CampaignsServiceImpl.url.sf.campaign");

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

		UriComponentsBuilder sfBuilder = UriComponentsBuilder.fromHttpUrl(sfURL);

		try {
			headers = new HttpHeaders();
			headers.add(Messages.getString("CampaignsServiceImpl.authorization"),
					Utils.getTokenFromRaw(oAuthResponse.getBody().toString()));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		HttpEntity<String> sfEntity = new HttpEntity<String>(Messages.getString("CampaignsServiceImpl.empty"), headers);

		ResponseEntity<String> sfResponse = restTemplate.exchange(sfBuilder.build().encode().toUri(), HttpMethod.GET,
				sfEntity, String.class);

		CampaingData campaingData = new Gson().fromJson(sfResponse.getBody().toString(), CampaingData.class);

		return campaingData.getData();
	}

}
