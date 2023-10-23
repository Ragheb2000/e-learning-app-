package com.example.finalprojectfirebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.finalprojectfirebase.databinding.ActivityStudentHomeBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class StudentHomeActivity extends AppCompatActivity {
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference ref =FirebaseDatabase.getInstance().getReference();
    DatabaseReference reference1=FirebaseDatabase.getInstance().getReference();
    FirebaseAuth auth = FirebaseAuth.getInstance();
    ActivityStudentHomeBinding binding;
    RecyclerView recyclerView;
    ArrayList<CourseData> list=new ArrayList<>();
    String name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityStudentHomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        /////////logout
        binding.btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(StudentHomeActivity.this, LogIn.class));
            }
        });


        Students students=new Students();



binding.btnAddMember.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        String courseId=binding.codeOfCourse.getText().toString();

        addCourse();
        addStudentToCourse(courseId);
        binding.codeOfCourse.setText("");
    }
});



        reference1.child("users").child(FirebaseAuth.getInstance().getUid()).orderByChild(FirebaseAuth.getInstance().getUid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot:snapshot.getChildren()){
                    name=String.valueOf(snapshot.child("name").getValue());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    private void addStudentToCourse(String courseId){

        String studentId=FirebaseAuth.getInstance().getUid();
        ref.child(courseId).child("courseName").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot!= null){
                    String courseName=snapshot.getValue(String.class);
                    addStudentToRealTime(courseId,studentId,courseName);
                    addStudentToCourse(courseId,studentId,courseName);

                    Toast.makeText(StudentHomeActivity.this, "Course add", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(StudentHomeActivity.this, "Course notFound", Toast.LENGTH_SHORT).show();
                }
    }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    private  void addStudentToRealTime(String courseId,String studentId,String courseName){
        Students students= new Students(studentId,courseId,name,courseName,0,0,0);
        reference1.child(Const.KEY_MEMBERS).child(studentId).child(courseId).setValue(students);

    }

    private void addStudentToCourse(String courseId,String studentId,String courseName){

        Students students= new Students(studentId,courseId,name,courseName,0,0,0);
        ref.child(Const.KEY_COURSES).child(courseId).child(Const.KEY_MEMBERS).child(studentId).setValue(students);
    }
    private void addCourse(){
        String studentId=FirebaseAuth.getInstance().getUid();
        recyclerView=findViewById(R.id.recyclerView_home_student);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        AdapterAddHimSelf adapterAddHimSelf=new AdapterAddHimSelf(list,this);

        reference1.child(Const.KEY_MEMBERS).child(studentId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                list.clear();
                for(DataSnapshot dataSnapshot:snapshot.getChildren()){
                    CourseData courseData=dataSnapshot.getValue(CourseData.class);
                    list.add(courseData);
                    binding.recyclerViewHomeStudent.setAdapter(adapterAddHimSelf);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}