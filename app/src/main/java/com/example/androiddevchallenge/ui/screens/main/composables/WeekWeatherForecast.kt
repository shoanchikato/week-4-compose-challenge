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
package com.example.androiddevchallenge.ui.screens.main.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.testTag
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.androiddevchallenge.R
import com.example.androiddevchallenge.model.DailyWeather
import com.example.androiddevchallenge.ui.theme.ptSansCaption

@Composable
fun WeekWeatherForecast(
    weekTemp: List<DailyWeather>
) {
    Column(
        modifier = Modifier
            .padding(horizontal = 32.dp),
    ) {
        weekTemp.map {
            if (weekTemp.first() == it) Spacer(modifier = Modifier.height(32.dp))
            DailyWeatherRow(daily = it)
            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}

@Composable
fun DailyWeatherRow(
    daily: DailyWeather,
) {
    val weekDay = daily.weekDay
    val maxTempIsString = stringResource(id = R.string.max_temperature_is, "${daily.max}°")
    val minTempIsString = stringResource(id = R.string.min_temperature_is, "${daily.min}°")
    val chancesOfRain = stringResource(id = R.string.chances_of_rain, daily.chancesOfRain)

    val chancesOfRainString = if (daily.chancesOfRain != "0%")
        "$chancesOfRain"
    else ""

    val tag = stringResource(id = R.string.daily_weather_row_tag)

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .semantics(mergeDescendants = true) {
                this.testTag = tag
                this.contentDescription =
                    "$weekDay, $maxTempIsString, $minTempIsString, $chancesOfRainString"
            },
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        Text(
            modifier = Modifier
                .fillMaxWidth(.3f),
            text = daily.weekDay,
            style = TextStyle(
                color = Color.White,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = ptSansCaption,
            )
        )
        IconAndChancesOfRain(daily = daily)
        DailyMaxAndMinRow(daily = daily)
    }
}

@Composable
fun IconAndChancesOfRain(
    daily: DailyWeather,
) {
    Row(
        modifier = Modifier
            .width(72.dp),
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        val chancesOfRain = if (daily.chancesOfRain != "0%") daily.chancesOfRain else ""
        Icon(
            modifier = Modifier
                .size(16.dp),
            painter = painterResource(id = daily.weatherIcon.icon),
            contentDescription = null,
            tint = Color.White,
        )
        Spacer(modifier = Modifier.width(16.dp))
        Text(
            text = chancesOfRain,
            style = TextStyle(
                color = Color.White.copy(alpha = .75f),
                fontSize = 14.sp,
                fontFamily = ptSansCaption,
            )
        )
    }
}

@Composable
fun DailyMaxAndMinRow(
    daily: DailyWeather,
) {
    Row(
        modifier = Modifier
            .width(56.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        Text(
            text = daily.max,
            style = TextStyle(
                color = Color.White,
                fontSize = 16.sp,
                fontFamily = ptSansCaption,
            )
        )
        Text(
            text = daily.min,
            style = TextStyle(
                color = Color.White.copy(alpha = .75f),
                fontSize = 16.sp,
                fontFamily = ptSansCaption,
            )
        )
    }
}
