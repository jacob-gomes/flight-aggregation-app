package com.tokigames.flight.aggregation.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.tokigames.flight.aggregation.controller.model.FlightFilterRequest;
import com.tokigames.flight.aggregation.controller.model.FlightResponse;
import com.tokigames.flight.aggregation.service.FlightAggregationService;

@Component
@RestController("/flights")
public class FlightController {
	private FlightAggregationService flightAggregationService;
	
	@Autowired
	public FlightController(FlightAggregationService flightAggregationService) {
		this.flightAggregationService = flightAggregationService;
	}
	
	@PostMapping("/")
	public ResponseEntity<List<FlightResponse>> getFlightsOnTheRoute(@RequestBody FlightFilterRequest flightFilterRequest){
		List<FlightResponse> flightResponses = flightAggregationService.getAllFlightsBasedOnFilterRequest(flightFilterRequest);
		return new ResponseEntity<>(flightResponses, HttpStatus.OK);
	}
	
}
