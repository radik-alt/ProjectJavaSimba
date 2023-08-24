package com.example.compose.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.compose.style.primaryColor
import com.example.compose.style.robotoBold
import com.example.compose.style.robotoRegular
import com.example.compose.style.whiteColor
import com.example.core.R

@Composable
fun AppBar(title: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(primaryColor)
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppBarWithFilter(
    title: String,
    clickListener: () -> Unit
) {
    TopAppBar(
        colors = TopAppBarDefaults.smallTopAppBarColors(primaryColor),
        title = {
            Text(
                title,
                Modifier
                    .padding(15.dp)
                    .fillMaxWidth(),
                textAlign = TextAlign.Center,
                fontFamily = robotoBold,
                color = whiteColor,
                fontSize = 18.sp,
            )
        },
        actions = {
            Icon(
                painter = painterResource(id = R.drawable.filter),
                contentDescription = "Фильтр",
                modifier = Modifier
                    .padding(end = 15.dp)
                    .clickable {
                        clickListener()
                    },
                tint = whiteColor
            )
        }
    )
}