package com.ef.srv.campaigns.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

/**
 * CampaingData
 */

@Data
@Builder
public class CampaignData implements Serializable {
	private static final long serialVersionUID = 1L;

	@JsonProperty("id")
	private String id;

	@JsonProperty("vendor")
	private Boolean vendor;
	
	@JsonProperty("pixel")
	private String pixel;
	
	@JsonProperty("forcedInsurance")
	private String forcedInsurance;
	
	@JsonProperty("defaultCampaign")
	private Boolean defaultCampaign;

	@Builder.Default
	@JsonProperty("finalities")
	@ApiModelProperty(required = true, value = "Finalidades")
	private List<Finality> finalities = new ArrayList<>();

	
	
	
	
	
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Boolean getVendor() {
		return vendor;
	}

	public void setVendor(Boolean vendor) {
		this.vendor = vendor;
	}

	public String getPixel() {
		return pixel;
	}

	public void setPixel(String pixel) {
		this.pixel = pixel;
	}

	public String getForcedInsurance() {
		return forcedInsurance;
	}

	public void setForcedInsurance(String forcedInsurance) {
		this.forcedInsurance = forcedInsurance;
	}

	public Boolean getDefaultCampaign() {
		return defaultCampaign;
	}

	public void setDefaultCampaign(Boolean defaultCampaign) {
		this.defaultCampaign = defaultCampaign;
	}

	public List<Finality> getFinalities() {
		return finalities;
	}

	public void setFinalities(List<Finality> finalities) {
		this.finalities = finalities;
	}

	
	
	
	
	
	
}
