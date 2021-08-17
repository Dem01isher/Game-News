package com.leskov.game_news.views.news

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.leskov.game_news.domain.response.NewsResponse
import com.leskov.game_news.remote.RemoteDataSource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import java.lang.Error
import javax.inject.Inject

/**
 *  Created by Android Studio on 8/17/2021 5:30 PM
 *  Developer: Sergey Leskov
 */

@HiltViewModel
class NewsViewModel @Inject constructor(private val repository : RemoteDataSource) : ViewModel() {

    private val _news = MutableLiveData<List<NewsResponse>>()
    var news : LiveData<List<NewsResponse>> = _news

    private lateinit var job: Job

    val errorMessage = MutableLiveData<String>()
    val loading = MutableLiveData<Boolean>()

    private val exceptionHandler = CoroutineExceptionHandler{ _, throwable ->
        onError("Exception handler: ${throwable.localizedMessage}")
    }

    fun getNews(){
        job = CoroutineScope(Dispatchers.IO + exceptionHandler).launch {
            val response = repository.getNews()
            withContext(Dispatchers.Main){
                if (response.isSuccessful){
                    _news.postValue(response.body())
                    loading.value = false
                } else {
                    onError("Error : ${response.message() }")
                    loading.value = true
                }
            }
        }
    }

    fun setFinder(finder: String){
        news = Transformations.map(news) { news ->
            news?.filter {
               it.title.contains(finder)
            }
        }
    }

    private fun onError(message: String){
        errorMessage.value = message
        loading.value = false
    }

    override fun onCleared() {
        super.onCleared()
        job.cancel()
    }
}