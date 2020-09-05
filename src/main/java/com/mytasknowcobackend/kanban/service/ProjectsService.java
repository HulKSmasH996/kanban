package com.mytasknowcobackend.kanban.service;

import com.mytasknowcobackend.kanban.dao.ProjectsDao;
import com.mytasknowcobackend.kanban.model.Issues;
import com.mytasknowcobackend.kanban.model.Projects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectsService {

    private  final ProjectsDao projectsDao;

    @Autowired
    public ProjectsService(@Qualifier("projectsfirestoredao") ProjectsDao projectsDao) {
        this.projectsDao = projectsDao;
    }

    public int addProject(Projects projects) {
        return projectsDao.addProjects(projects);
    }

    public List<Projects> returnAllProjects(){
        return projectsDao.selectAllProjects();
    }

    public  Projects selectProjectbyId(String projectId){ return  projectsDao.selectProjectbyId(projectId); }

    public int deleteProjectbyId(String projectId){ return projectsDao.deleteProjectbyId(projectId); }

    public int updateProjectbyId(String projectId, Projects updatedProject){ return projectsDao.updateProjectbyId(projectId,updatedProject); }

}
