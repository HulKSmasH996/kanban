package com.mytasknowcobackend.kanban.dao;

import com.mytasknowcobackend.kanban.model.Issues;

import java.util.List;

public interface IssuesDao {
	
	

    int addIssues(Issues issues);

    List<Issues> selectAllIssues();

    Issues selectIssuebyId(String issueId);

    int deleteIssuebyId(String issueId);

    int updateIssuebyId(String issueId, Issues updatedIssue);

	

}
