package com.example.steamdbmockup.network.response

import com.example.steamdbmockup.model2.game
import com.google.gson.annotations.SerializedName

data class SingleGameResponse(
    @SerializedName("results")
    val games:List<game>
)