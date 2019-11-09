package com.tokigames.flight.aggregation.service.util.conversion;

import java.util.ArrayList;
import java.util.List;

import com.tokigames.flight.aggregation.constants.FlightClassEnumerator;
import com.tokigames.flight.aggregation.service.model.BusinessFlightApiResponse;
import com.tokigames.flight.aggregation.service.model.BusinessFlightApiResponse.Data;
import com.tokigames.flight.aggregation.service.model.FlightModel;

public class ConvertBusinessFlightApiResponseToFlightModelList {

	private ConvertBusinessFlightApiResponseToFlightModelList() {}
	
	public static List<FlightModel> convert(BusinessFlightApiResponse businessFlightApiResponse) {
		List<FlightModel> flightModels = new ArrayList<>();
		
		if(null != businessFlightApiResponse 
				&& null != businessFlightApiResponse.getData()
				&& !businessFlightApiResponse.getData().isEmpty()) {
			
			businessFlightApiResponse.getData().forEach((data) ->{
				FlightModel flightModel = dataToFlightModel(data);				
				flightModels.add(flightModel);
			});
		
		}
		
		return flightModels;
	}

	private static FlightModel dataToFlightModel(Data data) {
		FlightModel flightModel = new FlightModel();
		
		flightModel.setFlightClass(FlightClassEnumerator.BUSINESS);
		flightModel.setArrival(data.getArrival());
		flightModel.setDeparture(data.getDeparture());
		flightModel.setArrivalTime(data.getArrivalTime());
		flightModel.setDepartureTime(data.getDepartureTime());
		
		
		return flightModel;
	}

}
