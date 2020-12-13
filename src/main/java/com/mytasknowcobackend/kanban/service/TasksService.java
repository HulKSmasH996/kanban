package com.mytasknowcobackend.kanban.service;

import com.mytasknowcobackend.kanban.dao.TasksDao;
import com.mytasknowcobackend.kanban.model.Tasks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TasksService {
    
    private final TasksDao tasksDao;

    @Autowired
    public TasksService(@Qualifier("taskfirestoredao") TasksDao tasksDao) {
        this.tasksDao = tasksDao;
    }

    public int addTask(Tasks tasks) {
        return tasksDao.addTasks(tasks);
    }

    public List<Tasks> returnAllTasks(){
        return tasksDao.selectAllTasks();
    }

    public List<Tasks> selectTaskbyCreator(String userId){
        return tasksDao.selectTaskbyCreator(userId);
    }

    public  Tasks selectTaskbyId(String taskId){ return  tasksDao.selectTaskbyId(taskId); }

    public int deleteTaskbyId(String taskId){ return tasksDao.deleteTaskbyId(taskId); }

    public int updateTaskbyId(String taskId, Tasks updatedtask){ return tasksDao.updateTaskbyId(taskId,updatedtask); }



}
