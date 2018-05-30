package com.ef.srv.campaigns.service;

import java.util.ArrayList;

import org.springframework.cache.annotation.Cacheable;

import com.ef.srv.campaigns.api.CampaignsController;
import com.ef.srv.campaigns.model.CampaingData;
import com.ev.arq.srv.api.exception.EntityNotFoundException;

/**
 * A delegate to be called by the {@link CampaignsController}}. Implement this
 * interface with a {@link org.springframework.stereotype.Service} annotated
 * class.
 */
public interface CampaignsService {

	/**
	 * Recupera todos los datos de una campaña Recupera todos los datos de una
	 * campaña para el proceso de contratación
	 * 
	 * @return
	 */
	CampaingData v1CampaignsCampaignCodeGet(String campaignCode) throws EntityNotFoundException;

}
