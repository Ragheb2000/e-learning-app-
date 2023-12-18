package com.example.todoapp.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.todoapp.R
import com.example.todoapp.databinding.FragmentSignInBinding
import com.example.todoapp.databinding.FragmentSignUpBinding
import com.google.firebase.auth.FirebaseAuth


class SignInFragment : Fragment() {

    private lateinit var binding: FragmentSignInBinding
    private lateinit var auth: FirebaseAuth
    private lateinit var navController: NavController

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding=FragmentSignInBinding.inflate(inflater)
        return binding.root

    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        init(view)
        signIn()
    }
    private fun init(view: View){
        navController=Navigation.findNavController(view)
        auth=FirebaseAuth.getInstance()
    }

    private fun signIn(){
        binding.textview32.setOnClickListener {
            navController.navigate(R.id.action_signInFragment_to_signUpFragment)
        }
        binding.nextBtn.setOnClickListener{
            var email=binding.emailEt.text.toString().trim()
            var pass=binding.passEt.text.toString().trim()
            if(email.isNotEmpty()&& pass.isNotEmpty()){
                binding.progress.visibility=View.VISIBLE
                auth.signInWithEmailAndPassword(email,pass).addOnCompleteListener {
                    if(it.isSuccessful){
                        Toast.makeText(context,"SignIn is Successfully",Toast.LENGTH_SHORT).show()
                        navController.navigate(R.id.action_signInFragment_to_homeFragment)
                    }else{
                        Toast.makeText(context,it.exception?.message,Toast.LENGTH_SHORT)
                            .show()

                    }
                    binding.progress.visibility=View.GONE

                }
        }else{
                Toast.makeText(context,"Empty fields not allowed",Toast.LENGTH_SHORT).show()
            }

        }
    }
}