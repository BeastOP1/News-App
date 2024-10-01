package com.example.newsapp


import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsapp.domain.usecase.app_entry.AppEntryUseCases
import com.example.newsapp.presentation.navGraph.Route
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class MainViewModel  @Inject constructor(
    private val appEntryUseCases: AppEntryUseCases
): ViewModel() {

//    this is the same working like below splashCondition variable
//    private val _splashCondition = mutableStateOf(true)
//    val splash : State<Boolean> = _splashCondition

     var splashCondition by mutableStateOf(true)
         private set

    //again this is same working like below startDestination
//    private val _start = mutableStateOf<String>(Route.AppStartNavigation.route)
//    val start : State<String> = _start

    var startDestination by mutableStateOf(Route.AppStartNavigation.route)
        private set


    init {

        appEntryUseCases.readAppEntry().onEach {shouldStartFromHomeScreen ->
            if (shouldStartFromHomeScreen){
                startDestination = Route.NewsNavigation.route
            }else{
                startDestination = Route.AppStartNavigation.route
            }
            delay(300)
            splashCondition = false
        }.launchIn(viewModelScope)

    }

}