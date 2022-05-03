package com.codegym.service;

import com.codegym.common.Constants;
import com.codegym.model.Book;
import com.codegym.model.IssuedBook;

import java.util.List;

public interface IIssuedBookService {

    public List<IssuedBook> getAll(); 

    public IssuedBook get(Long id); 

    public Long getCountByBook(Book book); 

    public IssuedBook save(IssuedBook issuedBook); 

    public IssuedBook addNew(IssuedBook issuedBook);
    
}
