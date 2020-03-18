package com.example.project;


import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {User.class},version = 1,exportSchema = false)

public abstract class UsersDatabase extends RoomDatabase {

    public abstract UserDao userDao();
}
