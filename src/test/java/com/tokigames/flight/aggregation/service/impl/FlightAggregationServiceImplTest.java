package com.tokigames.flight.aggregation.service.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.tokigames.flight.aggregation.configuration.ApplicationPropertiesConfiguration;
import com.tokigames.flight.aggregation.controller.model.FlightFilterRequest;
import com.tokigames.flight.aggregation.controller.model.FlightFilterRequest.TimeRange;
import com.tokigames.flight.aggregation.controller.model.FlightResponse;
import com.tokigames.flight.aggregation.service.model.FlightModel;
import com.tokigames.flight.aggregation.service.util.FlightAggregationServiceUtil;

@RunWith(MockitoJUnitRunner.class)
public class FlightAggregationServiceImplTest {

	private FlightAggregationServiceImpl flightAggregationServiceImpl;
	
	@Mock
	private ApplicationPropertiesConfiguration applicationPropertiesConfiguration;
	
	@Mock
	private FlightAggregationServiceUtil flightAggregationServiceUtil;
	
	@Before
	public void initiate() {
		flightAggregationServiceImpl = new FlightAggregationServiceImpl(applicationPropertiesConfiguration,flightAggregationServiceUtil);
		Mockito.when(applicationPropertiesConfiguration.getBusinessFlightUrl()).thenReturn("businessFlightUrl");
		Mockito.when(applicationPropertiesConfiguration.getCheapFlightUrl()).thenReturn("cheapFlightUrl");
		
	}
	
	@Test
	public void getAllFlightsBasedOnFilterRequestTest() {
		FlightFilterRequest flightFilterRequest = new FlightFilterRequest();
		List<FlightResponse> flightResponseList;
		List<FlightModel> flightModelsFromApi = new ArrayList<>();
		FlightModel flightModel;
		
		
		flightModel = new FlightModel();
		flightModel.setArrival("Istabul");
		flightModel.setDeparture("Antalya");
		flightModel.setArrivalTime(BigDecimal.valueOf(1558902656));
		flightModel.setDepartureTime(BigDecimal.valueOf(1563678000));
		flightModelsFromApi.add(flightModel);
		
		flightModel = new FlightModel();
		flightModel.setArrival("Tizi");
		flightModel.setDeparture("Cruz del Eje");
		flightModel.setArrivalTime(BigDecimal.valueOf(1558902656));
		flightModel.setDepartureTime(BigDecimal.valueOf(1558902656));
		flightModelsFromApi.add(flightModel);
		
		
		flightModel = new FlightModel();
		flightModel.setArrival("Greece");
		flightModel.setDeparture("Tizi");
		flightModel.setArrivalTime(BigDecimal.valueOf(1558935605));
		flightModel.setDepartureTime(BigDecimal.valueOf(1558935605));
		flightModelsFromApi.add(flightModel);
		
		Mockito.when(flightAggregationServiceUtil
				.getClassSpecificFlightModelsUsingApi(Mockito.eq(null),Mockito.any(String.class),Mockito.any(String.class)))
				.thenReturn(flightModelsFromApi);
		
		flightResponseList = flightAggregationServiceImpl.getAllFlightsBasedOnFilterRequest(flightFilterRequest);
		
		assertEquals(3, flightResponseList.size());
				
	}
	
	@Test
	public void getAllFlightsBasedOnFilterRequestWithDepatureFilterTest() {
		FlightFilterRequest flightFilterRequest = new FlightFilterRequest();
		List<FlightResponse> flightResponseList;
		List<FlightModel> flightModelsFromApi = new ArrayList<>();
		FlightModel flightModel;
		
		flightFilterRequest.setDeparture("Tizi");
		
		flightModel = new FlightModel();
		flightModel.setArrival("Istabul");
		flightModel.setDeparture("Antalya");
		flightModel.setArrivalTime(BigDecimal.valueOf(1558902656));
		flightModel.setDepartureTime(BigDecimal.valueOf(1563678000));
		flightModelsFromApi.add(flightModel);
		
		flightModel = new FlightModel();
		flightModel.setArrival("Tizi");
		flightModel.setDeparture("Cruz del Eje");
		flightModel.setArrivalTime(BigDecimal.valueOf(1558902656));
		flightModel.setDepartureTime(BigDecimal.valueOf(1558902656));
		flightModelsFromApi.add(flightModel);
		
		
		flightModel = new FlightModel();
		flightModel.setArrival("Greece");
		flightModel.setDeparture("Tizi");
		flightModel.setArrivalTime(BigDecimal.valueOf(1558935605));
		flightModel.setDepartureTime(BigDecimal.valueOf(1558935605));
		flightModelsFromApi.add(flightModel);
		
		
		flightModel = new FlightModel();
		flightModel.setArrival("Singapore");
		flightModel.setDeparture("Tizi");
		flightModel.setArrivalTime(BigDecimal.valueOf(1558935605));
		flightModel.setDepartureTime(BigDecimal.valueOf(1558935605));
		flightModelsFromApi.add(flightModel);
		
		Mockito.when(flightAggregationServiceUtil
				.getClassSpecificFlightModelsUsingApi(Mockito.eq(null),Mockito.any(String.class),Mockito.any(String.class)))
				.thenReturn(flightModelsFromApi);
		
		
		flightResponseList = flightAggregationServiceImpl.getAllFlightsBasedOnFilterRequest(flightFilterRequest);
		
		assertEquals(2, flightResponseList.size());
		
		for(FlightResponse flightResponse : flightResponseList) {
			assertEquals("Tizi",flightResponse.getDeparture());
		}
	}
	
	@Test
	public void getAllFlightsBasedOnFilterRequestWithArrivalFilterTest() {
		FlightFilterRequest flightFilterRequest = new FlightFilterRequest();
		List<FlightResponse> flightResponseList;
		List<FlightModel> flightModelsFromApi = new ArrayList<>();
		FlightModel flightModel;
		
		flightFilterRequest.setArrival("Istabul");
		
		flightModel = new FlightModel();
		flightModel.setArrival("Istabul");
		flightModel.setDeparture("Antalya");
		flightModel.setArrivalTime(BigDecimal.valueOf(1558902656));
		flightModel.setDepartureTime(BigDecimal.valueOf(1563678000));
		flightModelsFromApi.add(flightModel);
		
		flightModel = new FlightModel();
		flightModel.setArrival("Tizi");
		flightModel.setDeparture("Cruz del Eje");
		flightModel.setArrivalTime(BigDecimal.valueOf(1558902656));
		flightModel.setDepartureTime(BigDecimal.valueOf(1558902656));
		flightModelsFromApi.add(flightModel);
		
		
		flightModel = new FlightModel();
		flightModel.setArrival("Greece");
		flightModel.setDeparture("Tizi");
		flightModel.setArrivalTime(BigDecimal.valueOf(1558935605));
		flightModel.setDepartureTime(BigDecimal.valueOf(1558935605));
		flightModelsFromApi.add(flightModel);
		
		
		flightModel = new FlightModel();
		flightModel.setArrival("Istabul");
		flightModel.setDeparture("Tizi");
		flightModel.setArrivalTime(BigDecimal.valueOf(1558935605));
		flightModel.setDepartureTime(BigDecimal.valueOf(1558935605));
		flightModelsFromApi.add(flightModel);
		
		Mockito.when(flightAggregationServiceUtil
				.getClassSpecificFlightModelsUsingApi(Mockito.eq(null),Mockito.any(String.class),Mockito.any(String.class)))
				.thenReturn(flightModelsFromApi);
		
		
		flightResponseList = flightAggregationServiceImpl.getAllFlightsBasedOnFilterRequest(flightFilterRequest);
		
		assertEquals(2, flightResponseList.size());
		
		for(FlightResponse flightResponse : flightResponseList) {
			assertEquals("Istabul",flightResponse.getArrival());
		}
	}
	
	@Test
	public void getAllFlightsBasedOnFilterRequestWithArrivalTimeFilterTest() {
		FlightFilterRequest flightFilterRequest = new FlightFilterRequest();
		List<FlightResponse> flightResponseList;
		List<FlightModel> flightModelsFromApi = new ArrayList<>();
		FlightModel flightModel;
		TimeRange timeRange;
		
		timeRange = new TimeRange();
		timeRange.setStartTime(1558935604L);
		timeRange.setEndTime(1558935606L);
		flightFilterRequest.setArrivalTimeRange(timeRange);
		
		flightModel = new FlightModel();
		flightModel.setArrival("Istabul");
		flightModel.setDeparture("Antalya");
		flightModel.setArrivalTime(BigDecimal.valueOf(1558902656));
		flightModel.setDepartureTime(BigDecimal.valueOf(1563678000));
		flightModelsFromApi.add(flightModel);
		
		flightModel = new FlightModel();
		flightModel.setArrival("Tizi");
		flightModel.setDeparture("Cruz del Eje");
		flightModel.setArrivalTime(BigDecimal.valueOf(1558902656));
		flightModel.setDepartureTime(BigDecimal.valueOf(1558902656));
		flightModelsFromApi.add(flightModel);
		
		
		flightModel = new FlightModel();
		flightModel.setArrival("Greece");
		flightModel.setDeparture("Tizi");
		flightModel.setArrivalTime(BigDecimal.valueOf(1558935605));
		flightModel.setDepartureTime(BigDecimal.valueOf(1558935605));
		flightModelsFromApi.add(flightModel);
		
		
		flightModel = new FlightModel();
		flightModel.setArrival("Istabul");
		flightModel.setDeparture("Tizi");
		flightModel.setArrivalTime(BigDecimal.valueOf(1558935605));
		flightModel.setDepartureTime(BigDecimal.valueOf(1558935605));
		flightModelsFromApi.add(flightModel);
		
		Mockito.when(flightAggregationServiceUtil
				.getClassSpecificFlightModelsUsingApi(Mockito.eq(null),Mockito.any(String.class),Mockito.any(String.class)))
				.thenReturn(flightModelsFromApi);
		
		
		flightResponseList = flightAggregationServiceImpl.getAllFlightsBasedOnFilterRequest(flightFilterRequest);
		
		assertEquals(2, flightResponseList.size());
		
		for(FlightResponse flightResponse : flightResponseList) {
			assertTrue(flightResponse.getArrivalTime() > 1558935604L);
			assertTrue(flightResponse.getArrivalTime() < 1558935606L);
		}
	}
}
