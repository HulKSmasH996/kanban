package com.mytasknowcobackend.kanban.api;

import com.mytasknowcobackend.kanban.model.Tasks;
import com.mytasknowcobackend.kanban.service.TasksService;
import com.mytasknowcobackend.kanban.service.TasksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = {"https://mytasknowco-9d4d5.web.app","http://localhost"})
@RequestMapping("api/v1/tasks")
@RestController

public class TasksController {
    
    private final TasksService tasksService;

    @Autowired
    public TasksController(TasksService tasksService) {
        this.tasksService = tasksService;
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> addTask(@RequestBody Tasks tasks) {
        if(tasksService.addTask(tasks)!=0) {
            return new ResponseEntity<>(tasksService.addTask(tasks), HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(0, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping
    public List<Tasks> returnAllTasks(){
        return tasksService.returnAllTasks();
    }

    @RequestMapping(value="/getTaskbyId", method = RequestMethod.GET)
    public ResponseEntity<?> getTaskbyId(@RequestParam(name = "taskId") String taskId) {
        if(tasksService.selectTaskbyId(taskId)!=null) {
            return new ResponseEntity<>(tasksService.selectTaskbyId(taskId), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }
    @RequestMapping(value="/getTaskbyUserId", method = RequestMethod.GET)
    public ResponseEntity<?> getTaskbyICreator(@RequestParam(name = "userId") String taskId) {
        if(!tasksService.selectTaskbyCreator(taskId).isEmpty()) {
            return new ResponseEntity<>(tasksService.selectTaskbyCreator(taskId), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }
   /* @DeleteMapping(path = "{taskId}")
    public int deleteTasksbyId(@PathVariable("taskId") String taskId){
        return tasksService.deleteTaskbyId(taskId);
    }*/

    @RequestMapping(value="/deleteTaskbyId", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteTaskbyId(@RequestParam(name = "taskId") String taskId) {
        if(tasksService.deleteTaskbyId(taskId)!=0) {
            return new ResponseEntity<>(tasksService.deleteTaskbyId(taskId), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(0, HttpStatus.NOT_FOUND);
        }
    }


    @RequestMapping(value = "/updateTaskbyId",method = RequestMethod.PUT)
    public ResponseEntity<?> updateTaskbyId(@RequestParam(name = "taskId")String taskId,@RequestBody Tasks tasks) {
        if(tasksService.updateTaskbyId(taskId,tasks)!=0) {
            return new ResponseEntity<>(tasksService.updateTaskbyId(taskId,tasks), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(0, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
