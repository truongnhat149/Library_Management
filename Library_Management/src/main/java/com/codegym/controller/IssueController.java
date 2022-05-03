package com.codegym.controller;

import java.util.List;

import com.codegym.service.ICategoryService;
import com.codegym.service.IIssueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.codegym.common.Constants;
import com.codegym.model.Category;
import com.codegym.service.impl.CategoryServiceImpl;
import com.codegym.service.impl.IssueServiceImpl;

@Controller
@RequestMapping(value = "/issue")
public class IssueController {

	@Autowired
	private IIssueService issueServiceImpl;
	
	@Autowired
	private ICategoryService categoryServiceImpl;
	
	@ModelAttribute(name = "memberTypes")
	public List<String> memberTypes() {
		return Constants.MEMBER_TYPES;
	}
	
	@ModelAttribute("categories")
	public List<Category> getCategories() {
		return categoryServiceImpl.getAllBySort();
	}
	
	@RequestMapping(value = {"/", "/list"}, method = RequestMethod.GET)
	public String listIssuePage(Model model) {
		model.addAttribute("issues", issueServiceImpl.getAllUnreturned());
		return "/issue/list";
	}
	
	@RequestMapping(value = "/new", method = RequestMethod.GET)
	public String newIssuePage(Model model) { 
		return "/issue/form";
	}
	
}
