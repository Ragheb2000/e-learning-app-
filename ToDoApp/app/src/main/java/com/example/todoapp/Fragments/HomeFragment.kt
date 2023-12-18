package com.example.todoapp.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.todoapp.Moudle.DataAdapter
import com.example.todoapp.Moudle.DataMoudle
import com.example.todoapp.databinding.FragmentHomeBinding
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener


class HomeFragment : Fragment(), ToDoDialogFragment.DialogNextBtn, DataAdapter.ToDoClickListener {
   private lateinit var auth: FirebaseAuth
   private lateinit var binding: FragmentHomeBinding
   private lateinit var databaseReference: DatabaseReference
   private lateinit var navController: NavController
   private lateinit var popUpFragment:ToDoDialogFragment
   private lateinit var dataAdapter: DataAdapter
   private lateinit var  list: MutableList<DataMoudle>


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding=FragmentHomeBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init(view)
        registerEvents()
        getDatabase()
    }

    private fun init(view: View){
        navController=Navigation.findNavController(view)
        auth=FirebaseAuth.getInstance()
        databaseReference=FirebaseDatabase.getInstance().reference.child("Task")
            .child(auth.currentUser?.uid.toString())


        binding.mainRecyclerView.setHasFixedSize(true)
        binding.mainRecyclerView.layoutManager = LinearLayoutManager(context)
        list = mutableListOf()
        dataAdapter = DataAdapter(list)
        dataAdapter.setListener(this)
        binding.mainRecyclerView.adapter = dataAdapter


    }
    private fun registerEvents(){
        binding.addTaskBtn.setOnClickListener{
            popUpFragment= ToDoDialogFragment()
            popUpFragment.setListner(this)
            popUpFragment.show(
                childFragmentManager,
        "ToDoDialogFragment"
            )

        }

    }

    override fun onSave(todo: String, todoEt: TextInputEditText) {
        databaseReference.push().setValue(todo).addOnCompleteListener{
            if(it.isSuccessful){
                Toast.makeText(context,"ToDo Save Successfully !!",Toast.LENGTH_SHORT).show()
                todoEt.text=null

            }else{
                Toast.makeText(context,it.exception?.message,Toast.LENGTH_SHORT).show()
            }
            popUpFragment.dismiss()
        }
    }
    private fun getDatabase(){
        databaseReference.addValueEventListener(object :ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                list.clear()
                for(taskSnapshot in snapshot.children){
                    val todoTask=taskSnapshot.key?.let {
                        DataMoudle(it,taskSnapshot.value.toString())
                    }
                    if(todoTask!=null){
                        list.add(todoTask)
                    }
                }
                dataAdapter.notifyDataSetChanged()
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(context,error.message,Toast.LENGTH_SHORT).show()
            }

        })
    }

    override fun onDeleteClickListener(dataMoudle: DataMoudle) {
        databaseReference.child(dataMoudle.taskId).removeValue().addOnCompleteListener{
            if (it.isSuccessful){
                Toast.makeText(context,"Deleted Successfully",Toast.LENGTH_SHORT).show()
            }else{
                Toast.makeText(context,it.exception?.message,Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onEditClickListener(dataMoudle: DataMoudle) {
        TODO("Not yet implemented")
    }


}