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
package com.example.androiddevchallenge.ui.data

import com.example.androiddevchallenge.model.AdditionalDetails
import com.example.androiddevchallenge.model.DailyData
import com.example.androiddevchallenge.model.DailyWeather
import com.example.androiddevchallenge.model.HourlyTemp
import com.example.androiddevchallenge.model.WeatherIcon

val weekTemp = listOf(
    DailyWeather(
        weekDay = "Tuesday",
        weatherIcon = WeatherIcon.SunnyDay,
        max = "31",
        min = "13",
    ),
    DailyWeather(
        weekDay = "Wednesday",
        weatherIcon = WeatherIcon.CloudRain,
        chancesOfRain = "70%",
        max = "28",
        min = "12",
    ),
    DailyWeather(
        weekDay = "Thursday",
        weatherIcon = WeatherIcon.CloudRain,
        chancesOfRain = "60%",
        max = "26",
        min = "13",
    ),
    DailyWeather(
        weekDay = "Friday",
        weatherIcon = WeatherIcon.Cloudy,
        max = "27",
        min = "16",
    ),
    DailyWeather(
        weekDay = "Saturday",
        weatherIcon = WeatherIcon.SunnyDay,
        max = "31",
        min = "17",
    ),
    DailyWeather(
        weekDay = "Sunday",
        weatherIcon = WeatherIcon.Cloudy,
        max = "26",
        min = "15",
    ),
)

val currentDayHourlyTemp = listOf(
    HourlyTemp(
        hour = "Now",
        temp = "28°"
    ),
    HourlyTemp(
        hour = "1 pm",
        temp = "29°"
    ),
    HourlyTemp(
        hour = "2 pm",
        temp = "31°"
    ),
    HourlyTemp(
        hour = "3 pm",
        temp = "31°"
    ),
    HourlyTemp(
        hour = "4 pm",
        temp = "31°"
    ),
    HourlyTemp(
        hour = "5 pm",
        temp = "29°"
    ),
    HourlyTemp(
        hour = "6 pm",
        temp = "28°"
    ),
    HourlyTemp(
        hour = "7 pm",
        temp = "22°"
    ),
    HourlyTemp(
        hour = "8 pm",
        temp = "18°"
    ),
    HourlyTemp(
        hour = "9 pm",
        temp = "16°"
    ),
    HourlyTemp(
        hour = "10 pm",
        temp = "16°"
    ),
    HourlyTemp(
        hour = "11 pm",
        temp = "15°"
    ),
    HourlyTemp(
        hour = "12 am",
        temp = "14°"
    ),
    HourlyTemp(
        hour = "1 am",
        temp = "13°"
    ),
    HourlyTemp(
        hour = "2 am",
        temp = "12°"
    ),
)

val additionalDetails = AdditionalDetails(
    sunrise = "3:55 am",
    sunset = "9:13 pm",
    precipitation = "10%",
    humidity = "42%",
    wind = "11 km/h",
    pressure = "1009 hPa"
)

val today = DailyData(
    locationName = "Harare",
    dateString = "Mon, July 6",
    currentDayWeatherIcon = WeatherIcon.SunnyDay,
    currentTemp = "28°",
    currentDayMax = "31°",
    currentDayMin = "19°",
    currentDayShortDescription = "Sunny",
    currentDayLongDescription = "Feels like 29°",
    weekTemp = weekTemp,
    currentDayHourlyTemp = currentDayHourlyTemp,
    additionalDetails = additionalDetails,
)
