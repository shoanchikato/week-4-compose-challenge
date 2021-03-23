/*
 * Copyright 2021 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.androiddevchallenge.ui.screens.main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.androiddevchallenge.R
import com.example.androiddevchallenge.model.DailyData
import com.example.androiddevchallenge.model.WeatherIcon
import com.example.androiddevchallenge.ui.data.today
import com.example.androiddevchallenge.ui.screens.main.composables.AdditionalDetails
import com.example.androiddevchallenge.ui.screens.main.composables.HourlyTempRow
import com.example.androiddevchallenge.ui.screens.main.composables.LocationIndexRow
import com.example.androiddevchallenge.ui.screens.main.composables.WeatherIconAndTextSplit
import com.example.androiddevchallenge.ui.screens.main.composables.WeekWeatherForecast
import com.example.androiddevchallenge.ui.theme.deepOrange
import com.example.androiddevchallenge.ui.theme.lightOrange
import com.example.androiddevchallenge.ui.theme.ptSansCaption
import dev.chrisbanes.accompanist.insets.systemBarsPadding

@Composable
fun MainScreen() {
    Scaffold {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.linearGradient(
                        colors = listOf(
                            lightOrange,
                            deepOrange,
                        )
                    )
                )
                .systemBarsPadding()
        ) {
            val today = today
            LocationPage(
                dailyData = today
            )
        }
    }
}

@Composable
fun LocationPage(
    dailyData: DailyData,
) {
    val scrollState = rememberScrollState()
    Column(
        modifier = Modifier
            .verticalScroll(scrollState)
    ) {
        Column(
            modifier = Modifier
                .padding(top = 32.dp, bottom = 32.dp, start = 32.dp, end = 0.dp)
                .fillMaxSize()
        ) {
            DateAndDegreesRow(
                dateString = dailyData.dateString,
            )
            Spacer(modifier = Modifier.height(64.dp))
            WeatherIconAndTextSplit(
                dailyData = dailyData,
            ) {
                Column {
                    LocationName(
                        dailyData.locationName,
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    LocationIndexRow()
                    Spacer(modifier = Modifier.height(24.dp))
                    BigTemperatureText(
                        dailyData.currentTemp,
                    )
                    MaxAndMinTempRow(
                        maxTemp = dailyData.currentDayMax,
                        minTemp = dailyData.currentDayMin,
                    )
                    Spacer(modifier = Modifier.height(32.dp))
                    ShortWeatherDescription(
                        longDescription = dailyData.currentDayLongDescription,
                        shortDescription = dailyData.currentDayShortDescription,
                    )
                }
                OverlayIcon(
                    weatherIcon = dailyData.currentDayWeatherIcon
                )
            }
            Spacer(modifier = Modifier.height(24.dp))
        }
        HourlyTempRow(
            hourlyTemp = dailyData.currentDayHourlyTemp,
        )
        WeekWeatherForecast(
            weekTemp = dailyData.weekTemp
        )
        AdditionalDetails(
            additionalDetails = dailyData.additionalDetails,
        )
    }
}

@Composable
fun OverlayIcon(
    weatherIcon: WeatherIcon,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .clip(CircleShape)
            .background(Color.White.copy(alpha = .2f)),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Icon(
            modifier = Modifier
                .fillMaxSize(.6f),
            painter = painterResource(id = weatherIcon.icon),
            contentDescription = null,
            tint = Color.White,
        )
    }
}

@Composable
fun LocationName(
    location: String = "Kyiv",
) {
    Text(
        text = location,
        style = TextStyle(
            color = Color.White,
            fontSize = 24.sp,
            fontFamily = ptSansCaption
        ),
    )
}

@Composable
fun BigTemperatureText(
    currentTemp: String = "28°",
) {
    Text(
        text = currentTemp,
        style = TextStyle(
            color = Color.White,
            fontSize = 80.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = ptSansCaption
        ),
    )
}

@Composable
fun ShortWeatherDescription(
    shortDescription: String = "Sunny",
    longDescription: String = "Feels like 29°",
) {
    Column {
        Text(
            text = shortDescription,
            fontSize = 16.sp,
            color = Color.White,
            fontWeight = FontWeight.Bold,
            fontFamily = ptSansCaption,
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = longDescription,
            fontSize = 14.sp,
            color = Color.White.copy(alpha = .75f),
            fontFamily = ptSansCaption,
        )
    }
}

@Composable
fun MaxAndMinTempRow(
    maxTemp: String = "31°",
    minTemp: String = "19°",
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Icon(
            modifier = Modifier
                .size(24.dp),
            painter = painterResource(R.drawable.arrow_up),
            contentDescription = null,
            tint = Color.White.copy(alpha = .5f),
        )
        Text(
            text = maxTemp,
            style = TextStyle(
                color = Color.White,
                fontSize = 16.sp,
                fontFamily = ptSansCaption
            ),
        )
        Spacer(modifier = Modifier.width(16.dp))
        Icon(
            modifier = Modifier
                .size(24.dp),
            painter = painterResource(R.drawable.arrow_down),
            contentDescription = null,
            tint = Color.White.copy(alpha = .5f),
        )
        Text(
            text = minTemp,
            style = TextStyle(
                color = Color.White,
                fontSize = 16.sp,
                fontFamily = ptSansCaption
            ),
        )
    }
}

@Composable
fun DateAndDegreesRow(
    dateString: String = "Mon, July 6",
) {
    val description = stringResource(id = R.string.welcome)

    Row(
        modifier = Modifier
            .padding(end = 32.dp)
            .fillMaxWidth()
            .semantics(mergeDescendants = true) {
                this.contentDescription = description
            },
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        val textStyle = TextStyle(
            color = Color.White,
            fontSize = 16.sp,
            fontFamily = ptSansCaption
        )
        Text(
            text = "",
            style = textStyle,
        )
        Text(
            text = dateString,
            style = textStyle,
        )
        Text(
            text = "°C",
            style = textStyle,
        )
    }
}
