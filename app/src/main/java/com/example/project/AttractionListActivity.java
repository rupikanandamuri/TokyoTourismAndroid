package com.example.project;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class AttractionListActivity extends AppCompatActivity {

    ArrayList<Attraction> attractionsData = new ArrayList<Attraction>();

    ListView attractionListView;

    List<Attraction> attractionsValue;

    AttractionListViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attraction_list);

        // setup toolbar
        Toolbar t1 = (Toolbar) findViewById(R.id.toolbarAttractionListScreen);
        setSupportActionBar(t1);

        // add an up bar
        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);

        attractionsValue = LoginActivity.attractionConnection.attractionDao().getAllAttraction();

        attractionsData = (ArrayList<Attraction>) attractionsValue;

        //add adapter

        adapter = new AttractionListViewAdapter(this,R.layout.attraction_row_layout,attractionsData);

        attractionListView = (ListView) findViewById(R.id.AttractionListView);

        attractionListView.setAdapter(adapter);

        attractionListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Attraction a = (Attraction) attractionListView.getItemAtPosition(position);
                String atrractionValue = a.getAttractionName();
                String videoValue = a.getVideo();
                String priceValueget = a.getPrice();
                String longdescValue = a.getLongDescription();
                String imageValue = a.getPhoto();

                Intent i = new Intent(AttractionListActivity.this,DetailAttractionActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("videoKey",videoValue);
                bundle.putString("pricekey",priceValueget);
                bundle.putString("longdescKey",longdescValue);
                bundle.putString("imageValueKey",imageValue);
                bundle.putString("attractionnameVlue",atrractionValue);
                i.putExtras(bundle);
                startActivity(i);

                Toast t = Toast.makeText(getApplicationContext(), a.getAttractionName(), Toast.LENGTH_SHORT);
                t.show();


            }
        });

    }
}
