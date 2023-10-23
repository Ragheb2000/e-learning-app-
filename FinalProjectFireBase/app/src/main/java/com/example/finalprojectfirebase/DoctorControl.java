package com.example.finalprojectfirebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.finalprojectfirebase.databinding.ActivityDoctorControlBinding;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class DoctorControl extends AppCompatActivity {

    DatabaseReference ref = FirebaseDatabase.getInstance().getReference();

    ActivityDoctorControlBinding binding;
    String courseId;
    String doctorId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityDoctorControlBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        courseId=getIntent().getStringExtra("courseId");
        doctorId=getIntent().getStringExtra("doctorId");

        binding.btnAddMember.setText(getIntent().getStringExtra("courseId"));

        binding.btnAddMember.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ClipboardManager clipboardManager = (ClipboardManager)getSystemService(Context.CLIPBOARD_SERVICE);
                clipboardManager.setText( binding.btnAddMember.getText());
                Toast.makeText(DoctorControl.this, "Copied to clipboard", Toast.LENGTH_SHORT).show();


            }
        });
        binding.Attendance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(DoctorControl.this, AttandActivity.class);
                intent.putExtra("courseId",binding.btnAddMember.getText());
                startActivity(intent);
            }
        });
        binding.Grades.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(DoctorControl.this, StudentGrades.class);
                intent.putExtra("courseId",binding.btnAddMember.getText());

                startActivity(intent);
            }
        });

        binding.Quze.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(DoctorControl.this, QizeActivity.class);
                intent.putExtra("courseId",binding.btnAddMember.getText());
                startActivity(intent);
            }
        });
        binding.Material.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(DoctorControl.this, MatrialActivity.class);
                intent.putExtra("courseId",binding.btnAddMember.getText());
                startActivity(intent);
            }
        });
    }
}