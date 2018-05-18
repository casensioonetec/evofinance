package com.ef.srv.campaigns.model;

import lombok.Builder;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.math.BigDecimal;


/**
 * Product
 */

@Data
@Builder
public class Product  implements Serializable {
  private static final long serialVersionUID = 1L;

  @JsonProperty("codProd")
  private String codProd;
  
  @JsonProperty("TIN")
  private BigDecimal TIN;
  
  @JsonProperty("TAE")
  private BigDecimal TAE;
  
  @JsonProperty("optionalInsurance")
  private Boolean optionalInsurance;
  
  @JsonProperty("minMonths")
  private String minMonths;
  
  @JsonProperty("maxMonths")
  private String maxMonths;
  
  @JsonProperty("minAmount")
  private String minAmount;
  
  @JsonProperty("maxAmount")
  private String maxAmount;
  
  @JsonProperty("insuranceAmount") 
  private BigDecimal insuranceAmount;
  
  @JsonProperty("formalisationFee")
  private BigDecimal formalisationFee;
  
  @JsonProperty("earlyRepayFirst12Months")
  private String earlyRepayFirst12Months;
  
  @JsonProperty("earlyRepayAfter12Months")
  private String earlyRepayAfter12Months;
  
}

