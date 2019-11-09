package com.tokigames.flight.aggregation.service.util.conversion;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.tokigames.flight.aggregation.constants.FlightClassEnumerator;
import com.tokigames.flight.aggregation.controller.model.FlightResponse;
import com.tokigames.flight.aggregation.service.model.FlightModel;

public class ConvertListOfFlightModelToListOfFlightResponseTest {

	@Test
	public void convertTest() {
		List<FlightModel> flightModels = new ArrayList<>();
		FlightModel flightModel = new FlightModel();
		
		flightModel.setFlightClass(FlightClassEnumerator.CHEAP);		
		flightModel.setArrivalTime(BigDecimal.valueOf(1558902655L));
		flightModel.setDepartureTime(BigDecimal.valueOf(1558902655L));
		flightModel.setDeparture("Antalya");
		flightModel.setArrival("Cruz del Eje");
		flightModels.add(flightModel);
		
		List<FlightResponse> flightResponseList = ConvertListOfFlightModelToListOfFlightResponse.convert(flightModels);
		
		assertEquals(1, flightResponseList.size());
		assertEquals(flightModel.getDeparture(), flightResponseList.get(0).getDeparture());
		assertEquals(flightModel.getArrival(), flightResponseList.get(0).getArrival());
		assertEquals(Long.valueOf(1558902655L), flightResponseList.get(0).getDepartureTime());
		assertEquals(Long.valueOf(1558902655L), flightResponseList.get(0).getArrivalTime());
	}
}
