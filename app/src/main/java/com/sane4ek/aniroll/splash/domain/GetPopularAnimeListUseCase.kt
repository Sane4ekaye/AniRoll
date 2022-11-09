package com.sane4ek.aniroll.splash.domain

import com.google.gson.Gson
import com.sane4ek.aniroll.core.data.ApiResult
import com.sane4ek.aniroll.splash.data.repository.SplashRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.json.JSONObject

class GetPopularAnimeListUseCase(private val splashRepository: SplashRepository) {

    suspend fun execute(params: JSONObject): ApiResult<String> {
        return splashRepository.getPopularAnimeList(params)
    }
}