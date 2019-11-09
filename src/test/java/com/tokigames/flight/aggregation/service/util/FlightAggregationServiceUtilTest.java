package com.tokigames.flight.aggregation.service.util;

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
import org.springframework.web.client.RestTemplate;

import com.tokigames.flight.aggregation.constants.FlightClassEnumerator;
import com.tokigames.flight.aggregation.exception.FlightAggregationException;
import com.tokigames.flight.aggregation.service.model.BusinessFlightApiResponse;
import com.tokigames.flight.aggregation.service.model.CheapFlightApiResponse;
import com.tokigames.flight.aggregation.service.model.FlightModel;

@RunWith(MockitoJUnitRunner.class)
public class FlightAggregationServiceUtilTest {
	
	private FlightAggregationServiceUtil flightAggregationServiceUtil;
	
	@Mock
	private RestTemplate restTemplate;
	
	@Before
	public void initiate() {
		flightAggregationServiceUtil = new FlightAggregationServiceUtil(restTemplate);
	}
	
	@Test 
	public void getClassSpecificFlightModelsUsingApiTest() {
		List<FlightModel> flightModelList;
		BusinessFlightApiResponse businessFlightApiResponse = new BusinessFlightApiResponse();
		CheapFlightApiResponse cheapFlightApiResponse = new CheapFlightApiResponse();
		List<BusinessFlightApiResponse.Data> bDataList = new ArrayList<>();
		List<CheapFlightApiResponse.Data> cDataList = new ArrayList<>();		
		BusinessFlightApiResponse.Data bData;
		CheapFlightApiResponse.Data cData;
		
		bData = new BusinessFlightApiResponse.Data();
		bData.setArrival("Ankara");
		bData.setDeparture("Antalya");
		bData.setArrivalTime(BigDecimal.valueOf(1561627856L));
		bData.setDepartureTime(BigDecimal.valueOf(1561627856));
		bDataList.add(bData);
		
		cData = new CheapFlightApiResponse.Data();
		cData.setRoute("Cruz del Eje-Antalya");
		cData.setArrival(BigDecimal.valueOf(1558902656));
		cData.setDeparture(BigDecimal.valueOf(1558902656));
		cDataList.add(cData);
		
		businessFlightApiResponse.setData(bDataList);
		cheapFlightApiResponse.setData(cDataList);
		
		Mockito.when(restTemplate.getForObject(Mockito.eq("businessFlightUrl"), Mockito.eq(BusinessFlightApiResponse.class)))
		.thenReturn(businessFlightApiResponse);
		Mockito.when(restTemplate.getForObject(Mockito.eq("cheapFlightUrl"), Mockito.eq(CheapFlightApiResponse.class)))
		.thenReturn(cheapFlightApiResponse);
		
		flightModelList = flightAggregationServiceUtil.getClassSpecificFlightModelsUsingApi(null, "businessFlightUrl", "cheapFlightUrl");
		
		assertEquals(2, flightModelList.size());
	}
	
	@Test 
	public void getClassSpecificFlightModelsUsingApiForBusinessClassTest() {
		List<FlightModel> flightModelList;
		BusinessFlightApiResponse businessFlightApiResponse = new BusinessFlightApiResponse();
		CheapFlightApiResponse cheapFlightApiResponse = new CheapFlightApiResponse();
		List<BusinessFlightApiResponse.Data> bDataList = new ArrayList<>();
		List<CheapFlightApiResponse.Data> cDataList = new ArrayList<>();		
		BusinessFlightApiResponse.Data bData;
		CheapFlightApiResponse.Data cData;
		
		bData = new BusinessFlightApiResponse.Data();
		bData.setArrival("Ankara");
		bData.setDeparture("Antalya");
		bData.setArrivalTime(BigDecimal.valueOf(1561627856L));
		bData.setDepartureTime(BigDecimal.valueOf(1561627856));
		bDataList.add(bData);
		
		cData = new CheapFlightApiResponse.Data();
		cData.setRoute("Cruz del Eje-Antalya");
		cData.setArrival(BigDecimal.valueOf(1558902656));
		cData.setDeparture(BigDecimal.valueOf(1558902656));
		cDataList.add(cData);
		
		businessFlightApiResponse.setData(bDataList);
		cheapFlightApiResponse.setData(cDataList);
		
		Mockito.when(restTemplate.getForObject(Mockito.eq("businessFlightUrl"), Mockito.eq(BusinessFlightApiResponse.class)))
		.thenReturn(businessFlightApiResponse);
		
		flightModelList = flightAggregationServiceUtil.getClassSpecificFlightModelsUsingApi(FlightClassEnumerator.BUSINESS, "businessFlightUrl", "cheapFlightUrl");
		
		assertEquals(1, flightModelList.size());
		assertEquals(FlightClassEnumerator.BUSINESS, flightModelList.get(0).getFlightClass());
		assertEquals(bData.getDepartureTime(), flightModelList.get(0).getDepartureTime());
		assertEquals(bData.getArrivalTime(), flightModelList.get(0).getArrivalTime());
		assertEquals(bData.getArrival(), flightModelList.get(0).getArrival());
		assertEquals(bData.getDeparture(), flightModelList.get(0).getDeparture());
	}
	
	
	
	@Test 
	public void getClassSpecificFlightModelsUsingApiForCheapClassTest() {
		List<FlightModel> flightModelList;
		BusinessFlightApiResponse businessFlightApiResponse = new BusinessFlightApiResponse();
		CheapFlightApiResponse cheapFlightApiResponse = new CheapFlightApiResponse();
		List<BusinessFlightApiResponse.Data> bDataList = new ArrayList<>();
		List<CheapFlightApiResponse.Data> cDataList = new ArrayList<>();		
		BusinessFlightApiResponse.Data bData;
		CheapFlightApiResponse.Data cData;
		
		bData = new BusinessFlightApiResponse.Data();
		bData.setArrival("Ankara");
		bData.setDeparture("Antalya");
		bData.setArrivalTime(BigDecimal.valueOf(1561627856L));
		bData.setDepartureTime(BigDecimal.valueOf(1561627856));
		bDataList.add(bData);
		
		cData = new CheapFlightApiResponse.Data();
		cData.setRoute("Cruz del Eje-Antalya");
		cData.setArrival(BigDecimal.valueOf(1558902656));
		cData.setDeparture(BigDecimal.valueOf(1558902656));
		cDataList.add(cData);
		
		businessFlightApiResponse.setData(bDataList);
		cheapFlightApiResponse.setData(cDataList);
		
		Mockito.when(restTemplate.getForObject(Mockito.eq("cheapFlightUrl"), Mockito.eq(CheapFlightApiResponse.class)))
		.thenReturn(cheapFlightApiResponse);
		
		flightModelList = flightAggregationServiceUtil.getClassSpecificFlightModelsUsingApi(FlightClassEnumerator.CHEAP, "businessFlightUrl", "cheapFlightUrl");
		
		assertEquals(1, flightModelList.size());
		assertEquals(FlightClassEnumerator.CHEAP, flightModelList.get(0).getFlightClass());
		assertEquals(cData.getDeparture(), flightModelList.get(0).getDepartureTime());
		assertEquals(cData.getArrival(), flightModelList.get(0).getArrivalTime());
		assertEquals("Antalya", flightModelList.get(0).getArrival());
		assertEquals("Cruz del Eje", flightModelList.get(0).getDeparture());
	}
	
	@Test(expected = FlightAggregationException.class)
	public void getClassSpecificFlightModelsUsingApiForImproperResponseFromDownStreamTest() {
		List<FlightModel> flightModelList;
		BusinessFlightApiResponse businessFlightApiResponse = new BusinessFlightApiResponse();
		CheapFlightApiResponse cheapFlightApiResponse = new CheapFlightApiResponse();
		List<BusinessFlightApiResponse.Data> bDataList = new ArrayList<>();
		List<CheapFlightApiResponse.Data> cDataList = new ArrayList<>();		
		BusinessFlightApiResponse.Data bData;
		CheapFlightApiResponse.Data cData;
		
		bData = new BusinessFlightApiResponse.Data();
		bData.setArrival("Ankara");
		bData.setDeparture("Antalya");
		bData.setArrivalTime(BigDecimal.valueOf(1561627856L));
		bData.setDepartureTime(BigDecimal.valueOf(1561627856));
		bDataList.add(bData);
		
		cData = new CheapFlightApiResponse.Data();
		cData.setRoute("Cruz del Eje");
		cData.setArrival(BigDecimal.valueOf(1558902656));
		cData.setDeparture(BigDecimal.valueOf(1558902656));
		cDataList.add(cData);
		
		businessFlightApiResponse.setData(bDataList);
		cheapFlightApiResponse.setData(cDataList);
		
		Mockito.when(restTemplate.getForObject(Mockito.eq("businessFlightUrl"), Mockito.eq(BusinessFlightApiResponse.class)))
		.thenReturn(businessFlightApiResponse);
		Mockito.when(restTemplate.getForObject(Mockito.eq("cheapFlightUrl"), Mockito.eq(CheapFlightApiResponse.class)))
		.thenReturn(cheapFlightApiResponse);
		
		flightModelList = flightAggregationServiceUtil.getClassSpecificFlightModelsUsingApi(null, "businessFlightUrl", "cheapFlightUrl");
		
		assertEquals(1, flightModelList.size());
		
		assertEquals(bData, flightModelList.get(0));
	}
	
	@Test
	public void compareFlightModelTest() {
		FlightModel firstFlightModel;
		FlightModel nextFlightModel;
		int compareResult;
	
		firstFlightModel = new FlightModel();
		firstFlightModel.setArrival("Istabul");
		firstFlightModel.setDeparture("Antalya");
		firstFlightModel.setArrivalTime(BigDecimal.valueOf(1563678000));
		firstFlightModel.setDepartureTime(BigDecimal.valueOf(1563678000));
		
		nextFlightModel = new FlightModel();
		nextFlightModel.setArrival("Tizi");
		nextFlightModel.setDeparture("Cruz del Eje");
		nextFlightModel.setArrivalTime(BigDecimal.valueOf(1558902656));
		nextFlightModel.setDepartureTime(BigDecimal.valueOf(1558902656));
		
		compareResult = flightAggregationServiceUtil.compareFlightModel(firstFlightModel, nextFlightModel);
		
		assertTrue(compareResult > 0);
		
		compareResult = flightAggregationServiceUtil.compareFlightModel(nextFlightModel, firstFlightModel);
		
		assertTrue(compareResult < 0);
		
	}
}
