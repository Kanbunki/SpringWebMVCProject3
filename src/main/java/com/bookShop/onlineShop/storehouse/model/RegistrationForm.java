package com.bookShop.onlineShop.storehouse.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import javax.validation.constraints.Pattern;
import javax.validation.groups.Default;

import org.springframework.format.annotation.DateTimeFormat;

import com.bookShop.onlineShop.storehouse.validation.SameValue;

@SameValue(firstValue="registrantPassword", secondValue="confirmPassword", message="パスワードが一致できませんでした") 
public class RegistrationForm implements Serializable {

	private static final long serialVersionUID = 1L;
	
	public static interface NewRegistrant extends Default {}
	public static interface OldRegistrant extends Default {}
	
	@NotEmpty
	@Size(max=128)
	private String bookTitle;
	
	@NotEmpty
	@Size(max=4)
	private String bookAuthor;
	
	private int categoryId;
	
	private List<Category> categoryList;
	
	@DateTimeFormat(pattern = "yyyyMMdd")
	private Date publishedDate;
	
	@Size(min=3, max=3)
	private String registrantType;
	
	@Size(min=3, max=20, groups=NewRegistrant.class, message="新規登録者の名前を入力してください")
	@Size(max=0, groups=OldRegistrant.class, message="既存登録者は名前を入力しないでください")
	private String registrantName;
	
	@Pattern(regexp="[0-9a-zA-Z]{8}", groups=NewRegistrant.class)
	@Size(max=0, groups=OldRegistrant.class, message="既存登録者はパスワードを入力しないでください")
	private String registrantPassword;
	
	@NotEmpty(groups=NewRegistrant.class)
	private String confirmPassword;
	
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
	 * @return the categoryList
	 */
	public List<Category> getCategoryList() {
		return categoryList;
	}
	
	/**
	 * @param categoryList the categoryList to set
	 */
	public void setCategoryList(List<Category> categoryList) {
		this.categoryList = categoryList;
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
	 * @return the registrantType
	 */
	public String getRegistrantType() {
		return registrantType;
	}
	/**
	 * @param registrantType the registrantType to set
	 */
	public void setRegistrantType(String registrantType) {
		this.registrantType = registrantType;
	}
	/**
	 * @return the registrantName
	 */
	public String getRegistrantName() {
		return registrantName;
	}
	/**
	 * @param registrant the registrantName to set
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
	/**
	 * @return the confirmPassword
	 */
	public String getConfirmPassword() {
		return confirmPassword;
	}
	/**
	 * @param confirmPassword the confirmPassword to set
	 */
	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

}
