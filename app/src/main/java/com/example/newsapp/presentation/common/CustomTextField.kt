package com.example.newsapp.presentation.common

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Icon

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.PreviewLightDark
import com.example.newsapp.ui.theme.CustomGray
import com.example.newsapp.ui.theme.CustomOrange
import com.example.newsapp.ui.theme.CustomRed
import com.example.newsapp.ui.theme.NewsAppTheme

@Composable
fun CustomTextField(text: String, label: String, onValue: (String) -> Unit , isError : Boolean) {

    TextField(
        modifier = Modifier.fillMaxWidth(),
        value = text,
        onValueChange = onValue,
        label = {
            Text(text = label, color = Color.Gray, style = MaterialTheme.typography.bodySmall)
        },
        colors = TextFieldDefaults.colors(
            focusedTextColor = MaterialTheme.colorScheme.onBackground,
            focusedContainerColor = MaterialTheme.colorScheme.background,
            focusedLeadingIconColor = CustomOrange,
            unfocusedLabelColor = CustomGray,
            focusedLabelColor = CustomOrange,
            unfocusedLeadingIconColor = CustomGray,
            unfocusedContainerColor = Color.Transparent,
            focusedIndicatorColor = CustomOrange,
            cursorColor = CustomOrange
        ),
        textStyle = MaterialTheme.typography.labelLarge,
        maxLines = 1,
        isError = isError,
        keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Next
        ),
    )
}

@Composable
fun CustomTextFieldPassword(text: String, label: String, onValue: (String) -> Unit, isError: Boolean) {


    val focusManger = LocalFocusManager.current
    val passWordVisible = remember { mutableStateOf(false) }
    TextField(
        modifier = Modifier.fillMaxWidth(),
        value = text,
        onValueChange =onValue,
        label = {
            Text(text = label, color = Color.Gray, style = MaterialTheme.typography.bodySmall)
        },
        trailingIcon = {
            val icon = if (passWordVisible.value) Icons.Default.Visibility else Icons.Default.VisibilityOff
            val color = if(passWordVisible.value) CustomOrange else CustomGray
            if (text != "") {

                Icon(imageVector = icon, contentDescription = null, tint = color, modifier = Modifier.clickable {
                    passWordVisible.value = !passWordVisible.value
                })

            }
            else {
                null
            }
        },
        colors = TextFieldDefaults.colors(
            focusedTextColor = MaterialTheme.colorScheme.onBackground,
            focusedContainerColor = MaterialTheme.colorScheme.background,
            focusedLeadingIconColor = CustomOrange,
            unfocusedLabelColor = CustomGray,
            unfocusedLeadingIconColor = CustomGray,
            unfocusedContainerColor = Color.Transparent,
            errorIndicatorColor = CustomRed,
            focusedIndicatorColor = CustomOrange,
            errorContainerColor = Color.Transparent,
            cursorColor = CustomOrange
        ),
        keyboardActions = KeyboardActions(
            onDone = {
                focusManger.clearFocus()
            }
        ),
        keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Done
        ),
        textStyle = MaterialTheme.typography.labelLarge,
        maxLines = 1,
        isError = false,
        visualTransformation = if (passWordVisible.value) VisualTransformation.None else PasswordVisualTransformation()

    )
}


@PreviewLightDark
@Composable
fun TextFieldPreview(modifier: Modifier = Modifier) {

    NewsAppTheme {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            CustomTextField(text = "", label = "Email", onValue = {}, isError = false)
            CustomTextField(text = "", label = "Password", onValue = {}, isError = false)

        }
    }
}