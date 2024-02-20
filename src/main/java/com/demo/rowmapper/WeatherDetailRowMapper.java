package com.demo.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.demo.model.WeatherDetail;

public class WeatherDetailRowMapper implements RowMapper<WeatherDetail> {

	@Override
	public WeatherDetail mapRow(ResultSet rs, int rowNum) throws SQLException {
		WeatherDetail weatherItem = new WeatherDetail();
		weatherItem.setItemId(rs.getInt("item_id"));
		weatherItem.setRecordId(rs.getInt("record_id"));
		weatherItem.setTimeKey(rs.getString("time_key"));
		weatherItem.setElementName(rs.getString("element_name"));
		weatherItem.setParameterName(rs.getString("parameter_name"));
		weatherItem.setParameterUnit(rs.getString("parameter_unit"));
		weatherItem.setStartTime(rs.getTimestamp("start_time"));
		weatherItem.setEndTime(rs.getTimestamp("endTime"));
		weatherItem.setCreateDate(rs.getTimestamp("create_date"));
		weatherItem.setLastModifiedDate(rs.getTimestamp("last_modified_date"));

	
		
		return weatherItem;
	}

}
