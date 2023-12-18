package com.example.moviefinal.MvvM.ViewModle

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.moviefinal.DetailsModel
import com.example.moviefinal.MvvM.API.Repo
import com.example.moviefinal.MvvM.Modle.MovieModule

class MainViewModel:ViewModel() {

    val movieLiveData=MutableLiveData<MovieModule>()
    val movieLiveDataDetails=MutableLiveData<DetailsModel?>()
    private  var repo= Repo()



     fun getMovie() {

      repo.getMovie {
      it?.let {
          movieLiveData.value=it
      }
      }
    }

    fun getDetails(movieId:Int){
        repo.getDetails(movieId){
            it?.let {
                movieLiveDataDetails.postValue(it)
            }
        }
    }


}