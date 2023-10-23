package com.example.finalprojectfirebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.finalprojectfirebase.databinding.ActivitySignUpBinding;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignUp extends AppCompatActivity {


    UsersData usersData=new UsersData();
    ActivitySignUpBinding binding;
    FirebaseAuth auth=FirebaseAuth.getInstance();
    DatabaseReference ref =FirebaseDatabase.getInstance().getReference();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivitySignUpBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.buttonSign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name=binding.editTextName.getText().toString();
                String email=binding.editTextEmail.getText().toString();
                String password=binding.editTextPassword.getText().toString();
                validate(name,email,password);

            }
        });
        binding.back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SignUp.this,LogIn.class));
            }
        });
    }
    private void validate(String name, String email, String password){

        if(name.isEmpty()){
            binding.editTextName.setError(getString( R.string.required));
        }else if(email.isEmpty()){
            binding.editTextEmail.setError(getString(R.string.required));
        }else if(password.isEmpty()){
            binding.editTextPassword.setError(getString(R.string.required));
        }
        else {
            startAuth( name,  email, password);
        }
    }
    private void startAuth(String name, String email, String password){


        auth.createUserWithEmailAndPassword(email,password)
                .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {

                        String id=ref.push().getKey();

                        ref.child(Const.KEY_USERS).child(id)
                                .setValue(new UsersData(email,id,name,Const.userType));
                        if (Const.userType.equalsIgnoreCase("doctor")){
                            startActivity(new Intent(SignUp.this, DoctorHomeActivity.class));
                        }else if(Const.userType.equalsIgnoreCase("student")){
                            startActivity(new Intent(SignUp.this, StudentHomeActivity.class));
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(SignUp.this,e.getLocalizedMessage(),Toast.LENGTH_LONG).show();
                    }
                });


    }




    @Override
    protected void onDestroy() {
        super.onDestroy();
        binding=null;
    }






}