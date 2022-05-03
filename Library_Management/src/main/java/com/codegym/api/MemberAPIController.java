package com.codegym.api;

import java.util.List;

import com.codegym.service.IMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codegym.model.Member;
import com.codegym.service.impl.MemberServiceImpl;

@RestController
@RequestMapping(value = "/api/member")
public class MemberAPIController {

	@Autowired
	private IMemberService memberServiceImpl;
	
	@GetMapping(value = {"/", "/list"})
	public List<Member> all() {
		return memberServiceImpl.getAll();
	}
	
}
