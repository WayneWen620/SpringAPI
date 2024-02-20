package com.demo.model;

import java.util.Date;

import lombok.Data;
@Data
public class WeatherDetail {
	private Integer itemId;
	private Integer recordId;
	private String timeKey;
	private String elementName;
	private String parameterName;
	private String parameterUnit;
	private String parameterValue;
	private Date startTime;
	private Date endTime;
	private Date createDate;
	private Date lastModifiedDate;
	
}
