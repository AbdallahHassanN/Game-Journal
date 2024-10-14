package com.example.steamdbmockup.ui.presentation.SearchScreen

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.steamdbmockup.common.Constants.TAG
import com.example.steamdbmockup.common.SearchType
import com.example.steamdbmockup.model.Result
import com.example.steamdbmockup.network.response.Resource
import com.example.steamdbmockup.useCases.GetGameByGenreUseCase
import com.example.steamdbmockup.useCases.GetGameByNameUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class SearchScreenViewModel
@Inject constructor(
    private val getGameByNameUseCase: GetGameByNameUseCase,
    private val getGameByGenreUseCase: GetGameByGenreUseCase,
) : ViewModel() {

    private val _gamesByName = MutableStateFlow<List<Result>>(emptyList())
    private val _gamesByGenre = MutableStateFlow<List<Result>>(emptyList())
    val gamesByName: StateFlow<List<Result>> get() = _gamesByName
    val gamesByGenre: StateFlow<List<Result>> get() = _gamesByGenre


    private val _query = MutableStateFlow<String>("")
    val query = _query.asStateFlow()
    val loading = mutableStateOf(false)
    private val _currentSearchType = MutableStateFlow(SearchType.BY_NAME)
    val currentSearchType: StateFlow<SearchType> = _currentSearchType
    private var _lastQuery: String = ""


    var CurrentPageGenre = 1
    var CurrentPageName = 1


    fun search(searchType: SearchType) = viewModelScope.launch {
        loading.value = true

        if (searchType == SearchType.BY_GENRE) {
            CurrentPageName = 1
        } else {
            CurrentPageGenre = 1
        }


        val response = when (searchType) {
            SearchType.BY_NAME -> {
                if (_query.value == _lastQuery) {
                    getGameByNameUseCase.execute(_query.value, CurrentPageName)
                } else {
                    CurrentPageName = 1
                    getGameByNameUseCase.execute(_query.value, CurrentPageName)
                }
            }

            SearchType.BY_GENRE -> {
                if (_query.value == _lastQuery) {
                    getGameByGenreUseCase.execute(_query.value, CurrentPageGenre)
                } else {
                    CurrentPageGenre = 1
                    getGameByGenreUseCase.execute(_query.value, CurrentPageGenre)
                }
            }
        }

        response.catch {
            Log.d(TAG, "Error ${it.message}")
        }.collect { resource ->
            when (resource) {
                is Resource.Error -> Log.d(TAG, "Error response")
                is Resource.Loading -> {
                    loading.value = true
                    Log.d(TAG, "Loading")
                }

                is Resource.Success -> {
                    Log.d("Search Error", "${_query.value} and $_lastQuery")
                    if (_query.value == _lastQuery) {
                        if (searchType == SearchType.BY_NAME) {
                            CurrentPageGenre = 1
                            _gamesByName.value += resource.data ?: emptyList()
                        } else {
                            CurrentPageName = 1
                            _gamesByGenre.value += resource.data ?: emptyList()
                        }
                    } else {
                        if (searchType == SearchType.BY_NAME) {
                            CurrentPageName = 1
                            _gamesByName.value = resource.data ?: emptyList()
                        } else {
                            CurrentPageGenre = 1
                            _gamesByGenre.value = resource.data ?: emptyList()
                        }
                        _lastQuery = _query.value
                    }
                    loading.value = false
                }
            }
        }
        _currentSearchType.value = searchType
    }

    fun onQueryChanged(newQuery: String) {
        _query.value = newQuery
    }

    fun incrementPage() {
        when (_currentSearchType.value) {
            SearchType.BY_NAME -> {
                ++CurrentPageName
                Log.d(TAG, "NAME $CurrentPageName")
                search(SearchType.BY_NAME)
            }

            SearchType.BY_GENRE -> {
                ++CurrentPageGenre
                Log.d(TAG, "Genre $CurrentPageGenre")
                search(SearchType.BY_GENRE)
            }
        }
    }
}
