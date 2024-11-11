package com.example.newsapp.presentation.profile.components
import androidx.compose.animation.Crossfade
import com.example.newsapp.R
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.Logout
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.capitalize
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import com.example.newsapp.presentation.onBoarding.components.Dimens.MediumPadding1
import com.example.newsapp.ui.theme.CustomOrange

import com.example.newsapp.ui.theme.NewsAppTheme

@Composable
fun ProfileHead(user: String, onLogOutClick :() -> Unit) {

    Row(
        modifier = Modifier.fillMaxWidth().padding(bottom = MediumPadding1),
        horizontalArrangement = Arrangement.spacedBy(20.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = R.drawable.person),
            colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.onBackground),
            contentDescription = null,
            modifier = Modifier.size(70.dp).clip(CircleShape),
            contentScale = ContentScale.Crop,

        )


        Column(
            modifier = Modifier.wrapContentHeight(),
            verticalArrangement = Arrangement.Center
        ) {
            Text(text =user.capitalize(), style = MaterialTheme.typography.titleMedium.copy(color = MaterialTheme.colorScheme.onBackground) , )
            Spacer(modifier = Modifier.height(5.dp))
            Text(text = user, style = MaterialTheme.typography.bodyMedium.copy(color = Color.Gray) , )
        }

        Spacer(modifier = Modifier.weight(1f))
        Column(
            modifier = Modifier.clickable(
                indication = null,
                interactionSource = remember { MutableInteractionSource() }
            ){
                onLogOutClick.invoke()
            },
            verticalArrangement = Arrangement.Center
        ) {
            Spacer(modifier = Modifier.height(10.dp))
            Icon(imageVector = Icons.Default.Logout, contentDescription = null , tint = CustomOrange)
        }
    }

}


@PreviewLightDark
@Composable
fun ProfileHeadPreview(modifier: Modifier = Modifier) {

    NewsAppTheme {
//        ProfileHead(authUIState)
    }
}