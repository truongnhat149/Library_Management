package com.codegym.service.impl;

import java.util.Date;
import java.util.List;

import com.codegym.common.Constants;
import com.codegym.model.Book;
import com.codegym.model.Category;
import com.codegym.repository.BookRepository;
import com.codegym.service.IBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class BookServiceImpl implements IBookService {

	@Autowired
	private BookRepository bookRepository;
	
	@Autowired
	private IssuedBookServiceImpl issuedBookServiceImpl;
	
	public Long getTotalCount() {
		return bookRepository.count();
	}
	
	public Long getTotalIssuedBooks() {
		return bookRepository.countByStatus(Constants.BOOK_STATUS_ISSUED);
	}
	
	public List<Book> getAll() {
		return bookRepository.findAll();
	}
	
	public Book get(Long id) {
		return bookRepository.findById(id).get();
	}
	
	public Book getByTag(String tag) {
		return bookRepository.findByTag(tag);
	}
	
	public List<Book> get(List<Long> ids) {
		return bookRepository.findAllById(ids);
	}
	
	public List<Book> getByCategory(Category category) {
		return bookRepository.findByCategory(category);
	}
	
	public List<Book> geAvailableByCategory(Category category) {
		return bookRepository.findByCategoryAndStatus(category, Constants.BOOK_STATUS_AVAILABLE);
	}
	
	public Book addNew(Book book) {
		book.setCreateDate(new Date());
		book.setStatus( Constants.BOOK_STATUS_AVAILABLE );
		return bookRepository.save(book);
	}
	
	public Book save(Book book) {
		return bookRepository.save(book);
	}
	
	public void delete(Book book) {
		bookRepository.delete(book);
	}
	
	public void delete(Long id) {
		bookRepository.deleteById(id);
	}
	
	public boolean hasUsage(Book book) {
		return issuedBookServiceImpl.getCountByBook(book)>0;
	}
}
