package com.codegym.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.codegym.service.impl.HomeServiceImpl;

@Controller
public class HomeController {

	@Autowired
	HomeServiceImpl homeServiceImpl;
	
	@RequestMapping(value = {"/", "/home"}, method = RequestMethod.GET)
	public String homePage(Model model) {
		model.addAttribute("topTiles", homeServiceImpl.getTopTilesMap());
		return "home";
	}	
	
}
