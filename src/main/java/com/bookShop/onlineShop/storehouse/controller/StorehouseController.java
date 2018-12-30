package com.bookShop.onlineShop.storehouse.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("storehouse")
public class StorehouseController {

	@RequestMapping("")
	public String strorehouseMainMenu() {
		return "/storehouse/storehouseMenu";
	}
}
