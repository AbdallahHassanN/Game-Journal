package com.example.steamdbmockup.network.response

import com.example.steamdbmockup.model.Result
import com.google.gson.annotations.SerializedName

data class GameResponse(
    @SerializedName("results")
    val games:List<Result>
)