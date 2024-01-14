package com.example.steamdbmockup.common

enum class GameGenreCategory(val value:String) {
    ACTION("action"),
    INDIE("indie"),
    SHOOTER("shooter"),
    SPORTS("sports"),
    ADVENTURE("adventure"),
    RPG("role-playing-games-rpg"),
    STRATEGY("strategy"),
    CASUAL("casual"),
    PUZZLE("puzzle"),
    ARCADE("arcade"),
    PLATFORMER("platformer"),
    MASSIVELYMULTIPLAYER("massively-multiplayer"),
    RACING("racing"),
    FIGHTING("fighting"),
    SIMULATION("simulation"),
    FAMILY("family"),
    BOARDGAMES("board-games"),
    CARD("card")

}

fun getAllGameGenreCategories(): List<GameGenreCategory>{
    return listOf(GameGenreCategory.ACTION,GameGenreCategory.INDIE,GameGenreCategory.SHOOTER
    ,GameGenreCategory.SPORTS,GameGenreCategory.ADVENTURE,GameGenreCategory.RPG
    ,GameGenreCategory.STRATEGY,GameGenreCategory.CASUAL,GameGenreCategory.PUZZLE
    ,GameGenreCategory.ARCADE,GameGenreCategory.PLATFORMER,GameGenreCategory.MASSIVELYMULTIPLAYER
    ,GameGenreCategory.RACING,GameGenreCategory.FIGHTING,GameGenreCategory.SIMULATION
    ,GameGenreCategory.FAMILY,GameGenreCategory.BOARDGAMES,GameGenreCategory.CARD)
}