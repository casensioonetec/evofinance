package com.ef.srv.campaigns.service;

import com.ef.srv.campaigns.model.CampaingData;
import com.evo.api.springboot.exception.EntityNotFoundException;
import com.evo.api.springboot.exception.ApiException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;
import java.util.List;
import java.util.Optional;

/**
 * A delegate to be called by the {@link CampaignsController}}.
 * Implement this interface with a {@link org.springframework.stereotype.Service} annotated class.
 */
public interface CampaignsService {


 	/**
 	 * Recupera todos los datos de una campaña
 	 * Recupera todos los datos de una campaña para el proceso de contratación
     * @return
     */
    CampaingData v1CampaignsCampaignCodeGet(String campaignCode)  throws EntityNotFoundException;    
}
