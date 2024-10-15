package com.example.steamdbmockup.ui.presentation

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.steamdbmockup.common.Constants
import com.example.steamdbmockup.model.Result
import com.example.steamdbmockup.model2.Developer
import com.example.steamdbmockup.model2.game
import com.example.steamdbmockup.model2.screenshots.Screenshots
import com.example.steamdbmockup.model2.screenshots.gameScreenshots
import com.example.steamdbmockup.network.response.Resource
import com.example.steamdbmockup.useCases.GetDevelopersInfoUseCase
import com.example.steamdbmockup.useCases.GetGameByIdUseCase
import com.example.steamdbmockup.useCases.GetGameScreenshotsUseCase
import com.example.steamdbmockup.useCases.GetRelatedGamesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DevelopersScreenViewModel
@Inject constructor(
    val GetDeveloperInfoUseCase: GetDevelopersInfoUseCase,
) : ViewModel() {
    val developerInfo: MutableState<Developer?> = mutableStateOf(null)
    val loading = mutableStateOf(false)

    fun getDevelopersInfo(id: Int) = viewModelScope.launch {
        loading.value = true
        GetDeveloperInfoUseCase.execute(
            id = id
        ).catch {
            Log.d(Constants.TAG, "Error ${it.message}")
        }.collect { response ->
            when (response) {
                is Resource.Error -> {
                    //loading.value = false
                    Log.d(Constants.TAG, "Error response")
                }

                is Resource.Loading -> {
                    loading.value = true
                    Log.d(Constants.TAG, "Loading")
                }

                is Resource.Success -> {
                    developerInfo.value = response.data!!
                    loading.value = false
                }
            }
        }
    }
}