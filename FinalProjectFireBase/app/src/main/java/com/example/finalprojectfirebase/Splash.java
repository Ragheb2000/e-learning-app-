package com.example.finalprojectfirebase;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.google.firebase.auth.FirebaseAuth;

public class Splash extends AppCompatActivity {

    FirebaseAuth auth=FirebaseAuth.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

new Handler().postDelayed(new Runnable() {
    @Override
    public void run() {

        startActivity(new Intent(Splash.this,ChooseCheackBox.class));



    }
},3000);

        }


}