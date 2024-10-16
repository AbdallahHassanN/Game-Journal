package com.example.steamdbmockup.ui.presentation.TrendingScreen

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.steamdbmockup.common.Constants.TAG
import com.example.steamdbmockup.common.DateUtils
import com.example.steamdbmockup.model.Result
import com.example.steamdbmockup.network.response.Resource
import com.example.steamdbmockup.useCases.GetTrendingGamesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TrendingScreenViewModel
@Inject constructor(
    private val getTrendingGamesUseCase: GetTrendingGamesUseCase,
) : ViewModel() {

    val TrendingGames: MutableState<List<Result>> = mutableStateOf(listOf())

    val formattedDate = DateUtils.getDateRangeFromStartOfYear()

    val trendingLoading = mutableStateOf(false)
    var page = 1

    init {
        getTrendingGames()
    }

    private fun getTrendingGames() = viewModelScope.launch {
        trendingLoading.value = true
        getTrendingGamesUseCase.execute(
            formattedDate,
            page
        ).catch {
            Log.d(TAG, "Error ${it.message}")
        }.collect { response ->
            when (response) {
                is Resource.Error -> Log.d(TAG, "Error response")
                is Resource.Loading -> {
                    trendingLoading.value = true
                    Log.d(TAG, "Loading")
                }

                is Resource.Success -> {
                    TrendingGames.value += response.data!!
                    trendingLoading.value = false
                }
            }
        }
    }

    fun incrementPage() {
        ++page
        getTrendingGames()
    }
}

