package com.tokigames.flight.aggregation.service.model;

import java.math.BigDecimal;
import java.util.List;


public class CheapFlightApiResponse {
	private List<Data> data;
	
	public static class Data{
		private String route;
		
		private BigDecimal departure;
		
		private BigDecimal arrival;

		/**
		 * @return the route
		 */
		public String getRoute() {
			return route;
		}

		/**
		 * @param route the route to set
		 */
		public void setRoute(String route) {
			this.route = route;
		}

		/**
		 * @return the departure
		 */
		public BigDecimal getDeparture() {
			return departure;
		}

		/**
		 * @param departure the departure to set
		 */
		public void setDeparture(BigDecimal departure) {
			this.departure = departure;
		}

		/**
		 * @return the arrival
		 */
		public BigDecimal getArrival() {
			return arrival;
		}

		/**
		 * @param arrival the arrival to set
		 */
		public void setArrival(BigDecimal arrival) {
			this.arrival = arrival;
		}
		
		
	}

	/**
	 * 
	 * @return
	 */
	public List<Data> getData() {
		return data;
	}

	/**
	 * 
	 * @param data
	 */
	public void setData(List<Data> data) {
		this.data = data;
	}
}
