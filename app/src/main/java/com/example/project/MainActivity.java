package com.example.project;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.core.view.GravityCompat;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.navigation.NavigationView;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.Menu;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, WebViewFragment.OnFragmentInteractionListener {

    private AppBarConfiguration mAppBarConfiguration;

    // UI variables
    Toolbar toolbar;
    DrawerLayout drawer;
    NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Set a Toolbar to replace the ActionBar.

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        // Setup the navigation drawer

        drawer = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.nav_host_fragment, new WelcomeFragment());
        ft.commit();

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // The action bar home/up action should open or close the drawer.
        switch (item.getItemId()) {
            case android.R.id.home:
                drawer.openDrawer(GravityCompat.START);
                return true;
            case R.id.action_Logout:
                Intent i = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(i);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

//    @Override
//    public boolean onSupportNavigateUp() {
//        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
//        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
//                || super.onSupportNavigateUp();
//    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        int selectedMenuItem = item.getItemId();
        switch(selectedMenuItem) {
            //@TODO: Handle menu items here
            case R.id.nav_Attraction:
                Toast t = Toast.makeText(getApplicationContext(), "Attraction clicked", Toast.LENGTH_SHORT);
                t.show();
                Intent i = new Intent(this, AttractionListActivity.class);
                startActivity(i);
                return true;

            case R.id.nav_shedule:
                Toast t1 = Toast.makeText(getApplicationContext(), "shedule clicked", Toast.LENGTH_SHORT);
                t1.show();
                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.nav_host_fragment, new SheduleFragment());
                ft.commit();
                return true;

            case R.id.nav_wish:
                Toast t2 = Toast.makeText(getApplicationContext(), "wishlist clicked", Toast.LENGTH_SHORT);
                t2.show();
                Intent i2 = new Intent(MainActivity.this,WishListActivity.class);
                startActivity(i2);
                return true;
            case R.id.nav_contact:
                Toast t3 = Toast.makeText(getApplicationContext(), "contact clicked", Toast.LENGTH_SHORT);
                t3.show();
                FragmentTransaction ft1 = getSupportFragmentManager().beginTransaction();
                ft1.replace(R.id.nav_host_fragment, new ContactFragment());
                ft1.commit();
                return true;

            default:
                // do nothing
        }

        // after selecting a drawer menu item, show new screen and close the drawer

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
