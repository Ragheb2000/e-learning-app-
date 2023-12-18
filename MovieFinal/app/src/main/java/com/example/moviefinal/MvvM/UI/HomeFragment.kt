package com.example.moviefinal.MvvM.UI

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.moviefinal.MvvM.ViewModle.MainViewModel
import com.example.moviefinal.MvvM.Modle.MovieResult
import com.example.moviefinal.MvvM.Adapter.AdapterMovie
import com.example.moviefinal.R
import com.example.moviefinal.databinding.FragmentHomeBinding

import kotlin.collections.ArrayList


class HomeFragment : Fragment() {
    lateinit var adapterMovie: AdapterMovie

lateinit var mainViewModel: MainViewModel
 var _binding: FragmentHomeBinding?=null
    private val binding get() =_binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainViewModel=ViewModelProvider(this).get(MainViewModel::class.java)
        mainViewModel.getMovie()
    }



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding=FragmentHomeBinding.bind(view)
        mainViewModel.movieLiveData.observe(viewLifecycleOwner){
            adapterMovie.list=it.results as? ArrayList<MovieResult>
            binding.recycler.adapter=adapterMovie


            }
        adapterMovie= AdapterMovie {
            findNavController()
                .navigate(HomeFragmentDirections.
                actionHomeFragmentToDetailsFragment2(it))

        }
        binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding=null
    }

}