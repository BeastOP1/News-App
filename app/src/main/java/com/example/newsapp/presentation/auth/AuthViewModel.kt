package com.example.newsapp.presentation.auth

import AuthUIState
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asFlow
import androidx.lifecycle.viewModelScope
import com.example.newsapp.domain.Validation
import com.example.newsapp.domain.usecase.app_entry.AppEntryUseCases
import com.example.newsapp.presentation.navGraph.Route
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuth.AuthStateListener
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import okhttp3.internal.notify
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val appEntryUseCases: AppEntryUseCases,
) : ViewModel(){
    var authState by mutableStateOf(AuthUIState())

    private val _uiEvent = MutableStateFlow<UIEvent>(UIEvent.None)
    val uiEvent: StateFlow<UIEvent> get() = _uiEvent.asStateFlow()

    fun onEvent(event: AuthUIEvent) {
        when (event) {
            is AuthUIEvent.ChangedLogEmail -> {
                authState = authState.copy(
                    logEmail = event.logEmail
                )
                validateLogDataWithRules()
            }

            is AuthUIEvent.ChangedLogPassword -> {
                authState = authState.copy(
                    logPassword = event.logPassword
                )
                validateLogDataWithRules()

            }

            is AuthUIEvent.ChangedScreen -> {
                authState = authState.copy(
                    screen = event.screen,
                )
            }

            is AuthUIEvent.ChangedSignConfirmPassword -> {
                authState = authState.copy(
                    signConfirmPassword = event.signConfirmPassword
                )
                validateSignDataWithRules()

            }

            is AuthUIEvent.ChangedSignEmail -> {
                authState = authState.copy(
                    signEmail = event.signEmail
                )
            }

            is AuthUIEvent.ChangedSignPassword -> {
                authState = authState.copy(
                    signPassword = event.signPassword
                )
                validateSignDataWithRules()
            }

            AuthUIEvent.LogButtonClicked -> {
                logIn(authState.logEmail, authState.logPassword)
            }

            AuthUIEvent.SignButtonClicked -> {
                signIn(authState.signEmail, authState.signConfirmPassword)
            }

            is AuthUIEvent.ChangedLogButton -> {
                authState = authState.copy(
                    logButtonError = event.enabled
                )
            }

            is AuthUIEvent.ChangedSignButton -> {
                authState = authState.copy(
                    signButtonError = event.enabled
                )
            }
        }
    }


    private fun signIn(email: String, password: String) {
        FirebaseAuth.getInstance()
            .createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener {
                authState = authState.copy(
                    progressShow = true
                )

                viewModelScope.launch {
                    delay(3000L)
                    authState = authState.copy(
                        progressShow = false
                    )
                }
                if (it.isSuccessful) {
                    Log.d("News", "Sign in Successfully with ${email}, ${it.isSuccessful}.")

                    ///navigation
                    _uiEvent.value = UIEvent.NavigateToHome
//
                }

            }
            .addOnFailureListener {
                Log.d("News", "Failed to Sign with , ${it.localizedMessage}.")
                Log.d("News", "Failed to Sign with , ${it.message}.")
                _uiEvent.value =UIEvent.ShowError("Login failed: ${it.localizedMessage}")

            }
    }

    private fun logIn(email: String, password: String) {

        FirebaseAuth.getInstance()
            .signInWithEmailAndPassword(email, password)
            .addOnCompleteListener {
                Log.d("News", "Login Successfully With ${email} and password ${password}.")

                if (it.isSuccessful) {
                    _uiEvent.value = UIEvent.NavigateToHome
                }
            }
            .addOnFailureListener {
                Log.d("News", " ${it.message}")
                Log.d(
                    "News",
                    " Authentication Failed Causing error with :==> ${it.localizedMessage}"
                )
                _uiEvent.value =UIEvent.ShowError("Login failed: ${it.localizedMessage}")

            }
    }


    private fun validateSignDataWithRules() {
        val signPassword = Validation.validateSignPassword(authState.signPassword)
        val signConfirmPassword = Validation.validateSignPassword(authState.signPassword)
        val signEmail = Validation.validateSignEmail(authState.signEmail)

        authState = authState.copy(
            signButtonError = ((signEmail.status && signPassword.status) && (signConfirmPassword.status))
        )
    }

    private fun validateLogDataWithRules() {

        val logEmail = Validation.validateLogEmail(authState.logEmail)
        val logPassword = Validation.validateLogPassword(authState.logPassword)

        authState = authState.copy(
            logButtonError = (logEmail.status && logPassword.status)
        )
    }


     fun logOut(){

        val firebaseAuth =FirebaseAuth.getInstance()
        firebaseAuth.signOut()

        val authStateListener =AuthStateListener{
            if(it.currentUser == null){
                Log.d("News","Log out Successfully")
                _uiEvent.value = UIEvent.NavigateToLog
            }
            else {
                Log.d("News","Causing An Error")
            }

        }

        firebaseAuth.addAuthStateListener(authStateListener)

    }

}
