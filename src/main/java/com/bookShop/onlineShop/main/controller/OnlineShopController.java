package com.bookShop.onlineShop.main.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("onlineShop")
public class OnlineShopController {

	@RequestMapping(method=RequestMethod.GET)
	public String onlineShopMainMenu() {
		return "index";
	}
}
