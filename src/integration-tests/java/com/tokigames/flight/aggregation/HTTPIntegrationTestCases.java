package com.tokigames.flight.aggregation;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

import org.json.JSONArray;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.web.context.support.GenericWebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tokigames.flight.aggregation.controller.model.FlightResponse;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = App.class)
@WebAppConfiguration
public class HTTPIntegrationTestCases {
	
    
    public static final String URL = "/";

    @Autowired
    private GenericWebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    @Before
    public void getContext() {
        mockMvc = webAppContextSetup(webApplicationContext).build();
        assertNotNull(mockMvc);
    }

	
    @Test
    public void getAllResponseWithoutFilteration() throws Exception {
    	Long previousDepartureTime = -1L;
    	String content = "{}";
    	
    	ResultActions resultActions = mockMvc.perform(post(URL)
                .content(content)
                .contentType(MediaType.APPLICATION_JSON_UTF8));
        MockHttpServletResponse mockResponse = resultActions.andReturn()
                .getResponse();
        
        
        System.out.println(mockResponse.getContentAsString());
        assertEquals(HttpStatus.OK.value(), mockResponse.getStatus());
        
      //check for ascending sorting by Departure time
        
        JSONArray responseArray = new JSONArray(mockResponse.getContentAsString());
        ObjectMapper mapper = new ObjectMapper();
        
        for(int index = 0; index < responseArray.length(); index++) {
        	FlightResponse flightResponse = mapper.readValue(responseArray.get(index).toString(), FlightResponse.class);
        	assertTrue(previousDepartureTime <= flightResponse.getDepartureTime());
        	previousDepartureTime = flightResponse.getDepartureTime();
        }
    }
    
    @Test
    public void getAllResponseWithDepartureFilter() throws Exception {
    	
    	String content = "{\"departure\":\"Cruz del Eje\"}";
    	
    	ResultActions resultActions = mockMvc.perform(post(URL)
                .content(content)
                .contentType(MediaType.APPLICATION_JSON_UTF8));
        MockHttpServletResponse mockResponse = resultActions.andReturn()
                .getResponse();
        
        System.out.println(mockResponse.getContentAsString());
        assertEquals(HttpStatus.OK.value(), mockResponse.getStatus());
        
        JSONArray responseArray = new JSONArray(mockResponse.getContentAsString());
        ObjectMapper mapper = new ObjectMapper();
        
        for(int index = 0; index < responseArray.length(); index++) {
        	FlightResponse flightResponse = mapper.readValue(responseArray.get(index).toString(), FlightResponse.class);
        	assertEquals("Cruz del Eje",flightResponse.getDeparture());
        }
        
    }
    
    @Test
    public void getAllResponseWithArrivalFilter() throws Exception {
    	
    	String content = "{\"arrival\":\"Antalya\"}";
    	
    	ResultActions resultActions = mockMvc.perform(post(URL)
                .content(content)
                .contentType(MediaType.APPLICATION_JSON_UTF8));
        MockHttpServletResponse mockResponse = resultActions.andReturn()
                .getResponse();
        
        System.out.println(mockResponse.getContentAsString());
        assertEquals(HttpStatus.OK.value(), mockResponse.getStatus());
        
        JSONArray responseArray = new JSONArray(mockResponse.getContentAsString());
        ObjectMapper mapper = new ObjectMapper();
        
        for(int index = 0; index < responseArray.length(); index++) {
        	FlightResponse flightResponse = mapper.readValue(responseArray.get(index).toString(), FlightResponse.class);
        	assertEquals("Antalya",flightResponse.getArrival());
        }
        
    }
   
    @Test
    public void getAllResponseWithDepartureTimeFilter() throws Exception {
    	
    	String content = "{"
    			+ "			\"departureTimeRange\":"
    			+ "		{"
    			+ "			\"startTime\": 1558902655,"
    			+ "			\"endTime\": 1564229988"
    			+ "		}"
    			+ " }";
    	
    	ResultActions resultActions = mockMvc.perform(post(URL)
                .content(content)
                .contentType(MediaType.APPLICATION_JSON_UTF8));
        MockHttpServletResponse mockResponse = resultActions.andReturn()
                .getResponse();
        
        System.out.println(mockResponse.getContentAsString());
        assertEquals(HttpStatus.OK.value(), mockResponse.getStatus());
        
        JSONArray responseArray = new JSONArray(mockResponse.getContentAsString());
        ObjectMapper mapper = new ObjectMapper();
        
        for(int index = 0; index < responseArray.length(); index++) {
        	FlightResponse flightResponse = mapper.readValue(responseArray.get(index).toString(), FlightResponse.class);
        	assertTrue(flightResponse.getDepartureTime() > 1558902655);
        	assertTrue(flightResponse.getDepartureTime() < 1564229988);
        }
        
    }
    
    @Test
    public void getAllResponseWithArrivalTimeFilter() throws Exception {
    	
    	String content = "{"
    			+ "			\"arrivalTimeRange\":"
    			+ "		{"
    			+ "			\"startTime\": 1564410655,"
    			+ "			\"endTime\": 1564489188"
    			+ "		}"
    			+ " }";
    	
    	ResultActions resultActions = mockMvc.perform(post(URL)
                .content(content)
                .contentType(MediaType.APPLICATION_JSON_UTF8));
        MockHttpServletResponse mockResponse = resultActions.andReturn()
                .getResponse();
        
        System.out.println(mockResponse.getContentAsString());
        assertEquals(HttpStatus.OK.value(), mockResponse.getStatus());
        
        JSONArray responseArray = new JSONArray(mockResponse.getContentAsString());
        ObjectMapper mapper = new ObjectMapper();
        
        for(int index = 0; index < responseArray.length(); index++) {
        	FlightResponse flightResponse = mapper.readValue(responseArray.get(index).toString(), FlightResponse.class);
        	assertTrue(flightResponse.getArrivalTime() > 1564410655);
        	assertTrue(flightResponse.getArrivalTime() < 1564489188);
        }
        
    }
}
