package com.example.steamdbmockup.ui.presentation.DetailScreen

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.steamdbmockup.common.Constants
import com.example.steamdbmockup.model.Result
import com.example.steamdbmockup.model2.game
import com.example.steamdbmockup.model2.screenshots.Screenshots
import com.example.steamdbmockup.model2.screenshots.gameScreenshots
import com.example.steamdbmockup.network.response.Resource
import com.example.steamdbmockup.useCases.GetGameByIdUseCase
import com.example.steamdbmockup.useCases.GetGameScreenshotsUseCase
import com.example.steamdbmockup.useCases.GetRelatedGamesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailScreenViewModel
@Inject constructor(
    private val getGameIdUseCase: GetGameByIdUseCase,
    private val getRelatedGamesUseCase: GetRelatedGamesUseCase,
    private val getGameScreenshotsUseCase: GetGameScreenshotsUseCase
): ViewModel() {

    val currentGame: MutableState<game?> = mutableStateOf(null)
    val relatedGames: MutableState<List<Result>> = mutableStateOf(listOf())
    val getGameLoading = mutableStateOf(false)
    val getRelatedGamesLoading = mutableStateOf(false)

    val currentGameScreenshots: MutableState<List<gameScreenshots?>> = mutableStateOf(listOf())
    val getGameScreenshotsLoading = mutableStateOf(false)

    fun getGameScreenshots(id:Int) = viewModelScope.launch {
        getGameScreenshotsLoading.value = true
        getGameScreenshotsUseCase.execute(
            id = id
        ).catch {
            Log.d(Constants.TAG,"Error ${it.message}")
        }.collect{ response ->
            when(response) {
                is Resource.Error -> {
                    //loading.value = false
                    Log.d(Constants.TAG, "Error response")
                }
                is Resource.Loading -> {
                    getGameScreenshotsLoading.value = true
                    Log.d(Constants.TAG, "Loading")
                }
                is Resource.Success -> {
                    currentGameScreenshots.value = response.data!!
                    Log.d(Constants.TAG, "dataView ${currentGame.value}")
                    getGameScreenshotsLoading.value = false
                }
            }
        }
    }

    fun getGameById(id:Int) = viewModelScope.launch {
        getGameLoading.value = true
        getGameIdUseCase.execute(
            id = id
        ).catch {
            Log.d(Constants.TAG,"Error ${it.message}")
        }.collect{ response ->
            when(response) {
                is Resource.Error -> {
                    //loading.value = false
                    Log.d(Constants.TAG, "Error response")
                }
                is Resource.Loading -> {
                    getGameLoading.value = true
                    Log.d(Constants.TAG, "Loading")
                }
                is Resource.Success -> {
                    currentGame.value = response.data!!
                    Log.d(Constants.TAG, "dataView ${currentGame.value}")
                    getGameLoading.value = false
                }
            }
        }
    }

    fun getRelatedGames(id:Int) = viewModelScope.launch {
        getRelatedGamesLoading.value = true
        getRelatedGamesUseCase.execute(
            id = id
        ).catch {
            Log.d(Constants.TAG,"Error ${it.message}")
        }.collect{ response ->
            when(response) {
                is Resource.Error -> {
                    getRelatedGamesLoading.value = false
                    Log.d(Constants.TAG, "Error response")
                }
                is Resource.Loading -> {
                    getRelatedGamesLoading.value = true
                    Log.d(Constants.TAG, "Loading")
                }
                is Resource.Success -> {
                    relatedGames.value = response.data!!
                    getRelatedGamesLoading.value = false
                }
            }
        }
    }
}