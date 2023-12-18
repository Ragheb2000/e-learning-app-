package com.example.moviefinal.MvvM.API

import com.example.moviefinal.MvvM.Modle.MovieModule

interface InterFaceRepo {

    fun getMovie(movieModule: MovieModule)
}