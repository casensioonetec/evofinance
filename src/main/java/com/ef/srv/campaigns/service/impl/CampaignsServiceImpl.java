package com.ef.srv.campaigns.service.impl;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
import com.ef.srv.campaigns.model.Finality;
import com.ef.srv.campaigns.model.Product;
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

		String authURL = "https://test.salesforce.com/services/oauth2/token"; //$NON-NLS-1$

		UriComponentsBuilder authBuilder = UriComponentsBuilder.fromHttpUrl(authURL)
				.queryParam("client_secret", "5533177683449434149") //$NON-NLS-1$ //$NON-NLS-2$
				.queryParam("client_id", //$NON-NLS-1$
						"3MVG9w8uXui2aB_rlAPrAgWPrr3g20tNLVPg9ov9lBaO4n5o8irqj8TpFMoiaHBhySCFO5uAwu8Ud8CuB9ZtS") //$NON-NLS-1$
				.queryParam("grant_type", "password") //$NON-NLS-1$ //$NON-NLS-2$
				.queryParam("username", "evfapiuser@evofinance.com.atmira") //$NON-NLS-1$ //$NON-NLS-2$
				.queryParam("password", "DigitalAtmOne_2018!M7NxZ7XtIyBpFvzuFtx12bXp"); //$NON-NLS-1$ //$NON-NLS-2$

		HttpHeaders authHeaders = new HttpHeaders();
		// authHeaders.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
		authHeaders.setAccept(Arrays.asList(MediaType.APPLICATION_FORM_URLENCODED));

		HttpEntity<String> authEntity = new HttpEntity<String>("", authHeaders); //$NON-NLS-1$

		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<?> oAuthResponse = restTemplate.exchange(authBuilder.build().encode().toUri(), HttpMethod.POST,
				authEntity, String.class);

		// System.out.println(getToken( oAuth.getBody().toString()));

		String sfURL = "https://evobanco--atmira.cs83.my.salesforce.com/services/apexrest/getCampaignData/v1/"; //$NON-NLS-1$

		UriComponentsBuilder sfBuilder = UriComponentsBuilder.fromHttpUrl(sfURL);

		HttpHeaders sfHeaders = new HttpHeaders();
		try {
			sfHeaders.add("Authorization", Utils.getTokenFromRaw(oAuthResponse.getBody().toString())); //$NON-NLS-1$
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// sfHeaders.setContentType(MediaType.APPLICATION_JSON);

		HttpEntity<String> sfEntity = new HttpEntity<String>("", sfHeaders); //$NON-NLS-1$

		ResponseEntity<String> sfResponse = restTemplate.exchange(sfBuilder.build().encode().toUri(), HttpMethod.GET,
				sfEntity, String.class);

		CampaingData campaingData = new Gson().fromJson(sfResponse.getBody().toString(), CampaingData.class);

		return campaingData.getData();
	}

}
