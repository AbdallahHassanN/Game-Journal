package com.example.steamdbmockup.common

import com.example.steamdbmockup.R


object Constants {


    const val TAG = "AppDebug"
    val DEFAULT_Game_IMAGE = R.drawable.heart
    const val IMAGE_HEIGHT = 260
    // API VALUES
    const val url ="https://api.rawg.io/api/"
    const val key = "?key=8a424c20193a43b2ac62b88ea2ea7831"
    const val gamesKey = "games/"
    const val authToken = "games$key"


    const val Ordering_Games ="&ordering=-metacritic&"
    const val Trending_Games = "&dates=2023-01-01,2023-12-31&ordering=-added"
    const val Most_Anticipated_Games ="&dates=2024-01-01,2026-01-01&ordering=-added"
    const val High_Rated_Games = "&dates=2015-01-01,2023-12-31&ordering=-rating&platforms=1"
    const val Related_Games= "/game-series"

    const val GAME_ID = "gameId"

}