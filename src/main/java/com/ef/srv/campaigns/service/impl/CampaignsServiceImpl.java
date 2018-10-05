package com.ef.srv.campaigns.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.ef.srv.campaigns.api.CampaignsController;
import com.ef.srv.campaigns.components.HttpCall;
import com.ef.srv.campaigns.model.CampaignData;
import com.ef.srv.campaigns.service.CampaignsService;

/**
 * A delegate to be called by the {@link CampaignsController}}. Implement this
 * interface with a {@link org.springframework.stereotype.Service} annotated
 * class.
 */

@Service
public class CampaignsServiceImpl implements CampaignsService {
	
	@Value("${campaignsServiceImpl.url.sf.auth}")
	private String auth;

	@Value("${campaignsServiceImpl.url.sf.authtest}")
	private String authtest;

	@Value("${campaignsServiceImpl.url.sf.campaign}")
	private String campaign;

	@Value("${campaignsServiceImpl.username}")
	private String username;

	@Value("${campaignsServiceImpl.grant_type}")
	private String grantType;

	@Value("${campaignsServiceImpl.client_id}")
	private String clientId;

	@Value("${campaignsServiceImpl.client_secret}")
	private String clientSecret;

	@Value("${campaignsServiceImpl.password}")
	private String password;

	@Value("${campaignsServiceImpl.authorization}")
	private String authorization;

	@Value("${campaignsServiceImpl.client_idValue}")
	private String clientIdValue;

	@Value("${campaignsServiceImpl.client_secretValue}")
	private String clientSecretValue;

	@Value("${campaignsServiceImpl.grant_typeValue}")
	private String grantTypeValue;

	@Value("${campaignsServiceImpl.passwordValue}")
	private String passwordValue;

	@Value("${campaignsServiceImpl.usernameValue}")
	private String usernameValue;

	@Value("${campaignsServiceImpl.empty}")
	private String empty;

	@Override
	public CampaignData v1CampaignsCampaignCodeGet(String campaignCode, HttpCall call,String authorization, String token,String campaign) {
		CampaignData response = null;
		CampaignData responseDefault = null;
		if(!campaignCode.isEmpty()) {
			List<CampaignData> campaings = getCampaignsFromSF(call,authorization, token,campaign);
			for (CampaignData data : campaings) {

				if (data.getId().equals(campaignCode)) {
					response = data;
					break;
				}
				if (data.getDefaultCampaign()) {
					responseDefault = data;
				}
			}

		}
		
		if (response == null) {
			return responseDefault;
		} else {
			return response;
		}
	}

	public List<CampaignData> getCampaignsFromSF(HttpCall call,String authorization, String token,String campaign) {
		List<CampaignData> response = null;
		if(call !=null) {
			response = call.getData(this.authorization, call.getToken(clientSecret, clientSecretValue, clientId, clientIdValue, grantType, grantTypeValue, username, usernameValue, password, passwordValue, empty, authtest), this.campaign, empty);	
		}else {
			HttpCall _call= new HttpCall();
			response = _call.getData(authorization, token,campaign, "");
		}
		
		return response;
	}

}
