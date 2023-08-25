package com.example.feature_events.presentation.news.view

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.compose.component.AppBarWithFilter
import com.example.compose.style.blueGrayColor
import com.example.compose.style.defaultTextColor
import com.example.compose.style.primaryColor
import com.example.compose.style.robotoBold
import com.example.compose.style.robotoRegular
import com.example.compose.style.roundCorners
import com.example.compose.style.whiteColor
import com.example.core.entity.EventEntity
import com.example.feature_events.R
import com.example.feature_events.presentation.news.viewmodel.NewsViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewsScreen(
    viewModel: NewsViewModel,
    appBarListener: () -> Unit,
    clickCardListener: (EventEntity) -> Unit
) {
    Scaffold(
        topBar = {
            AppBarWithFilter(title = "Новости") {
                appBarListener()
            }
        },
    ) {
        val stateEvent = viewModel.events.observeAsState()
        val listEvent = stateEvent.value ?: arrayListOf()

        LazyColumn(
            contentPadding = PaddingValues(10.dp),
        ) {
            items(listEvent) { item ->
                ItemEvent(item) {
                    clickCardListener(it)
                }
            }
        }
    }
}

@SuppressLint("CheckResult", "PrivateResource")
@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun ItemEvent(
    event: EventEntity,
    clickCardListener: (EventEntity) -> Unit
) {
    Card(
        modifier = Modifier
            .background(whiteColor)
            .fillMaxWidth()
            .clickable {
                clickCardListener(event)
            },
        shape = RoundedCornerShape(roundCorners),
        elevation = CardDefaults.cardElevation(
            roundCorners
        )
    ) {
        Box(
            modifier = Modifier.padding(top = 10.dp),
            contentAlignment = Alignment.BottomCenter
        ) {
            val image =
                if (event.listImage.isNotEmpty())
                    event.listImage[0]
                else
                    painterResource(id = R.drawable.img)
            GlideImage(
                model = image,
                contentDescription = "",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(400.dp)
                    .alpha(0.6f),
                contentScale = ContentScale.FillBounds
            ) {
                it.error(R.drawable.img)
                    .placeholder(R.drawable.bg_placeholder)
                    .load(image)
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.Bottom
            ) {
                Text(
                    text = event.title,
                    color = blueGrayColor,
                    fontFamily = robotoBold,
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center,
                    fontSize = 21.sp,
                    maxLines = 2
                )
                Spacer(modifier = Modifier.height(10.dp))
                Image(
                    painter = painterResource(id = R.drawable.decor),
                    contentDescription = "",
                    modifier = Modifier.fillMaxWidth(),
                    alignment = Alignment.Center
                )
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = event.description,
                    color = defaultTextColor,
                    fontFamily = robotoRegular,
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center,
                    fontSize = 14.sp,
                    maxLines = 3
                )
                Spacer(modifier = Modifier.height(10.dp))
                Button(
                    onClick = { },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(40.dp),
                    colors = ButtonDefaults.buttonColors(primaryColor),
                    shape = RectangleShape,
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.icon_calendar),
                        contentDescription = "Календарь"
                    )
                    Text(
                        text = "Осталось 13 дней (21.09 – 20.10)",
                        fontSize = 12.sp,
                        fontFamily = robotoRegular
                    )
                }
            }
        }
    }
}

