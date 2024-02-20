package com.demo.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class WeatherData {
	@JsonProperty("success")
	private String success;
	private Object result;
	@JsonProperty("records")
	private WeatherDataRecord records;

	@Data
	public static class WeatherDataRecord {
		@JsonProperty("datasetDescription")
		private String datasetDescription;
	
		private List<LocationData> location;

	}

	@Data
	@NoArgsConstructor
	public static class LocationData {
		@JsonProperty("locationName")
		private String locationName;
		@JsonProperty("weatherElement")
		private List<WeatherElement> weatherElement;
	}

	@Data
	@NoArgsConstructor
	public static class WeatherElement {
		@JsonProperty("elementName")
		private String elementName;
		@JsonProperty("time")
		private List<Time> times;
	}

	@Data
	@NoArgsConstructor
	public static class Time {
		@JsonProperty("startTime")
		private String startTime;
		@JsonProperty("endTime")
		private String endTime;
		@JsonProperty("parameter")
		private Parameter parameter;
	}

	@Data
	public static class Parameter {
		@JsonProperty("parameterName")
		private String parameterName;
		@JsonProperty("parameterUnit")
		private String parameterUnit;
		@JsonProperty("parameterValue")
		private String parameterValue;
	}
}
