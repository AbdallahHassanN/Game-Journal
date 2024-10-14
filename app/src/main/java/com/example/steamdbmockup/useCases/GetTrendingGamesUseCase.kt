package com.example.steamdbmockup.useCases

import android.content.Context
import android.util.Log
import com.example.steamdbmockup.common.Constants.TAG
import com.example.steamdbmockup.network.response.Resource
import com.example.steamdbmockup.repository.Repository
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject

class GetTrendingGamesUseCase
@Inject constructor(
    private val repo:Repository,
    @ApplicationContext private val context: Context
) {
    suspend fun execute(
        dates:String
    )  = repo.getTrendingGames(dates)
        .flatMapConcat { trendingGames ->
            when(trendingGames)  {
                is Resource.Error -> {
                    Log.d(TAG,"UseCase Error ? ${trendingGames.message.toString()}")
                    flowOf(Resource.Error(trendingGames.message.toString()))
                }
                is Resource.Loading -> {
                    Log.d(TAG,"UseCase Loading ?")
                    flowOf(Resource.Loading())
                }
                is Resource.Success -> {
                    Log.d(TAG,"UseCase Success ? ${trendingGames.data!!.games}")
                    /*flowOf(Resource.Success(it.data.games.map {
                        it.map()
                    }))*/
                    flowOf(Resource.Success(trendingGames.data.games))
                }
            }
        }
}