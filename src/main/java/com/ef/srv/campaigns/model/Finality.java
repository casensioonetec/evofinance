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
 * Finality
 */

@Getter
@Setter
@Builder
public class Finality implements Serializable {
	private static final long serialVersionUID = 1L;

	@JsonProperty("code")
	private String code;
	
	@JsonProperty("name")
	private String name;
	
	@JsonProperty("order")
	private String order;
	
	
	@Builder.Default
	@JsonProperty("products")
	@ApiModelProperty(required = true, value = "Productos")
	private List<Product> products = new ArrayList<>();

}