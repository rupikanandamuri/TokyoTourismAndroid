package com.example.project;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Rating.class},version = 1,exportSchema = false)
public abstract class RatingDatabase extends RoomDatabase {

    public abstract RatingDao ratingDao();

}
