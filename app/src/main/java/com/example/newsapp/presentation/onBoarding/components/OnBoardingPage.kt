package com.example.newsapp.presentation.onBoarding.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.example.newsapp.presentation.onBoarding.components.Dimens.MediumPadding1
import com.example.newsapp.presentation.onBoarding.components.Dimens.MediumPadding2
import com.example.newsapp.presentation.onBoarding.Page
import com.example.newsapp.presentation.onBoarding.pages
import com.example.newsapp.ui.theme.CustomOrange


@Composable
fun OnBoardingPage(modifier: Modifier = Modifier  ,
                   page: Page) {


    Column(

        modifier = Modifier
    ) {
        Image(modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(fraction = 0.6f), 
            contentScale = ContentScale.Crop, 
            painter = painterResource(id = page.image), 
            contentDescription = null )

        Spacer(modifier = Modifier.height(MediumPadding1))
        
        Text(text = page.title , modifier = Modifier.padding(horizontal = MediumPadding2) ,
            style = MaterialTheme.typography.displaySmall.copy(fontWeight = FontWeight.Bold),
            color = CustomOrange
            )
        Text(text = page.description , modifier = Modifier.padding(horizontal = MediumPadding2) ,
            style = MaterialTheme.typography.bodyMedium,
            color = Color.DarkGray
        )
    }

}
@Preview(showSystemUi = true)
@Composable
fun PreView(modifier: Modifier = Modifier) {
    
    
    OnBoardingPage(page = pages[0])
}