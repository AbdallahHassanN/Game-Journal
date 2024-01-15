package com.example.steamdbmockup.common

import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SuggestionChip
import androidx.compose.material3.SuggestionChipDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.steamdbmockup.ui.theme.Grey1
import com.example.steamdbmockup.ui.theme.Grey2

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchAppBar(
    query: String,
    onQueryChanged: (String) -> Unit,
    onExecuteSearch: () -> Unit,
    onChipsSearch: () -> Unit
) {
    val keyboardController = LocalSoftwareKeyboardController.current
    val focusRequester = remember { FocusRequester() }

    DisposableEffect(Unit) {
        focusRequester.requestFocus()
        onDispose { }
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
                        autoCorrect = true
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
        }
    }
}