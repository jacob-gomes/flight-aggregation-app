package com.tokigames.flight.aggregation.controller.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.tokigames.flight.aggregation.constants.FlightClassEnumerator;

@JsonInclude(Include.NON_NULL)
public class FlightFilterRequest {
	
	private String departure;
	
	private String arrival;
	
	private TimeRange departureTimeRange;
	
	private TimeRange arrivalTimeRange;
	
	private FlightClassEnumerator flyingClass;
	
	public static class TimeRange{
		private Long startTime;
		
		private Long endTime;

		/**
		 * @return the startTime
		 */
		public Long getStartTime() {
			return startTime;
		}

		/**
		 * @param startTime the startTime to set
		 */
		public void setStartTime(Long startTime) {
			this.startTime = startTime;
		}

		/**
		 * @return the endTime
		 */
		public Long getEndTime() {
			return endTime;
		}

		/**
		 * @param endTime the endTime to set
		 */
		public void setEndTime(Long endTime) {
			this.endTime = endTime;
		}
		
		
	}


	/**
	 * @return the flyingClass
	 */
	public FlightClassEnumerator getFlyingClass() {
		return flyingClass;
	}

	/**
	 * @param flyingClass the flyingClass to set
	 */
	public void setFlyingClass(FlightClassEnumerator flyingClass) {
		this.flyingClass = flyingClass;
	}

	/**
	 * @return the departureTimeRange
	 */
	public TimeRange getDepartureTimeRange() {
		return departureTimeRange;
	}

	/**
	 * @param departureTimeRange the departureTimeRange to set
	 */
	public void setDepartureTimeRange(TimeRange departureTimeRange) {
		this.departureTimeRange = departureTimeRange;
	}

	/**
	 * @return the arrivalTimeRange
	 */
	public TimeRange getArrivalTimeRange() {
		return arrivalTimeRange;
	}

	/**
	 * @param arrivalTimeRange the arrivalTimeRange to set
	 */
	public void setArrivalTimeRange(TimeRange arrivalTimeRange) {
		this.arrivalTimeRange = arrivalTimeRange;
	}

	public String getDeparture() {
		return departure;
	}

	public void setDeparture(String departure) {
		this.departure = departure;
	}

	public String getArrival() {
		return arrival;
	}

	public void setArrival(String arrival) {
		this.arrival = arrival;
	} 
	
	
}
