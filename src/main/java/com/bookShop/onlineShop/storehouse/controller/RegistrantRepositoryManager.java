package com.bookShop.onlineShop.storehouse.controller;

import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import com.bookShop.onlineShop.storehouse.model.*;

@Controller
@Transactional
public class RegistrantRepositoryManager {
	
	private RegistrantRepositoryDAO dao;
	
	@Autowired
	public RegistrantRepositoryManager(JdbcTemplate jdbcTemp, NamedParameterJdbcTemplate namedParamJdbcTemp) {
		this.dao = new RegistrantRepositoryDAO(jdbcTemp, namedParamJdbcTemp);
	}
	
	public int insertRegistrant(String username, String password) throws DuplicateKeyException {
		Registrant registrant = new Registrant(username, password);
		return dao.insertRegistrant(registrant);
	}
}
