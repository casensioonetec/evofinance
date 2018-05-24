package com.ef.srv.campaigns.service;

import java.util.ArrayList;

import com.ef.srv.campaigns.api.CampaignsController;
import com.ef.srv.campaigns.model.DataBody;
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
	ArrayList<DataBody> v1CampaignsCampaignCodeGet(String campaignCode) throws EntityNotFoundException;
}
