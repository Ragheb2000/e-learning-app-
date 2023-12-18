package com.example.todoapp.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import com.example.todoapp.R
import com.example.todoapp.databinding.FragmentToDoDialogBinding
import com.google.android.material.textfield.TextInputEditText

class ToDoDialogFragment : DialogFragment() {

    private lateinit var binding: FragmentToDoDialogBinding
    private lateinit var listener:DialogNextBtn
    fun setListner(listener: DialogNextBtn){
        this.listener=listener
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding=FragmentToDoDialogBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        rigesterEvants()
    }

    private fun rigesterEvants(){
        binding.todoNextBtn.setOnClickListener{
            var editText=binding.todoEt.text.toString().trim()
            if (editText.isNotEmpty()){
                listener.onSave(editText,binding.todoEt)
            }
            else
            {
                Toast.makeText(context,"Please Fill The Task",Toast.LENGTH_SHORT).show()
            }
        }
        binding.todoClose.setOnClickListener{
            dismiss()
        }

    }
    interface DialogNextBtn{
        fun onSave(todo:String,todoEt:TextInputEditText)
    }
}