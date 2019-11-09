package com.tokigames.flight.aggregation.service.util;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

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
	
	@PostConstruct
	public void intitate() {
		restTemplate = new RestTemplate();
	}

	public List<FlightModel> getClassSpecificFlightModelsUsingApi(FlightClassEnumerator flyingClass, String businessFlightUrl, String cheapFlightUrl) {
		BusinessFlightApiResponse businessFlightApiResponse = null;
		CheapFlightApiResponse cheapFlightApiResponse = null;
		List<FlightModel> unifiedFlightModel = new ArrayList<>();
		
		if(null == flyingClass || FlightClassEnumerator.BUSINESS.equals(flyingClass)) {
			businessFlightApiResponse = restTemplate.getForObject(businessFlightUrl, BusinessFlightApiResponse.class);
		}
		if(null == flyingClass || FlightClassEnumerator.CHEAP.equals(flyingClass)) {
			cheapFlightApiResponse = restTemplate.getForObject(cheapFlightUrl, CheapFlightApiResponse.class);
		}
		
		unifiedFlightModel.addAll(
				ConvertBusinessFlightApiResponseToFlightModelList.convert(businessFlightApiResponse));
		
		unifiedFlightModel.addAll(
				ConvertCheapFlightApiResponseToFlightModelList.convert(cheapFlightApiResponse));
		
		
		return unifiedFlightModel;
	}
	
	/**
	 * Sorting by departure time
	 * @param firstFlightModel
	 * @param nextFlightModel
	 * @return
	 */
	public int compareFlightModel(FlightModel firstFlightModel, FlightModel nextFlightModel) {
		return (int)(firstFlightModel.getDepartureTime().longValue() - 
				nextFlightModel.getDepartureTime().longValue());
	}
}
