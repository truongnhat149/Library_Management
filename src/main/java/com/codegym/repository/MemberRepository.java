package com.codegym.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.codegym.model.Member;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {

	 List<Member> findAllByOrderByFirstNameAscLastNameAsc();

	 Long countByType(String type);
}
