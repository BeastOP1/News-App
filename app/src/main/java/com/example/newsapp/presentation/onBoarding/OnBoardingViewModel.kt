package com.example.newsapp.presentation.onBoarding

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsapp.domain.usecase.app_entry.AppEntryUseCases
import com.example.newsapp.presentation.onBoarding.OnBoardingEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OnBoardingViewModel @Inject constructor(
    private val appEntryUseCases: AppEntryUseCases
): ViewModel() {

    fun onEvent(event: OnBoardingEvent){

        when(event){
            OnBoardingEvent.SaveAppEntry -> {
                saveAppEntry()
            }

            else -> {}
        }

    }

    private fun saveAppEntry() {
        viewModelScope.launch {
            appEntryUseCases.saveAppEntry()
        }
    }
}