package com.demo.model;

import java.util.Date;
import java.util.List;

import lombok.Data;

@Data
public class Weather {
	private Integer weatherId;
	private Integer recordId;
	private String recordName;
	private String weatherDate;
	private String weatherApiType;
	private String memo;
	private Date createDate;
	private Date lastModifiedDate;
	private List<WeatherDetail> weatherItemList;

	
}
