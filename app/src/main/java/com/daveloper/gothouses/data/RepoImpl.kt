package com.daveloper.gothouses.data

import com.daveloper.gothouses.data.remote.GoTService
import com.daveloper.gothouses.data.remote.dto.toHouse
import com.daveloper.gothouses.domain.Repo
import com.daveloper.gothouses.domain.model.House
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

class RepoImpl @Inject constructor(
    private val goTService: GoTService,
    private val coroutineContext: CoroutineContext
): Repo {
    override fun getHouse(id: Int): Flow<Resource<House>> =
        flowResource(coroutineContext) {
            goTService.getHouseById(id).toHouse()
        }

    override fun getHouses(): Flow<Resource<List<House>>> =
        flowResource(coroutineContext) {
            goTService.getHouses().map { it.toHouse() }
        }
}