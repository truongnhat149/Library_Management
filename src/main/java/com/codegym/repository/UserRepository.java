package com.codegym.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.codegym.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	 List<User> findAllByOrderByDisplayNameAsc();

	 List<User> findAllByActiveOrderByDisplayNameAsc(Integer active);

	 User findByUsername(String username);
}
