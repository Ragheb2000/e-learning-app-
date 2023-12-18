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
import com.example.todoapp.databinding.FragmentSignUpBinding
import com.google.firebase.auth.FirebaseAuth

class SignUpFragment : Fragment() {

private lateinit var auth:FirebaseAuth
private lateinit var navControl:NavController
private lateinit var binding: FragmentSignUpBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        binding=FragmentSignUpBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        init(view)
        registration()

    }

private fun init(view: View){
    auth=FirebaseAuth.getInstance()
    navControl=Navigation.findNavController(view)
}


    private fun registration(){
        binding.textview32.setOnClickListener{

            navControl.navigate(R.id.action_signUpFragment_to_signInFragment)
        }
        binding.nextBtn.setOnClickListener {
            var email=binding.emailEt.text.toString().trim()
            var pass=binding.passEt.text.toString().trim()
            var re_pass=binding.verifyPassEt.text.toString().trim()
            if(email.isNotEmpty()&& pass.isNotEmpty()&&re_pass.isNotEmpty()){
                if (pass==re_pass){
                    auth.createUserWithEmailAndPassword(email,pass).addOnCompleteListener {
                        if (it.isSuccessful) {
                            Toast.makeText(context, "Register Successfully", Toast.LENGTH_SHORT) .show()
                                navControl.navigate(R.id.action_signUpFragment_to_homeFragment)

                        } else {
                            Toast.makeText(context, it.exception?.message, Toast.LENGTH_SHORT)
                                .show()
                        }
                    }
                }
                else{
                    Toast.makeText(context,"password is not match",Toast.LENGTH_SHORT).show()
                }
            }
            else{
                Toast.makeText(context,"Empty fields not allowed",Toast.LENGTH_SHORT).show()
            }
        }

    }

}