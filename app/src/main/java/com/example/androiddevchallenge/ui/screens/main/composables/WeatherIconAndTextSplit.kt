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

import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.selectableGroup
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.testTag
import androidx.compose.ui.unit.Constraints
import com.example.androiddevchallenge.R
import com.example.androiddevchallenge.model.DailyData

@Composable
fun WeatherIconAndTextSplit(
    modifier: Modifier = Modifier,
    dailyData: DailyData,
    selectedIndex: Int = 0,
    length: Int = 5,
    content: @Composable () -> Unit,
) {
    val selectedString = stringResource(id = R.string.selected, selectedIndex + 1, length)
    val shortDescription = dailyData.currentDayShortDescription
    val longDescription = dailyData.currentDayLongDescription
    val description =
        stringResource(id = R.string.today_description, shortDescription, longDescription)
    val maxTemp = dailyData.currentDayMax
    val maxIs = stringResource(id = R.string.max_temperature_is, maxTemp)
    val minTemp = dailyData.currentDayMin
    val minIs = stringResource(id = R.string.min_temperature_is, minTemp)
    val locationName = dailyData.locationName
    val currentTemp = dailyData.currentTemp
    val tempAt = stringResource(id = R.string.tempAt, locationName, currentTemp)

    val tempAtString = "$tempAt ${dailyData.locationName}, ${dailyData.currentTemp}"
    val maxMinString = "$maxIs, $minIs"

    val tag = stringResource(id = R.string.weather_icon_and_text_split_tag)

    Layout(
        modifier = modifier
            .aspectRatio(1.6f)
            .semantics(mergeDescendants = true) {
                this.testTag = tag
                this.contentDescription =
                    "$selectedString $tempAtString $maxMinString $description"
                this.selectableGroup()
            },
        content = content
    ) { measurables, constraints ->
        // Text given a set proportion of width (which is determined by the aspect ratio)
        val textWidth = (constraints.maxWidth * .62f).toInt()
        val textPlaceable = measurables[0].measure(Constraints.fixedWidth(textWidth))

        // Image is sized to the larger of height of item, or a minimum value
        // i.e. may appear larger than item (but clipped to the item bounds)
        val imageSize = (constraints.minHeight * 1.2f).toInt()
        val imagePlaceable = measurables[1].measure(Constraints.fixed(imageSize, imageSize))
        layout(
            width = constraints.maxWidth,
            height = constraints.minHeight
        ) {
            textPlaceable.place(
                x = 0,
                y = (constraints.maxHeight - textPlaceable.height) / 2 // centered
            )
            imagePlaceable.place(
                // image is placed to end of text i.e. will overflow to the end (but be clipped)
                x = textWidth,
                y = (constraints.maxHeight - imagePlaceable.height) / 2 // centered
            )
        }
    }
}
