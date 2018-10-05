package com.ef.srv.campaigns.components;

import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

// @EnableCaching
@Component
public class HttpCall {

	private static Logger logger = LogManager.getLogger();

	InputStream is = null;

	public List<CampaignData> getData(String authorization, String token, String campaign, String body) {

		HttpHeaders headers = new HttpHeaders();
		headers.add(authorization, token);

		if (authorization != null && !authorization.isEmpty()) {

			return this.getResponse(headers, campaign, body);
		}
		return new ArrayList<>();
	}

	public List<CampaignData> getResponse(HttpHeaders headers, String uri, String body) {

		RestTemplate restTemplate = new RestTemplate();
		restTemplate.getMessageConverters().add(0, new StringHttpMessageConverter(Charset.forName("UTF-8")));
		String sfURL = uri;

		UriComponentsBuilder sfBuilder = UriComponentsBuilder.fromHttpUrl(sfURL);

		HttpEntity<String> sfEntity = new HttpEntity<>(body, headers);

		try {
			ResponseEntity<String> sfResponse = restTemplate.exchange(sfBuilder.build().encode().toUri(), HttpMethod.GET, sfEntity, String.class);
			return new Gson().fromJson(sfResponse.getBody(), new TypeToken<ArrayList<CampaignData>>() {
			}.getType());
		} catch (Exception e) {
			return new ArrayList<>();
		}

	}

	public String getToken(String clientSecret, String clientSecretValue, String clientId, String clientIdValue, String grantType, String grantTypeValue, String username, String usernameValue, String password,
			String passwordValue, String body, String authtest) {

		String token = "";

		String authURLTest = authtest;
		UriComponentsBuilder authBuilder = UriComponentsBuilder.fromHttpUrl(authURLTest).queryParam(clientSecret, clientSecretValue).queryParam(clientId, clientIdValue).queryParam(grantType, grantTypeValue)
				.queryParam(username, usernameValue).queryParam(password, passwordValue);

		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_FORM_URLENCODED));

		HttpEntity<String> authEntity = new HttpEntity<>(body, headers);

		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<?> oAuthResponse = restTemplate.exchange(authBuilder.build().encode().toUri(), HttpMethod.POST, authEntity, String.class);

		try {
			token = Utils.getTokenFromRaw(oAuthResponse.getBody().toString());
			logger.info("The token has recovered");
		} catch (UnsupportedEncodingException e) {

			logger.error("The token has not recovered");
		}
		return token;
	}

}
