package com.example.steamdbmockup.ui.presentation.AchievementsScreen

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.steamdbmockup.common.Constants
import com.example.steamdbmockup.model2.Developer
import com.example.steamdbmockup.model2.achievements.Achievements
import com.example.steamdbmockup.network.response.Resource
import com.example.steamdbmockup.useCases.GetDevelopersInfoUseCase
import com.example.steamdbmockup.useCases.GetGameAchievementsInfoUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AchievementsScreenViewModel
@Inject constructor(
    val getGameAchievementsUseCase: GetGameAchievementsInfoUseCase,
) : ViewModel() {
    val achievements: MutableState<Achievements?> = mutableStateOf(null)
    val loading = mutableStateOf(false)

    fun getGameAchievements(id: Int) = viewModelScope.launch {
        loading.value = true
        getGameAchievementsUseCase.execute(
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
                    achievements.value = response.data!!
                    loading.value = false
                }
            }
        }
    }
}