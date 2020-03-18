package com.example.project;


import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "ratings")
public class Rating {


    @PrimaryKey(autoGenerate = true)
    @NonNull
    private int ratingid;

   private float ratingVlaue;
   private String attractionName;
   private String userName;


    public Rating(float ratingVlaue, String attractionName,String userName) {
        this.ratingVlaue = ratingVlaue;
        this.attractionName = attractionName;
        this.userName = userName;
    }

    public float getRatingVlaue() {
        return ratingVlaue;
    }

    public void setRatingVlaue(float ratingVlaue) {
        this.ratingVlaue = ratingVlaue;
    }

    public String getAttractionName() {
        return attractionName;
    }

    public void setAttractionName(String attractionName) {
        this.attractionName = attractionName;
    }

    public int getRatingid() {
        return ratingid;
    }

    public void setRatingid(int ratingid) {
        this.ratingid = ratingid;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
