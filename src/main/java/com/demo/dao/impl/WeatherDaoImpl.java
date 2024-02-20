package com.demo.dao.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import com.demo.dao.WeatherDao;
import com.demo.model.Weather;
import com.demo.model.WeatherDetail;

@Component
public class WeatherDaoImpl implements WeatherDao {

	@Autowired
	private NamedParameterJdbcTemplate template;

	@Override
	public Integer insertData(Weather data) {
		String sql = "insert into weather(record_id,record_name,weather_api_type,create_date,last_modified_date) "
				+ "values(:recordId,:recordName,:weatherApiType,:createDate,:lastModifiedDate)";
		Map<String, Object> map = new HashMap<String, Object>();

		map.put("recordId", data.getRecordId());
		map.put("recordName", data.getRecordName());
		map.put("weatherApiType", data.getWeatherApiType());

		Date now = new Date();

		map.put("createDate", now);
		map.put("lastModifiedDate", now);

		KeyHolder holder = new GeneratedKeyHolder();
		template.update(sql, new MapSqlParameterSource(map), holder);
		int productId = holder.getKey().intValue();
		return productId;

	}

	@Override
	public void createOrderItems(Integer weatherId, List<WeatherDetail> detailList) {
		String sql = "insert into weather_detail (record_id,element_name,parameter_name,parameter_unit,parameter_value,start_time,end_time,time_key,create_date,last_modified_date)"
				+ " values(:recordId,:elementName,:parameterName,:parameterUnit,:parameterValue,:startTime,:endTime,:timeKey,:createDate,:lastModifiedDate)";
		
		
		MapSqlParameterSource[] parameterSources=new MapSqlParameterSource[detailList.size()];
		for(int i=0; i<detailList.size();i++) {
			WeatherDetail weatherDetail=detailList.get(i);
			
			parameterSources[i]=new MapSqlParameterSource();
			parameterSources[i].addValue("recordId", weatherDetail.getRecordId());
			parameterSources[i].addValue("elementName",weatherDetail.getElementName());
			parameterSources[i].addValue("parameterName", weatherDetail.getParameterName());
			parameterSources[i].addValue("parameterUnit", weatherDetail.getParameterUnit());
			parameterSources[i].addValue("parameterValue", weatherDetail.getParameterValue());
			parameterSources[i].addValue("startTime", weatherDetail.getStartTime());
			parameterSources[i].addValue("endTime", weatherDetail.getEndTime());
			parameterSources[i].addValue("timeKey", weatherDetail.getTimeKey());
			
			
			Date now = new Date();

			parameterSources[i].addValue("createDate", now);
			parameterSources[i].addValue("lastModifiedDate", now);
			
		}
		template.batchUpdate(sql, parameterSources);
	}

}
