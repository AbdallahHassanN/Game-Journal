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

class GetTopRatedGamesUseCase
@Inject constructor(
    private val repo:Repository,
    @ApplicationContext private val context: Context
) {
    suspend fun execute(
        page:Int
    )  = repo.getTopRatedGames(page = page)
        .flatMapConcat { games ->
            when(games)  {
                is Resource.Error -> {
                    Log.d(TAG,"UseCase Error ? ${games.message.toString()}")
                    flowOf(Resource.Error(games.message.toString()))
                }
                is Resource.Loading -> {
                    Log.d(TAG,"UseCase Loading ?")
                    flowOf(Resource.Loading())
                }
                is Resource.Success -> {
                    Log.d(TAG,"UseCase Success ? ${games.data!!}")
                    flowOf(Resource.Success(games.data.games))
                }
            }
        }
}