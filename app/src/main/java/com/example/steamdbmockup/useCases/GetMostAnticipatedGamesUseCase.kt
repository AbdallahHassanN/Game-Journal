package com.example.steamdbmockup.useCases

import android.content.Context
import android.util.Log
import com.example.steamdbmockup.common.Constants
import com.example.steamdbmockup.network.response.Resource
import com.example.steamdbmockup.repository.Repository
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject

class GetMostAnticipatedGamesUseCase
@Inject constructor(
    private val repo: Repository,
    @ApplicationContext private val context: Context) {
    suspend fun execute(
        page:Int
    )  = repo.getMostAnticipatedGames(page)
        .flatMapConcat { it ->
            when(it)  {
                is Resource.Error -> {
                    Log.d(Constants.TAG,"UseCase Error ? ${it.message.toString()}")
                    flowOf(Resource.Error(it.message.toString()))
                }
                is Resource.Loading -> {
                    Log.d(Constants.TAG,"UseCase Loading ?")
                    flowOf(Resource.Loading())
                }
                is Resource.Success -> {
                    Log.d(Constants.TAG,"UseCase Success ? ${it.data!!.games}")

                    flowOf(Resource.Success(it.data.games))
                }
            }
        }
}