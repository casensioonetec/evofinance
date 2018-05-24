package com.ef.srv.campaigns.model;

import java.io.Serializable;
import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;
import lombok.Data;

/**
 * Product
 */

@Builder
@Data
public class Product implements Serializable {
	private static final long serialVersionUID = 1L;


	@JsonProperty("TIN")
	private BigDecimal TIN;

	@JsonProperty("TAE")
	private BigDecimal TAE;

	@JsonProperty("optionalInsurance")
	private Boolean optionalInsurance;

	@JsonProperty("minMonths")
	private String minMonths;

	@JsonProperty("minAmount")
	private String minAmount;
	
	@JsonProperty("maxMonths")
	private String maxMonths;

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

	@JsonProperty("codProd")
	private String codProd;

}
