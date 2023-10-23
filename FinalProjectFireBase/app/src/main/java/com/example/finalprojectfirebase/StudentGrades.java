package com.example.finalprojectfirebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.os.Bundle;

import com.example.finalprojectfirebase.databinding.ActivityStudentGradesBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class StudentGrades extends AppCompatActivity {

    DatabaseReference ref= FirebaseDatabase.getInstance().getReference("members");;
    ActivityStudentGradesBinding binding;
    RecyclerView recyclerView;
    public static String courseId;
    ArrayList<Students>list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityStudentGradesBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        courseId=getIntent().getStringExtra("courseId");
        recyclerView =binding.recyclerStudentGrade;
        recyclerView.setHasFixedSize(true);
        list=new ArrayList<>();
        LinearLayoutManager layoutManager =new LinearLayoutManager(this);
       recyclerView.setLayoutManager(layoutManager);
        AdapterShowStudent adapterShowStudent=new AdapterShowStudent(list,getApplicationContext());

        ref.child(FirebaseAuth.getInstance().getUid()).child(courseId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                list.clear();
                Students student=snapshot.getValue(Students.class);
                list.add(student);
                binding.recyclerStudentGrade.setAdapter(adapterShowStudent);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
    }
