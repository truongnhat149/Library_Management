package com.codegym.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.codegym.model.Issue;
import com.codegym.model.Member;

@Repository
public interface IssueRepository extends JpaRepository<Issue, Long> {

	List<Issue> findByReturned(Integer returned);

	Long countByMemberAndReturned(Member member, Integer returned);
}
