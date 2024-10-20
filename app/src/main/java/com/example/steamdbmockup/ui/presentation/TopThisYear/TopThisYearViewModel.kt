package com.example.steamdbmockup.ui.presentation.TopThisYear

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
import com.example.steamdbmockup.useCases.GetTopThisYearGamesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@RequiresApi(Build.VERSION_CODES.O)
@HiltViewModel
class TopThisYearViewModel
@Inject constructor(
    private val getTopThisYearGamesUseCase: GetTopThisYearGamesUseCase,
) : ViewModel() {

    val TopThisYearGames: MutableState<List<Result>> = mutableStateOf(listOf())
    val formattedDate = DateUtils.getDateRangeFromStartOfYear()

    val TopThisYearGamesLoading = mutableStateOf(false)
    var page = 1

    init {
        getTopThisYearGames()
    }

    private fun getTopThisYearGames() = viewModelScope.launch {
        TopThisYearGamesLoading.value = true
        getTopThisYearGamesUseCase.execute(
            page = page,
            dates = formattedDate
        ).catch {
            Log.d(TAG, "Error ${it.message}")
        }.collect { response ->
            when (response) {
                is Resource.Error -> Log.d(TAG, "Error response")
                is Resource.Loading -> {
                    TopThisYearGamesLoading.value = true
                    Log.d(TAG, "Loading")
                }

                is Resource.Success -> {
                    TopThisYearGames.value += response.data!!
                    TopThisYearGamesLoading.value = false
                }
            }
        }
    }

    fun incrementPage() {
        ++page
        getTopThisYearGames()
    }
}

