package com.codegym.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codegym.model.Category;
import com.codegym.service.impl.CategoryServiceImpl;

@RestController
@RequestMapping(value = "/api/category")
public class CategoryAPIController {
	
	@Autowired
	private CategoryServiceImpl categoryServiceImpl;
	
	@GetMapping(value = {"/", "/list"})
	public List<Category> all() {
		return categoryServiceImpl.getAll();
	}

}
