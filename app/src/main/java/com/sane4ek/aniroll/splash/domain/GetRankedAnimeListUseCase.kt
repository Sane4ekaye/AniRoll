package com.sane4ek.aniroll.splash.domain

import com.sane4ek.aniroll.core.data.ApiResult
import com.sane4ek.aniroll.splash.data.repository.SplashRepository
import org.json.JSONObject

class GetRankedAnimeListUseCase(private val splashRepository: SplashRepository) {

    suspend fun execute(params: JSONObject): ApiResult<String> {
        return splashRepository.getRankedAnimeList(params)
    }
}