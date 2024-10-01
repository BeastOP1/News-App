package com.example.newsapp.presentation.common

import android.annotation.SuppressLint
import android.content.res.Configuration
import androidx.compose.foundation.border
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.traceEventEnd
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.newsapp.presentation.onBoarding.components.Dimens.IconSize

@Composable
fun SearchBar(
    modifier: Modifier = Modifier,
    text :String,
    readOnly : Boolean,
    onClick :(() -> Unit)? = null,
    onValueChange : (String) -> Unit,
    onSearch : () -> Unit
    ) {

    val interactionSource = remember {
        MutableInteractionSource()
    }
    val isClicked  = interactionSource.collectIsPressedAsState().value
    
    LaunchedEffect(key1 = isClicked) {
        if(isClicked){
            onClick?.invoke()
        }
        
    }

    Box(
        modifier = Modifier
    ) {
        TextField(
            modifier = modifier
                .fillMaxWidth()
                .searchBarBorder(),
            value = text,
            onValueChange = onValueChange,
            readOnly = readOnly,
            leadingIcon = {
                Icon(imageVector = Icons.Filled.Search,
                    modifier = Modifier.size(IconSize),
                    tint = if(isSystemInDarkTheme()) Color.White else Color.Black,
                    contentDescription = null)
            },
            placeholder = {
                Text(
                    text = "Search" ,
                    style = MaterialTheme.typography.bodySmall,
                    color = if(isSystemInDarkTheme()) Color.White else Color.Black
                )
            },
            shape = MaterialTheme.shapes.medium,
            colors = TextFieldDefaults.colors(
                focusedContainerColor = if(isSystemInDarkTheme()) Color.Black else Color.White,
                unfocusedContainerColor = if(isSystemInDarkTheme()) Color.Black else Color.White,
                focusedTextColor = if(isSystemInDarkTheme()) Color.White else Color.Black,
                unfocusedTextColor = if(isSystemInDarkTheme()) Color.White else Color.Black,
                cursorColor =  if(isSystemInDarkTheme()) Color.White else Color.Black,
                disabledIndicatorColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                errorIndicatorColor = Color.Transparent
            ),
            singleLine = true,
            keyboardActions = KeyboardActions(
                onSearch = {
                    onSearch()
                }
            ),
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
            textStyle = MaterialTheme.typography.bodySmall,
            interactionSource = interactionSource,


        )
    }

}


@SuppressLint("ModifierFactoryUnreferencedReceiver")
fun Modifier.searchBarBorder() = composed {

        border(
            color = if(isSystemInDarkTheme()) Color.White else Color.Black,
            width = 1.dp,
            shape = MaterialTheme.shapes.medium)

}

@Preview(showBackground = true)
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun TExtFieldPreView(modifier: Modifier = Modifier) {

    SearchBar(text = "", readOnly = false, onValueChange = {}) {
        
    }
}