package com.example.newsapp.presentation.common

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import com.example.newsapp.ui.theme.CustomGray
import com.example.newsapp.ui.theme.CustomOrange
import com.example.newsapp.ui.theme.NewsAppTheme

@Composable
fun CustomButton(text: String, onCLick: () -> Unit, isEnabled : Boolean) {


    val interactionSource = remember {
        MutableInteractionSource()    }
    Button(
        onClick = {onCLick.invoke()},
        modifier = Modifier.fillMaxWidth().height(50.dp),
        enabled = isEnabled,
        shape = RoundedCornerShape(10.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = CustomOrange,
            disabledContainerColor = CustomGray,
            disabledContentColor = MaterialTheme.colorScheme.onBackground,
            contentColor = MaterialTheme.colorScheme.onBackground
        ),
        elevation = ButtonDefaults.buttonElevation(
            10.dp
        ),
        interactionSource = interactionSource
    ) {
        Text(text = text, style = MaterialTheme.typography.titleLarge)
    }
}


@PreviewLightDark
@Composable
fun CustomButtonPreview(modifier: Modifier = Modifier) {
    NewsAppTheme {
        Column(
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {

            CustomButton(text = "Log in ", onCLick = {}, isEnabled = true)
            CustomButton(text = "Sign Up ", onCLick = {}, isEnabled = false)

        }
        }
}