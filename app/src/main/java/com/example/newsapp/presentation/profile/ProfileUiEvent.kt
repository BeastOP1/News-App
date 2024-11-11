package com.example.newsapp.presentation.profile

sealed class ProfileUiEvent {

    data class ChangedNotificationState(var status : Boolean): ProfileUiEvent()
    data class ChangedHDState(var status : Boolean): ProfileUiEvent()
    data class ChangedModeState(var status : Boolean): ProfileUiEvent()
    data class ChangedAutoPlayState(val status : Boolean): ProfileUiEvent()

    data class LanguageClicked(var showBottomSheet: Boolean): ProfileUiEvent()
    data class FeedClicked(var showBottomSheet: Boolean): ProfileUiEvent()
    data class TextSizeClicked(var showBottomSheet: Boolean): ProfileUiEvent()
    data class ShareClicked(var showBottomSheet: Boolean): ProfileUiEvent()
    data class LogOutClicked(val status : Boolean): ProfileUiEvent()
}