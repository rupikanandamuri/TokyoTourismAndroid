package com.example.project;


import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface wishListDao {

    @Insert
    public void addwishlist(Wishlists wishlists);

    @Delete
    public void deleteWhislist(Wishlists wishlists);

    @Query("Select * from wishlistsattract")
    List<Wishlists> getAllWishList();

    @Update
    public void updateWishList(Wishlists wishlists);

    @Query("SELECT * FROM wishlistsattract " +
            "WHERE wishlistsattract.username = :username")
    public List<Wishlists> getWishlistByUser(String username);

}
