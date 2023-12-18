package com.example.moviefinal


import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiCall {
    @GET("discover/movie")
fun getMovies(@Query("api_key") api_key:String="300cbeb8d36e791cf85879629cf221f4"): Call<MovieModule>

@GET("movie/{movie_id}")
fun getDetails(@Path("movie_id")movie_id:Int,@Query("api_key")api_key:String="300cbeb8d36e791cf85879629cf221f4"):Call<DetailsModel>
}