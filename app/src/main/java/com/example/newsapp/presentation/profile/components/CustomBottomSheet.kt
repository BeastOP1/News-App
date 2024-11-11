package com.example.newsapp.presentation.profile.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.newsapp.presentation.onBoarding.components.Dimens.MediumPadding1
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomSheet(modifier: Modifier = Modifier) {


    val scope = rememberCoroutineScope()
    val state = rememberModalBottomSheetState()
    ModalBottomSheet(
        onDismissRequest = {
          scope.launch{
              state.hide()
          }
        },
        modifier = Modifier,
        sheetState = state,
        containerColor = Color.Transparent,
        contentColor = MaterialTheme.colorScheme.onBackground,
        content = {
            Column(
                modifier  = Modifier.wrapContentHeight().padding(MediumPadding1),
                verticalArrangement = Arrangement.spacedBy(MediumPadding1),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.Bottom
                ) {

                    Text("")
                }
            }
        }
    )
}