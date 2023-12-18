package com.example.moviefinal

import android.os.Binder
import android.view.LayoutInflater
import android.view.View.OnClickListener
import android.view.ViewGroup
import android.view.inputmethod.InputBinding
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.example.moviefinal.databinding.ItemMoiveBinding

class AdapterMovie(val onClick:(Int)->Unit): RecyclerView.Adapter<AdapterMovie.Holder>() {

        var list:ArrayList<MovieResult>?=null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view=ItemMoiveBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return Holder(view)
    }

    override fun getItemCount(): Int {
       return list?.size?:0
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val movieResult=list?.get(position)!!
        holder.bind(movieResult)
    }

   inner class Holder (val binding:ItemMoiveBinding):RecyclerView.ViewHolder(binding.root){

        init {
            binding.root.setOnClickListener {
                list?.get(layoutPosition)?.let { it1 -> onClick.invoke(it1.id) }
            }
        }
        fun bind(movieResult: MovieResult){
            binding.textTitle.text=movieResult.title
            Glide.with(binding.imageMovie.context).load("https://image.tmdb.org/t/p/w500/"+movieResult?.backdrop_path).into(binding.imageMovie)
        }


    }
}