package com.ef.srv.campaigns.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ef.srv.campaigns.model.CampaingData;
import com.ef.srv.campaigns.service.CampaignsService;
import com.ev.arq.srv.api.exception.EntityNotFoundException;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api")
public class CampaignsController {

	@Autowired
	private CampaignsService service;

	@ApiOperation(value = "Recupera todos los datos de una campaña", nickname = "v1CampaignsCampaignCodeGet", notes = "Recupera todos los datos de una campaña para el proceso de contratación", response = CampaingData.class, tags = {
			"Campaigns", })
	@ApiResponses(value = { @ApiResponse(code = 200, message = "OK", response = CampaingData.class),
			@ApiResponse(code = 401, message = "Unauthorized"), @ApiResponse(code = 403, message = "Forbidden"),
			@ApiResponse(code = 404, message = "Not Found") })
	@RequestMapping(value = "/v1/campaigns/{campaignCode}", produces = {
			"application/json" }, method = RequestMethod.GET)

	public ResponseEntity<CampaingData> v1CampaignsCampaignCodeGet(
			@ApiParam(value = "Código de la campaña a descargar", required = true) @PathVariable("campaignCode") String campaignCode)
			throws EntityNotFoundException {
		HttpHeaders headers = new HttpHeaders();
		headers.add("Access-Control-Allow-Origin", "*");
		return new ResponseEntity<CampaingData>(service.v1CampaignsCampaignCodeGet(campaignCode), headers,
				HttpStatus.NOT_IMPLEMENTED);

		// CampaingData
		//
		// HttpHeaders headers = new HttpHeaders();
		// headers.add("Responded", "MyController");
		//
		// return ResponseEntity.accepted().headers(headers).body(c);
		//
	}
}
