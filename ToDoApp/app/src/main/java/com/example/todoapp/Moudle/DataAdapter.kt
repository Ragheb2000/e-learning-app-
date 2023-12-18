package com.example.todoapp.Moudle

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.todoapp.databinding.EachTodoItemBinding

class DataAdapter(private val list: MutableList<DataMoudle>) :
    RecyclerView.Adapter<DataAdapter.ToDoHolder>() {

    private var listener: ToDoClickListener? = null
     fun setListener(listener: ToDoClickListener) {
        this.listener = listener
    }

    inner class ToDoHolder(val binding: EachTodoItemBinding) :
        RecyclerView.ViewHolder(binding.root) {


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ToDoHolder {
        var binding =
            EachTodoItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ToDoHolder(binding)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ToDoHolder, position: Int) {
        with(holder){
            with(list[position]){
                binding.todoTask.text=this.task
                binding.deleteTask.setOnClickListener{
                    listener?.onDeleteClickListener(this)
                }

                binding.editTask.setOnClickListener{
                    listener?.onEditClickListener(this)
                }
            }
        }
    }

    interface ToDoClickListener {

        fun onDeleteClickListener(dataMoudle: DataMoudle)
        fun onEditClickListener(dataMoudle: DataMoudle)
    }
}