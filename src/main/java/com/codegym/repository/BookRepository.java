package com.codegym.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.codegym.model.Book;
import com.codegym.model.Category;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

	 Book findByTag(String tag);

	 List<Book> findByCategory(Category category);

	 List<Book> findByCategoryAndStatus(Category category, Integer status);

	 Long countByStatus(Integer status);
}
