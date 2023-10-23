package com.example.finalprojectfirebase;

import static java.security.AccessController.getContext;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.finalprojectfirebase.databinding.ActivityMainBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class DoctorHomeActivity extends AppCompatActivity {
    DatabaseReference ref =FirebaseDatabase.getInstance().getReference();

    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        ArrayList<CourseData> list=new ArrayList<>();
        AdapterGrade adapterGrade=new AdapterGrade(list,getApplicationContext());
        LinearLayoutManager layoutManager =new LinearLayoutManager(this);
        binding.recyclerInstructorCourse.setLayoutManager(layoutManager);


        ref.child(Const.KEY_COURSES).orderByChild("instructorId").equalTo(FirebaseAuth.getInstance().getUid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                list.clear();

                    for (DataSnapshot snapshot1 : snapshot.getChildren()) {
                        list.add(snapshot1.getValue(CourseData.class));
                        binding.recyclerInstructorCourse.setAdapter(adapterGrade);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {


            }

        });

        binding.btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(DoctorHomeActivity.this, LogIn.class));
            }
        });

        binding.btnAddCourse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DoctorHomeActivity.this,DoctorAddCourse.class));
            }
        });
    }


}