package com.demo.service.impl;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.demo.constant.RecordData;
import com.demo.dao.WeatherDao;
import com.demo.dto.WeatherData;
import com.demo.dto.WeatherData.LocationData;
import com.demo.dto.WeatherData.Parameter;
import com.demo.dto.WeatherData.Time;
import com.demo.dto.WeatherData.WeatherDataRecord;
import com.demo.dto.WeatherData.WeatherElement;
import com.demo.model.Weather;
import com.demo.model.WeatherDetail;
import com.demo.service.WeatherService;
import com.demo.utils.WeatherApiClient;

@Service
public class WeatherServiceImpl implements WeatherService {

	private final static Logger log = LoggerFactory.getLogger(WeatherService.class);
	@Autowired
	private WeatherApiClient weatherApiClient;
	@Autowired
	private WeatherDao weatherDao;

	@Transactional
	@Override
	public void getWeather() {
		log.info("getWeather");
		WeatherData weatherData = weatherApiClient.getWeatherData("F-C0032-001");
		System.out.println(weatherData);
		
		List<Weather> weatherList=getWeatherInsertData(weatherData);
		for(Weather weather :weatherList) {
			Integer weatherId= weatherDao.insertData(weather);
			List<WeatherDetail> detailList=weather.getWeatherItemList();
			weatherDao.createOrderItems(weatherId,detailList);
			
		}

	}

	private List<Weather> getWeatherInsertData(WeatherData weatherData) {
		WeatherDataRecord recordData = weatherData.getRecords();

		List<Weather> weatherList = new ArrayList<Weather>();

		for (LocationData location : recordData.getLocation()) {
			Weather weather = new Weather();
			String locationName = location.getLocationName();

			RecordData record = RecordData.valueOf(locationName);
			weather.setRecordName(locationName);
			weather.setRecordId(record.ordinal());
			weather.setWeatherApiType("weather_36HR");
			List<WeatherDetail> weatherDetailList = new ArrayList<WeatherDetail>();
			for (WeatherElement weatherElement : location.getWeatherElement()) {
				String element = weatherElement.getElementName();

				for (Time time : weatherElement.getTimes()) {
					WeatherDetail weatherDetail = new WeatherDetail();
					weatherDetail.setRecordId(record.ordinal());
					weatherDetail.setElementName(element);
					Parameter parameter = time.getParameter();
					weatherDetail.setParameterName(parameter.getParameterName());
					weatherDetail.setParameterUnit(parameter.getParameterUnit());
					weatherDetail.setParameterValue(parameter.getParameterValue());

					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
					DateFormat dateFormat=new SimpleDateFormat("yyyyMMddhhmm");
					
					try {
						Date startTime = sdf.parse(time.getStartTime());
						weatherDetail.setStartTime(startTime);
						Date endTime = sdf.parse(time.getEndTime());
						weatherDetail.setEndTime(endTime);
						Date keyData = sdf.parse(time.getStartTime());
						weatherDetail.setTimeKey(dateFormat.format(keyData));
					} catch (ParseException e) {
						log.error("Error:{}",e.getMessage());
						e.printStackTrace();
					}
					weatherDetailList.add(weatherDetail);
				}
			}
			weather.setWeatherItemList(weatherDetailList);
			weatherList.add(weather);
		}
		return weatherList;
	}

}
