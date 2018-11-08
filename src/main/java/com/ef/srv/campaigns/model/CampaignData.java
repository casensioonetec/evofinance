package com.ef.srv.campaigns.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * CampaingData
 */

@Getter
@Setter	
@Builder
public class CampaignData implements Serializable {
	private static final long serialVersionUID = 1L;

	@JsonProperty("id")
	private String id;

	@JsonProperty("ChannelType")
	private String ChannelType; 
	
	@JsonProperty("pixel")
	private String pixel;
	
	@JsonProperty("forcedInsurance")
	private String forcedInsurance;
	
	@JsonProperty("defaultCampaign")
	private Boolean defaultCampaign;

	@Builder.Default
	@JsonProperty("finalities")
	@ApiModelProperty(required = true, value = "Finalidades")
	private List<Finality> finalities = new ArrayList<>();//NOSONAR

}
