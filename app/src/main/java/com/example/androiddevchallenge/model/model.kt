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
package com.example.androiddevchallenge.model

import com.example.androiddevchallenge.R

data class DailyData(
    val locationName: String,
    val dateString: String,
    val currentDayWeatherIcon: WeatherIcon,
    val currentTemp: String,
    val currentDayMax: String,
    val currentDayMin: String,
    val currentDayShortDescription: String,
    val currentDayLongDescription: String,
    val weekTemp: List<DailyWeather>,
    val currentDayHourlyTemp: List<HourlyTemp>,
    val additionalDetails: AdditionalDetails,
)

data class HourlyTemp(val hour: String, val temp: String)

data class DailyWeather(
    val weekDay: String,
    val weatherIcon: WeatherIcon,
    val chancesOfRain: String = "0%",
    val max: String,
    val min: String,
)

data class AdditionalDetails(
    val sunrise: String,
    val sunset: String,
    val precipitation: String,
    val humidity: String,
    val wind: String,
    val pressure: String,
)

enum class WeatherIcon(val icon: Int, val description: String) {
    CloudRain(R.drawable.cloud_rain, "clouds with rain icon"),
    Cloudy(R.drawable.cloudy, "cloudy icon"),
    SunnyDay(R.drawable.sunny_day, "sun icon"),
    // ChanceOfRain(R.drawable.chances_of_rain, "chances of rain icon"),
    // ClearNight(R.drawable.clear_night, "clear night icon"),
    // CloudRainStorm(R.drawable.cloud_rain_storm, "rain storm icon"),
    // CloudSnow(R.drawable.cloud_snow, "cloud with snow icon"),
    // CloudSun(R.drawable.cloud_sun, "cloud and sun icon"),
    // CloudThunderStorm(R.drawable.cloud_thunder_storm, "thunderstorm icon"),
    // CloudyAndMisty(R.drawable.cloudy_and_misty, "cloud with mist icon"),
    // CloudyHail(R.drawable.cloudy_hail, "cloud with hail icon"),
    // CloudyOvercast(R.drawable.cloudy_overcast, "overcast icon"),
    // CloudyRain(R.drawable.cloud_rain, "cloud with rain icon"),
    // CloudyWithSun(R.drawable.cloudy_with_sun, "cloud with sun icon"),
    // Umbrella(R.drawable.umbrella, "umbrella icon"),
}
