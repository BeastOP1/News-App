package com.example.newsapp.presentation.auth

import android.widget.Toast
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import com.example.newsapp.R
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerDefaults
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.newsapp.MainViewModel
import com.example.newsapp.presentation.common.CustomAuthTab
import com.example.newsapp.presentation.common.CustomButton
import com.example.newsapp.presentation.common.CustomTextField
import com.example.newsapp.presentation.common.CustomTextFieldPassword
import com.example.newsapp.presentation.navGraph.Route
import com.example.newsapp.presentation.onBoarding.components.Dimens.MediumPadding1
import com.example.newsapp.ui.theme.CustomOrange
import com.example.newsapp.ui.theme.CustomRed
import com.example.newsapp.ui.theme.NewsAppTheme
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun AuthScreen(
    authUIState: AuthUIState,
    event: (AuthUIEvent) -> Unit,
    navController: NavController,
) {

    val pagerState = rememberPagerState(initialPage = 1) {
        2
    }
    val screens = listOf("Log In", "Sign Up")

    val coroutineScope = rememberCoroutineScope()
    LaunchedEffect(key1 = pagerState) {
        snapshotFlow { pagerState.currentPage }.collect { page ->
            event(AuthUIEvent.ChangedScreen(screen = screens[page]))
        }

    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(MediumPadding1),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {


        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(R.drawable.news),
                contentDescription = null,
                modifier = Modifier.size(200.dp)
            )
        }

        CustomAuthTab(
                pagerState = pagerState,
                onTabSelected = {
                    coroutineScope.launch{
                        pagerState.animateScrollToPage(it)
                    }

                },
                screen = screens
            )
        HorizontalPager(
                state = pagerState,
                flingBehavior = PagerDefaults.flingBehavior(state = pagerState)
            ) { index->

                when (index) {
                    0->{
                        LogInScreen(authUIState, event)
                    }

                    1 -> {
                        SignUpScreen(authUIState, event)
                    }

                    else -> {
                        null
                    }
                }
            }


    }

}

@PreviewLightDark
@Composable
fun AuthScreenPreview() {

    NewsAppTheme {
        val viewmodel : MainViewModel = hiltViewModel()
//        val uiState = viewmodel.authState
//        AuthScreen(
//            authUIState = uiState
//        ) {
//            viewmodel::onEvent
//        }
    }
}

@Composable
fun LogInScreen(uiState: AuthUIState, event: (AuthUIEvent) -> Unit) {

    Column(
        modifier = Modifier.fillMaxHeight(),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        Spacer(modifier = Modifier.height(10.dp))
        CustomTextField(
            text = uiState.logEmail,
            label = "Email",
            onValue = {
                event(AuthUIEvent.ChangedLogEmail(it))
            },
            isError = uiState.logEmailError
        )
        CustomTextFieldPassword(
            text = uiState.logPassword,
            label = "Password",
            onValue = {
                event(AuthUIEvent.ChangedLogPassword(it))
            },
            isError = uiState.logPasswordError
        )

        Spacer(modifier = Modifier.height(10.dp))
        CustomButton(text = "Log in", isEnabled = uiState.logButtonError, onCLick = {
            event(AuthUIEvent.LogButtonClicked)
        })

    }

}


@Composable
fun SignUpScreen(uiState: AuthUIState, event: (AuthUIEvent) -> Unit) {

    Column(
        modifier = Modifier.fillMaxHeight(),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {

        Row(
            modifier = Modifier.fillMaxWidth().padding(top = 10.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = "Create an account")
        }

        CustomTextField(
            text = uiState.signEmail,
            label = "Email",
            onValue = {
                event(AuthUIEvent.ChangedSignEmail(it))
            },
            isError = uiState.signEmailError
        )

        CustomTextFieldPassword(
            text = uiState.signPassword,
            label = "Password",
            onValue = {
                event(AuthUIEvent.ChangedSignPassword(it))
            },
            isError = uiState.signPasswordError
        )


        CustomTextFieldPassword(
            text = uiState.signConfirmPassword,
            label = "Confirm Password",
            onValue = {
                event(AuthUIEvent.ChangedSignConfirmPassword(it))
            },
            isError = uiState.signConfirmPasswordError
        )

        if(uiState.signPassword != "" ){
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    "Password Should be at least 6 char",
                    color = if(uiState.signPassword == uiState.signConfirmPassword) CustomOrange else CustomRed,
                    style = MaterialTheme.typography.bodySmall
                )
            }
        }


        Spacer(modifier = Modifier.height(20.dp))

        CustomButton(
            text = "Sign up",
            onCLick = {
                event(AuthUIEvent.SignButtonClicked)
            },
            isEnabled = uiState.signButtonError
        )

    }


}

