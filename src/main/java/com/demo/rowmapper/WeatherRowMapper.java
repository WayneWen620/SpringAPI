package com.demo.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.demo.model.Weather;

public class WeatherRowMapper implements RowMapper<Weather> {

	@Override
	public Weather mapRow(ResultSet rs, int rowNum) throws SQLException {
		Weather weather = new Weather();
		weather.setWeatherId(rs.getInt("weather_id"));
		weather.setRecordId(rs.getInt("record_id"));
		weather.setRecordName(rs.getString("record_name"));
		weather.setMemo(rs.getString("memo"));
		weather.setCreateDate(rs.getTimestamp("create_date"));
		weather.setLastModifiedDate(rs.getTimestamp("last_modified_date"));

	
		return weather;
	}

}
