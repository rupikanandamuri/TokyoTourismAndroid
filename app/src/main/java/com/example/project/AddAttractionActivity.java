package com.example.project;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class AddAttractionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_attraction);

        // setup toolbar
        Toolbar t1 = (Toolbar) findViewById(R.id.toolbaraddAttractionScreen);
        setSupportActionBar(t1);

    }

    public void addIntoDataBase(View view){

        EditText attractNameValue = (EditText) findViewById(R.id.nameedittxt);
        EditText attractpriceValue = (EditText) findViewById(R.id.priceedittxt);
        EditText attractAddressValue = (EditText) findViewById(R.id.adressedittxt);
        EditText attractdescValue = (EditText) findViewById(R.id.decsedittx);
        EditText attractimageValue = (EditText) findViewById(R.id.imageedittxt);
        EditText attractionVideoValue = (EditText) findViewById(R.id.videoEditTxt);
        EditText attractlongdescValue = (EditText) findViewById(R.id.longdescedittxt);


        String nameofAttraction = attractNameValue.getText().toString();
        String priceofAttraction = attractpriceValue.getText().toString();
        String addressofAttraction = attractAddressValue.getText().toString();
        String descAttraction = attractdescValue.getText().toString();
        String imageAttraction = attractimageValue.getText().toString();
        String videoAttraction = attractionVideoValue.getText().toString();
        String londescAttraction = attractlongdescValue.getText().toString();

        Attraction att = new Attraction(nameofAttraction,priceofAttraction,addressofAttraction,descAttraction,imageAttraction,videoAttraction,londescAttraction);

        LoginActivity.attractionConnection.attractionDao().addAttractions(att);

        Toast t = Toast.makeText(this,"added to data base",Toast.LENGTH_LONG);
        t.show();
        attractNameValue.setText("");
        attractpriceValue.setText("");
        attractAddressValue.setText("");
        attractimageValue.setText("");
        attractlongdescValue.setText("");
        attractionVideoValue.setText("");
        attractdescValue.setText("");

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // The action bar home/up action should open or close the drawer.
        switch (item.getItemId()) {
            case R.id.action_Logout:
                Intent i = new Intent(AddAttractionActivity.this, LoginActivity.class);
                startActivity(i);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
