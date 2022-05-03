package com.codegym.service;

import com.codegym.model.Member;

import java.util.List;

public interface IMemberService {

     Long getTotalCount();

     Long getParentsCount();

     Long getStudentsCount(); 

     List<Member> getAll(); 

     Member get(Long id); 

     Member addNew(Member member); 

     Member save(Member member);

     void delete(Member member); 

     void delete(Long id); 

     boolean hasUsage(Member member); 
}
