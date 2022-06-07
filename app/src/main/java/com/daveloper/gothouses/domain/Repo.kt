package com.daveloper.gothouses.domain

import com.daveloper.gothouses.data.Resource
import com.daveloper.gothouses.domain.model.House
import kotlinx.coroutines.flow.Flow

interface Repo {
    fun getHouse(id: Int): Flow<Resource<House>>
    fun getHouses(): Flow<Resource<List<House>>>
}