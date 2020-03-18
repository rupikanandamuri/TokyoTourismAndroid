package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class AdminAddingPageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_adding_page);
    }

    //attratction
    public void addAttractionButtonClciked(View view){

        Toast t = Toast.makeText(this,"Add attraction clicked",Toast.LENGTH_LONG);
        t.show();
        Intent intent = new Intent(AdminAddingPageActivity.this, AddAttractionActivity.class);
        startActivity(intent);


    }

    //logout
    public void logoutButtonClicked(View view){

        Toast t = Toast.makeText(this,"Logout clicked",Toast.LENGTH_LONG);
        t.show();
        Intent intent = new Intent(AdminAddingPageActivity.this, LoginActivity.class);
        startActivity(intent);
        finish();

    }
}
