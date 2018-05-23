package com.ef.srv.campaigns.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;
import lombok.Data;

/**
 * OAuth2
 */

@Data
@Builder
public class OAuth2 implements Serializable {
	private static final long serialVersionUID = 1L;

	@JsonProperty("access_token")
	private String access_token;

	@JsonProperty("instance_url")
	private String instance_url;

	@JsonProperty("id")
	private String id;

	@JsonProperty("token_type")
	private String token_type;

	@JsonProperty("issued_at")
	private String issued_at;

	@JsonProperty("signature")
	private String signature;

}
