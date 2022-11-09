package com.sane4ek.aniroll.splash.data.repository

import com.sane4ek.aniroll.core.data.ApiResult
import org.json.JSONObject

interface SplashRepository {

    suspend fun getPopularAnimeList(json: JSONObject) : ApiResult<String>

    suspend fun getRankedAnimeList(json: JSONObject) : ApiResult<String>

//    suspend fun getNewestAnimeList(params: HashMap<String, String>) : ApiResult<ArrayList<AnimeModel>>
}