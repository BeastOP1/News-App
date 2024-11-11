package com.example.newsapp.presentation.profile

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.newsapp.domain.usecase.news.NewsUseCases
import com.example.newsapp.presentation.auth.UIEvent
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuth.AuthStateListener

import dagger.hilt.android.lifecycle.HiltViewModel
import okhttp3.internal.wait
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel  @Inject constructor(
    val useCases: NewsUseCases
): ViewModel(){



    var profileUiState = mutableStateOf(ProfileUIState())
        private set


    fun onEvent(event: ProfileUiEvent){
        when(event){
            is ProfileUiEvent.ChangedAutoPlayState -> {
                profileUiState.value = profileUiState.value.copy(
                    autoPlay = event.status
                )
            }
            is ProfileUiEvent.ChangedHDState -> {

                profileUiState.value = profileUiState.value.copy(
                    hdEnabled = event.status
                )

            }
            is ProfileUiEvent.ChangedModeState -> {
                profileUiState.value = profileUiState.value.copy(
                    mode = event.status
                )
            }
            is ProfileUiEvent.ChangedNotificationState -> {
                profileUiState.value = profileUiState.value.copy(
                    notificationEnabled = event.status
                )
            }
            is ProfileUiEvent.FeedClicked -> {
                profileUiState.value = profileUiState.value.copy(
                    feedClicked = event.showBottomSheet
                )
            }
            is ProfileUiEvent.LanguageClicked -> {
                profileUiState.value = profileUiState.value.copy(
                    languageClicked = event.showBottomSheet
                )
            }
            is ProfileUiEvent.ShareClicked -> {
                profileUiState.value = profileUiState.value.copy(
                    shareClicked = event.showBottomSheet
                )
            }
            is ProfileUiEvent.TextSizeClicked -> {
                profileUiState.value = profileUiState.value.copy(
                    textSizeClicked = event.showBottomSheet
                )
            }

           is ProfileUiEvent.LogOutClicked -> {
                profileUiState.value = profileUiState.value.copy(
                    alertDialog = event.status
                )
            }
        }
    }


    fun logOut(){

        val user =FirebaseAuth.getInstance()
        user.signOut()

          val authStateListener =AuthStateListener{
            if(it.currentUser == null){
                Log.d("News","Log out Successfully")
            }
            else {
                Log.d("News","Causing An Error")
            }

        }

        user.addAuthStateListener(authStateListener)

    }

}
