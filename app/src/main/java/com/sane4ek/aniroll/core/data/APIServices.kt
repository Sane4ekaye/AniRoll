package com.sane4ek.aniroll.core.data

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*

interface APIServices {

    @Headers("Content-Type: application/json")
    @POST("/")
    suspend fun getFromAniList(@Body body: String): Response<String>
}
