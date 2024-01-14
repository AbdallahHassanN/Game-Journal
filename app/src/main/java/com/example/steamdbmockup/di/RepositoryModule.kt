package com.example.steamdbmockup.di


import android.content.Context
import com.example.steamdbmockup.network.GameService
import com.example.steamdbmockup.repository.Repository
import com.example.steamdbmockup.repository.Repository_Impl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideRepository(
        gameService: GameService,
        @ApplicationContext appContext: Context // Use @ApplicationContext to get the application context
    ): Repository {
        return Repository_Impl(gameService,appContext)
    }
}