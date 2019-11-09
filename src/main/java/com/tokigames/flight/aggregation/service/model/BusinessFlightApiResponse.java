package com.tokigames.flight.aggregation.service.model;

import java.math.BigDecimal;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class BusinessFlightApiResponse {
	private List<Data> data;
	


	public static class Data{
		private String departure;
		
		private String arrival;
		
		private BigDecimal departureTime;
		
		private BigDecimal arrivalTime;

		/**
		 * @return the departure
		 */
		public String getDeparture() {
			return departure;
		}

		/**
		 * @param departure the departure to set
		 */
		public void setDeparture(String departure) {
			this.departure = departure;
		}

		/**
		 * @return the arrival
		 */
		public String getArrival() {
			return arrival;
		}

		/**
		 * @param arrival the arrival to set
		 */
		public void setArrival(String arrival) {
			this.arrival = arrival;
		}

		/**
		 * @return the departureTime
		 */
		public BigDecimal getDepartureTime() {
			return departureTime;
		}

		/**
		 * @param departureTime the departureTime to set
		 */
		public void setDepartureTime(BigDecimal departureTime) {
			this.departureTime = departureTime;
		}

		/**
		 * @return the arrivalTime
		 */
		public BigDecimal getArrivalTime() {
			return arrivalTime;
		}

		/**
		 * @param arrivalTime the arrivalTime to set
		 */
		public void setArrivalTime(BigDecimal arrivalTime) {
			this.arrivalTime = arrivalTime;
		}

	}
	
	/**
	 * @return the data
	 */
	public List<Data> getData() {
		return data;
	}

	/**
	 * @param data the data to set
	 */
	public void setData(List<Data> data) {
		this.data = data;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		ObjectMapper mapper = new ObjectMapper();		
		
		try {
			return mapper.writeValueAsString(this);
		} catch (JsonProcessingException e) {
			return "";
		}
	}
}
