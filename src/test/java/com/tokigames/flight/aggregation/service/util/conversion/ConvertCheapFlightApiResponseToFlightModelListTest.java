package com.tokigames.flight.aggregation.service.util.conversion;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.tokigames.flight.aggregation.constants.FlightClassEnumerator;
import com.tokigames.flight.aggregation.service.model.CheapFlightApiResponse;
import com.tokigames.flight.aggregation.service.model.FlightModel;

public class ConvertCheapFlightApiResponseToFlightModelListTest {

	@Test
	public void convertTest() {
		List<FlightModel> flightModelList;
		CheapFlightApiResponse cheapFlightApiResponse = new CheapFlightApiResponse();
		List<CheapFlightApiResponse.Data> cDataList = new ArrayList<>();	
		CheapFlightApiResponse.Data cData;
		
		cData = new CheapFlightApiResponse.Data();
		cData.setRoute("Cruz del Eje-Antalya");
		cData.setArrival(BigDecimal.valueOf(1558902656));
		cData.setDeparture(BigDecimal.valueOf(1558902656));
		cDataList.add(cData);
		cheapFlightApiResponse.setData(cDataList);
		
		flightModelList = ConvertCheapFlightApiResponseToFlightModelList.convert(cheapFlightApiResponse);
		
		assertEquals(1, flightModelList.size());
		assertEquals(FlightClassEnumerator.CHEAP, flightModelList.get(0).getFlightClass());
		assertEquals(cData.getDeparture(), flightModelList.get(0).getDepartureTime());
		assertEquals(cData.getArrival(), flightModelList.get(0).getArrivalTime());
		assertEquals("Antalya", flightModelList.get(0).getArrival());
		assertEquals("Cruz del Eje", flightModelList.get(0).getDeparture());
	}
}
