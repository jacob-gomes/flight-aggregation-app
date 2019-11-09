package com.tokigames.flight.aggregation.service.util.conversion;

import java.util.ArrayList;
import java.util.List;

import com.tokigames.flight.aggregation.constants.FlightClassEnumerator;
import com.tokigames.flight.aggregation.exception.FlightAggregationException;
import com.tokigames.flight.aggregation.service.model.CheapFlightApiResponse;
import com.tokigames.flight.aggregation.service.model.CheapFlightApiResponse.Data;
import com.tokigames.flight.aggregation.service.model.FlightModel;

public class ConvertCheapFlightApiResponseToFlightModelList {
	
	private ConvertCheapFlightApiResponseToFlightModelList() {}

	public static List<FlightModel> convert(CheapFlightApiResponse cheapFlightApiResponse) {
	List<FlightModel> flightModels = new ArrayList<>();
		
		if(null != cheapFlightApiResponse 
				&& null != cheapFlightApiResponse.getData()
				&& !cheapFlightApiResponse.getData().isEmpty()) {
			
			cheapFlightApiResponse.getData().forEach((data) ->{
				FlightModel flightModel = dataToFlightModel(data);				
				flightModels.add(flightModel);
			});
		
		}
		
		return flightModels;
	}

	private static FlightModel dataToFlightModel(Data data) {
		FlightModel flightModel = new FlightModel();
		String[] soruceDestinationArray = data.getRoute().split("-");
		
		flightModel.setFlightClass(FlightClassEnumerator.CHEAP);		
		flightModel.setArrivalTime(data.getArrival());
		flightModel.setDepartureTime(data.getDeparture());
		
		try {
			flightModel.setDeparture(soruceDestinationArray[0]);
			flightModel.setArrival(soruceDestinationArray[1]);
		}catch(ArrayIndexOutOfBoundsException e) {
			throw new FlightAggregationException("Source-Destination not properly specified");
		}
		
		return flightModel;
	}

}
