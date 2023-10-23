package com.example.finalprojectfirebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Toast;

import com.example.finalprojectfirebase.databinding.ActivityLogInBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class LogIn extends AppCompatActivity {
    ActivityLogInBinding binding;
    FirebaseAuth auth = FirebaseAuth.getInstance();
    UsersData usersData=new UsersData();
    DatabaseReference ref= FirebaseDatabase.getInstance().getReference();
    FirebaseDatabase database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLogInBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        database = FirebaseDatabase.getInstance();

        binding.textViewRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LogIn.this,SignUp.class));
            }
        });


        binding.buttonLogin.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String email=binding.editTextEmail.getText().toString();
                String password = binding.editTextPassword.getText().toString();
                validate(email, password);
            }
        });



    }

    private void validate(String email, String password) {
        if (email.isEmpty() ) {
            Toast.makeText(LogIn.this, "Please enter your email", Toast.LENGTH_SHORT).show();

        } else if (password.isEmpty()) {
            Toast.makeText(LogIn.this, "Please enter your password", Toast.LENGTH_SHORT).show();

        } else {
            auth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()){
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {

                                    startActivity(new Intent(LogIn.this, DoctorHomeActivity.class));

                            }
                        },1000);
                    }
                    else{
                        Toast.makeText(LogIn.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }

            });
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        binding=null;
    }
}