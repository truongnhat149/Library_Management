package com.codegym.service;

import com.codegym.model.Category;

import java.util.Date;
import java.util.List;

public interface ICategoryService {

     Long getTotalCount() ;

     List<Category> getAllBySort();

     List<Category> getAll();

     Category get(Long id);

     Category addNew(Category category);

     Category save(Category category);

     void delete(Category category) ;

     void delete(Long id) ;

     boolean hasUsage(Category category) ;
}
