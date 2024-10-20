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

class GetTopThisYearGamesUseCase
@Inject constructor(
    private val repo: Repository,
    @ApplicationContext private val context: Context
) {
    suspend fun execute(
        dates: String,
        page: Int
    ) = repo.getTopThisYearGames(dates = dates, page = page)
        .flatMapConcat { topThisYear ->
            when (topThisYear) {
                is Resource.Error -> {
                    Log.d(TAG, "UseCase Error ? ${topThisYear.message.toString()}")
                    flowOf(Resource.Error(topThisYear.message.toString()))
                }

                is Resource.Loading -> {
                    Log.d(TAG, "UseCase Loading ?")
                    flowOf(Resource.Loading())
                }

                is Resource.Success -> {
                    Log.d(TAG, "UseCase Success ? ${topThisYear.data!!.games}")
                    flowOf(Resource.Success(topThisYear.data.games))
                }
            }
        }
}