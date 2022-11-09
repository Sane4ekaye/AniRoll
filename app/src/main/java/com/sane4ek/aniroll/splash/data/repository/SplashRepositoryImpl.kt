package com.sane4ek.aniroll.splash.data.repository

import com.sane4ek.aniroll.core.data.APIServices
import com.sane4ek.aniroll.core.data.CloudDataSource
import com.sane4ek.aniroll.core.data.ApiResult
import com.sane4ek.aniroll.utils.anilistUrl
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.json.JSONObject
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory

class SplashRepositoryImpl : SplashRepository {

    override suspend fun getPopularAnimeList(json: JSONObject): ApiResult<String> {
        val retrofit = Retrofit.Builder()
            .baseUrl(anilistUrl)
            .addConverterFactory(ScalarsConverterFactory.create())
            .build()
            .create(APIServices::class.java)

        val call: (suspend () -> Response<String>) = {
            retrofit.getFromAniList(json.toString())
        }
        return CloudDataSource<String>().execute(call)
    }

    override suspend fun getRankedAnimeList(json: JSONObject): ApiResult<String> {
        val retrofit = Retrofit.Builder()
            .baseUrl(anilistUrl)
            .addConverterFactory(ScalarsConverterFactory.create())
            .build()
            .create(APIServices::class.java)

        val call: (suspend () -> Response<String>) = {
            retrofit.getFromAniList(json.toString())
        }
        return CloudDataSource<String>().execute(call)
    }
}