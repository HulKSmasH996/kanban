package com.mytasknowcobackend.kanban.service;

import com.mytasknowcobackend.kanban.dao.IssuesDao;
import com.mytasknowcobackend.kanban.model.Issues;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IssuesService {

	private final IssuesDao issuesDao;

	@Autowired
	public IssuesService(@Qualifier("issuefirestoredao") IssuesDao issuesDao) {
		this.issuesDao = issuesDao;
	}
	
	public int addIssue(Issues issues) {
	  return issuesDao.addIssues(issues);
	}
	
	public List<Issues> returnAllIssues(){
		return issuesDao.selectAllIssues();
	}

	public  Issues selectIssuebyId(String issueId){ return  issuesDao.selectIssuebyId(issueId); }

	public int deleteIssuebyId(String issueId){ return issuesDao.deleteIssuebyId(issueId); }

	public int updateIssuebyId(String issueId, Issues updatedIssue){ return issuesDao.updateIssuebyId(issueId,updatedIssue); }
}
