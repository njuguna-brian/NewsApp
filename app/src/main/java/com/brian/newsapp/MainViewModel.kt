package com.brian.newsapp

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.brian.newsapp.domain.usecases.AppEntryUseCases
import com.brian.newsapp.presentation.navgraph.Route
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val appEntryUseCases: AppEntryUseCases
) : ViewModel() {

    var splashCondition by mutableStateOf(true)
        private set

    var startDestination by mutableStateOf(Route.AppStartNavigation.route)
        private set

    init {
        viewModelScope.launch {
            appEntryUseCases.readAppEntry().onEach { shouldStartFromHomeScreen ->
                startDestination = if (shouldStartFromHomeScreen) {
                    Route.NewsNavigation.route
                } else {
                    Route.AppStartNavigation.route
                }
                delay(300)
                splashCondition = false
            }.launchIn(viewModelScope)
        }
    }
}