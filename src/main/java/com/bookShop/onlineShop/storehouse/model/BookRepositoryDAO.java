package com.bookShop.onlineShop.storehouse.model;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.dao.DuplicateKeyException;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

public class BookRepositoryDAO {

	private JdbcTemplate jdbcTemplate;
	
	private NamedParameterJdbcTemplate namedParamJdbcTemplate;
	
	public BookRepositoryDAO(JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParamJdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
		this.namedParamJdbcTemplate = namedParamJdbcTemplate;
	}
	
	// 登録者IDとともに書籍登録
	public int insertBookWithRegistrantId(Book book) throws DuplicateKeyException {
		String sql = "INSERT INTO book(book_title, book_author, category_id, published_date, registrant_id) "
				   + "VALUES(:title, :author, :categoryId, :publishedDate, :registrantId)";
		Map<String, Object> paramsMap = new HashMap<String, Object>();
		paramsMap.put("title", book.getTitle());
		paramsMap.put("author", book.getAuthor());
		paramsMap.put("categoryId", book.getCategoryId());
		
		if (book.getPublishedDate() != null) {
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
			paramsMap.put("publishedDate", dateFormat.format(book.getPublishedDate()));
		} else {
			paramsMap.put("publishedDate", "000000");
		}
		paramsMap.put("registrantId", book.getRegistrant().getId());
		return namedParamJdbcTemplate.update(sql, paramsMap);
	}
	
	// 登録者IDなしで書籍登録
	public int insertBookWithoutRegistrantId(Book book) throws DuplicateKeyException {
		String sql = "INSERT INTO book(book_title, book_author, category_id, published_date) "
				   + "VALUES(:title, :author, :categoryId, :publishedDate)";
		Map<String, Object> paramsMap = new HashMap<String, Object>();
		paramsMap.put("title", book.getTitle());
		paramsMap.put("author", book.getAuthor());
		paramsMap.put("categoryId", book.getCategoryId());
		if (book.getPublishedDate() != null) {
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
			paramsMap.put("publishedDate", dateFormat.format(book.getPublishedDate()));
		} else {
			paramsMap.put("publishedDate", "000000");
		}
		return namedParamJdbcTemplate.update(sql, paramsMap);
	}
	
	// 書籍IDで書籍検索
	public Book searchBookById(long bookId) throws EmptyResultDataAccessException {
		String sql = "SELECT * FROM book "
				   + "WHERE book_id = ?";
		BookRowMapper bookRowMapper = new BookRowMapper();
		Book book = jdbcTemplate.queryForObject(sql, bookRowMapper, bookId);
		book.setChapterList(searchBookChapterListById(bookId));
		return book;
	}
	
	// 書籍IDで書籍チャプターを検索
	public List<Chapter> searchBookChapterListById(long bookId) {
		String sql = "SELECT * FROM chapter "
				   + "WHERE book_id = ?";
		return jdbcTemplate.query(sql, (rs, rowNum) -> {
			Chapter chapter = new Chapter();
			chapter.setChapterSeq(rs.getInt("chapter_seq"));
			chapter.setChapterTitle(rs.getString("chapter_title"));
			chapter.setPageNum(rs.getInt("page_num"));
			return chapter;
		}, bookId);
	}
	
	// 書籍情報で書籍リスト検索
	public List<Book> searchBookByBookInfo(String title, String author, String fromDate, String toDate) {
		String sql = "SELECT * FROM book "
				   + "WHERE book_title LIKE :bookTitle "
				   + "AND book_author LIKE :bookAuthor "
				   + "AND published_date BETWEEN :fromDate AND :toDate";
		String from = !fromDate.isEmpty() ? fromDate : "00000000";
		String to = !toDate.isEmpty() ? toDate : "99999999";
		
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("bookTitle", "%" + title + "%");
		params.put("bookAuthor", "%" + author + "%");
		params.put("fromDate", from);
		params.put("toDate", to);
		
		BookRowMapper rowMapper = new BookRowMapper();
		return namedParamJdbcTemplate.query(sql, params, rowMapper);
	}
	
	// 全カテゴリーリスト検索
	public List<Category> getAllCategory() {
		String sql = "SELECT * FROM category "
				   + "ORDER BY cate_id";
		return jdbcTemplate.query(sql, (rs, rowNum) -> {
			Category category = new Category();
			category.setId(rs.getInt("cate_id"));
			category.setName(rs.getString("cate_name"));
			return category;
		});
	}
	
	// カテゴリー登録
	public int insertCategory(String categoryName) {
		String sql = "INSERT INTO category (cate_name)"
				   + "VALUES(?)";
		return jdbcTemplate.update(sql, categoryName);
	}
	
	// カテゴリー削除
	public int deleteCategory(int categoryId) {
		String sql = "DELETE FROM category "
				   + "WHERE cate_id = ?";
		return jdbcTemplate.update(sql, categoryId);
	}
}
