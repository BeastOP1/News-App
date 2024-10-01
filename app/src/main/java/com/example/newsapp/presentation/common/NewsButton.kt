package com.example.newsapp.presentation.common

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp


@Composable
fun NewsButton(
    text : String,
    onCLick :()-> Unit
) {


    Button(onClick = {onCLick() }, colors = ButtonDefaults.buttonColors(
        containerColor = Color.Blue,
        contentColor = Color.White
    ),
        shape = RoundedCornerShape(size = 6.dp)
    ) {
        
        Text(text = text, style = MaterialTheme.typography.labelMedium.copy(
            fontWeight = FontWeight.SemiBold
        ))

    }
}

@Composable
fun NewsTextButton(  text : String,
                     onCLick :()-> Unit) {
    Button(onClick = {onCLick() },
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.Transparent,
            contentColor = Color.Black
        )
    ) {

        Text(text = text, style = MaterialTheme.typography.labelMedium.copy(
            fontWeight = FontWeight.SemiBold,
            color = Color.Black
        ))

    }
}


