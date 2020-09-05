package com.mytasknowcobackend.kanban.dao;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import com.mytasknowcobackend.kanban.model.Projects;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Repository("projectsfirestoredao")
public class ProjectsDaoService implements ProjectsDao{

    public static final String COL_NAME = "projects";
    public static List<Projects> projectsList = new ArrayList<>();

    @Override
    public int addProjects(Projects newprojects) {
        Firestore dbFirestore = FirestoreClient.getFirestore();

        DocumentReference addedDocRef = dbFirestore.collection(COL_NAME).document();
        //System.out.println("Added document with ID: " + addedDocRef.getId());
        newprojects.setProjectId(addedDocRef.getId().toString());
        ApiFuture<WriteResult> writeResult = addedDocRef.set(newprojects);
        return 1;
    }

    @Override
    public List<Projects> selectAllProjects() {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<QuerySnapshot> future = dbFirestore.collection(COL_NAME).get();
        Projects project = null;
        List<QueryDocumentSnapshot> documents = null;
        projectsList.clear();
        try {
            documents = future.get().getDocuments();
            for (QueryDocumentSnapshot document : documents) {
                System.out.println(document.getId() + " => " + document.toObject(Projects.class));
                project = document.toObject(Projects.class);
                projectsList.add(project);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }


        return projectsList;
    }

    @Override
    public Projects selectProjectbyId(String projectId) {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<QuerySnapshot> future = dbFirestore.collection(COL_NAME).get();
        Projects mySelectedProject = null;
        List<QueryDocumentSnapshot> documents = null;
        try {
            documents = future.get().getDocuments();
            for (QueryDocumentSnapshot document : documents) {
                //System.out.println(document.getId() + " => " + document.toObject(Issues.class));
                Projects allProjects = document.toObject(Projects.class);
                if (allProjects.getProjectId().equals(projectId))
                    mySelectedProject = allProjects;
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return mySelectedProject;
    }

    @Override
    public int deleteProjectbyId(String projectId) {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> writeResult = dbFirestore.collection(COL_NAME).document(String.valueOf(projectId)).delete();
        return 1;
    }

    @Override
    public int updateProjectbyId(String projectId, Projects updatedProject) {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        DocumentReference docRef = dbFirestore.collection(COL_NAME).document(projectId);

        // (async) Update fields
        ApiFuture<WriteResult> future = docRef.
                update(
                        "projectName"  , updatedProject.getProjectName(),
                        "projectId"  , updatedProject.getProjectId(),
                        "projectType"  , updatedProject.getProjectType(),
                        "projectPriority"  , updatedProject.getProjectPriority(),
                        "projectDescription"  , updatedProject.getProjectDescription(),
                        "projectOwner"  , updatedProject.getProjectOwner(),
                        "projectCreatedBy"  , updatedProject.getProjectCreatedBy(),
                        "projectStartTime"  , updatedProject.getProjectStartTime(),
                        "projectEndTime"  , updatedProject.getProjectEndTime(),
                        "projectDuration"  , updatedProject.getProjectDuration(),
                        "projectStatus"  , updatedProject.getProjectStatus()
                );
        return  1;
    }
}
