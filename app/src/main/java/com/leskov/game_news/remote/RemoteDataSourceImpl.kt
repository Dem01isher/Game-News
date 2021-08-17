package com.leskov.game_news.remote

import com.leskov.game_news.domain.response.NewsResponse
import retrofit2.Response
import retrofit2.Retrofit

/**
 *  Created by Android Studio on 8/17/2021 5:54 PM
 *  Developer: Sergey Leskov
 */

class RemoteDataSourceImpl(private val retrofit: Retrofit) : RemoteDataSource {

    private val api = retrofit.create(RemoteDataSource::class.java)

    override suspend fun getNews(): Response<List<NewsResponse>> = api.getNews()
}