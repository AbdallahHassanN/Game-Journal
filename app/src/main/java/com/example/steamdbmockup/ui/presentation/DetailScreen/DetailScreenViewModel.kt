package com.example.steamdbmockup.ui.presentation.DetailScreen

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.steamdbmockup.common.Constants
import com.example.steamdbmockup.model.Result
import com.example.steamdbmockup.model.results
import com.example.steamdbmockup.model2.game
import com.example.steamdbmockup.network.response.GameResponse
import com.example.steamdbmockup.network.response.Resource
import com.example.steamdbmockup.network.response.SingleGameResponse
import com.example.steamdbmockup.useCases.GetGameByIdUseCase
import com.example.steamdbmockup.useCases.GetRelatedGamesUseCase

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailScreenViewModel
@Inject constructor(
    private val getGameIdUseCase: GetGameByIdUseCase,
    private val getRelatedGamesUseCase: GetRelatedGamesUseCase
): ViewModel() {

    val CurrentGame: MutableState<game?> = mutableStateOf(null)
    val RelatedGames: MutableState<List<Result>> = mutableStateOf(listOf())
    val loading = mutableStateOf(false)

    fun getGameById(id:Int) = viewModelScope.launch {
        loading.value = true
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
                    loading.value = true
                    Log.d(Constants.TAG, "Loading")
                }
                is Resource.Success -> {
                    CurrentGame.value = response.data!!
                    Log.d(Constants.TAG, "dataView ${CurrentGame.value}")
                    loading.value = false
                }
            }
        }
    }

    fun getRelatedGames(id:Int) = viewModelScope.launch {
        loading.value = true
        getRelatedGamesUseCase.execute(
            id = id
        ).catch {
            Log.d(Constants.TAG,"Error ${it.message}")
        }.collect{ response ->
            when(response) {
                is Resource.Error -> {
                    loading.value = false
                    Log.d(Constants.TAG, "Error response")
                }
                is Resource.Loading -> {
                    loading.value = true
                    Log.d(Constants.TAG, "Loading")
                }
                is Resource.Success -> {
                    RelatedGames.value = response.data!!
                    loading.value = false
                }
            }
        }
    }
}