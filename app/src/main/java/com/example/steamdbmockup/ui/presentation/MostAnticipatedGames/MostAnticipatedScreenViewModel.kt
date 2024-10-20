package com.example.steamdbmockup.ui.presentation.MostAnticipatedGames

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.steamdbmockup.common.Constants.TAG
import com.example.steamdbmockup.model.Result
import com.example.steamdbmockup.network.response.Resource
import com.example.steamdbmockup.useCases.GetMostAnticipatedGamesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MostAnticipatedScreenViewModel
@Inject constructor(
    private val getMostAnticipatedGamesUseCase: GetMostAnticipatedGamesUseCase,
) : ViewModel() {

    val mostAnticipatedGames: MutableState<List<Result>> = mutableStateOf(listOf())

    val mostAnticipatedGamesLoading = mutableStateOf(false)
    var page = 1

    init {
        getMostAnticipatedGames()
    }

    private fun getMostAnticipatedGames() = viewModelScope.launch {
        mostAnticipatedGamesLoading.value = true
        getMostAnticipatedGamesUseCase.execute(
            page
        ).catch {
            Log.d(TAG, "Error ${it.message}")
        }.collect { response ->
            when (response) {
                is Resource.Error -> Log.d(TAG, "Error response")
                is Resource.Loading -> {
                    mostAnticipatedGamesLoading.value = true
                    Log.d(TAG, "Loading")
                }

                is Resource.Success -> {
                    mostAnticipatedGames.value += response.data!!
                    mostAnticipatedGamesLoading.value = false
                }
            }
        }
    }

    fun incrementPage() {
        ++page
        getMostAnticipatedGames()
    }
}

