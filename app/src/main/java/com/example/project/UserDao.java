package com.example.project;


import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface UserDao {

    @Insert
    public void addUser(User user);

    @Delete
    public void deleteUser(User user);

    @Query("Select * from users")
    List<User> getAllUsers();

    @Update
    public void updateUser(User user);

}
