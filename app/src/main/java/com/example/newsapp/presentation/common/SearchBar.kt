package com.example.newsapp.presentation.common

import com.example.newsapp.R
import android.annotation.SuppressLint
import android.content.res.Configuration
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Cancel
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.*
import androidx.compose.runtime.remember
import androidx.compose.runtime.traceEventEnd
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.newsapp.presentation.onBoarding.components.Dimens.IconSize
import com.example.newsapp.presentation.onBoarding.components.Dimens.MediumPadding1
import com.example.newsapp.presentation.onBoarding.components.Dimens.MediumPadding2
import com.example.newsapp.ui.theme.CustomBlack
import com.example.newsapp.ui.theme.CustomGray
import com.example.newsapp.ui.theme.CustomGray1
import com.example.newsapp.ui.theme.CustomGray2
import com.example.newsapp.ui.theme.CustomOrange
import com.example.newsapp.ui.theme.CustomWhite

@Composable
fun SearchBar(
    onBack :()-> Unit,
    modifier: Modifier = Modifier,
    text: String,
    readOnly: Boolean,
    onClick: (() -> Unit)? = null,
    onValueChange: (String) -> Unit,
    onSearch: () -> Unit,
    onClearClick :()-> Unit,
) {

    val interactionSource = remember {
        MutableInteractionSource()
    }
    val isClicked = interactionSource.collectIsPressedAsState().value

    val backAnimate by remember { mutableStateOf(false) }
    val backXOffset by animateDpAsState(
        targetValue =  if(backAnimate) 120.dp else 0.dp,
    animationSpec = spring(dampingRatio = Spring.DampingRatioHighBouncy, stiffness = Spring.StiffnessHigh),
    label = "x Offset"
    )

    val backAlpha by animateFloatAsState(
        targetValue =  if(backAnimate) 0.1f else 1f,
        animationSpec = spring(dampingRatio = Spring.DampingRatioLowBouncy, stiffness = Spring.StiffnessHigh),
        label = "x Alpha"

    )
    LaunchedEffect(key1 = isClicked) {
        if (isClicked) {
            onClick?.invoke()
        }

    }

    Box(
        modifier = Modifier
    ) {

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            Icon(painter = painterResource(R.drawable.arrow_right), contentDescription = null, tint = CustomOrange,
                modifier = Modifier.offset(x = backXOffset).alpha(backAlpha).clickable(
                indication = null,
                interactionSource = interactionSource
            ){
                onBack.invoke()
            }.size(IconSize).rotate(180f))
            TextField(
                modifier = modifier.clip(RoundedCornerShape(30.dp)).fillMaxWidth(),
                shape = CircleShape,
                value = text,
                onValueChange = onValueChange,
                readOnly = readOnly,
                placeholder = {
                    Text(
                        text = "Search for news",
                        style = MaterialTheme.typography.bodySmall,
                        color = CustomGray1
                    )
                },
                trailingIcon = {

                    if(text !="")
                    Icon(imageVector = Icons.Default.Cancel, contentDescription = null, modifier = Modifier.clickable{
                        onClearClick.invoke()
                    }, tint = MaterialTheme.colorScheme.onBackground)
                },
                colors = TextFieldDefaults.colors(
                    unfocusedContainerColor = if(isSystemInDarkTheme()) MaterialTheme.colorScheme.background else CustomGray2,
                    focusedContainerColor = if(isSystemInDarkTheme()) MaterialTheme.colorScheme.background else CustomGray2,
                    focusedTextColor = MaterialTheme.colorScheme.onBackground ,
                    unfocusedTextColor = if(isSystemInDarkTheme()) MaterialTheme.colorScheme.onBackground else CustomGray,
                    cursorColor = CustomOrange,
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

}


@SuppressLint("ModifierFactoryUnreferencedReceiver")
fun Modifier.searchBarBorder() = composed {

    border(
        color = if (isSystemInDarkTheme()) Color.White else Color.Black,
        width = 1.dp,
        shape = MaterialTheme.shapes.medium
    )

}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomSearchBar(modifier: Modifier = Modifier) {

    var text by remember {
        mutableStateOf("")
    }
    var active by remember {
        mutableStateOf(false)
    }

    SearchBar(
        query = text,
        onQueryChange = {
            text = it
        },
        onSearch = {
            active = false
        },
        active = true,
        onActiveChange = {
            active = it
        },
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(30.dp))
            .height(50.dp),
        enabled = true,
        placeholder = {
            Text(text = "Search for News", fontSize = 19.sp)
        },
        colors = SearchBarDefaults.colors(
            inputFieldColors = TextFieldDefaults.colors(
                focusedContainerColor = Color.Yellow,
                unfocusedContainerColor = CustomGray,
            )
        ),

        ) {
    }
}

@PreviewLightDark
@Composable
fun TExtFieldPreView(modifier: Modifier = Modifier) {
//
//    SearchBar(text = "", readOnly = false, onValueChange = {}) {
//
//
    //    }


    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(horizontal = MediumPadding1, vertical = MediumPadding1),
        verticalArrangement = Arrangement.spacedBy(MediumPadding1),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        SearchBar(text = "", onSearch = {}, onValueChange = {}, onClick = {}, readOnly = false, modifier = Modifier, onBack = {}, onClearClick = {})
        Column(
            modifier = Modifier
                .weight(1f)
                .background(CustomWhite).padding(start = MediumPadding2 +10.dp, end = 0.dp),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = "Recent Search")
                Spacer(modifier = Modifier.weight(1f))
                Text(text = "Clear all", color = CustomOrange)

            }


        }
    }

}

@PreviewLightDark
@Composable
fun Preview2(modifier: Modifier = Modifier) {

    SearchBar(readOnly = false,
        text = "",
        onValueChange = {},
        onSearch = {},
        onClick = {},
        modifier = Modifier,
        onBack = {},
        onClearClick = {}
    )
}