package com.bookShop.onlineShop.storehouse.model;

import java.util.List;
import java.io.Serializable;
import javax.validation.constraints.NotBlank;

public class CategoryForm implements Serializable {

	private static final long serialVersionUID = -76399885413482541L;
	
	private List<Category> categoryList;
	@NotBlank
	private String categoryName;
	
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
	 * @return the categoryName
	 */
	public String getCategoryName() {
		return categoryName;
	}
	
	/**
	 * @param categoryName the categoryName to set
	 */
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
}
