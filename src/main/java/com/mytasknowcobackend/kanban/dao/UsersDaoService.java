package com.mytasknowcobackend.kanban.dao;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;

import com.mytasknowcobackend.kanban.model.Issues;
import com.mytasknowcobackend.kanban.model.Users;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Repository("usersfirestoredao")
public class UsersDaoService implements UsersDao{

    public static final String COL_NAME = "users";
    public static List<Users> usersList = new ArrayList<>();


    @Override
    public int addUser(Users newuser) {
        Firestore dbFirestore = FirestoreClient.getFirestore();

        DocumentReference addedDocRef = dbFirestore.collection(COL_NAME).document();
        //System.out.println("Added document with ID: " + addedDocRef.getId());
        newuser.setUserId(addedDocRef.getId().toString());
        ApiFuture<WriteResult> writeResult = addedDocRef.set(newuser);
        return 1;
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
                System.out.println(document.getId() + " => " + document.toObject(Users.class));
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
        ApiFuture<QuerySnapshot> future = dbFirestore.collection(COL_NAME).get();
        Users mySelectedUser = null;
        List<QueryDocumentSnapshot> documents = null;
        try {
            documents = future.get().getDocuments();
            for (QueryDocumentSnapshot document : documents) {
                //System.out.println(document.getId() + " => " + document.toObject(Issues.class));
                Users allUsers = document.toObject(Users.class);
                if (allUsers.getUserId().equals(userId))
                    mySelectedUser = allUsers;
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return mySelectedUser;
    }

    @Override
    public int deleteUserbyId(String userId) {
        return 0;
    }

    @Override
    public int updateUserbyId(String userId, Users updatedUser) {
        return 0;
    }
}
