package com.leskov.game_news.remote

import com.leskov.game_news.domain.response.NewsResponse
import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import retrofit2.http.GET

/**
 *  Created by Android Studio on 8/17/2021 5:54 PM
 *  Developer: Sergey Leskov
 */

interface RemoteDataSource {
    @GET("http://188.40.167.45:3001/")
    suspend fun getNews() : Response<List<NewsResponse>>
}