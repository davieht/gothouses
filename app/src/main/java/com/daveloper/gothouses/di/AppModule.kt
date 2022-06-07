package com.daveloper.gothouses.di

import com.daveloper.gothouses.data.RepoImpl
import com.daveloper.gothouses.data.remote.GoTService
import com.daveloper.gothouses.domain.Repo
import com.daveloper.gothouses.domain.usecases.GetHouse
import com.daveloper.gothouses.domain.usecases.GetHouses
import com.daveloper.gothouses.domain.usecases.UseCases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.Dispatchers
import javax.inject.Singleton
import kotlin.coroutines.CoroutineContext

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun provideCoroutineContext(): CoroutineContext =
        Dispatchers.IO

    @Singleton
    @Provides
    fun provideRepo(goTService: GoTService, coroutineContext: CoroutineContext): Repo =
        RepoImpl(goTService, coroutineContext)

    @Singleton
    @Provides
    fun provideUseCases(repo: Repo): UseCases =
        UseCases(
            getHouse = GetHouse(repo),
            getHouses = GetHouses(repo)
        )
}