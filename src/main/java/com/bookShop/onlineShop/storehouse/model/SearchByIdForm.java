package com.bookShop.onlineShop.storehouse.model;

import java.io.Serializable;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Digits;

public class SearchByIdForm implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@NotNull
	@Digits(fraction=0, integer=5)
	private long bookId;

	/**
	 * @return the bookId
	 */
	public long getBookId() {
		return bookId;
	}

	/**
	 * @param bookId the bookId to set
	 */
	public void setBookId(long bookId) {
		this.bookId = bookId;
	}
	
}
