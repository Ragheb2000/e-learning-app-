package com.example.moviefinal.MvvM.API

import com.example.moviefinal.DetailsModel
import com.example.moviefinal.MvvM.Modle.MovieModule
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Repo {

    fun getMovie(movieCallback:(MovieModule?)->Unit){

        RetroConniction.getMovie.getMovies().enqueue(object : retrofit2.Callback<MovieModule?> {
            override fun onResponse(call: Call<MovieModule?>, response: Response<MovieModule?>) {
                movieCallback.invoke(response.body())
            }

            override fun onFailure(call: Call<MovieModule?>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })
    }

    fun getDetails(movieId:Int ,movieResult: (DetailsModel?)->Unit){
        RetroConniction.getMovie.getDetails(movieId).enqueue(object : Callback<DetailsModel?> {
            override fun onResponse(call: Call<DetailsModel?>, response: Response<DetailsModel?>) {
                movieResult.invoke(response.body())
            }

            override fun onFailure(call: Call<DetailsModel?>, t: Throwable) {
                TODO("Not yet implemented")
            }
        })

    }
}