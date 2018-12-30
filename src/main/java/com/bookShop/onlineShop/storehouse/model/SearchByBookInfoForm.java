package com.bookShop.onlineShop.storehouse.model;

import java.io.Serializable;
import javax.validation.constraints.Pattern;

import com.bookShop.onlineShop.storehouse.validation.*;

@ComparesDate(oldDate="fromDate", newDate="toDate", message="終了日が開始日より前")
public class SearchByBookInfoForm implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String bookTitle;
	private String bookAuthor;
	
	@Pattern(regexp="^(\\d{8})|.{0}$", message="YYYYMMDD形式で日付を入力してください。")
	private String fromDate;
	
	@Pattern(regexp="^(\\d{8})|.{0}$", message="YYYYMMDD形式で日付を入力してください。")
	private String toDate;
	
	/**
	 * @return the bookTitle
	 */
	public String getBookTitle() {
		return bookTitle;
	}
	/**
	 * @param bookTitle the bookTitle to set
	 */
	public void setBookTitle(String bookTitle) {
		this.bookTitle = bookTitle;
	}
	/**
	 * @return the bookAuthor
	 */
	public String getBookAuthor() {
		return bookAuthor;
	}
	/**
	 * @param bookAuthor the bookAuthor to set
	 */
	public void setBookAuthor(String bookAuthor) {
		this.bookAuthor = bookAuthor;
	}
	/**
	 * @return the fromDate
	 */
	public String getFromDate() {
		return fromDate;
	}
	/**
	 * @param fromDate the fromDate to set
	 */
	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}
	/**
	 * @return the toDate
	 */
	public String getToDate() {
		return toDate;
	}
	/**
	 * @param toDate the toDate to set
	 */
	public void setToDate(String toDate) {
		this.toDate = toDate;
	}

}
