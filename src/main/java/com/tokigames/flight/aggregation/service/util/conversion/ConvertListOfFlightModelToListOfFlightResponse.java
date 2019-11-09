package com.tokigames.flight.aggregation.service.util.conversion;

import java.util.ArrayList;
import java.util.List;

import com.tokigames.flight.aggregation.controller.model.FlightResponse;
import com.tokigames.flight.aggregation.service.model.FlightModel;

public class ConvertListOfFlightModelToListOfFlightResponse {
	
	private ConvertListOfFlightModelToListOfFlightResponse() {}

	public static List<FlightResponse> convert(List<FlightModel> filterFlightModels) {
		List<FlightResponse> flightResponses = new ArrayList<>();
		
		if(null != filterFlightModels
				&& !filterFlightModels.isEmpty()) {
			
			filterFlightModels.forEach((flightModel) ->{
				FlightResponse flightResponse = flightModelToFlightResponse(flightModel);				
				flightResponses.add(flightResponse);
			});
		
		}
		
		return flightResponses;
	}

	private static FlightResponse flightModelToFlightResponse(FlightModel flightModel) {
		FlightResponse flightResponse = new FlightResponse();
		
		flightResponse.setArrival(flightModel.getArrival());
		flightResponse.setDeparture(flightModel.getDeparture());
		flightResponse.setArrivalTime(flightModel.getArrivalTime().longValue());
		flightResponse.setDepartureTime(flightModel.getDepartureTime().longValue());
		
		return flightResponse;
	}

}
