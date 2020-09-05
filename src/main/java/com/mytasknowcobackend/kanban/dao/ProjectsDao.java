package com.mytasknowcobackend.kanban.dao;

import com.mytasknowcobackend.kanban.model.Projects;

import java.util.List;

public interface ProjectsDao {


    int addProjects(Projects projects);

    List<Projects> selectAllProjects();

    Projects selectProjectbyId(String projectId);

    int deleteProjectbyId(String projectId);

    int updateProjectbyId(String projectId, Projects updatedProject);

}
