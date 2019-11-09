package com.tokigames.flight.aggregation.service.util.conversion;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import com.tokigames.flight.aggregation.constants.FlightClassEnumerator;
import com.tokigames.flight.aggregation.service.model.BusinessFlightApiResponse;
import com.tokigames.flight.aggregation.service.model.FlightModel;

@RunWith(MockitoJUnitRunner.class)
public class ConvertBusinessFlightApiResponseToFlightModelListTest {

	@Test
	public void convertTest() {
		BusinessFlightApiResponse businessFlightApiResponse = new BusinessFlightApiResponse();
		List<BusinessFlightApiResponse.Data> bDataList = new ArrayList<>();
		BusinessFlightApiResponse.Data bData;
		List<FlightModel> flightModelList;
		
		bData = new BusinessFlightApiResponse.Data();
		bData.setArrival("Ankara");
		bData.setDeparture("Antalya");
		bData.setArrivalTime(BigDecimal.valueOf(1561627856L));
		bData.setDepartureTime(BigDecimal.valueOf(1561627856));
		bDataList.add(bData);
		businessFlightApiResponse.setData(bDataList);
		
		flightModelList = ConvertBusinessFlightApiResponseToFlightModelList.convert(businessFlightApiResponse);
		
		assertEquals(1, flightModelList.size());
		assertEquals(FlightClassEnumerator.BUSINESS, flightModelList.get(0).getFlightClass());
		assertEquals(bData.getDepartureTime(), flightModelList.get(0).getDepartureTime());
		assertEquals(bData.getArrivalTime(), flightModelList.get(0).getArrivalTime());
		assertEquals(bData.getArrival(), flightModelList.get(0).getArrival());
		assertEquals(bData.getDeparture(), flightModelList.get(0).getDeparture());
	}
}
