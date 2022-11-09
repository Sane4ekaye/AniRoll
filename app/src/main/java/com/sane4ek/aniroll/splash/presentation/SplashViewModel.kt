package com.sane4ek.aniroll.splash.presentation

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.sane4ek.aniroll.core.data.ApiResult
import com.sane4ek.aniroll.splash.data.model.AnimeResultModel
import com.sane4ek.aniroll.splash.data.model.AnimeEntity
import com.sane4ek.aniroll.splash.data.model.AnimeModel
import com.sane4ek.aniroll.splash.data.repository.SplashRepositoryImpl
import com.sane4ek.aniroll.splash.domain.GetNewestAnimeListUseCase
import com.sane4ek.aniroll.splash.domain.GetPopularAnimeListUseCase
import com.sane4ek.aniroll.splash.domain.GetRankedAnimeListUseCase
import kotlinx.coroutines.*
import org.json.JSONObject

class SplashViewModel() : ViewModel() {

    private val TAG = "splashvm"

    private val splashRepository = SplashRepositoryImpl()

    private val getPopularAnimeListUseCase by lazy { GetPopularAnimeListUseCase(splashRepository) }
    private val getRankedAnimeListUseCase by lazy { GetRankedAnimeListUseCase(splashRepository) }
    private val getNewestAnimeListUseCase by lazy { GetNewestAnimeListUseCase(splashRepository) }

    private var popularList: ArrayList<AnimeEntity> = ArrayList()
    private var rankedList: ArrayList<AnimeEntity> = ArrayList()
    private var newestList: ArrayList<AnimeEntity> = ArrayList()
    private var resultListsErrorStatus = false

    private val mutableSuccessLiveData = MutableLiveData<AnimeResultModel>()
    val successLiveData = mutableSuccessLiveData

    private val mutableFailureLiveData = MutableLiveData<Boolean>()
    val failureLiveData = mutableFailureLiveData

    fun getLists(popularParams: JSONObject, rankedParams: JSONObject) = viewModelScope.launch(Dispatchers.IO) {
        val tasks = listOf(
            getPopularAnimeList(request = popularParams),
            getRankedAnimeList(request = rankedParams),
//                getNewestAnimeList(params = newestParams)
        )
        tasks.joinAll()

        if (!resultListsErrorStatus) { // если нету ошибок, запросы прошли успешно и списки заполнены
            withContext(Dispatchers.Main) {
                mutableSuccessLiveData.value = AnimeResultModel(popular = popularList, ranked = rankedList, newest = newestList)
            }
        } else {
            withContext(Dispatchers.Main) {
                mutableFailureLiveData.value = resultListsErrorStatus
            }
        }
    }

    private fun getPopularAnimeList(request: JSONObject) = viewModelScope.launch(Dispatchers.IO) {
        when (val result = getPopularAnimeListUseCase.execute(request)) {
            is ApiResult.Success -> {
                val model = Gson().fromJson(result.data, AnimeModel::class.java)
                popularList = model.data.Page.media
                Log.i(TAG, "popular: ${popularList}")
            }
            is ApiResult.Failure -> {
                Log.i(TAG, "Popular error, code - " + result.statusCode)
                resultListsErrorStatus = true
            }
        }
    }

    private fun getRankedAnimeList(request: JSONObject) = viewModelScope.launch(Dispatchers.IO) {
        when (val result = getRankedAnimeListUseCase.execute(request)) {
            is ApiResult.Success -> {
                val model = Gson().fromJson(result.data, AnimeModel::class.java)
                rankedList = model.data.Page.media
                Log.i(TAG, "ranked: ${popularList}")
            }
            is ApiResult.Failure -> {
                Log.i(TAG, "Ranked error, code - " + result.statusCode)
                resultListsErrorStatus = true
            }
        }
    }

    private fun getNewestAnimeList(params: HashMap<String, String>) = viewModelScope.launch(Dispatchers.IO) {
//        when (val result = getNewestAnimeListUseCase.execute(params)) {
//            is ApiResult.Success -> {
//                newestList = result.data
//                Log.i(TAG, "newest: ${result.data}")
//            }
//            is ApiResult.Failure -> {
//                Log.i(TAG, "Newest error, code - " + result.statusCode)
//                resultListsErrorStatus = true
//            }
//        }
    }
}