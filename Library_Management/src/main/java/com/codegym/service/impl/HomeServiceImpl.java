package com.codegym.service.impl;

import java.util.HashMap;
import java.util.Map;

import com.codegym.service.IHomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HomeServiceImpl implements IHomeService {

	@Autowired
	private MemberServiceImpl memberServiceImpl;
	
	@Autowired
	private CategoryServiceImpl categoryServiceImpl;
	
	@Autowired
	private BookServiceImpl bookServiceImpl;
	
	public Map<String, Long> getTopTilesMap() {
		Map<String, Long> map = new HashMap<String, Long>();
		map.put("totalMembers", memberServiceImpl.getTotalCount());
		map.put("totalStudents", memberServiceImpl.getStudentsCount());
		map.put("totalParents", memberServiceImpl.getParentsCount());
		map.put("totalCategories", categoryServiceImpl.getTotalCount());
		map.put("totalBooks", bookServiceImpl.getTotalCount());
		map.put("totalIssuedBooks", bookServiceImpl.getTotalIssuedBooks());
		return map;
	}
	
}
