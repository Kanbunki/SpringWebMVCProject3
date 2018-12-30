package com.bookShop.onlineShop.storehouse.controller;

import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.ui.Model;
import org.springframework.dao.DuplicateKeyException;
import com.bookShop.onlineShop.storehouse.model.*;
import com.bookShop.onlineShop.storehouse.model.RegistrationForm.*;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("storehouse/registration")
public class RegistrationController {
	
	@Autowired
	BookRepositoryManager bookRepositoryManager;
	
	@Autowired
	RegistrantRepositoryManager registrantRepositoryManager;
	
	@ModelAttribute("cateForm")
	public CategoryForm getCategoryForm() {
		CategoryForm form = new CategoryForm();
		return form;
	}

	// 書籍登録フォーム初期化
	@RequestMapping(method=RequestMethod.GET)
	public String bookRegistrationMain(Model model) {
		RegistrationForm form = new RegistrationForm();
		List<Category> categoryList = bookRepositoryManager.getAllCategory();
		form.setCategoryList(categoryList);
		model.addAttribute(form);
		return "storehouse/registration/registrationMenu";
	}
	
	// カテゴリー登録画面へ移動
	@RequestMapping(method=RequestMethod.POST, params="addCategory")
	public String gotoCagetoryForm(@ModelAttribute("cateForm") CategoryForm form) {
		List<Category> categoryList = bookRepositoryManager.getAllCategory();
		form.setCategoryList(categoryList);
		return "storehouse/registration/categoryForm";
	}
	
	// カテゴリー登録
	@RequestMapping(method=RequestMethod.POST, params="categoryRegistration")
	public String categoryRegistration(@Validated @ModelAttribute("cateForm") CategoryForm form, BindingResult result) {
		if (form.getCategoryName() != null && !form.getCategoryName().isEmpty()) {
			bookRepositoryManager.insertCategory(form.getCategoryName());
		}
		List<Category> categoryList = bookRepositoryManager.getAllCategory();
		form.setCategoryList(categoryList);
		return "storehouse/registration/categoryForm";
	}
	
	// カテゴリー削除処理
	@RequestMapping(method=RequestMethod.POST, params="deleteId")
	public String categoryDeletion(@ModelAttribute("cateForm") CategoryForm form, @RequestParam int deleteId) {
		bookRepositoryManager.deleteCategory(deleteId);
		List<Category> categoryList = bookRepositoryManager.getAllCategory();
		form.setCategoryList(categoryList);
		return "storehouse/registration/categoryForm";
	}
	
	// 新規登録者として書籍登録
	@RequestMapping(method=RequestMethod.POST, params= {"register", "registrantType=new"})
	public String registerByNewRegistrant(@Validated(NewRegistrant.class) RegistrationForm form, BindingResult result) {
		if(result.hasErrors()) {
			
			return "storehouse/registration/registrationMenu";
		}
		
		String bookTitle = form.getBookTitle();
		String bookAuthor = form.getBookAuthor();
		int categoryId = form.getCategoryId();
		Date publishedDate = form.getPublishedDate();
		int registrantId = 0;
		
		try {
			registrantId = registrantRepositoryManager.insertRegistrant(form.getRegistrantName(), form.getRegistrantPassword());
		} catch(DuplicateKeyException e) {
			result.reject("registrationForm.registrant.duplicateKey");
			return "storehouse/registration/registrationMenu";
		}
		
		try {
			bookRepositoryManager.insertBook(bookTitle, bookAuthor, categoryId, publishedDate, registrantId);
		} catch(DuplicateKeyException e) {
			result.reject("registrationForm.book.duplicateKey");
			return "storehouse/registration/registrationMenu";
		}

		return "storehouse/registration/registried";
	}
	
	// 既存登録者として書籍登録
	@RequestMapping(method=RequestMethod.POST, params= {"register", "registrantType=old"})
	public String registerByOldRegistrant(@Validated(OldRegistrant.class) RegistrationForm form, BindingResult result) {
		if(result.hasErrors()) {
			return "storehouse/registration/registrationMenu";
		}
		
		String bookTitle = form.getBookTitle();
		String bookAuthor = form.getBookAuthor();
		int categoryId = form.getCategoryId();
		Date publishedDate = form.getPublishedDate();
		try {
			bookRepositoryManager.insertBook(bookTitle, bookAuthor, categoryId, publishedDate);
		} catch(DuplicateKeyException e) {
			result.reject("registrationForm.duplicateKey");
			return "storehouse/registration/registrationMenu";
		}
		return "storehouse/registration/registried";
	}
	
	// 倉庫画面へ戻る
	@RequestMapping(method=RequestMethod.POST, params="back")
	public String back() {
		return "storehouse/storehouseMenu";
	}
	
	//　書籍登録画面へ戻る
	@RequestMapping(method=RequestMethod.POST, params="backToBookRegistration")
	public String backToBookRegistration() {
		return "redirect:registration";
	}
}
