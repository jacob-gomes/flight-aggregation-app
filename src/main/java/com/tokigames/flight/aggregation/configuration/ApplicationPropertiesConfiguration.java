package com.tokigames.flight.aggregation.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ApplicationPropertiesConfiguration {

	@Value("${cheap.flight.url}")
	private String cheapFlightUrl;
	
	@Value("${business.flight.url}")
	private String businessFlightUrl;

	/**
	 * @return the cheapFlightUrl
	 */
	public String getCheapFlightUrl() {
		return cheapFlightUrl;
	}

	/**
	 * @param cheapFlightUrl the cheapFlightUrl to set
	 */
	public void setCheapFlightUrl(String cheapFlightUrl) {
		this.cheapFlightUrl = cheapFlightUrl;
	}

	/**
	 * @return the businessFlightUrl
	 */
	public String getBusinessFlightUrl() {
		return businessFlightUrl;
	}

	/**
	 * @param businessFlightUrl the businessFlightUrl to set
	 */
	public void setBusinessFlightUrl(String businessFlightUrl) {
		this.businessFlightUrl = businessFlightUrl;
	}
	
	
}
