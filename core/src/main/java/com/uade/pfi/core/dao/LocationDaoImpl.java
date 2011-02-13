package com.uade.pfi.core.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.uade.pfi.core.TransportLocation;

public class LocationDaoImpl implements LocationDao {
	private JdbcTemplate template;

	public List<TransportLocation> retrieveLocations() {
		return template.query("select * from locations", new RowMapper<TransportLocation>(){

			public TransportLocation mapRow(ResultSet rs, int rowNum)
					throws SQLException {
				return new TransportLocation(rs.getFloat("LATITUDE"), rs.getFloat("LONGITUDE"), rs.getString("NAME"));
			}
		});
	}

	public void storeLocation(TransportLocation location) {
		template.update("insert into locations (name, latitude, longitude) values (?,?,?)", location.getName(),location.getLocation().getLatitude(),location.getLocation().getLongitude());
	}

	
	public void setTemplate(JdbcTemplate template) {
		this.template = template;
	}
	public JdbcTemplate getTemplate() {
		return template;
	}
}
