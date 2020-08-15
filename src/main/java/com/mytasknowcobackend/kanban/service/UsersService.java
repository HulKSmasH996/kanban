package com.mytasknowcobackend.kanban.service;

import com.mytasknowcobackend.kanban.dao.UsersDao;
import com.mytasknowcobackend.kanban.model.Issues;
import com.mytasknowcobackend.kanban.model.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsersService {

    private final UsersDao usersDao;

    @Autowired
    public UsersService(@Qualifier("usersfirestoredao") UsersDao usersDao) {
        this.usersDao = usersDao;
    }

    public int addUsers(Users user) {
        return usersDao.addUser(user);
    }

    public List<Users> returnAllUsers(){  return usersDao.selectAllUsers();  }

    public  Users selectUserbyId(String userId){ return  usersDao.selectUserbyId(userId); }

    public void deleteUserbyId(String userId){  usersDao.deleteUserbyId(userId); }

    public int updateUserbyId(String userId, Users updatedUser){ return usersDao.updateUserbyId(userId,updatedUser); }


}
