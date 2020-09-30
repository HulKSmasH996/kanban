package com.mytasknowcobackend.kanban.dao;

import com.mytasknowcobackend.kanban.model.Tasks;

import java.util.List;

public interface TasksDao {

    int addTasks(Tasks tasks);

    List<Tasks> selectAllTasks();

    Tasks selectTaskbyId(String taskId);

    int deleteTaskbyId(String taskId);

    int updateTaskbyId(String taskId, Tasks updatedTask);
}
