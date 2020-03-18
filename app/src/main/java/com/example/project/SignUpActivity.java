package com.example.project;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

public class SignUpActivity extends AppCompatActivity {

    boolean isExistsInDb = false;
    //that is to see whether box os checked or not
    boolean checkBoxSelected = false;
    String userType;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

//        if (detailsMatched("admin","admin")== false) {
//
//            User user1 = new User("admin","admin","admin");
//            LoginActivity.connection.userDao().addUser(user1);
//        }

    }

    public void signUpButtonPressed(View view){

        // get username /password from UI
        EditText etUsername = (EditText) findViewById(R.id.etUsernametxt);
        EditText etPassword = (EditText) findViewById(R.id.etPasswordtxt);

        String usernametxt = etUsername.getText().toString();
        String passwordtxt = etPassword.getText().toString();
        userType = "user";

        if(usernametxt.equals("") || passwordtxt.equals("")){
            Toast t = Toast.makeText(this,"Empty Fields",Toast.LENGTH_SHORT);
            t.show();
        }else
        {
            isExistsInDb = detailsMatched(usernametxt,passwordtxt);
            if(isExistsInDb){

                Toast t = Toast.makeText(this,"Details exists in data base",Toast.LENGTH_SHORT);
                t.show();
                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(i);

            }
            else{
                User u = new User(usernametxt,passwordtxt,userType);
                LoginActivity.connection.userDao().addUser(u);
                Toast.makeText(getApplicationContext(), "Details added!", Toast.LENGTH_SHORT).show();
                etUsername.setText("");
                etPassword.setText("");

                Intent i = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(i);

            }
        }

    }

//    public void checkBoxButtonClicked(View view) {
//
//        checkBoxSelected = checkBox.isChecked();
//    }

    public boolean detailsMatched(String username,String password){

        boolean isavailable = false;

        List<User> users;

        users = LoginActivity.connection.userDao().getAllUsers();

         if(users!= null){

            for(int i = 0;i<users.size();i++){

                String userNameFromUser = users.get(i).getUsername();
                String passwordFromUser = users.get(i).getPassword();

                if(userNameFromUser.equals(username) && password.equals(passwordFromUser)){

                    isavailable = true;
                    return isavailable;
                }

            }
        }
        else {

            isavailable = false;
            return isavailable;
        }


        return isavailable;
    }

}
