package com.bookShop.onlineShop.storehouse.controller;

import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.dao.DuplicateKeyException;

import com.bookShop.onlineShop.storehouse.model.*;

import java.util.Date;
import java.util.List;

@Controller
@Transactional
public class BookRepositoryManager {
	
	private BookRepositoryDAO dao;
	
	@Autowired
	public BookRepositoryManager(JdbcTemplate jdbcTemp, NamedParameterJdbcTemplate namedParamJdbcTemp) {
		dao = new BookRepositoryDAO(jdbcTemp, namedParamJdbcTemp);
	}
	
	// 登録者IDとともに書籍登録
	public int insertBook(String title, String author, int categoryId, Date publishedDate, int registrantId) throws DuplicateKeyException {
		Book book = new Book();
		book.setTitle(title);
		book.setAuthor(author);
		book.setCategoryId(categoryId);
		book.setPublishedDate(publishedDate);
		Registrant registrant = new Registrant();
		registrant.setId(registrantId);
		book.setRegistrant(registrant);
		return dao.insertBookWithRegistrantId(book);
	}
	
	// 登録者IDなしで書籍登録
	public int insertBook(String title, String author, int categoryId, Date publishedDate) throws DuplicateKeyException {
		Book book = new Book();
		book.setTitle(title);
		book.setAuthor(author);
		book.setCategoryId(categoryId);
		book.setPublishedDate(publishedDate);
		return dao.insertBookWithoutRegistrantId(book);
	}
	
	// 書籍IDで書籍検索
	public Book searchBookById(long bookId) throws EmptyResultDataAccessException {
		return dao.searchBookById(bookId);
	}
	
	// 書籍情報で書籍リスト検索
	public List<Book> searchBookByBookInfo(SearchByBookInfoCondition condition) {
		List<Book> bookList = dao.searchBookByBookInfo(condition.getBookTitle(),
					condition.getBookAuthor(), condition.getFromDate(), condition.getToDate());
		for (Book book : bookList) {
			List<Chapter> chapterList = dao.searchBookChapterListById(book.getBookId());
			book.setChapterList(chapterList);
		}
		return bookList;
	}
	
	
	// 全カテゴリーリスト検索
	public List<Category> getAllCategory() {
		return dao.getAllCategory();
	}
	
	// カテゴリー登録
	public int insertCategory(String categoryName) {
		return dao.insertCategory(categoryName);
	}
	
	// カテゴリー削除
	public int deleteCategory(int categoryId) {
		return dao.deleteCategory(categoryId);
	}
}
