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

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.testTag
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.androiddevchallenge.R
import com.example.androiddevchallenge.model.AdditionalDetails
import com.example.androiddevchallenge.ui.theme.ptSansCaption

@Composable
fun AdditionalDetails(
    additionalDetails: AdditionalDetails,
) {
    val sunrise = additionalDetails.sunrise
    val sunriseString = stringResource(id = R.string.sunrise_at, sunrise)
    val sunset = additionalDetails.sunset
    val sunsetString = stringResource(id = R.string.sunset_at, sunset)
    val precipitation = additionalDetails.precipitation
    val precipitationString = stringResource(id = R.string.current_precipitation, precipitation)
    val humidity = additionalDetails.humidity
    val humidityString = stringResource(id = R.string.current_humidity, humidity)
    val wind = additionalDetails.wind
    val windString = stringResource(id = R.string.wind_speed, wind)
    val pressure = additionalDetails.pressure
    val pressureString = stringResource(id = R.string.atmospheric, pressure)

    val tag = stringResource(id = R.string.additional_details_tag)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White.copy(alpha = .2f))
            .padding(horizontal = 32.dp, vertical = 24.dp)
            .semantics(mergeDescendants = true) {
                this.testTag = tag
                this.contentDescription =
                    "$sunriseString, $sunsetString, $precipitationString, $humidityString, " +
                    "$windString $pressureString"
            }
    ) {
        LabelAndValueRow(
            first = {
                LabelAndValue(label = "Sunrise", value = additionalDetails.sunrise)
            },
            second = {
                LabelAndValue(label = "Sunset", value = additionalDetails.sunset)
            }
        )
        LabelAndValueRow(
            first = {
                LabelAndValue(label = "Precipitation", value = additionalDetails.precipitation)
            },
            second = {
                LabelAndValue(label = "Humidity", value = additionalDetails.humidity)
            }
        )
        LabelAndValueRow(
            isLast = true,
            first = {
                LabelAndValue(label = "Wind", value = additionalDetails.wind)
            },
            second = {
                LabelAndValue(label = "Pressure", value = additionalDetails.pressure)
            }
        )
    }
}

@Composable
fun LabelAndValueRow(
    isLast: Boolean = false,
    first: @Composable () -> Unit,
    second: @Composable () -> Unit,
) {
    val bottomPadding = if (isLast) 0.dp else 24.dp
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = bottomPadding),
        horizontalArrangement = Arrangement.Start,
    ) {
        Column(
            modifier = Modifier
                .weight(.5f)
        ) {
            first()
        }
        Column(
            modifier = Modifier
                .weight(.5f)
        ) {
            second()
        }
    }
}

@Composable
fun LabelAndValue(
    label: String = "Sunrise",
    value: String = "3:55 am",
) {
    Column {
        Text(
            text = label,
            style = TextStyle(
                fontFamily = ptSansCaption,
                fontSize = 14.sp,
                color = Color.White.copy(alpha = .75f),
            )
        )
        Spacer(modifier = Modifier.height(24.dp))
        Text(
            text = value,
            style = TextStyle(
                fontFamily = ptSansCaption,
                fontSize = 16.sp,
                color = Color.White,
                fontWeight = FontWeight.Bold,
            )
        )
    }
}
