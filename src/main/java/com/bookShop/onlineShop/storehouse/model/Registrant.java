package com.bookShop.onlineShop.storehouse.model;

import java.io.Serializable;

public class Registrant implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private int id;
	private String registrantName;
	private String registrantPassword;
	
	public Registrant() {}
	
	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	public Registrant(String username, String password) {
		this.registrantName = username;
		this.registrantPassword = password;
	}
	
	/**
	 * @return the registrantName
	 */
	public String getRegistrantName() {
		return registrantName;
	}
	/**
	 * @param registrantName the registrantName to set
	 */
	public void setRegistrantName(String registrantName) {
		this.registrantName = registrantName;
	}
	/**
	 * @return the registrantPassword
	 */
	public String getRegistrantPassword() {
		return registrantPassword;
	}
	/**
	 * @param registrantPassword the registrantPassword to set
	 */
	public void setRegistrantPassword(String registrantPassword) {
		this.registrantPassword = registrantPassword;
	}
}
