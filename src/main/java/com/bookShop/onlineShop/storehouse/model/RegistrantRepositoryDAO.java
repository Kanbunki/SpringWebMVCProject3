package com.bookShop.onlineShop.storehouse.model;

import java.util.Map;
import java.util.HashMap;

import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

public class RegistrantRepositoryDAO {

	private JdbcTemplate jdbcTemp;
	private NamedParameterJdbcTemplate namedParamJdbcTemp;
	
	public RegistrantRepositoryDAO(JdbcTemplate jdbcTemp, NamedParameterJdbcTemplate namedParamJdbcTemp) {
		this.jdbcTemp = jdbcTemp;
		this.namedParamJdbcTemp = namedParamJdbcTemp;
	}
	
	public int insertRegistrant(Registrant registrant) throws DuplicateKeyException {
		String sql = "INSERT INTO registrant (registrant_name, registrant_password) "
				   + "VALUES(:username, :password)";
		Map<String, Object> paramsMap = new HashMap<String, Object>();
		paramsMap.put("username", registrant.getRegistrantName());
		paramsMap.put("password", registrant.getRegistrantPassword());
		namedParamJdbcTemp.update(sql, paramsMap);
		return getRegistrantIdByName(registrant.getRegistrantName());
	}
	
	public int getRegistrantIdByName(String username) {
		String sql = "SELECT registrant_id FROM registrant "
				   + "WHERE registrant_name = ?";
		return jdbcTemp.queryForObject(sql, Integer.class, username);
	}
}
