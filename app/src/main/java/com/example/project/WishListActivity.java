package com.example.project;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class WishListActivity extends AppCompatActivity {

    SharedPreferences prefs;
    public static final String PREFERENCES_NAME = "RupikaDatabase";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wish_list);

        // setup toolbar
        Toolbar t1 = (Toolbar) findViewById(R.id.toolbarAttractionwishScreen);
        setSupportActionBar(t1);

        // add an up bar
        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);

        prefs = getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE);

        SharedPreferences.Editor prefsEditor = prefs.edit();


        String name = prefs.getString("usernameKeyLoged","");


        List<Wishlists> wishListData = LoginActivity.wishListConnection.wishListDao().getWishlistByUser(name);

        ArrayList<String> wishlistValue = new ArrayList<String>();
        for (Wishlists wish : wishListData){
             wishlistValue.add(wish.getWishAttractionName());
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,wishlistValue);
        ListView list = (ListView) findViewById(R.id.listViewwishid);
        list.setAdapter(adapter);
    }



}
