package com.ef.srv.campaigns.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.ef.srv.campaigns.api.CampaignsController;
import com.ef.srv.campaigns.model.CampaingData;
import com.ef.srv.campaigns.model.Finality;
import com.ef.srv.campaigns.model.Product;
import com.ef.srv.campaigns.service.CampaignsService;
import com.ev.arq.srv.api.exception.EntityNotFoundException;

import lombok.extern.slf4j.Slf4j;

/**
 * A delegate to be called by the {@link CampaignsController}}. Implement this
 * interface with a {@link org.springframework.stereotype.Service} annotated
 * class.
 */

@Service
@Slf4j
public class CampaignsServiceImpl implements CampaignsService {

	@Override
	public CampaingData v1CampaignsCampaignCodeGet(String campaignCode) throws EntityNotFoundException {

		List<Product> listaProductos = new ArrayList<Product>();
		List<Finality> listaFinalidades = new ArrayList<Finality>();

		Product product = Product.builder().TIN(new BigDecimal(12341)).codProd("adsfasdfa").TAE(new BigDecimal(123))
				.optionalInsurance(false).minMonths("1").maxMonths("25").minAmount("1").maxAmount("1232323")
				.insuranceAmount(new BigDecimal(12312312)).formalisationFee(new BigDecimal(12312312))
				.earlyRepayFirst12Months("asdfas").earlyRepayAfter12Months("aasdfasd").build();
		listaProductos.add(product);
		product = Product.builder().TIN(new BigDecimal(12341)).codProd("adsfasdfa").TAE(new BigDecimal(123))
				.optionalInsurance(false).minMonths("1").maxMonths("25").minAmount("1").maxAmount("1232323")
				.insuranceAmount(new BigDecimal(12312312)).formalisationFee(new BigDecimal(12312312))
				.earlyRepayFirst12Months("asdfas").earlyRepayAfter12Months("aasdfasd").build();
		listaProductos.add(product);

		Finality finality = Finality.builder().name("Coche").products(listaProductos).build();
		listaFinalidades.add(finality);
		finality = Finality.builder().name("Coche").products(listaProductos).build();
		listaFinalidades.add(finality);

		CampaingData campaingData = CampaingData.builder().id("12").vendor(false).finalities(listaFinalidades).build();

		return campaingData;
	}

}
