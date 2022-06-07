package com.daveloper.gothouses.domain.usecases

import com.daveloper.gothouses.data.Resource
import com.daveloper.gothouses.domain.Repo
import com.daveloper.gothouses.domain.model.House
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetHouse @Inject constructor(
    private val repo: Repo
){
    operator fun invoke(id: Int): Flow<Resource<House>> = repo.getHouse(id)
}