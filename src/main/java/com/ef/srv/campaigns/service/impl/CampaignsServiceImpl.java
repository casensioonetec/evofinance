package com.ef.srv.campaigns.service.impl;

import com.ef.srv.campaigns.service.CampaignsService;
import com.ef.srv.campaigns.model.CampaingData;
import com.evo.api.springboot.exception.EntityNotFoundException;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;
import java.util.List;

/**
 * A delegate to be called by the {@link CampaignsController}}.
 * Implement this interface with a {@link org.springframework.stereotype.Service} annotated class.
 */

@Service
@Slf4j
public class CampaignsServiceImpl implements CampaignsService{

    @Override
    public CampaingData v1CampaignsCampaignCodeGet(String campaignCode) throws EntityNotFoundException{
        
        // TODO Auto-generated method stub
        return null;
     }

}
