package com.bookShop.onlineShop.storehouse.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.format.datetime.DateFormatter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.dao.EmptyResultDataAccessException;

import org.springframework.validation.BindingResult;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import com.bookShop.onlineShop.storehouse.model.*;

@Controller
@RequestMapping("storehouse/search")
public class SearchController {

	@Autowired
	BookRepositoryManager manager;
	
//	@ModelAttribute
//	public SearchByBookInfoForm searchByBookInfoForm() {
//		return new SearchByBookInfoForm();
//	}
//	
	// 検索方法メニュー
	@RequestMapping(method=RequestMethod.GET)
	public String bookSearchMethodMenu() {
		return "storehouse/search/searchMethodMenu";
	}
	
	// 書籍IDで検索
	@RequestMapping(method=RequestMethod.GET, params="method=byId")
	public String bookSearchByIdMenu(Model model) {
		SearchByIdForm form = new SearchByIdForm();
		model.addAttribute(form);
		return "storehouse/search/searchByIdMenu";
	}
	
	@RequestMapping(method=RequestMethod.POST, params="searchById")
	public String searchById(@Valid SearchByIdForm form, BindingResult result, RedirectAttributes attributes) {
		if (result.hasErrors()) {
			return "storehouse/search/searchByIdMenu";
		}
		List<Book> bookList = new ArrayList<Book>();
		try {
			Book book = manager.searchBookById(form.getBookId());
			bookList.add(book);
			attributes.addAttribute("id", book.getBookId());
			attributes.addFlashAttribute("bookList", bookList);
			
			attributes.addFlashAttribute("backToSearchMenu", "byId");
		} catch(EmptyResultDataAccessException e) {
			attributes.addFlashAttribute("bookList", bookList);
			attributes.addFlashAttribute("backToSearchMenu", "byId");
			return "redirect:search/bookListResult";
		} 
		return "redirect:search";
	}
	
	// 書籍情報で検索
	@RequestMapping(method=RequestMethod.GET, params="method=byBookInfo")
	public String bookSearchByBookInfoMenu(Model model) {
		SearchByBookInfoForm form = new SearchByBookInfoForm();
		model.addAttribute(form);
		return "storehouse/search/searchByBookInfoMenu";
	}
	
	@RequestMapping(method=RequestMethod.POST, params="searchByBookInfo")
	public String searchByBookInfo(@Valid SearchByBookInfoForm form, BindingResult result, RedirectAttributes attributes) {
		if (result.hasErrors()) {
			return "storehouse/search/searchByBookInfoMenu";
		}
		SearchByBookInfoCondition condition = new SearchByBookInfoCondition();
		condition.setBookTitle(form.getBookTitle());
		condition.setBookAuthor(form.getBookAuthor());
		condition.setFromDate(form.getFromDate());
		condition.setToDate(form.getToDate());
		List<Book> bookList = manager.searchBookByBookInfo(condition);
		attributes.addFlashAttribute("bookList", bookList);
		
		attributes.addFlashAttribute("backToSearchMenu", "byBookInfo");
		return "redirect:search/bookListResult";
	}
	
	// 書籍検索結果
	@RequestMapping(path="{bookId}", method=RequestMethod.GET)
	public String bookSearchingResult(@ModelAttribute("book") Book book, @PathVariable String bookId) {
		return "storehouse/search/searchingResult";
	}
	
	@RequestMapping(method=RequestMethod.GET, params={"id"})
	public String searchingResult(@ModelAttribute("bookList") List<Book> bookList, @RequestParam String id) {
		return "storehouse/search/searchingResult";
	}
	
	@RequestMapping(method=RequestMethod.POST, params="backToMethodMenu")
	public String backToMethodMenu() {
		return "storehouse/search/searchMethodMenu";
	}
	
	@RequestMapping(path="bookListResult", method=RequestMethod.GET)
	public String bookListResult(@ModelAttribute("bookList") List<Book> bookList) {
		return "storehouse/search/searchingResult";
	}
	
	// 出版日変換操作
	@InitBinder({"searchByIdForm", "searchByBookInfoForm"})
	public void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
		dateFormat.setLenient(false);
		binder.registerCustomEditor(Date.class, "publishedDate", new CustomDateEditor(dateFormat, true));
	}
	
	@InitBinder("date")
	public void convertdate(WebDataBinder binder) {
		binder.addCustomFormatter(new DateFormatter("yyyyMMdd"));
	}
}
