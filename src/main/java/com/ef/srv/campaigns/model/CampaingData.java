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
public class CampaingData implements Serializable {
	private static final long serialVersionUID = 1L;

	@JsonProperty("data")
	@ApiModelProperty(required = true, value = "datos")
	private ArrayList<DataBody> data;

}
