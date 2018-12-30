package com.bookShop.onlineShop.storehouse.model;

import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class BookRowMapper implements RowMapper<Book> {

	@Override
	public Book mapRow(ResultSet rs, int rowNum) throws SQLException {
		Book book = new Book();
		try {
			book.setBookId(rs.getLong("book_id"));
			book.setTitle(rs.getString("book_title"));
			book.setAuthor(rs.getString("book_author"));
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
			book.setPublishedDate(dateFormat.parse(rs.getString("published_date")));
		} catch(ParseException e) {
			e.printStackTrace();
		}
		return book;
	}
}
