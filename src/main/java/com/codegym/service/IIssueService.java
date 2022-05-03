package com.codegym.service;
import com.codegym.model.Issue;
import com.codegym.model.Member;

import java.util.List;

public interface IIssueService {

     List<Issue> getAll();

     Issue get(Long id);

     List<Issue> getAllUnreturned();

     Issue addNew(Issue issue);

     Issue save(Issue issue);

     Long getCountByMember(Member member);
}
