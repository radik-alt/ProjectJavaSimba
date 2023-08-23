package com.example.compose.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.compose.style.robotoRegular
import com.example.core.R

@Composable
fun AppBar(title: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xff66a636))
    ) {
        Image(
            painter = painterResource(id = R.drawable.baseline_arrow_back_24),
            contentDescription = "Назад",
            modifier = Modifier
                .align(Alignment.CenterVertically)
                .offset(x = 15.dp),
            alignment = Alignment.Center
        )
        Text(
            title,
            Modifier
                .padding(15.dp)
                .fillMaxWidth(),
            textAlign = TextAlign.Center,
            fontFamily = robotoRegular,
            color = Color.White,
            fontSize = 18.sp,
        )
    }
}

@Composable
fun AppBarWithFilter(
    title: String,
    clickListener: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xff66a636))
    ) {
        Image(
            painter = painterResource(id = R.drawable.filter),
            contentDescription = "Назад",
            modifier = Modifier
                .align(Alignment.CenterVertically)
                .offset(x = 15.dp)
                .clickable {
                    clickListener()
                }
                .weight(3f),
            alignment = Alignment.Center
        )
        Text(
            title,
            Modifier
                .padding(15.dp)
                .weight(1f),
            textAlign = TextAlign.Center,
            fontFamily = robotoRegular,
            color = Color.White,
            fontSize = 18.sp,
        )
    }
}