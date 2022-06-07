package com.daveloper.gothouses.data

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.HttpException
import java.io.IOException
import kotlin.coroutines.CoroutineContext

fun <T> flowResource(
    coroutineContext: CoroutineContext,
    block: suspend () -> T
): Flow<Resource<T>> = flow {
    try {
        emit(Resource.loading())
        val data: T = block.invoke()
        emit(Resource.success(data))
    } catch (e: HttpException) {
        emit(Resource.error(e.localizedMessage ?: "An unexpected error occurred"))
    } catch (e: IOException) {
        emit(Resource.error("Couldn't reach server"))
    }
}.flowOn(coroutineContext)