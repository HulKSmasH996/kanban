package com.mytasknowcobackend.kanban.dao;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;

import com.mytasknowcobackend.kanban.model.Issues;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Repository("issuefirestoredao")
public class IssuesDaoService implements IssuesDao{

	 public static final String COL_NAME = "issues";
	 public static List<Issues> issuesList = new ArrayList<>();

	@Override
	public int addIssues(Issues newissue) {
		Firestore dbFirestore = FirestoreClient.getFirestore();

		DocumentReference addedDocRef = dbFirestore.collection(COL_NAME).document();
		//System.out.println("Added document with ID: " + addedDocRef.getId());
		newissue.setIssueId(addedDocRef.getId().toString());
		ApiFuture<WriteResult> writeResult = addedDocRef.set(newissue);
		return 1;
	}

	@Override
	public List<Issues> selectAllIssues() {
		Firestore dbFirestore = FirestoreClient.getFirestore();
		ApiFuture<QuerySnapshot> future = dbFirestore.collection(COL_NAME).get();
		Issues issue = null;
		List<QueryDocumentSnapshot> documents = null;
		issuesList.clear();
		try {
			documents = future.get().getDocuments();
			for (QueryDocumentSnapshot document : documents) {
				System.out.println(document.getId() + " => " + document.toObject(Issues.class));
				issue = document.toObject(Issues.class);
				issuesList.add(issue);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}


		return issuesList;
	}

	@Override
	public Issues selectIssuebyId(String issueId) {
		Firestore dbFirestore = FirestoreClient.getFirestore();
		ApiFuture<QuerySnapshot> future = dbFirestore.collection(COL_NAME).get();
		Issues mySelectedIssue = null;
		List<QueryDocumentSnapshot> documents = null;
		try {
			documents = future.get().getDocuments();
			for (QueryDocumentSnapshot document : documents) {
				//System.out.println(document.getId() + " => " + document.toObject(Issues.class));
				Issues allIssues = document.toObject(Issues.class);
				if (allIssues.getIssueId().equals(issueId))
					mySelectedIssue = allIssues;
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
		return mySelectedIssue;
	}

	@Override
	public int deleteIssuebyId(String issueId) {
		Firestore dbFirestore = FirestoreClient.getFirestore();
		ApiFuture<WriteResult> writeResult = dbFirestore.collection(COL_NAME).document(String.valueOf(issueId)).delete();
		return 1;
	}

	@Override
	public int updateIssuebyId(String issueId, Issues updatedIssue) {
		Firestore dbFirestore = FirestoreClient.getFirestore();
		DocumentReference docRef = dbFirestore.collection(COL_NAME).document(issueId);

	// (async) Update fields
		ApiFuture<WriteResult> future = docRef.
				update(
						 "issueName" , updatedIssue.getIssueName(),
						 "issueId" , updatedIssue.getIssueId(),
						 "issueType" , updatedIssue.getIssueType(),
						 "issuePriority" , updatedIssue.getIssuePriority(),
						 "issueDescription" , updatedIssue.getIssueDescription(),
						 "issueTags" , updatedIssue.getIssueTags(),
						 "issueAssignee" , updatedIssue.getIssueAssignee(),
						 "issueSummary" , updatedIssue.getIssueSummary(),
						 "issueStartTime" , updatedIssue.getIssueStartTime(),
						 "issueEndTime" , updatedIssue.getIssueEndTime(),
						 "issueDuration" , updatedIssue.getIssueDuration(),
						 "issueStatus" , updatedIssue.getIssueStatus()
				);
		return  1;
	}
	 
	/*@Override
	public int insertUser(User newUser) {
		// TODO Auto-generated method stub
		 Firestore dbFirestore = FirestoreClient.getFirestore();
	     
	     DocumentReference addedDocRef = dbFirestore.collection(COL_NAME).document();
	     //System.out.println("Added document with ID: " + addedDocRef.getId());
	     newUser.setUserid(addedDocRef.getId().toString());
	     ApiFuture<WriteResult> writeResult = addedDocRef.set(newUser);
	     return 1;
		
	}

	@Override
	public List<User> selectAllUsers() {
		// TODO Auto-generated method stub
		 	Firestore dbFirestore = FirestoreClient.getFirestore();
	        ApiFuture<QuerySnapshot> future = dbFirestore.collection("users").get();
	        User user = null;
	        List<QueryDocumentSnapshot> documents = null;
	        userList.clear();
	        try {
	            documents = future.get().getDocuments();
	            for (QueryDocumentSnapshot document : documents) {
	                System.out.println(document.getId() + " => " + document.toObject(User.class));
	                user = document.toObject(User.class);
	                userList.add(user);
	            }
	        } catch (InterruptedException e) {
	            e.printStackTrace();
	        } catch (ExecutionException e) {
	            e.printStackTrace();
	        }


	        return userList;
	}*/



}
