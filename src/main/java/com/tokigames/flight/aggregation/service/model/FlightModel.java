package com.tokigames.flight.aggregation.service.model;

import java.math.BigDecimal;

import com.tokigames.flight.aggregation.constants.FlightClassEnumerator;

public class FlightModel {

	private String departure;
	
	private String arrival;
	
	private BigDecimal departureTime;
	
	private BigDecimal arrivalTime;
	
	private FlightClassEnumerator flightClass;

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

	/**
	 * @return the flightClass
	 */
	public FlightClassEnumerator getFlightClass() {
		return flightClass;
	}

	/**
	 * @param flightClass the flightClass to set
	 */
	public void setFlightClass(FlightClassEnumerator flightClass) {
		this.flightClass = flightClass;
	}
	
	
}
