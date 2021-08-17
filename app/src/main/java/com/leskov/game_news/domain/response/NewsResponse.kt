package com.leskov.game_news.domain.response

import com.squareup.moshi.Json

/**
 *  Created by Android Studio on 8/17/2021 5:55 PM
 *  Developer: Sergey Leskov
 */

data class NewsResponse(
    @field:Json(name = "title")
    val title: String,
    @field:Json(name = "type")
    val type: String,
    @field:Json(name = "img")
    val img: String,
    @field:Json(name = "click_url")
    val url: String,
    @field:Json(name = "time")
    val time: String,
    @field:Json(name = "top")
    val top: Int
)