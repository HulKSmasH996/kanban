package com.mytasknowcobackend.kanban.api;

import com.mytasknowcobackend.kanban.model.Issues;
import com.mytasknowcobackend.kanban.model.Tasks;
import com.mytasknowcobackend.kanban.model.Users;
import com.mytasknowcobackend.kanban.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = {"https://mytasknowco-9d4d5.web.app","http://localhost"})
@RequestMapping("api/v1/users")
@RestController
public class UsersController {

    private final UsersService usersService;

    @Autowired
    public UsersController(UsersService usersService) {
        this.usersService = usersService;
    }


    @RequestMapping(value="/addUser",method = RequestMethod.POST)
    public ResponseEntity<?> addUser(@RequestBody Users users) {
        if(usersService.addUsers(users)!=0) {
            return new ResponseEntity<>(usersService.addUsers(users), HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(0, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

   @GetMapping
    public List<Users> returnallUsers(){
        return  usersService.returnAllUsers();
   }


    @RequestMapping(value="/getUserbyId", method = RequestMethod.GET)
    public ResponseEntity<?> getUserbyId(@RequestParam(name = "userId") String userId) {
        if(usersService.selectUserbyId(userId)!=null) {
            return new ResponseEntity<>(usersService.selectUserbyId(userId), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value="/deleteUserbyId", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteTaskbyId(@RequestParam(name = "userId") String UserId) {
        if(usersService.deleteUserbyId(UserId)!=0) {
            return new ResponseEntity<>(usersService.deleteUserbyId(UserId), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(0, HttpStatus.NOT_FOUND);
        }
    }

   /* @PutMapping(path = "{userId}")
    public  void  updateUserbyId(@PathVariable("userId") String userId, @RequestBody Users updatedUser){
        usersService.updateUserbyId(userId,updatedUser);
    }*/

    @RequestMapping(value = "/updateUserbyId",method = RequestMethod.PUT)
    public ResponseEntity<?> updateUserbyId(@RequestParam(name = "userId")String userId,@RequestBody Users users) {
        if(usersService.updateUserbyId(userId,users)!=0) {
            return new ResponseEntity<>(usersService.updateUserbyId(userId,users), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(0, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
