package com.example.finalprojectfirebase;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;

import com.example.finalprojectfirebase.databinding.ActivityChooseCheackBoxBinding;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ChooseCheackBox extends AppCompatActivity {

    ActivityChooseCheackBoxBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=  ActivityChooseCheackBoxBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

       binding.student.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
           @Override
           public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
               if(isChecked) {
                   binding.doctor.setChecked(false);
                   Const.userType=Const.KEY_STUDENT;
               }
               else {
                   Const.userType = "";

               }
           }
       });
       binding.doctor.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
           @Override
           public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
               if (isChecked){
                   binding.student.setChecked(false);
                  Const.userType=Const.KEY_DOCTOR;
               }
               else {
                   Const.userType ="";

               }
           }
       });
        binding.btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ChooseCheackBox.this,SignUp.class));
            }
        });

    }


}