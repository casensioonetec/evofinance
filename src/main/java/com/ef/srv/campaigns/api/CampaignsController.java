package com.ef.srv.campaigns.api;

import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.guava.GuavaCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.ef.srv.campaigns.components.HttpCall;
import com.ef.srv.campaigns.model.CampaignData;
import com.ef.srv.campaigns.service.CampaignsService;
import com.ev.arq.srv.api.exception.EntityNotFoundException;
import com.google.common.cache.CacheBuilder;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api")
@EnableWebMvc
public class CampaignsController extends WebMvcConfigurerAdapter {

	@Autowired
	private CampaignsService service;
	
	@Autowired
	private HttpCall call;

	@Override
	public void addCorsMappings(CorsRegistry registry) {
		super.addCorsMappings(registry);
		registry.addMapping("/**").allowedOrigins("*").allowedMethods("GET", "PUT", "POST", "DELETE", "OPTIONS");
	}

	@ApiOperation(value = "Recupera todos los datos de una campaña", nickname = "v1CampaignsCampaignCodeGet", notes = "Recupera todos los datos de una campaña para el proceso de contratación", response = CampaignData.class, tags = {
			"Campaigns", })
	@ApiResponses(value = { @ApiResponse(code = 200, message = "OK", response = CampaignData.class),
			@ApiResponse(code = 401, message = "Unauthorized"), @ApiResponse(code = 403, message = "Forbidden"),
			@ApiResponse(code = 404, message = "Not Found") })
	@RequestMapping(value = "/v1/campaigns/{campaignCode}", produces = {
			"application/json" }, method = RequestMethod.GET)
	public ResponseEntity<CampaignData> v1CampaignsCampaignCodeGet(
			@ApiParam(value = "Código de la campaña a descargar", required = true) @PathVariable("campaignCode") String campaignCode,
			@RequestHeader HttpHeaders headers) throws EntityNotFoundException {
		
		return new ResponseEntity<CampaignData>(service.v1CampaignsCampaignCodeGet(campaignCode, call), HttpStatus.OK);
	}
	
}
