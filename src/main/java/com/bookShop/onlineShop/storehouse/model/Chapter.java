package com.bookShop.onlineShop.storehouse.model;

import java.io.Serializable;

public class Chapter implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private int chapterSeq;
	private String chapterTitle;
	private int pageNum;
	
	/**
	 * @return the chapterSeq
	 */
	public int getChapterSeq() {
		return chapterSeq;
	}
	/**
	 * @param chapterSeq the chapterSeq to set
	 */
	public void setChapterSeq(int chapterSeq) {
		this.chapterSeq = chapterSeq;
	}
	/**
	 * @return the chapterTitle
	 */
	public String getChapterTitle() {
		return chapterTitle;
	}
	/**
	 * @param chapterTitle the chapterTitle to set
	 */
	public void setChapterTitle(String chapterTitle) {
		this.chapterTitle = chapterTitle;
	}
	/**
	 * @return the pageNum
	 */
	public int getPageNum() {
		return pageNum;
	}
	/**
	 * @param pageNum the pageNum to set
	 */
	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}
}
