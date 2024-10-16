package com.example.steamdbmockup.ui.presentation.TopRatedGamesScreen

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.steamdbmockup.common.Constants.TAG
import com.example.steamdbmockup.common.DateUtils
import com.example.steamdbmockup.model.Result
import com.example.steamdbmockup.network.response.Resource
import com.example.steamdbmockup.useCases.GetTopRatedGamesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TopRatedGamesScreenViewModel
@Inject constructor(
    private val getTopRatedGamesUseCase: GetTopRatedGamesUseCase,
) : ViewModel() {

    val TopRatedGames: MutableState<List<Result>> = mutableStateOf(listOf())

    val TopRatedGamesLoading = mutableStateOf(false)
    var page = 1

    init {
        getTopRatedGames()
    }

    private fun getTopRatedGames() = viewModelScope.launch {
        TopRatedGamesLoading.value = true
        getTopRatedGamesUseCase.execute(
            page
        ).catch {
            Log.d(TAG, "Error ${it.message}")
        }.collect { response ->
            when (response) {
                is Resource.Error -> Log.d(TAG, "Error response")
                is Resource.Loading -> {
                    TopRatedGamesLoading.value = true
                    Log.d(TAG, "Loading")
                }

                is Resource.Success -> {
                    TopRatedGames.value += response.data!!
                    TopRatedGamesLoading.value = false
                }
            }
        }
    }

    fun incrementPage() {
        ++page
        getTopRatedGames()
    }
}

