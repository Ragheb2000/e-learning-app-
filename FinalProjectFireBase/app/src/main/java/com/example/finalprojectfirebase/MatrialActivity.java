package com.example.finalprojectfirebase;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.finalprojectfirebase.databinding.ActivityMainBinding;
import com.example.finalprojectfirebase.databinding.ActivityMatrialBinding;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.ArrayList;

public class MatrialActivity extends AppCompatActivity {

    ActivityMatrialBinding binding;
    FirebaseStorage storage = FirebaseStorage.getInstance();
    Uri pdf;
    public static String courseId;
    DatabaseReference ref=FirebaseDatabase.getInstance().getReference("Courses Materials");
    ArrayList<String> material_list=new ArrayList<>();
    DatabaseReference reference= FirebaseDatabase.getInstance().getReference();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityMatrialBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        courseId=getIntent().getStringExtra("courseId");
        AdapterPdf adapterPdf= new AdapterPdf();

        ref.child(courseId).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.getValue()!=null)
                    material_list.clear();
               for(DataSnapshot dataSnapshot:snapshot.getChildren()){
                    material_list.add(dataSnapshot.getValue(String.class));
                    adapterPdf.setList(material_list);
                    binding.recyclerPdf.setAdapter(adapterPdf);
               }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        binding.add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                intent.setAction(Intent.ACTION_GET_CONTENT);
                intent.setType("application/pdf");
                intent.addCategory(Intent.CATEGORY_OPENABLE);
                startActivityForResult(intent, 0);
            }
        });

        binding.upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (pdf==null){
                    Toast.makeText(MatrialActivity.this, "Please Add Pdf", Toast.LENGTH_SHORT).show();
                }
                else {
                    SendMaterialToRealTime(courseId);
                }

            }

        });


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if ( data.getData() != null && requestCode == 0 ){
            pdf=data.getData();

        }
    }

    private void   SendMaterialToRealTime(String courseId){


        reference.child("Courses Materials").child(courseId).push().setValue(pdf.toString());
        Toast.makeText(MatrialActivity.this, "Uploaded", Toast.LENGTH_SHORT).show();
        SendMaterialToStorage(courseId);
    }

    private void SendMaterialToStorage(String courseId){
        final StorageReference reference1=storage.getReference().child("Materials").child(courseId);
        reference1.putFile(pdf).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                reference1.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {

                    }
                });
            }
        });
    }

}