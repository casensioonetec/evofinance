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
@EnableCaching
public class CampaignsServiceImpl implements CampaignsService {

	@Override
	public CampaignData v1CampaignsCampaignCodeGet(String campaignCode, HttpCall call) {

		CampaignData response = null;
		for (CampaignData data : getCampaignsFromSF(call)) {
			log.info("Se recupera el codigo de campaña");
			if (data.getId().toString().equals(campaignCode)) {
				response = data;
			}else
			{
				System.out.println("El código de campaña no existe");
			}
		}

		return response;
	}

	public ArrayList<CampaignData> getCampaignsFromSF(HttpCall call) {
		ArrayList<CampaignData> response = null;
		try {
			response = call.getData();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return response;
	}

	

}
