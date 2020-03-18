package com.example.project;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class LoginActivity extends AppCompatActivity {

    public static UsersDatabase connection;
    public static AttractionDatabase attractionConnection;
    public static wishlistDatabase wishListConnection;
    public static RatingDatabase ratingConnection;


    SharedPreferences prefs;
    SharedPreferences remberMe;
    //to save it so that we will get user for wishlist which user has aadded to wishlist
    public static final String PREFERENCES_NAME = "RupikaDatabase";

    //another again different share preference to save data when check box clicked
    public static final String SAVED_VAlUES = "SaveUserDetailsDatabase";

    boolean isExistsInDb = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        prefs = getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE);

        remberMe = getSharedPreferences(SAVED_VAlUES,Context.MODE_PRIVATE);


        connection = Room.databaseBuilder(getApplicationContext(),UsersDatabase.class,"users").allowMainThreadQueries().build();

        attractionConnection = Room.databaseBuilder(getApplicationContext(),AttractionDatabase.class,"attractions").allowMainThreadQueries().build();

        wishListConnection = Room.databaseBuilder(getApplicationContext(),wishlistDatabase.class,"wishlist").allowMainThreadQueries().build();

        ratingConnection = Room.databaseBuilder(getApplicationContext(),RatingDatabase.class,"ratings").allowMainThreadQueries().build();
        //List<Wishlists> te =  LoginActivity.wishListConnection.wishListDao().getAllWishList();


        if (detailsMatched("admin","admin") == null) {

            User user1 = new User("admin","admin","admin");
            LoginActivity.connection.userDao().addUser(user1);
        }
        //getting values from share preference when rember me clicked
        getUserValueIntoShare();

    }
//it is aving only user name to show which user added to wishlist when particular user loged in
    public void saveData(){

        SharedPreferences.Editor prefsEditor = prefs.edit();

        EditText etUsername = (EditText) findViewById(R.id.etUsername);

        String usernametxt = etUsername.getText().toString();

        prefsEditor.putString("usernameKeyLoged",usernametxt);

        prefsEditor.apply();



    }
    //this is again different shared preference where saving user name and password when you clciked rember me check box
    public void saveUserValuesIntoShared(){

        //chaning name
        CheckBox check = findViewById(R.id.saveValuesid);

        if(check.isChecked()){
        SharedPreferences.Editor prefsEditor2 = remberMe.edit();

        EditText etUsername = (EditText) findViewById(R.id.etUsername);

        EditText etPassword = (EditText) findViewById(R.id.etPassword);
        String passwordtxt = etPassword.getText().toString();


        String usernametxt = etUsername.getText().toString();

        prefsEditor2.putString("usernameKeychecked",usernametxt);
        prefsEditor2.putString("passwordKeychecked",passwordtxt);


        prefsEditor2.apply();

        Toast t = Toast.makeText(this,"data saved",Toast.LENGTH_LONG);
        t.show();

        }
        else{

            SharedPreferences clearData = getSharedPreferences(SAVED_VAlUES,Context.MODE_PRIVATE);
            clearData.edit().clear().commit();
        }

    }

    public void getUserValueIntoShare(){

        String name = remberMe.getString("usernameKeychecked","");
        String pass = remberMe.getString("passwordKeychecked","");

        EditText userValue = findViewById(R.id.etUsername);
        userValue.setText(name);


        EditText passwordValue = findViewById(R.id.etPassword);
        passwordValue.setText(pass);

        CheckBox check = findViewById(R.id.saveValuesid);

        if(name != "" && pass != ""){

            check.setChecked(true);
        }
        else{
            check.setChecked(false);
        }
    }


    public void loginButtonPressed(View view){


        // get username /password from UI
        EditText etUsername = (EditText) findViewById(R.id.etUsername);
        EditText etPassword = (EditText) findViewById(R.id.etPassword);

        String usernametxt = etUsername.getText().toString();
        String passwordtxt = etPassword.getText().toString();



        if(usernametxt.equals("") || passwordtxt.equals("")){
            Toast t = Toast.makeText(this,"Enter data in Fields",Toast.LENGTH_SHORT);
            t.show();
        }else
        {
           User user = detailsMatched(usernametxt,passwordtxt);
            if(user != null){

                if (user.getType().equals("admin")){
                    //Go to admin screen
                    Toast t1 = Toast.makeText(this,"your are admin",Toast.LENGTH_SHORT);
                    t1.show();
                    etUsername.setText("");
                    etPassword.setText("");
                    Intent i = new Intent(getApplicationContext(), AdminAddingPageActivity.class);
                    startActivity(i);
                }else{
                    Toast t = Toast.makeText(this,"Details are correct",Toast.LENGTH_SHORT);
                    t.show();
                    //by default whther user not allowed to save also we are saving user name for wishlist
                    saveData();
                    //when check box clciked then saving data
                    saveUserValuesIntoShared();
                    etUsername.setText("");
                    etPassword.setText("");
                    Intent i = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(i);

                }


            }
            else{
                 //User u = new User(usernametxt,passwordtxt);
                 //Toast.makeText(getApplicationContext(), "Details are incorrect", Toast.LENGTH_SHORT).show();
                 alertBox();

            }
        }

    }

    public void signUpButtonClicked(View view){

        Intent i = new Intent(getApplicationContext(), SignUpActivity.class);
        startActivity(i);
    }

    public User detailsMatched(String username,String password){

        List<User> users;

        users = LoginActivity.connection.userDao().getAllUsers();

        if(users!= null){

             for(int i = 0;i<users.size();i++){

                 String userNameFromUser = users.get(i).getUsername();
                 String passwordFromUser = users.get(i).getPassword();

                 if(userNameFromUser.equals(username) && password.equals(passwordFromUser)){
                     return users.get(i);
                 }
             }
        }
        return null;

    }


    public void alertBox(){
        AlertDialog.Builder popUPBox = new AlertDialog.Builder(LoginActivity.this);
        popUPBox.setTitle("Details are not exists in database");
        popUPBox.setMessage("Sorry,click okay to go to sign In page or click below sign in button");
        popUPBox.setPositiveButton("ok!",new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //write code for positve button
                Toast t = Toast.makeText(getApplicationContext(),"wrongly entered",Toast.LENGTH_LONG);
                t.show();
                Intent i = new Intent(getApplicationContext(), SignUpActivity.class);
                startActivity(i);

            }
        });
        popUPBox.show();
    }


}

