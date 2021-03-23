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

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.Divider
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
import com.example.androiddevchallenge.model.HourlyTemp
import com.example.androiddevchallenge.ui.theme.ptSansCaption

@Composable
fun HourlyTempRow(
    hourlyTemp: List<HourlyTemp>
) {
    Column {
        Divider(
            color = Color.White.copy(alpha = .5f),
            thickness = .5.dp,
        )
        LazyRow {
            hourlyTemp.map {
                item {
                    if (hourlyTemp.first() == it) Spacer(modifier = Modifier.width(40.dp))
                    HourlyTempItem(it)
                    Spacer(modifier = Modifier.width(40.dp))
                }
            }
        }
        Divider(
            color = Color.White.copy(alpha = .5f),
            thickness = .5.dp,
        )
    }
}

@Composable
fun HourlyTempItem(
    item: HourlyTemp = HourlyTemp(
        hour = "1pm",
        temp = "28°"
    )
) {
    val tempAtString = stringResource(id = R.string.tempAt, item.hour, item.temp)
    val tag = stringResource(id = R.string.hourly_temp_item_tag)

    Column(
        modifier = Modifier
            .semantics(mergeDescendants = true) {
                this.testTag = tag
                this.contentDescription = tempAtString
            }
    ) {
        Spacer(modifier = Modifier.height(32.dp))
        Text(
            text = item.hour,
            style = TextStyle(
                color = Color.White.copy(alpha = .75f),
                fontSize = 14.sp,
                fontFamily = ptSansCaption
            ),
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = item.temp,
            style = TextStyle(
                color = Color.White,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = ptSansCaption
            ),
        )
        Spacer(modifier = Modifier.height(32.dp))
    }
}
