package com.example.steamdbmockup.ui.presentation.HighlyRatedGames

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.steamdbmockup.common.Constants.TAG
import com.example.steamdbmockup.common.DateUtils
import com.example.steamdbmockup.model.Result
import com.example.steamdbmockup.network.response.Resource
import com.example.steamdbmockup.useCases.GetHighRatedGamesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HighlyRatedScreenViewModel
@Inject constructor(
    private val getHighRatedGamesUseCase: GetHighRatedGamesUseCase,
) : ViewModel() {

    val HighlyRatedGames: MutableState<List<Result>> = mutableStateOf(listOf())

    val formattedDate = DateUtils.getDateRangeFromStartOfYear()

    val HighlyRatedGamesLoading = mutableStateOf(false)
    var page = 1

    init {
        getTrendingGames()
    }

    private fun getTrendingGames() = viewModelScope.launch {
        HighlyRatedGamesLoading.value = true
        getHighRatedGamesUseCase.execute(
            formattedDate,
            page
        ).catch {
            Log.d(TAG, "Error ${it.message}")
        }.collect { response ->
            when (response) {
                is Resource.Error -> Log.d(TAG, "Error response")
                is Resource.Loading -> {
                    HighlyRatedGamesLoading.value = true
                    Log.d(TAG, "Loading")
                }

                is Resource.Success -> {
                    HighlyRatedGames.value += response.data!!
                    HighlyRatedGamesLoading.value = false
                }
            }
        }
    }

    fun incrementPage() {
        ++page
        getTrendingGames()
    }
}

