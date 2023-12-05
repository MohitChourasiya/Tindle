package com.user.tindle;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;
import com.user.tindle.Activity.HomePage;
import com.user.tindle.Activity.MainActivity;

public class SplashScreen extends AppCompatActivity {

    FirebaseAuth auth;
    FirebaseDatabase database;
    FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        database = FirebaseDatabase.getInstance();
        auth = FirebaseAuth.getInstance();
        user = FirebaseAuth.getInstance().getCurrentUser();





        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (user!= null){
                Intent i = new Intent(SplashScreen.this, HomePage.class);
                startActivity(i);
                finish();
                }else{
                    Intent v = new Intent(SplashScreen.this,MainActivity.class);
                    startActivity(v);
                    finish();
                }
            }
        },2000 );
    }
    }
