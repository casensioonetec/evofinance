package com.ef.srv.campaigns.model;

import lombok.Builder;
import lombok.Data;
import com.ef.srv.campaigns.model.Finality;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


/**
 * CampaingData
 */

@Data
@Builder
public class CampaingData  implements Serializable {
  private static final long serialVersionUID = 1L;

  @JsonProperty("id")
  private String id;
  
  @JsonProperty("vendor")
  private Boolean vendor;
  
  @JsonProperty("finalities")
  @ApiModelProperty(required = true, value = "Finalidades")
  private List<Finality> finalities = new ArrayList<>();
  
}

