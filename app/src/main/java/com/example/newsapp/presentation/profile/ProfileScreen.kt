package com.example.newsapp.presentation.profile

import android.widget.Toast
import com.example.newsapp.R
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.newsapp.presentation.common.LogOUtAlertDialog
import com.example.newsapp.presentation.onBoarding.components.Dimens.MediumPadding1
import com.example.newsapp.presentation.profile.components.ProfileHead
import com.example.newsapp.presentation.profile.components.SettingItem
import com.example.newsapp.presentation.profile.components.SettingItemWithSwitch
import com.example.newsapp.ui.theme.NewsAppTheme

@Composable
fun ProfileScreen(
    user: String,
    profileUIState: ProfileUIState,
    event: (ProfileUiEvent) -> Unit,
    logOut:()-> Unit,
) {

    val context = LocalContext.current


    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = MediumPadding1, vertical = 20.dp),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        Spacer(modifier = Modifier.height(10.dp))
        Text(text = "Profile")
        ProfileHead(user, onLogOutClick = {
            event(ProfileUiEvent.LogOutClicked(status = true))
        })
        SettingItem(text = "Language", onClick = {
            Toast.makeText(context, "Not Implemented yet!", Toast.LENGTH_SHORT).show()

        }, image = R.drawable.lang)
        SettingItemWithSwitch(
            text = "Notification",
            check = profileUIState.notificationEnabled,
            onCheck = {
                event(ProfileUiEvent.ChangedNotificationState(it))
            },
            image = R.drawable.notification
        )
        SettingItem(text = "Personalize Your Feed", onClick = {
            Toast.makeText(context, "Not Implemented yet!", Toast.LENGTH_SHORT).show()

        }, image = R.drawable.question)
        SettingItemWithSwitch(
            text = "HD Images",
            check = profileUIState.hdEnabled,
            onCheck = {
                event(ProfileUiEvent.ChangedHDState(it))
            },
            image = R.drawable.image
        )
        SettingItemWithSwitch(
            text = "Dark Mode",
            check = profileUIState.mode,
            onCheck = {
                event(ProfileUiEvent.ChangedModeState(it))
            },
            image = R.drawable.dark_mode
        )
        SettingItemWithSwitch(
            text = "AutoPlay",
            check = profileUIState.autoPlay,
            onCheck = {
                event(ProfileUiEvent.ChangedAutoPlayState(it))
            },
            image = R.drawable.video
        )
        SettingItem(text = "Text Size", onClick = {
//            event(ProfileUiEvent.ShareClicked(true))
            Toast.makeText(context, "Not Implemented yet!", Toast.LENGTH_SHORT).show()

        }, image = R.drawable.text)
        SettingItem(text = "Share This App", onClick = {
            Toast.makeText(context, "Not Implemented yet!", Toast.LENGTH_SHORT).show()

        }, image = R.drawable.share)

        Spacer(modifier = Modifier.weight(1f))
    }


    if(profileUIState.alertDialog){
        LogOUtAlertDialog(
            onConfirmClick  = {
                event(ProfileUiEvent.LogOutClicked(true))
                logOut.invoke()
            }
        ) {
            event(ProfileUiEvent.LogOutClicked(false))

        }
    }
}


@PreviewLightDark
@Composable
fun ProfileScreenPreview(modifier: Modifier = Modifier) {

    NewsAppTheme {
//        ProfileScreen(authUIState)
    }
}