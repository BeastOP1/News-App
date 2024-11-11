package com.example.newsapp


import AuthUIState
import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.currentCompositionLocalContext
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsapp.domain.Validation
import com.example.newsapp.domain.usecase.app_entry.AppEntryUseCases
import com.example.newsapp.presentation.auth.AuthUIEvent
import com.example.newsapp.presentation.navGraph.Route
import com.example.quote.producer
import com.google.android.play.core.integrity.au
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.events.Event
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import okhttp3.Dispatcher
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val appEntryUseCases: AppEntryUseCases,
) : ViewModel() {

//    this is the same working like below splashCondition variable
//    private val _splashCondition = mutableStateOf(true)
//    val splash : State<Boolean> = _splashCondition

    var splashCondition by mutableStateOf(true)
        private set
    val currentUser = FirebaseAuth.getInstance().currentUser?.uid

    //again this is same working like below startDestination
//    private val _start = mutableStateOf<String>(Route.AppStartNavigation.route)
//    val start : State<String> = _start



    var startDestination by mutableStateOf(Route.AppStartNavigation.route)
        private set


    init {
        appStart()
    }


    private fun appStart() {
        viewModelScope.launch(Dispatchers.IO) {
            appEntryUseCases.readAppEntry().onEach { shouldStartFromHomeScreen ->
                startDestination = if (shouldStartFromHomeScreen) {
                        Route.NewsNavigation.route
                } else {
                    Route.AppStartNavigation.route /// thats for first time user
                }
                // Delay to ensure a smooth transition after splash
                delay(100)
                splashCondition = false
            }.launchIn(viewModelScope)
        }

    }


}

