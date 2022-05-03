package com.codegym.service;
import com.codegym.model.Book;
import com.codegym.model.Category;

import java.util.List;

public interface IBookService {

     Long getTotalCount();

     Long getTotalIssuedBooks() ;

     List<Book> getAll() ;

     Book get(Long id);

     Book getByTag(String tag) ;

     List<Book> get(List<Long> ids);

     List<Book> getByCategory(Category category) ;

     List<Book> geAvailableByCategory(Category category) ;

     Book addNew(Book book) ;
      
     Book save(Book book) ;
      
     void delete(Book book) ;

     void delete(Long id) ;

     boolean hasUsage(Book book) ;
     
}
