package com.example.project;


import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface RatingDao {

    @Insert
    public void addRating(Rating rating);

    @Delete
    public void deleteRating(Rating rating);

    @Query("Select * from ratings")
    List<Rating> getAllRating();

    @Update
    public void updateRating(Rating rating);

    @Query("SELECT * FROM RATINGS " +
            "WHERE ratings.attractionName = :attractionName")
    public List<Rating> getRatingByAttraction(String attractionName);

//    @Query("SELECT * FROM RATINGS " +
//            "WHERE attractionName = :attractionName AND userName = :username ")
//    public List<Rating> getRatingByAttractionName(String attractionName,String username);
}
