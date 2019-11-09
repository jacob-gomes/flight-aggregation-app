package com.tokigames.flight.aggregation.constants;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.tokigames.flight.aggregation.controller.model.deserializer.FlightClassDeserializer;

@JsonDeserialize(using = FlightClassDeserializer.class)
public enum FlightClassEnumerator {
	BUSINESS("business"),CHEAP("cheap");
	
	public String flyingClass;
	
	private FlightClassEnumerator(String flyingClass){
		this.flyingClass = flyingClass;
	}
}
