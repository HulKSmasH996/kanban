package com.mytasknowcobackend.kanban.dao;

import com.mytasknowcobackend.kanban.model.Issues;
import com.mytasknowcobackend.kanban.model.Users;

import java.util.List;

public interface UsersDao {


    int addUser(Users user);

    List<Users> selectAllUsers();

    Users selectUserbyId(String userId);

    int deleteUserbyId(String userId);

    int updateUserbyId(String userId, Users updatedUser);

}
