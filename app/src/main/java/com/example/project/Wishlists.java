package com.example.project;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(tableName = "wishlistsattract")
public class Wishlists {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    private int id;

    @ColumnInfo
    private String username;
    private String wishAttractionName;

    public Wishlists(@NonNull String username, String wishAttractionName) {
        this.username = username;
        this.wishAttractionName = wishAttractionName;
    }

    @NonNull
    public String getUsername() {
        return username;
    }

    @NonNull
    public int getId() {
        return id;
    }

    public void setId(@NonNull int id) {
        this.id = id;
    }

    public void setUsername(@NonNull String username) {
        this.username = username;
    }

    public String getWishAttractionName() {
        return wishAttractionName;
    }

    public void setWishAttractionName(String wishAttractionName) {
        this.wishAttractionName = wishAttractionName;
    }
}
