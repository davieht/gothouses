package com.daveloper.gothouses.domain.usecases

import com.daveloper.gothouses.data.Resource
import com.daveloper.gothouses.domain.Repo
import com.daveloper.gothouses.domain.model.House
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetHouses @Inject constructor(
    private val repo: Repo
){
    operator fun invoke(): Flow<Resource<List<House>>> = repo.getHouses()
}