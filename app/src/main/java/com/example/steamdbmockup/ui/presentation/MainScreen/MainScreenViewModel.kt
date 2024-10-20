package com.example.steamdbmockup.ui.presentation.MainScreen

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.steamdbmockup.common.Constants.TAG
import com.example.steamdbmockup.common.DateUtils
import com.example.steamdbmockup.model.Result
import com.example.steamdbmockup.network.response.Resource
import com.example.steamdbmockup.useCases.GetHighRatedGamesUseCase
import com.example.steamdbmockup.useCases.GetMostAnticipatedGamesUseCase
import com.example.steamdbmockup.useCases.GetTrendingGamesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainScreenViewModel
@Inject constructor(
    private val getTrendingGamesUseCase: GetTrendingGamesUseCase,
    private val getMostAnticipatedGamesUseCase: GetMostAnticipatedGamesUseCase,
    private val getHighRatedGamesUseCase: GetHighRatedGamesUseCase
): ViewModel() {

    val trendingGames: MutableState<List<Result>> = mutableStateOf(listOf())
    val mostAnticipatedGames: MutableState<List<Result>> = mutableStateOf(listOf())
    val highRatedGames: MutableState<List<Result>> = mutableStateOf(listOf())

    @RequiresApi(Build.VERSION_CODES.O)
    private val formattedDate = DateUtils.getDateRangeFromStartOfYear()

    val trendingLoading = mutableStateOf(false)
    val mostAnticipatedLoading = mutableStateOf(false)
    val highRatedLoading = mutableStateOf(false)

    init {
        getTrendingGames()
        getMostAnticipatedGames()
        getHighRatedGames()
    }

    private fun getTrendingGames() = viewModelScope.launch {
        trendingLoading.value = true
        getTrendingGamesUseCase.execute(
            formattedDate,
            1
        ).catch {
            Log.d(TAG,"Error ${it.message}")
        }.collect{ response ->
            when(response) {
                is Resource.Error ->  Log.d(TAG,"Error response")
                is Resource.Loading -> {
                    trendingLoading.value = true
                    Log.d(TAG, "Loading")
                }
                is Resource.Success -> {
                    trendingGames.value = response.data!!
                    Log.d(TAG, "dataView ${trendingGames.value}")
                    trendingLoading.value = false
                }
            }
        }
    }

    private fun getMostAnticipatedGames(

    ) = viewModelScope.launch {
        mostAnticipatedLoading.value = true
        getMostAnticipatedGamesUseCase.execute(
            page = 1
        ).catch {
            Log.d(TAG,"Error ${it.message}")
        }.collect{ response ->
            when(response) {
                is Resource.Error ->  Log.d(TAG,"Error response")
                is Resource.Loading -> {
                    mostAnticipatedLoading.value = true
                    Log.d(TAG, "Loading")
                }
                is Resource.Success -> {
                    mostAnticipatedGames.value = response.data!!
                    Log.d(TAG, "dataView ${mostAnticipatedGames.value}")
                    mostAnticipatedLoading.value = false
                }
            }
        }
    }

    private fun getHighRatedGames() = viewModelScope.launch {
        highRatedLoading.value = true
        getHighRatedGamesUseCase.execute(
            formattedDate,
            1
        ).catch {
            Log.d(TAG,"Error ${it.message}")
        }.collect{ response ->
            when(response) {
                is Resource.Error ->  Log.d(TAG,"Error response")
                is Resource.Loading -> {
                    highRatedLoading.value = true
                    Log.d(TAG, "Loading")
                }
                is Resource.Success -> {
                    highRatedGames.value = response.data!!
                    Log.d(TAG, "dataView ${highRatedGames.value}")
                    highRatedLoading.value = false
                }
            }
        }
    }
}

