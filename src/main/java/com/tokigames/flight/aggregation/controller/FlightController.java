package com.tokigames.flight.aggregation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RestController;

import com.tokigames.flight.aggregation.service.FlightAggregationService;

@Component
@RestController
public class FlightController {
	private FlightAggregationService flightAggregationService;
	
	@Autowired
	public FlightController(FlightAggregationService flightAggregationService) {
		this.flightAggregationService = flightAggregationService;
	}
	
}
