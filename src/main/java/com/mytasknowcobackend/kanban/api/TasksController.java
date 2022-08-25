package com.mytasknowcobackend.kanban.api;

import com.mytasknowcobackend.kanban.model.Tasks;
import com.mytasknowcobackend.kanban.service.TasksService;
import com.mytasknowcobackend.kanban.service.TasksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("api/v1/tasks")
@RestController

public class TasksController {
    
    private final TasksService tasksService;

    @Autowired
    public TasksController(TasksService tasksService) {
        this.tasksService = tasksService;
    }

    @PostMapping
    public void addTask(@RequestBody Tasks tasks) {
        tasksService.addTask(tasks);
    }

    @GetMapping
    public List<Tasks> returnAllTasks(){
        return tasksService.returnAllTasks();
    }

    /*@GetMapping(path = "{taskId}")
    public  Tasks selectTasksbyId(@PathVariable ("taskId") String taskId) { return tasksService.selectTaskbyId(taskId); }
*/
   /* @GetMapping(path="getTaskbyId")
    public  List<Tasks> selectTasksbyIdv2(@RequestParam(name = "taskId") String taskId){
        //if(tasksService.selectTaskbyId(taskId)!=null)
        return tasksService.selectTaskbyId(taskId);
       // else
    }*/

    @RequestMapping(value="/getTaskbyId", method = RequestMethod.GET)
    public ResponseEntity<?> getTaskbyId(@RequestParam(name = "taskId") String taskId) {
        if(tasksService.selectTaskbyId(taskId)!=null) {
            return new ResponseEntity<>(tasksService.selectTaskbyId(taskId), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(path = "user-"+"{userId}")
    public  List<Tasks> selectTasksbyCreator(@PathVariable ("userId") String userId) { return tasksService.selectTaskbyCreator(userId); }


    @DeleteMapping(path = "{taskId}")
    public int deleteTasksbyId(@PathVariable("taskId") String taskId){
        return tasksService.deleteTaskbyId(taskId);
    }

    @RequestMapping(value="/deleteTaskbyId", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteTaskbyId(@RequestParam(name = "taskId") String taskId) {
        if(tasksService.deleteTaskbyId(taskId)!=0) {
            return new ResponseEntity<>(tasksService.selectTaskbyId(taskId), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(0, HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping(path = "{taskId}")
    public  int  updateTaskbyId(@PathVariable("taskId") String taskId, @RequestBody Tasks updatedtask){
        return tasksService.updateTaskbyId(taskId,updatedtask);
    }


}
