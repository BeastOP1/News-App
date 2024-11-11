package com.example.newsapp.presentation.common

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.DialogProperties
import com.example.newsapp.ui.theme.CustomOrange
import com.example.newsapp.ui.theme.CustomWhite
import com.example.newsapp.ui.theme.NewsAppTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LogOUtAlertDialog( onConfirmClick :() -> Unit , onCancel :() -> Unit ) {


    AlertDialog(onDismissRequest = {
onCancel.invoke()
    }, content = {
        Card(
            enabled = false,
            onClick = {
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(170.dp),
            elevation = CardDefaults.elevatedCardElevation(
                pressedElevation = 40.dp,
                hoveredElevation = 60.dp,
                defaultElevation = 30.dp,
                 focusedElevation = 50.dp
            )
        ) {

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colorScheme.background),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceAround

            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Absolute.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Text(text = "Log Out", style = MaterialTheme.typography.titleSmall.copy(
                        color = MaterialTheme.colorScheme.onBackground))
                }
                Spacer(modifier = Modifier.height(10.dp))
                Text(text = "Are You Sure?", style = MaterialTheme.typography.bodyMedium.copy(
                    color = MaterialTheme.colorScheme.onBackground
                ))
                Spacer(modifier = Modifier.height(10.dp))

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 15.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Button(
                        onClick = {},
                        modifier = Modifier
                            .clip(RoundedCornerShape(10.dp))
                            .background(
                                CustomOrange
                            ),
                        colors = ButtonDefaults.elevatedButtonColors(
                            containerColor = Color.Transparent,
                            contentColor = MaterialTheme.colorScheme.background
                        )
                    ) {
                        Text(text = "Cancel", style = MaterialTheme.typography.labelLarge)
                    }

                    Spacer(modifier = Modifier.weight(1f))
                    Button(
                        onClick = {
                            onConfirmClick.invoke()
                        },
                        modifier = Modifier
                            .clip(RoundedCornerShape(10.dp))
                            .background(
                                CustomOrange
                            ),
                        colors = ButtonDefaults.elevatedButtonColors(
                            containerColor = Color.Transparent,
                            contentColor = MaterialTheme.colorScheme.background
                        )
                    ) {
                        Text(text = "Confirm", style = MaterialTheme.typography.labelLarge)
                    }
                }

            }


        }

    },
        properties = DialogProperties(
            dismissOnBackPress = true,
            dismissOnClickOutside = true,
            decorFitsSystemWindows = true
        )
        )


}

@PreviewLightDark
@Composable
fun LogOutAlertDialogPreview(modifier: Modifier = Modifier) {

    NewsAppTheme {
        LogOUtAlertDialog(onCancel = {}, onConfirmClick = {})
    }
}