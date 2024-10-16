package com.example.steamdbmockup.common

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import android.util.Log

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.DismissValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.SuggestionChip
import androidx.compose.material3.SuggestionChipDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.SwipeToDismiss
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.rememberDismissState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.steamdbmockup.common.Constants.RECENT_SEARCHES_KEY
import com.example.steamdbmockup.common.Constants.TAG
import com.example.steamdbmockup.ui.theme.Grey1
import com.example.steamdbmockup.ui.theme.Grey2

@SuppressLint("MutableCollectionMutableState")
@Composable
fun SearchAppBar(
    query: String,
    onQueryChanged: (String) -> Unit,
    onExecuteSearch: () -> Unit,
    onChipsSearch: () -> Unit
) {
    val searchText = remember { mutableStateOf("") }
    val currentSearchText = remember { mutableStateOf("") }
    val recentSearches = remember { mutableStateListOf<String>() }
    val context = LocalContext.current
    LaunchedEffect(
        key1 = context
    ) {
        searchText.value = recentSearches.addAll(loadRecentSearches(context)).toString()
        searchText.value = recentSearches.joinToString(separator = " ") // Corrected line
        Log.d(TAG, "Recent on startup ${recentSearches.toList()}")
    }

    val keyboardController = LocalSoftwareKeyboardController.current
    val focusRequester = remember { FocusRequester() }

    DisposableEffect(
        context
    ) {
        focusRequester.requestFocus()
        onDispose {
            saveRecentSearches(context, recentSearches)
            Log.d(TAG, "Saves on destroy ${recentSearches.toList()}")
        }
    }

    Surface(
        modifier = Modifier
            .fillMaxWidth(),
        color = Grey1,
        shadowElevation = 8.dp
    ) {
        Column {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                TextField(
                    value = query,
                    onValueChange = { newValue ->
                        onQueryChanged(newValue)
                        searchText.value = newValue
                        currentSearchText.value = newValue
                    },
                    Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                        .focusRequester(focusRequester),
                    label = {
                        Text(text = "Search")
                    },

                    keyboardOptions = KeyboardOptions.Default.copy(
                        keyboardType = KeyboardType.Text,
                        imeAction = ImeAction.Search,
                        capitalization = KeyboardCapitalization.Sentences,
                    ),
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Filled.Search,
                            contentDescription = "Search Icon"
                        )
                    },
                    keyboardActions = KeyboardActions(
                        onSearch = {
                            onExecuteSearch()
                            keyboardController?.hide()
                            if (searchText.value.isNotBlank()) {
                                if (!recentSearches.contains(searchText.value)) {
                                    recentSearches.add(0, searchText.value)
                                    saveRecentSearches(context, recentSearches)
                                }
                            }
                        },
                    ),
                    colors = TextFieldDefaults.colors(
                        focusedTextColor = Color.White, // Change text color
                        unfocusedTextColor = Color.White,
                        focusedContainerColor = Grey1, // Change background color
                        unfocusedContainerColor = Grey1,
                        disabledContainerColor = Grey1,
                        cursorColor = Color.Green, // Change cursor color
                        focusedLeadingIconColor = Color.White,
                        unfocusedLeadingIconColor = Color.White,
                        focusedLabelColor = Color.White,
                        unfocusedLabelColor = Color.White,
                    ),
                )
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .horizontalScroll(rememberScrollState()),
            ) {
                for (category in getAllGameGenreCategories()) {
                    SuggestionChip(
                        onClick = {
                            onQueryChanged(category.value)
                            onChipsSearch()
                            searchText.value = "A"
                            currentSearchText.value = "A"
                        },
                        label = {
                            Text(
                                text = category.value,
                            )
                        },
                        modifier = Modifier
                            .padding(end = 8.dp, bottom = 3.dp, start = 8.dp),
                        colors = SuggestionChipDefaults.suggestionChipColors(
                            containerColor = Grey2,
                            labelColor = Color.White
                        )
                    )
                }
            }
            if (currentSearchText.value.isEmpty()) {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Grey2)
                ) {
                    items(
                        recentSearches.size
                    ) { index ->

                        RecentSearchItem(searchText = recentSearches[index],
                            onClick = {
                                onQueryChanged(recentSearches[index])
                                onExecuteSearch()
                                searchText.value = recentSearches[index]
                                currentSearchText.value = recentSearches[index]
                            },
                            onDelete = { deletedSearch ->
                                recentSearches.remove(deletedSearch)
                                saveRecentSearches(context, recentSearches)
                            }
                        )
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterial3Api::class)
@Composable
fun RecentSearchItem(
    searchText: String,
    onClick: () -> Unit,
    onDelete: (String) -> Unit
) {
    var show by remember { mutableStateOf(true) }
    var dismissedToStart by remember { mutableStateOf(false) }

    val dismissState = rememberDismissState(
        confirmValueChange = {
            if (it == DismissValue.DismissedToStart ||
                it == DismissValue.DismissedToEnd
            ) {
                dismissedToStart = true
                true
            } else {
                dismissedToStart = false
                false
            }
        }
    )
    LaunchedEffect(dismissedToStart) {
        if (dismissedToStart) {
            show = false
            onDelete(searchText)
            Log.d(TAG, "Item Deleted $searchText")
        }
    }
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 15.dp, top = 15.dp)
            .combinedClickable(
                true,
                onClick = onClick,
            )
    ) {
        SwipeToDismiss(
            state = dismissState,
            modifier = Modifier,
            background = {
                DismissBackground(dismissState)
            },
            dismissContent = {
                Text(
                    text = searchText,
                    modifier = Modifier
                        .weight(1f),
                    color = Color.White
                )
            }
        )
    }
}


private fun loadRecentSearches(
    context: Context
): List<String> {
    val sharedPreferences: SharedPreferences = context.getSharedPreferences(
        "GameJournal",
        Context.MODE_PRIVATE
    )
    val recentSearchesSet = sharedPreferences.getStringSet(RECENT_SEARCHES_KEY, setOf())
    Log.d(TAG, "Load Recent $recentSearchesSet")
    return recentSearchesSet?.toList() ?: emptyList()
}

// Function to save recent searches to SharedPreferences
@SuppressLint("CommitPrefEdits")
private fun saveRecentSearches(
    context: Context,
    recentSearches: List<String>
) {
    val sharedPref = context.getSharedPreferences(
        "GameJournal",
        Context.MODE_PRIVATE
    ) ?: return
    with(sharedPref.edit()) {
        putStringSet(RECENT_SEARCHES_KEY, recentSearches.toSet())
        apply()
    }
}