package com.example.steamdbmockup.ui.presentation.MostAnticipatedGames

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.steamdbmockup.common.Constants.TAG
import com.example.steamdbmockup.common.DateUtils
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

    val MostAnticipatedGames: MutableState<List<Result>> = mutableStateOf(listOf())

    val formattedDate = DateUtils.getDateRangeFromStartOfYear()

    val MostAnticipatedGamesLoading = mutableStateOf(false)
    var page = 1

    init {
        getTrendingGames()
    }

    private fun getTrendingGames() = viewModelScope.launch {
        MostAnticipatedGamesLoading.value = true
        getMostAnticipatedGamesUseCase.execute(
            page
        ).catch {
            Log.d(TAG, "Error ${it.message}")
        }.collect { response ->
            when (response) {
                is Resource.Error -> Log.d(TAG, "Error response")
                is Resource.Loading -> {
                    MostAnticipatedGamesLoading.value = true
                    Log.d(TAG, "Loading")
                }

                is Resource.Success -> {
                    MostAnticipatedGames.value += response.data!!
                    MostAnticipatedGamesLoading.value = false
                }
            }
        }
    }

    fun incrementPage() {
        ++page
        getTrendingGames()
    }
}

