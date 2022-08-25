package com.mytasknowcobackend.kanban.dao;

import com.google.api.core.ApiFuture;
import com.google.api.core.ApiFutureCallback;
import com.google.api.core.ApiFutures;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import com.mytasknowcobackend.kanban.model.Tasks;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;

@Repository("taskfirestoredao")
public class TasksDaoService implements TasksDao{


    public static final String COL_NAME = "tasks";
    public static List<Tasks> tasksList = new ArrayList<>();
    public static int res =0;
    @Override
    public int addTasks(Tasks tasks) {
        Firestore dbFirestore = FirestoreClient.getFirestore();

        DocumentReference addedDocRef = dbFirestore.collection(COL_NAME).document();
        //System.out.println("Added document with ID: " + addedDocRef.getId());
        tasks.setTaskId(addedDocRef.getId().toString());
        ApiFuture<WriteResult> writeResult = addedDocRef.set(tasks);
        return 1;
    }

    @Override
    public List<Tasks> selectAllTasks() {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<QuerySnapshot> future = dbFirestore.collection(COL_NAME).get();
        Tasks tasks = null;
        List<QueryDocumentSnapshot> documents = null;
        tasksList.clear();
        try {
            documents = future.get().getDocuments();
            for (QueryDocumentSnapshot document : documents) {
                System.out.println(document.getId() + " => " + document.toObject(Tasks.class));
                tasks = document.toObject(Tasks.class);
                tasksList.add(tasks);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }


        return tasksList;
    }

    @Override
    public Tasks selectTaskbyId(String taskId) {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<QuerySnapshot> future = dbFirestore.collection(COL_NAME).get();
        Tasks mySelectedTask = null;
        List<QueryDocumentSnapshot> documents = null;
        try {
            documents = future.get().getDocuments();
            for (QueryDocumentSnapshot document : documents) {
                //System.out.println(document.getId() + " => " + document.toObject(Issues.class));
                Tasks allTasks = document.toObject(Tasks.class);
                if (allTasks.getTaskId().equals(taskId)){
                    mySelectedTask = allTasks;
                   // tasksList.add(mySelectedTask);
                }


            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return mySelectedTask;
    }

    @Override
    public int deleteTaskbyId(String taskId) {

        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> writeResult = dbFirestore.collection(COL_NAME).document(String.valueOf(taskId)).delete();
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
    public int updateTaskbyId(String taskId, Tasks updatedTask) {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        DocumentReference docRef = dbFirestore.collection(COL_NAME).document(taskId);

        // (async) Update fields
        ApiFuture<WriteResult> future = docRef.
                update(
                        "taskName" , updatedTask.getTaskName(),
                        "taskId" , updatedTask.getTaskId(),
                        "taskType" , updatedTask.getTaskType(),
                        "taskPriority" , updatedTask.getTaskPriority(),
                        "taskDescription" , updatedTask.getTaskDescription(),
                        "taskCreatedBy", updatedTask.getTaskCreatedBy(),
                        "taskStartTime" , updatedTask.getTaskStartTime(),
                        "taskEndTime" , updatedTask.getTaskEndTime(),
                        "taskDuration" , updatedTask.getTaskDuration(),
                        "taskStatus" , updatedTask.getTaskStatus()
                );
        return  1;
    }

    @Override
    public List<Tasks> selectTaskbyCreator(String userId) {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<QuerySnapshot> future = dbFirestore.collection(COL_NAME).get();
        String uid = userId.replace("user-","");
        Tasks tasks = null;
        List<QueryDocumentSnapshot> documents = null;
        tasksList.clear();
       // System.out.println(userId +"     "+ uid);
        try {
            documents = future.get().getDocuments();
            for (QueryDocumentSnapshot document : documents) {
                //System.out.println(document.getId() + " => " + document.toObject(Tasks.class));
                tasks = document.toObject(Tasks.class);
                if(tasks.getTaskCreatedBy().equals(uid))
                tasksList.add(tasks);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }catch (ExecutionException e) {
            e.printStackTrace();
        }

        return tasksList;
    }
}
