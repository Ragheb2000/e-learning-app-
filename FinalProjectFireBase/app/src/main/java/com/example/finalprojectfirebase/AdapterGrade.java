package com.example.finalprojectfirebase;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.security.AccessControlContext;
import java.util.ArrayList;

public class AdapterGrade extends RecyclerView.Adapter<AdapterGrade.Holder> {


    private ArrayList<CourseData> list ;
    Context context;

    public AdapterGrade(ArrayList<CourseData> list,Context context) {
        this.list = list;
        this.context=context;

    }



    @NonNull
    @Override
    public AdapterGrade.Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.addcourse_recycler_item,parent,false);
        return new Holder(v);

    }

    @Override
    public void onBindViewHolder(@NonNull AdapterGrade.Holder holder, int position) {
        CourseData courseData=list.get(position);
        holder.text_course.setText(courseData.getCourseName());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context,DoctorControl.class);
                intent.putExtra("courseId",courseData.getCourseId());
                holder.itemView.getContext().startActivity(intent);

            }
        });

    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    class Holder extends RecyclerView.ViewHolder {

        TextView text_course;

        public Holder(@NonNull View itemView) {
            super(itemView);
            text_course=itemView.findViewById(R.id.textView_course);

        }
    }
}
