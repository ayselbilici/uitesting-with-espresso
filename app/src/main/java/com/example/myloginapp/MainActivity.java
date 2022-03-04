package com.example.myloginapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        TextView username =(TextView) findViewById(R.id.username);
        TextView password =(TextView) findViewById(R.id.password);
        TextView warningMessage =(TextView) findViewById(R.id.warningMessage);

        MaterialButton loginbtn = (MaterialButton) findViewById(R.id.loginbtn);

        //admin and admin

        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {
                    String usernameVal = username.getText().toString();
                    String passwordVal = password.getText().toString();

                    if (!(usernameVal.length() >0) || !(passwordVal.length() >0)){
                        warningMessage.setTextColor(Color.YELLOW);
                        warningMessage.setText("Email and password should not be empty");
                    }
                    else if (!usernameVal.contains("@") || !usernameVal.contains(".")){
                        warningMessage.setTextColor(Color.YELLOW);
                        warningMessage.setText("Email format not correct");
                    }
                    else if (passwordVal.length() < 5){
                        warningMessage.setTextColor(Color.YELLOW);
                        warningMessage.setText("Password should be minimum 5 characters");
                    }
                    else if (username.getText().toString().equals("admin@fireflyon.com") && password.getText().toString().equals("admin")){
                        //correct
                        warningMessage.setTextColor(Color.GREEN);
                        warningMessage.setText("LOGIN SUCCESSFUL!");
                        Thread.sleep(1000);
                        openWelcomePage();
                    }
                    else {
                        //incorrect
                        warningMessage.setTextColor(Color.YELLOW);
                        warningMessage.setText("LOGIN FAILED!");
                    }
                }
                catch(Exception ex) {

                }
            }
        });


    }

    public void openWelcomePage(){
        Intent intent = new Intent(this, WelcomeActivity.class);
        startActivity(intent);
    }
}