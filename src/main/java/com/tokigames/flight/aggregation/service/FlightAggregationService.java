package com.tokigames.flight.aggregation.service;

import java.util.List;

import com.tokigames.flight.aggregation.controller.model.FlightFilterRequest;
import com.tokigames.flight.aggregation.controller.model.FlightResponse;

public interface FlightAggregationService {
	List<FlightResponse> getAllFlightsBasedOnFilterRequest(FlightFilterRequest flightFilterRequest);

}
