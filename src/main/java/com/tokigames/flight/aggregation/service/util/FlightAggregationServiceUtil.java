package com.tokigames.flight.aggregation.service.util;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.tokigames.flight.aggregation.constants.FlightClassEnumerator;
import com.tokigames.flight.aggregation.service.model.BusinessFlightApiResponse;
import com.tokigames.flight.aggregation.service.model.CheapFlightApiResponse;
import com.tokigames.flight.aggregation.service.model.FlightModel;
import com.tokigames.flight.aggregation.service.util.conversion.ConvertBusinessFlightApiResponseToFlightModelList;
import com.tokigames.flight.aggregation.service.util.conversion.ConvertCheapFlightApiResponseToFlightModelList;

@Component
public class FlightAggregationServiceUtil {
	
	private RestTemplate restTemplate;
	
	private Logger logger = LoggerFactory.getLogger(FlightAggregationServiceUtil.class); 
	
	@Autowired
	public FlightAggregationServiceUtil(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}	

	public List<FlightModel> getClassSpecificFlightModelsUsingApi(FlightClassEnumerator flyingClass, String businessFlightUrl, String cheapFlightUrl) {
		BusinessFlightApiResponse businessFlightApiResponse = null;
		CheapFlightApiResponse cheapFlightApiResponse = null;
		List<FlightModel> unifiedFlightModel = new ArrayList<>();
		
		if(null == flyingClass || FlightClassEnumerator.BUSINESS.equals(flyingClass)) {
			businessFlightApiResponse = restTemplate.getForObject(businessFlightUrl, BusinessFlightApiResponse.class);
			logger.info("Business Flight API response: {}", businessFlightApiResponse.toString());
		}
		if(null == flyingClass || FlightClassEnumerator.CHEAP.equals(flyingClass)) {
			cheapFlightApiResponse = restTemplate.getForObject(cheapFlightUrl, CheapFlightApiResponse.class);
			logger.info("Cheap Flight API response: {}", cheapFlightApiResponse.toString());
		}
		
		unifiedFlightModel.addAll(
				ConvertBusinessFlightApiResponseToFlightModelList.convert(businessFlightApiResponse));
		
		unifiedFlightModel.addAll(
				ConvertCheapFlightApiResponseToFlightModelList.convert(cheapFlightApiResponse));
		
		
		return unifiedFlightModel;
	}
	
	/**
	 * Comparing by departure time
	 * @param firstFlightModel
	 * @param nextFlightModel
	 * @return
	 */
	public int compareFlightModel(FlightModel firstFlightModel, FlightModel nextFlightModel) {
		return (int)(firstFlightModel.getDepartureTime().longValue() - 
				nextFlightModel.getDepartureTime().longValue());
	}
}
