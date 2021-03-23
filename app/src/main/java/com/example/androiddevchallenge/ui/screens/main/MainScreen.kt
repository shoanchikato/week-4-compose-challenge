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
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.androiddevchallenge.R
import com.example.androiddevchallenge.model.DailyData
import com.example.androiddevchallenge.model.DailyWeather
import com.example.androiddevchallenge.model.HourlyTemp
import com.example.androiddevchallenge.model.WeatherIcon
import com.example.androiddevchallenge.ui.data.today
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
                            Color(0xFFecb425),
                            Color(0xFFed7200),
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
fun WeatherIconAndTextSplit(
    modifier: Modifier = Modifier,
    dailyData: DailyData,
    selectedIndex: Int = 0,
    length: Int = 5,
    content: @Composable () -> Unit,
) {

    val shortDescription = dailyData.currentDayShortDescription
    val longDescription = dailyData.currentDayLongDescription
    val maxTemp = dailyData.currentDayMax
    val minTemp = dailyData.currentDayMin
    val locationName = dailyData.locationName
    val currentTemp = dailyData.currentTemp

    val selectedString = stringResource(id = R.string.selected, selectedIndex + 1, length)
    val description = stringResource(id = R.string.today_temp, shortDescription, longDescription)
    val maxIs = stringResource(id = R.string.max_temperature_is, maxTemp)
    val minIs = stringResource(id = R.string.min_temperature_is, minTemp)
    val tempAt = stringResource(id = R.string.tempAt, locationName, currentTemp)

    val tempAtString = "$tempAt ${dailyData.locationName}, ${dailyData.currentTemp}"
    val maxMinString = "$maxIs, $minIs"

    Layout(
        modifier = modifier
            .aspectRatio(1.6f)
            .semantics(mergeDescendants = true) {
                this.contentDescription =
                    "$selectedString $tempAtString $maxMinString $description"
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

@Composable
fun DailyWeatherRow(
    daily: DailyWeather,
) {
    val weekDay = daily.weekDay
    val maxTempIs = stringResource(id = R.string.max_temperature_is)
    val minTempIs = stringResource(id = R.string.min_temperature_is)
    val chancesOfRain = stringResource(id = R.string.chances_of_rain)

    val chancesOfRainString = if (daily.chancesOfRain != "0%")
        "$chancesOfRain ${daily.chancesOfRain}"
    else ""

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .semantics(mergeDescendants = true) {
                this.contentDescription =
                    "$weekDay, $maxTempIs ${daily.max}°, $minTempIs ${daily.min}°, " +
                    chancesOfRainString
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
fun HourlyTempItem(
    item: HourlyTemp = HourlyTemp(
        hour = "1pm",
        temp = "28°"
    )
) {
    val tempAt = stringResource(id = R.string.tempAt)
    Column(
        modifier = Modifier
            .semantics(mergeDescendants = true) {
                this.contentDescription =
                    "$tempAt ${item.hour}, ${item.temp}"
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
fun LocationIndexRow() {
    Row {
        IndexDot(
            selected = true,
        )
        IndexDot()
        IndexDot()
        IndexDot()
        IndexDot()
    }
}

@Composable
fun IndexDot(
    selected: Boolean = false,
) {
    val color = if (selected)
        Color.White
    else
        Color.White.copy(alpha = .5f)

    Box(
        modifier = Modifier
            .padding(end = 8.dp)
            .size(5.dp)
            .clip(CircleShape)
            .background(color)
    )
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
