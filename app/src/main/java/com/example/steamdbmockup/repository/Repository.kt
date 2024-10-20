package com.example.steamdbmockup.repository


import com.example.steamdbmockup.model2.Developer
import com.example.steamdbmockup.model2.achievements.Achievements
import com.example.steamdbmockup.model2.game
import com.example.steamdbmockup.model2.screenshots.Screenshots
import com.example.steamdbmockup.network.response.GameResponse
import com.example.steamdbmockup.network.response.Resource
import kotlinx.coroutines.flow.Flow
import retrofit2.http.Query

interface Repository {

    suspend fun getTrendingGames(dates: String,page: Int)
            : Flow<Resource<GameResponse>>

    suspend fun getMostAnticipatedGames(page: Int)
            : Flow<Resource<GameResponse>>

    suspend fun getHighRatedGames(
        dates: String,
        page: Int
    ): Flow<Resource<GameResponse>>

    suspend fun getByName(query: String, page: Int)
            : Flow<Resource<GameResponse>>

    suspend fun getById(id: Int)
            : Flow<Resource<game>>

    suspend fun getByGenre(query: String, page: Int)
            : Flow<Resource<GameResponse>>

    suspend fun getRelatedGames(id: Int)
            : Flow<Resource<GameResponse>>

    suspend fun getGameScreenshots(id: Int)
            : Flow<Resource<Screenshots>>

    suspend fun getDeveloperInfo(id: Int)
            : Flow<Resource<Developer>>

    suspend fun getGameAchievements(id: Int)
            : Flow<Resource<Achievements>>

    suspend fun getTopRatedGames(page: Int)
            : Flow<Resource<GameResponse>>

    suspend fun getTopThisYearGames(page: Int,dates: String)
            : Flow<Resource<GameResponse>>
}