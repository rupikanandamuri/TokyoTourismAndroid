package com.example.project;


import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "users")

public class User {

    @PrimaryKey @NonNull
    @ColumnInfo
    private String username;

    private String password;

    private String type;

//    public User(@NonNull String username){
//        this.username = username;
//    }


    public User(@NonNull String username,@NonNull String password,@NonNull String type) {
        this.username = username;
        this.password = password;
        this.type = type;

    }

    public String getUsername() {

        return username;
    }

    public void setUsername(String username) {

        this.username = username;
    }

    public String getPassword() {

        return password;
    }

    public void setPassword(String password) {

        this.password = password;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", type = '"+type+
                '}';
    }
}
