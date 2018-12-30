package com.bookShop.onlineShop.storehouse.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class Book implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private long bookId;
	private String title;
	private String author;
	private int categoryId;
	private Date publishedDate;
	private Registrant registrant;
	private List<Chapter> chapterList;
	
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
	
	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}
	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	
	/**
	 * @return the author
	 */
	public String getAuthor() {
		return author;
	}
	/**
	 * @param author the author to set
	 */
	public void setAuthor(String author) {
		this.author = author;
	}
	
	/**
	 * @return the categoryId
	 */
	public int getCategoryId() {
		return categoryId;
	}
	/**
	 * @param categoryId the categoryId to set
	 */
	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}
	/**
	 * @return the publishedDate
	 */
	public Date getPublishedDate() {
		return publishedDate;
	}
	/**
	 * @param publishedDate the publishedDate to set
	 */
	public void setPublishedDate(Date publishedDate) {
		this.publishedDate = publishedDate;
	}
	/**
	 * @return the registrant
	 */
	public Registrant getRegistrant() {
		return registrant;
	}
	/**
	 * @param registrant the registrant to set
	 */
	public void setRegistrant(Registrant registrant) {
		this.registrant = registrant;
	}
	/**
	 * @return the chapterList
	 */
	public List<Chapter> getChapterList() {
		return chapterList;
	}
	/**
	 * @param chapterList the chapterList to set
	 */
	public void setChapterList(List<Chapter> chapterList) {
		this.chapterList = chapterList;
	}
	
}
