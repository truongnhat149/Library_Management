package com.codegym.service.impl;

import java.util.List;

import com.codegym.common.Constants;
import com.codegym.model.Book;
import com.codegym.model.IssuedBook;
import com.codegym.repository.IssuedBookRepository;
import com.codegym.service.IIssuedBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IssuedBookServiceImpl implements IIssuedBookService {

	@Autowired
	private IssuedBookRepository issuedBookRepository;
	
	public List<IssuedBook> getAll() {
		return issuedBookRepository.findAll();
	}
	
	public IssuedBook get(Long id) {
		return issuedBookRepository.findById(id).get();
	}
	
	public Long getCountByBook(Book book) {
		return issuedBookRepository.countByBookAndReturned(book, Constants.BOOK_NOT_RETURNED);
	}
	
	public IssuedBook save(IssuedBook issuedBook) {
		return issuedBookRepository.save(issuedBook);
	}
	
	public IssuedBook addNew(IssuedBook issuedBook) {
		issuedBook.setReturned( Constants.BOOK_NOT_RETURNED );
		return issuedBookRepository.save(issuedBook);
	}

}
