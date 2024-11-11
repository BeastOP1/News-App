package com.example.newsapp.presentation.auth

sealed class AuthUIEvent {

    data class ChangedScreen(var screen: String) : AuthUIEvent()
    data class ChangedLogEmail(var logEmail: String) : AuthUIEvent()
    data class ChangedLogPassword(var logPassword: String) : AuthUIEvent()
    data class ChangedLogButton(var enabled: Boolean) : AuthUIEvent()

    object LogButtonClicked : AuthUIEvent()

    data class ChangedSignEmail(var signEmail: String) : AuthUIEvent()
    data class ChangedSignPassword(var signPassword: String) : AuthUIEvent()
    data class ChangedSignConfirmPassword(var signConfirmPassword: String) : AuthUIEvent()
    data class ChangedSignButton(var enabled: Boolean) : AuthUIEvent()

    object SignButtonClicked : AuthUIEvent()

}


sealed class UIEvent {
    object None : UIEvent()
    object NavigateToHome : UIEvent()
    object NavigateToLog : UIEvent()
    data class ShowError(val message: String) : UIEvent()
}