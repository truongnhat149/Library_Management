package com.codegym.api;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.codegym.service.IBookService;
import com.codegym.service.IIssueService;
import com.codegym.service.IIssuedBookService;
import com.codegym.service.IMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.codegym.common.Constants;
import com.codegym.model.Book;
import com.codegym.model.Issue;
import com.codegym.model.IssuedBook;
import com.codegym.model.Member;
import com.codegym.service.impl.BookServiceImpl;
import com.codegym.service.impl.IssueServiceImpl;
import com.codegym.service.impl.IssuedBookServiceImpl;
import com.codegym.service.impl.MemberServiceImpl;

@RestController
@RequestMapping(value = "/api/issue")
public class IssueAPIController {

	@Autowired
	private IMemberService memberServiceImpl;
	
	@Autowired
	private IBookService bookServiceImpl;

	@Autowired
	private IIssueService issueServiceImpl;

	@Autowired
	private IIssuedBookService issuedBookServiceImpl;
	
	@RequestMapping(value="/save", method = RequestMethod.POST)
	public String save(@RequestParam Map<String, String> payload) {
		
		String memberIdStr = (String)payload.get("member");
		String[] bookIdsStr = payload.get("books").toString().split(",");
		
		Long memberId = null;
		List<Long> bookIds = new ArrayList<Long>();
		try {
			memberId = Long.parseLong( memberIdStr );

			for (int k=0 ; k<bookIdsStr.length ; k++) {
				bookIds.add( Long.parseLong(bookIdsStr[k]) );
			}
		} catch (NumberFormatException ex) {
			ex.printStackTrace();
			return "invalid number format";
		}
		
		Member member = memberServiceImpl.get( memberId );
		List<Book> books = bookServiceImpl.get(bookIds);
		
		Issue issue = new Issue();
		issue.setMember(member);
		issue = issueServiceImpl.addNew(issue);
		
		for( int k=0 ; k<books.size() ; k++ ) {
			Book book = books.get(k);
			book.setStatus( Constants.BOOK_STATUS_ISSUED );
			book = bookServiceImpl.save(book);
			
			IssuedBook ib = new IssuedBook();
			ib.setBook( book );
			ib.setIssue( issue );
			issuedBookServiceImpl.addNew( ib );
			
		}
		
		return "success";
	}
	
	@RequestMapping(value = "/{id}/return/all", method = RequestMethod.GET)
	public String returnAll(@PathVariable(name = "id") Long id) {
		Issue issue = issueServiceImpl.get(id);
		if( issue != null ) {
			List<IssuedBook> issuedBooks = issue.getIssuedBooks();
			for( int k=0 ; k<issuedBooks.size() ; k++ ) {
				IssuedBook ib = issuedBooks.get(k);
				ib.setReturned( Constants.BOOK_RETURNED );
				issuedBookServiceImpl.save( ib );
				
				Book book = ib.getBook();
				book.setStatus( Constants.BOOK_STATUS_AVAILABLE );
				bookServiceImpl.save(book);
			}
			
			issue.setReturned( Constants.BOOK_RETURNED );
			issueServiceImpl.save(issue);
			
			return "successful";
		} else {
			return "unsuccessful";
		}
	}
	
	@RequestMapping(value="/{id}/return", method = RequestMethod.POST)
	public String returnSelected(@RequestParam Map<String, String> payload, @PathVariable(name = "id") Long id) {
		Issue issue = issueServiceImpl.get(id);
		String[] issuedBookIds = payload.get("ids").split(",");
		if( issue != null ) {
			
			List<IssuedBook> issuedBooks = issue.getIssuedBooks();
			for( int k=0 ; k<issuedBooks.size() ; k++ ) {
				IssuedBook ib = issuedBooks.get(k);
				if( Arrays.asList(issuedBookIds).contains( ib.getId().toString() ) ) {
					ib.setReturned( Constants.BOOK_RETURNED );
					issuedBookServiceImpl.save( ib );
					
					Book book = ib.getBook();
					book.setStatus( Constants.BOOK_STATUS_AVAILABLE );
					bookServiceImpl.save(book);
				}
			}
			
			return "successful";
		} else {
			return "unsuccessful";
		}
	}
	
}
