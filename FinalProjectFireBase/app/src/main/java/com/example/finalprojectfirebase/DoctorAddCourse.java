package com.example.finalprojectfirebase;

import static java.security.AccessController.getContext;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.finalprojectfirebase.databinding.ActivityDoctorAddCourseBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class DoctorAddCourse extends AppCompatActivity {
ActivityDoctorAddCourseBinding binding;


     CourseData courseData=new CourseData();
    FirebaseAuth auth = FirebaseAuth.getInstance();
    DatabaseReference ref =FirebaseDatabase.getInstance().getReference();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityDoctorAddCourseBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        binding.btnCreateCourse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               String courseName = binding.editCourseName.getText().toString().trim() ;
              String  quizGrade  = binding.editQuizGrade.getText().toString().trim();
             String   attendGrade  = binding.editAttendGrade.getText().toString().trim();
             String   projectGrade  = binding.editProjectGrade.getText().toString().trim();
                if (courseName.isEmpty()){
                    binding.editCourseName.setError("Required");
                }else if (quizGrade.isEmpty()){
                    binding.editQuizGrade.setError("Required");
                }else if (attendGrade.isEmpty()){
                    binding.editAttendGrade.setError("Required");
                }else if (projectGrade.isEmpty()){
                    binding.editProjectGrade.setError("Required");
                }else {
                    String id = ref.push().getKey();
                    ref.child(Const.KEY_COURSES).child(id).setValue(new CourseData
                            (auth.getUid(), id, courseName, quizGrade, attendGrade, projectGrade));
                    startActivity(new Intent(DoctorAddCourse.this, DoctorHomeActivity.class));
                }
            }
        });
    }


}