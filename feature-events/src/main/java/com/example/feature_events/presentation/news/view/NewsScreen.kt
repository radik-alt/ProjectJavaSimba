package com.example.feature_events.presentation.news.view

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.painterResource
import com.example.compose.component.AppBarWithFilter
import com.example.core.entity.EventEntity
import com.example.feature_events.R

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewsScreen(
    appBarListener: () -> Unit
) {
    MaterialTheme {
        Scaffold(
            topBar = {
                AppBarWithFilter(title = "Новости") {
                    appBarListener()
                }
            }
        ) {

        }
    }
}

@Composable
fun ItemNews(
    event: EventEntity?
) {
    Image(
        painter = painterResource(
            id = R.drawable.background_image
        ),
        contentDescription = ""
    )
    Column(
        horizontalAlignment = Alignment.End
    ) {
        Text(text = "Спонсоры отремонтируют школу-интернат")
    }
}

