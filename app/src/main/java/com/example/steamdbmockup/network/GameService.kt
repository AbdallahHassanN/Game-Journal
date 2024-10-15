package com.example.steamdbmockup.network

import com.example.steamdbmockup.common.Constants.Most_Anticipated_Games
import com.example.steamdbmockup.common.Constants.Ordering_Games
import com.example.steamdbmockup.common.Constants.Related_Games
import com.example.steamdbmockup.common.Constants.authToken
import com.example.steamdbmockup.common.Constants.gamesKey
import com.example.steamdbmockup.common.Constants.key
import com.example.steamdbmockup.model2.Developer
import com.example.steamdbmockup.model2.game
import com.example.steamdbmockup.model2.screenshots.Screenshots
import com.example.steamdbmockup.network.response.GameResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GameService {

    @GET(authToken)
    suspend fun getTrendingGames(
        @Query("dates") dates:String,
        ): Response<GameResponse>

    @GET(authToken+Most_Anticipated_Games)
    suspend fun getMostAnticipatedGames(): Response<GameResponse>

    @GET(authToken)
    suspend fun getHighRatedGames(
        @Query("dates") dates:String,
        @Query("ordering") ordering:String = "-rating"
    ): Response<GameResponse>

    @GET(authToken)
    suspend fun getByName(
        @Query("search") query:String,
        @Query("page") page : Int
    ): Response<GameResponse>

    @GET("$gamesKey{id}$key")
    suspend fun getById(
        @Path("id") id:Int
    ) : Response<game>

    @GET(authToken+Ordering_Games)
    suspend fun getByGenre(
        @Query("genres") query:String,
        @Query("page") page : Int
    ): Response<GameResponse>

    @GET("$gamesKey{id}$Related_Games$key")
    suspend fun getRelatedGames(
        @Path("id") id:Int
    ) : Response<GameResponse>

    @GET("$gamesKey{id}/screenshots$key")
    suspend fun getGameScreenshots(
        @Path("id") id:Int
    ) : Response<Screenshots>

    @GET("developers/{id}$key")
    suspend fun getDevelopersInfo(
        @Path("id") id:Int
    ) : Response<Developer>
}