package com.example.project;


import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Wishlists.class,User.class},version = 1,exportSchema = false)

public abstract class wishlistDatabase extends RoomDatabase {

    public abstract wishListDao wishListDao();

}
