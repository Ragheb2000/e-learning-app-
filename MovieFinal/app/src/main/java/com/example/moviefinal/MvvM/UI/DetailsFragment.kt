package com.example.moviefinal.MvvM.UI

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.moviefinal.MvvM.ViewModle.MainViewModel
import com.example.moviefinal.R
import com.example.moviefinal.databinding.FragmentDetailsBinding


class DetailsFragment : Fragment() {
    lateinit var binding: FragmentDetailsBinding
    lateinit var mainViewModel: MainViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=FragmentDetailsBinding.inflate(layoutInflater)
        mainViewModel=ViewModelProvider(requireActivity()).get(MainViewModel::class.java)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val movieId=DetailsFragmentArgs.fromBundle(requireArguments()).movieId
        binding=FragmentDetailsBinding.bind(view)

        mainViewModel.getDetails(movieId)
        mainViewModel.movieLiveDataDetails.observe(viewLifecycleOwner){

            binding.text3.text=it?.belongs_to_collection?.name
            Glide.with(binding.root.context).load("https://image.tmdb.org/t/p/w500/"+it?.poster_path).into(binding.movieImage)
            binding.text5.text=it?.overview

        }
        binding.root




    }



}
