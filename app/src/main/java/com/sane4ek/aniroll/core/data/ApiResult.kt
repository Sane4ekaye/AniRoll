package com.sane4ek.aniroll.core.data

import org.jetbrains.annotations.NotNull

sealed class ApiResult <T: Any> {
    class Success<T : Any>(@NotNull val data: T): ApiResult<T>()
    class Failure<T : Any>(val statusCode: Int = 0): ApiResult<T>()
}
