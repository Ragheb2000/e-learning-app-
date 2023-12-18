package com.example.moviefinal.MvvM.API

import com.example.moviefinal.MvvM.KeyWords.Constant.Companion.BASE_URL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetroConniction {

        val retrofit=Retrofit.Builder()
        .baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build()

      val getMovie= retrofit.create(ApiCall::class.java)

}