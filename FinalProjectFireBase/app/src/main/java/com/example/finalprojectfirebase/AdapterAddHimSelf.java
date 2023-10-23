package com.example.finalprojectfirebase;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AdapterAddHimSelf extends RecyclerView.Adapter<AdapterAddHimSelf.Holder> {
    ArrayList<CourseData>list=new ArrayList<>();
    Context context;

    public AdapterAddHimSelf(ArrayList<CourseData> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public AdapterAddHimSelf.Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(context).inflate(R.layout.addcourse_recycler_item,parent,false);
        return new Holder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterAddHimSelf.Holder holder, int position) {
        CourseData courseData=list.get(position);
        holder.textCourseName.setText(courseData.getCourseName());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    Intent intent = new Intent(context, StudentContorActivity.class);
                    intent.putExtra("courseId", courseData.getCourseId());
                    intent.putExtra("courseName",courseData.getCourseName());
                    intent.putExtra("instructorId",courseData.getInstructorId());
                    context.startActivity(intent);

            }
        });
    }

    @Override
    public int getItemCount() {
        return list==null ? 0 : list.size();
    }
    class Holder extends RecyclerView.ViewHolder{

        TextView textCourseName ;

        public Holder(@NonNull View itemView) {
            super(itemView);
            textCourseName = itemView.findViewById(R.id.textView_course);



        }
    }
}
