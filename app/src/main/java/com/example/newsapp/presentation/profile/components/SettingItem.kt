package com.example.newsapp.presentation.profile.components

import androidx.compose.foundation.Indication
import com.example.newsapp.R
import androidx.compose.foundation.background
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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.Language
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.focusTarget
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import com.example.newsapp.presentation.onBoarding.components.Dimens.MediumPadding1
import com.example.newsapp.ui.theme.CustomGray
import com.example.newsapp.ui.theme.CustomOrange
import com.example.newsapp.ui.theme.NewsAppTheme

@Composable
fun SettingItem(text : String, onClick :() -> Unit , image : Int) {

    Column(
        modifier = Modifier.wrapContentHeight().clickable(
            indication = null,
            interactionSource = remember { MutableInteractionSource() }
        ){ onClick.invoke()},
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Spacer(modifier = Modifier.height(5.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            Icon(painter = painterResource(id = image), contentDescription = null , tint = CustomOrange, modifier = Modifier.size(25.dp))

            Text(text = text, style = MaterialTheme.typography.labelLarge.copy(color = MaterialTheme.colorScheme.onBackground))
            Spacer(modifier = Modifier.weight(1f))
            Icon(imageVector = Icons.Default.KeyboardArrowRight, contentDescription = null , tint = MaterialTheme.colorScheme.onBackground, modifier = Modifier)

        }

        Spacer(modifier = Modifier.height(10.dp))
        Divider(color = CustomOrange)
    }

}
@Composable
fun SettingItemWithSwitch(text: String, check: Boolean, onCheck: (Boolean) -> Unit, image: Int) {


    val interactionSource = remember { MutableInteractionSource() }

    Column(
        modifier = Modifier.wrapContentHeight(),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            Icon(painter = painterResource( id = image), contentDescription = null , tint = CustomOrange, modifier = Modifier.size(25.dp))

            Text(text = text, style = MaterialTheme.typography.labelLarge.copy(color = MaterialTheme.colorScheme.onBackground))
            Spacer(modifier = Modifier.weight(1f))

            Switch(
                checked = check,
                onCheckedChange =onCheck,
                modifier = Modifier,
                thumbContent = {
                    null
                },
                enabled = true,
                colors = SwitchDefaults.colors(
                    disabledCheckedTrackColor = CustomGray,
                    disabledCheckedIconColor = CustomGray,
                    disabledCheckedThumbColor = Color.White,
                    checkedThumbColor = Color.White,
                    checkedTrackColor = CustomOrange,
                ),
                interactionSource = interactionSource
            )
        }

        Spacer(modifier = Modifier.height(5.dp))
        Divider(color = CustomOrange)
    }

}


@PreviewLightDark
@Composable
fun SettingItemPreview(modifier: Modifier = Modifier) {

    NewsAppTheme {
        Column(
            modifier = Modifier.background(MaterialTheme.colorScheme.background).padding(horizontal = MediumPadding1).wrapContentHeight(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            SettingItem(text = "Language", image = R.drawable.home, onClick = {})
            SettingItemWithSwitch(text ="Notification", check = true, onCheck = {

            }, image = R.drawable.search)
            SettingItem(text = "Personalize Your Feed", image = R.drawable.share, onClick = {})
            SettingItem(text = "Language", image = R.drawable.person, onClick = {})
            SettingItem(text = "Language", image = R.drawable.person, onClick = {})


        }
    }
}