package com.demo.dao;

import java.util.List;

import com.demo.model.Weather;
import com.demo.model.WeatherDetail;

public interface WeatherDao {

	Integer insertData(Weather weather);

	void createOrderItems(Integer weatherId, List<WeatherDetail> detailList);

}
