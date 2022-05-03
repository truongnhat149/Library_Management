package com.codegym.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codegym.model.Book;
import com.codegym.model.Category;
import com.codegym.service.impl.BookServiceImpl;
import com.codegym.service.impl.CategoryServiceImpl;

@RestController
@RequestMapping(value = "/api/book")
public class BookAPIController {

	@Autowired
	private BookServiceImpl bookServiceImpl;
	
	@Autowired
	private CategoryServiceImpl categoryServiceImpl;
	
	@GetMapping(value = {"/", "/list"})
	public List<Book> all() {
		return bookServiceImpl.getAll();
	}
	
	@GetMapping(value = "/{id}/list")
	public List<Book> get(@PathVariable(name = "id") Long id) {
		Category category = categoryServiceImpl.get(id);
		return bookServiceImpl.getByCategory( category );
	}
	
	@GetMapping(value = "/{id}/available")
	public List<Book> getAvailableBooks(@PathVariable(name = "id") Long id) {
		Category category = categoryServiceImpl.get(id);
		return bookServiceImpl.geAvailableByCategory( category );
	}
	
}
