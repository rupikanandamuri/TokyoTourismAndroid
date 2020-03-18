package com.example.project;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface AttractionDao {

    @Insert
    public void  addAttractions(Attraction attraction);

    //delete from data base add anotation delete

    @Delete
    public  void deleteAttraction(Attraction attraction);

    //anotate which query and also select all the users from users(here we change the name of User into users)


    @Query("Select * from ATTRACTIONS")
    List<Attraction> getAllAttraction();

    @Update
    public void updateAttraction(Attraction attraction);
}
