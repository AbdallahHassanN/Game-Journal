package com.example.steamdbmockup.repository

import android.content.Context
import com.example.steamdbmockup.common.handleResponse
import com.example.steamdbmockup.model2.Developer
import com.example.steamdbmockup.model2.achievements.Achievements
import com.example.steamdbmockup.model2.game
import com.example.steamdbmockup.model2.screenshots.Screenshots
import com.example.steamdbmockup.network.GameService
import com.example.steamdbmockup.network.response.GameResponse
import com.example.steamdbmockup.network.response.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.net.UnknownHostException

class Repository_Impl(
    private val gameService: GameService,
    private val appContext: Context
) : Repository {

    override suspend fun getTrendingGames(dates: String,page: Int): Flow<Resource<GameResponse>> {
        return try {
            val response = gameService.getTrendingGames(dates,page)
            handleResponse(response, appContext)
        } catch (e: UnknownHostException) {
            flow {
                emit(Resource.Error("No internet connection"))
            }
        } catch (e: Exception) {
            flow {
                emit(Resource.Error("An unexpected error occurred"))
            }
        }
    }


    override suspend fun getMostAnticipatedGames(): Flow<Resource<GameResponse>> {
        return try {
            val response = gameService.getMostAnticipatedGames()
            handleResponse(response, appContext)
        } catch (e: UnknownHostException) {
            flow {
                emit(Resource.Error("No internet connection"))
            }
        } catch (e: Exception) {
            flow {
                emit(Resource.Error("An unexpected error occurred"))
            }
        }
    }


    override suspend fun getHighRatedGames(
        dates: String
    ): Flow<Resource<GameResponse>> {
        return try {
            val response = gameService.getHighRatedGames(dates)
            handleResponse(response, appContext)
        } catch (e: UnknownHostException) {
            flow {
                emit(Resource.Error("No internet connection"))
            }
        } catch (e: Exception) {
            flow {
                emit(Resource.Error("An unexpected error occurred"))
            }
        }
    }


    override suspend fun getByName(query: String, page: Int): Flow<Resource<GameResponse>> {
        return try {
            val response = gameService.getByName(query, page)
            handleResponse(response, appContext)
        } catch (e: UnknownHostException) {
            flow {
                emit(Resource.Error("No internet connection"))
            }
        } catch (e: Exception) {
            flow {
                emit(Resource.Error("An unexpected error occurred"))
            }
        }
    }

    override suspend fun getById(id: Int): Flow<Resource<game>> {
        return try {
            val response = gameService.getById(id)
            handleResponse(response, appContext)
        } catch (e: UnknownHostException) {
            flow {
                emit(Resource.Error("No internet connection"))
            }
        } catch (e: Exception) {
            flow {
                emit(Resource.Error("An unexpected error occurred"))
            }
        }
    }


    override suspend fun getByGenre(query: String, page: Int): Flow<Resource<GameResponse>> {
        return try {
            val response = gameService.getByGenre(query, page)
            handleResponse(response, appContext)
        } catch (e: UnknownHostException) {
            flow {
                emit(Resource.Error("No internet connection"))
            }
        } catch (e: Exception) {
            flow {
                emit(Resource.Error("An unexpected error occurred"))
            }
        }
    }


    override suspend fun getRelatedGames(id: Int): Flow<Resource<GameResponse>> {
        return try {
            val response = gameService.getRelatedGames(id)
            handleResponse(response, appContext)
        } catch (e: UnknownHostException) {
            flow {
                emit(Resource.Error("No internet connection"))
            }
        } catch (e: Exception) {
            flow {
                emit(Resource.Error("An unexpected error occurred"))
            }
        }
    }

    override suspend fun getGameScreenshots(id: Int): Flow<Resource<Screenshots>> {
        return try {
            val response = gameService.getGameScreenshots(id)
            handleResponse(response, appContext)
        } catch (e: UnknownHostException) {
            flow {
                emit(Resource.Error("No internet connection"))
            }
        } catch (e: Exception) {
            flow {
                emit(Resource.Error("An unexpected error occurred"))
            }
        }
    }

    override suspend fun getDeveloperInfo(id: Int): Flow<Resource<Developer>> {
        return try {
            val response = gameService.getDevelopersInfo(id)
            handleResponse(response, appContext)
        } catch (e: UnknownHostException) {
            flow {
                emit(Resource.Error("No internet connection"))
            }
        } catch (e: Exception) {
            flow {
                emit(Resource.Error("An unexpected error occurred"))
            }
        }
    }

    override suspend fun getGameAchievements(id: Int): Flow<Resource<Achievements>> {
        return try {
            val response = gameService.getGameAchievements(id)
            handleResponse(response, appContext)
        } catch (e: UnknownHostException) {
            flow {
                emit(Resource.Error("No internet connection"))
            }
        } catch (e: Exception) {
            flow {
                emit(Resource.Error("An unexpected error occurred"))
            }
        }
    }
}