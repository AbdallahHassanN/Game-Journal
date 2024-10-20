package com.example.steamdbmockup.ui.presentation.HighlyRatedGames

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
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@RequiresApi(Build.VERSION_CODES.O)
@HiltViewModel
class HighlyRatedScreenViewModel
@Inject constructor(
    private val getHighRatedGamesUseCase: GetHighRatedGamesUseCase,
) : ViewModel() {

    val highlyRatedGames: MutableState<List<Result>> = mutableStateOf(listOf())

    private val formattedDate = DateUtils.getDateRangeFromStartOf2019()

    val highlyRatedGamesLoading = mutableStateOf(false)
    var page = 1

    init {
        getHighlyRatedGames()
    }

    private fun getHighlyRatedGames() = viewModelScope.launch {
        highlyRatedGamesLoading.value = true
        getHighRatedGamesUseCase.execute(
            formattedDate,
            page
        ).catch {
            Log.d(TAG, "Error ${it.message}")
        }.collect { response ->
            when (response) {
                is Resource.Error -> Log.d(TAG, "Error response")
                is Resource.Loading -> {
                    highlyRatedGamesLoading.value = true
                    Log.d(TAG, "Loading")
                }

                is Resource.Success -> {
                    highlyRatedGames.value += response.data!!
                    highlyRatedGamesLoading.value = false
                }
            }
        }
    }

    fun incrementPage() {
        ++page
        getHighlyRatedGames()
    }
}

