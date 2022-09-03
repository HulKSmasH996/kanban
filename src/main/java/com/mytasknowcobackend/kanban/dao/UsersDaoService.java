package com.mytasknowcobackend.kanban.dao;

import com.google.api.core.ApiFuture;
import com.google.api.core.ApiFutureCallback;
import com.google.api.core.ApiFutures;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;

import com.mytasknowcobackend.kanban.model.Users;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Repository("usersfirestoredao")
public class UsersDaoService implements UsersDao{

    public static final String COL_NAME = "users";
    public static List<Users> usersList = new ArrayList<>();
    public static int res = 0;

    @Override
    public int addUser(Users newuser) {
        Firestore dbFirestore = FirestoreClient.getFirestore();

        DocumentReference addedDocRef = dbFirestore.collection(COL_NAME).document(newuser.getUserId());
        //System.out.println("Added document with ID: " + addedDocRef.getId());
        newuser.setUserId(addedDocRef.getId().toString());
        ApiFuture<WriteResult> writeResult = addedDocRef.set(newuser);
        ApiFutures.addCallback(writeResult, new ApiFutureCallback<WriteResult>() {
            @Override
            public void onFailure(Throwable throwable) {
                res=0;
            }

            @Override
            public void onSuccess(WriteResult writeResult) {
                res =1;
            }
        },Runnable::run);
        return res;
    }

    @Override
    public List<Users> selectAllUsers() {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<QuerySnapshot> future = dbFirestore.collection(COL_NAME).get();
        Users user = null;
        List<QueryDocumentSnapshot> documents = null;
        usersList.clear();
        try {
            documents = future.get().getDocuments();
            for (QueryDocumentSnapshot document : documents) {
                //System.out.println(document.getId() + " => " + document.toObject(Users.class));
                user = document.toObject(Users.class);
                usersList.add(user);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }


        return usersList;
    }

    @Override
    public Users selectUserbyId(String userId) {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<QuerySnapshot> future = dbFirestore.collection(COL_NAME).whereEqualTo("userId",userId).get();
        Users myselectedUser = null;

        try {
            List<QueryDocumentSnapshot> documents = future.get().getDocuments();
            for (QueryDocumentSnapshot document : documents) {

                myselectedUser = document.toObject(Users.class);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return myselectedUser;
    }

    @Override
    public int deleteUserbyId(String userId) {

        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> writeResult = dbFirestore.collection(COL_NAME).document(String.valueOf(userId)).delete();
        ApiFutures.addCallback(writeResult, new ApiFutureCallback<WriteResult>() {
            @Override
            public void onFailure(Throwable throwable) {
                res = 0;
            }

            @Override
            public void onSuccess(WriteResult writeResult) {
                res = 1;
            }
        },Runnable::run);
        return res;
    }

    @Override
    public int updateUserbyId(String userId, Users updatedUser) {

        Firestore dbFirestore = FirestoreClient.getFirestore();
        DocumentReference docRef = dbFirestore.collection(COL_NAME).document(userId);

        // (async) Update fields
        ApiFuture<WriteResult> future = docRef.
                update(
                        "userName" , updatedUser.getUserName(),
                        "userEmail" , updatedUser.getUserEmail(),
                        "userId" , updatedUser.getUserId(),
                        "userDetails" , updatedUser.getUserDetails(),
                        "userIssues" , updatedUser.getUserIssues()

                        );
        ApiFutures.addCallback(future, new ApiFutureCallback<WriteResult>() {
            @Override
            public void onFailure(Throwable throwable) {
                res=0;
            }

            @Override
            public void onSuccess(WriteResult writeResult) {
                res=1;
            }
        },Runnable::run);
        return res;

    }
}
