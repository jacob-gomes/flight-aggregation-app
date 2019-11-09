package com.tokigames.flight.aggregation.service.impl;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tokigames.flight.aggregation.configuration.ApplicationPropertiesConfiguration;
import com.tokigames.flight.aggregation.controller.model.FlightFilterRequest;
import com.tokigames.flight.aggregation.controller.model.FlightFilterRequest.TimeRange;
import com.tokigames.flight.aggregation.controller.model.FlightResponse;
import com.tokigames.flight.aggregation.service.FlightAggregationService;
import com.tokigames.flight.aggregation.service.model.FlightModel;
import com.tokigames.flight.aggregation.service.util.FlightAggregationServiceUtil;
import com.tokigames.flight.aggregation.service.util.conversion.ConvertListOfFlightModelToListOfFlightResponse;

@Component
public class FlightAggregationServiceImpl implements FlightAggregationService {

	
	private ApplicationPropertiesConfiguration applicationPropertiesConfiguration;
	
	private FlightAggregationServiceUtil flightAggregationServiceUtil;
	
	@Autowired
	public FlightAggregationServiceImpl( ApplicationPropertiesConfiguration applicationPropertiesConfiguration,
			FlightAggregationServiceUtil flightAggregationServiceUtil) {
		this.applicationPropertiesConfiguration = applicationPropertiesConfiguration;
		this.flightAggregationServiceUtil = flightAggregationServiceUtil;
	}
	
	@Override
	public List<FlightResponse> getAllFlightsBasedOnFilterRequest(FlightFilterRequest flightFilterRequest) {
		List<FlightModel> filteredFlightModels;
		List<FlightModel> flightModelsFromApi = flightAggregationServiceUtil.getClassSpecificFlightModelsUsingApi(
				flightFilterRequest.getFlyingClass(),
				applicationPropertiesConfiguration.getBusinessFlightUrl(),
				applicationPropertiesConfiguration.getCheapFlightUrl());
		
		filteredFlightModels = filterFlightsUsingFilterRequest(flightModelsFromApi, flightFilterRequest);
		
		Collections.sort(filteredFlightModels, flightAggregationServiceUtil::compareFlightModel);
		
		return ConvertListOfFlightModelToListOfFlightResponse.convert(filteredFlightModels);
	}
	

	private List<FlightModel>  filterFlightsUsingFilterRequest(List<FlightModel> filteredFlightModel,
			FlightFilterRequest flightFilterRequest) {
		String departure = flightFilterRequest.getDeparture();
		String arrival = flightFilterRequest.getArrival();
		TimeRange departureTimeRange = flightFilterRequest.getDepartureTimeRange();
		TimeRange arrivalTimeRange = flightFilterRequest.getArrivalTimeRange();
		
			for(int index = 0; index < filteredFlightModel.size(); index++) {
				boolean isDepartureInvalid = isDepartureInvalid(departure, filteredFlightModel.get(index));
				boolean isArrivalInvalid = isArrivalInvalid(arrival ,filteredFlightModel.get(index));
				boolean isDepartureTimeInvalid = isDepartureTimeInvalid(departureTimeRange, filteredFlightModel.get(index));
				boolean isArrivalTimeInvalid = isArrivalTimeInvalid(arrivalTimeRange, filteredFlightModel.get(index));
				
				if(isDepartureInvalid || isArrivalInvalid || isDepartureTimeInvalid || isArrivalTimeInvalid) {
					filteredFlightModel.remove(index);
					index--;
				}
		}
		
		return filteredFlightModel;
	}

	private boolean isArrivalTimeInvalid(TimeRange arrivalTimeRange, FlightModel flightModel) {
		return null != arrivalTimeRange
				&&(arrivalTimeRange.getStartTime() > flightModel.getArrivalTime().longValue()
						|| arrivalTimeRange.getEndTime() < flightModel.getArrivalTime().longValue());
	}

	private boolean isDepartureTimeInvalid(TimeRange departureTimeRange, FlightModel flightModel) {		
		return null != departureTimeRange
				&&(departureTimeRange.getStartTime() > flightModel.getDepartureTime().longValue()
						|| departureTimeRange.getEndTime() < flightModel.getDepartureTime().longValue());
	}

	private boolean isArrivalInvalid(String arrival, FlightModel flightModel) {
		return null != arrival 
				&& (!arrival.isEmpty() 
						&& !arrival.equals(flightModel.getArrival()));
	}

	private boolean isDepartureInvalid(String departure, FlightModel flightModel) {		
		return null != departure 
				&& (!departure.isEmpty() 
					&& !departure.equals(flightModel.getDeparture()));
	}



}
