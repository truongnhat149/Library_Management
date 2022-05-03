package com.codegym.service.impl;

import java.util.Date;
import java.util.List;

import com.codegym.common.Constants;
import com.codegym.model.Member;
import com.codegym.repository.MemberRepository;
import com.codegym.service.IIssueService;
import com.codegym.service.IMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberServiceImpl implements IMemberService {

	@Autowired
	private MemberRepository memberRepository;
	
	@Autowired
	private IIssueService issueServiceImpl;
	
	public Long getTotalCount() {
		return memberRepository.count();
	}
	
	public Long getParentsCount() {
		return memberRepository.countByType(Constants.MEMBER_PARENT);
	}
	
	public Long getStudentsCount() {
		return memberRepository.countByType(Constants.MEMBER_STUDENT);
	}
	
	public List<Member> getAll() {
		return memberRepository.findAllByOrderByFirstNameAscLastNameAsc();
	}
	
	public Member get(Long id) {
		return memberRepository.findById(id).get();
	}
	
	public Member addNew(Member member) {
		member.setJoiningDate( new Date() );
		return memberRepository.save( member );
	}
	
	public Member save(Member member) {
		return memberRepository.save( member );
	}
	
	public void delete(Member member) {
		memberRepository.delete(member);
	}
	
	public void delete(Long id) {
		memberRepository.deleteById(id);
	}
	
	public boolean hasUsage(Member member) {
		return issueServiceImpl.getCountByMember(member) > 0;
	}
	
}
