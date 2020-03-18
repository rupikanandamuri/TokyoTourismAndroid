package com.example.project;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "attractions")

public class Attraction {

    @PrimaryKey @NonNull

    @ColumnInfo

    private String attractionName;
    private String price;
    private String attractionAddress;
    private String attractionDesc;
    private String photo;
    private String video;
    private String longDescription;

    public Attraction(@NonNull String attractionName,String price,@NonNull String attractionAddress,String attractionDesc, String photo,String video,String longDescription) {
        this.attractionName = attractionName;
        this.price = price;
        this.attractionAddress = attractionAddress;
        this.attractionDesc = attractionDesc;
        this.photo = photo;
        this.video = video;
        this.longDescription = longDescription;
    }

    public String getAttractionName() {
        return attractionName;
    }

    public void setAttractionName(String attractionName) {
        this.attractionName = attractionName;
    }

    public String getAttractionAddress() {
        return attractionAddress;
    }

    public void setAttractionAddress(String attractionAddress) {
        this.attractionAddress = attractionAddress;
    }

    public String getPhoto() {

        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getAttractionDesc() {

        return attractionDesc;
    }

    public void setAttractionDesc(String attractionDesc) {

        this.attractionDesc = attractionDesc;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getLongDescription() {
        return longDescription;
    }

    public void setLongDescription(String longDescription) {
        this.longDescription = longDescription;
    }

    public String getVideo() {
        return video;
    }

    public void setVideo(String video) {
        this.video = video;
    }
}
