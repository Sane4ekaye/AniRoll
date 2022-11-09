package com.sane4ek.aniroll.core.data

import android.util.Log
import retrofit2.Call
import retrofit2.Response

class CloudDataSource<T : Any> {

    suspend fun execute(call: suspend () -> Response<T>): ApiResult<T> {
        return try {
            val response = call.invoke()
            Log.i("cdserror", "execute: ${response.raw()}")
            Log.i("cdserror", "execute: ${response.code()}")
            if (response.code() == 200 || response.code() == 201)
                ApiResult.Success(data = response.body()!!)
            else
                ApiResult.Failure(statusCode = response.code())
        } catch (e: Exception) {
            Log.i("cdserror", "Ошибка: $e")
            ApiResult.Failure()
        }
    }

}