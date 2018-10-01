package com.ef.srv.campaigns.service.impl;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.stereotype.Service;

import com.ef.srv.campaigns.api.CampaignsController;
import com.ef.srv.campaigns.components.HttpCall;
import com.ef.srv.campaigns.model.CampaignData;
import com.ef.srv.campaigns.service.CampaignsService;

import lombok.extern.slf4j.Slf4j;

/**
 * A delegate to be called by the {@link CampaignsController}}. Implement this
 * interface with a {@link org.springframework.stereotype.Service} annotated
 * class.
 */

@Service
@Slf4j
// @EnableCaching
public class CampaignsServiceImpl implements CampaignsService {
	// ArrayList<CampaignData> responseArr = null;
	
	@Override
	public CampaignData v1CampaignsCampaignCodeGet(String campaignCode, HttpCall call) {
		CampaignData response = null;
		CampaignData responseDefault = null;
		
		ArrayList<CampaignData> campaings = getCampaignsFromSF(call);
		for (CampaignData data : campaings) {
			//log.info("The campaign code is retrieved");
			if (data.getId().toString().equals(campaignCode)) {
				response = data;
				break;
			}
			if (data.getDefaultCampaign()) {
				responseDefault = data;
			}
		}

		if (response == null) {
			return responseDefault;
		} else {
			return response;
		}
	}

	public ArrayList<CampaignData> getCampaignsFromSF(HttpCall call) {
			try { 
				return call.getData();
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return null;
			}		
	}

	

}
