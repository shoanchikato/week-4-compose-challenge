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
package com.example.androiddevchallenge

import androidx.compose.ui.test.assertAny
import androidx.compose.ui.test.assertContentDescriptionContains
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.hasContentDescription
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onAllNodesWithTag
import androidx.compose.ui.test.onAllNodesWithText
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performScrollTo
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.androiddevchallenge.ui.data.today
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class WeatherAppDisplayInstrumentedTest {
    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @Test
    fun selected_location() {
        val text = composeTestRule.activity.getString(R.string.harare)
        composeTestRule.onNodeWithText(text, useUnmergedTree = true).assertExists()
    }

    @Test
    fun current_temperature() {
        val text = today.currentTemp
        composeTestRule.onAllNodesWithText(text, useUnmergedTree = true).assertAny(hasText(text))
    }

    @Test
    fun max_temperature() {
        val text = today.currentDayMax
        composeTestRule.onAllNodesWithText(text, useUnmergedTree = true).assertAny(hasText(text))
    }

    @Test
    fun min_temperature() {
        val text = today.currentDayMin
        composeTestRule.onNodeWithText(text, useUnmergedTree = true).assertIsDisplayed()
    }

    @Test
    fun short_description() {
        val text = today.currentDayShortDescription
        composeTestRule.onNodeWithText(text, useUnmergedTree = true).assertIsDisplayed()
    }

    @Test
    fun long_description() {
        val text = today.currentDayLongDescription
        composeTestRule.onNodeWithText(text, useUnmergedTree = true).assertIsDisplayed()
    }

    @Test
    fun sunrise() {
        val text = today.additionalDetails.sunrise
        composeTestRule.onNodeWithText(text, useUnmergedTree = true)
            .performScrollTo()
            .assertIsDisplayed()
    }

    @Test
    fun sunset() {
        val text = today.additionalDetails.sunset
        composeTestRule.onNodeWithText(text, useUnmergedTree = true)
            .performScrollTo()
            .assertIsDisplayed()
    }

    @Test
    fun precipitation() {
        val text = today.additionalDetails.precipitation
        composeTestRule.onNodeWithText(text, useUnmergedTree = true)
            .performScrollTo()
            .assertIsDisplayed()
    }

    @Test
    fun humidity() {
        val text = today.additionalDetails.humidity
        composeTestRule.onNodeWithText(text, useUnmergedTree = true)
            .performScrollTo()
            .assertIsDisplayed()
    }

    @Test
    fun wind() {
        val text = today.additionalDetails.humidity
        composeTestRule.onNodeWithText(text, useUnmergedTree = true)
            .performScrollTo()
            .assertIsDisplayed()
    }

    @Test
    fun pressure() {
        val text = today.additionalDetails.pressure
        composeTestRule.onNodeWithText(text, useUnmergedTree = true).performScrollTo()
            .assertIsDisplayed()
    }
}

@RunWith(AndroidJUnit4::class)
class WeatherAppContentDescriptionInstrumentedTest {
    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @Test
    fun additional_details_content_description() {
        val additionalDetails = today.additionalDetails
        val sunrise = additionalDetails.sunrise
        val sunriseString = composeTestRule.activity.getString(R.string.sunrise_at, sunrise)
        val sunset = additionalDetails.sunset
        val sunsetString = composeTestRule.activity.getString(R.string.sunset_at, sunset)
        val precipitation = additionalDetails.precipitation
        val precipitationString =
            composeTestRule.activity.getString(R.string.current_precipitation, precipitation)
        val humidity = additionalDetails.humidity
        val humidityString = composeTestRule.activity.getString(R.string.current_humidity, humidity)
        val wind = additionalDetails.wind
        val windString = composeTestRule.activity.getString(R.string.wind_speed, wind)
        val pressure = additionalDetails.pressure
        val pressureString = composeTestRule.activity.getString(R.string.atmospheric, pressure)

        val text = "$sunriseString, $sunsetString, $precipitationString, $humidityString, " +
            "$windString $pressureString"

        val tag = composeTestRule.activity.getString(R.string.additional_details_tag)

        composeTestRule.onNodeWithTag(testTag = tag)
            .performScrollTo()
            .assertContentDescriptionContains(text)
    }

    @Test
    fun weather_icon_and_text_split_content_description() {

        val dailyData = today
        val selectedIndex = 0
        val length = 5
        val selectedString = composeTestRule.activity.getString(R.string.selected, selectedIndex + 1, length)
        val shortDescription = dailyData.currentDayShortDescription
        val longDescription = dailyData.currentDayLongDescription
        val description =
            composeTestRule.activity.getString(R.string.today_description, shortDescription, longDescription)
        val maxTemp = dailyData.currentDayMax
        val maxIs = composeTestRule.activity.getString(R.string.max_temperature_is, maxTemp)
        val minTemp = dailyData.currentDayMin
        val minIs = composeTestRule.activity.getString(R.string.min_temperature_is, minTemp)
        val locationName = dailyData.locationName
        val currentTemp = dailyData.currentTemp
        val tempAt = composeTestRule.activity.getString(R.string.tempAt, locationName, currentTemp)

        val tempAtString = "$tempAt ${dailyData.locationName}, ${dailyData.currentTemp}"
        val maxMinString = "$maxIs, $minIs"

        val text = "$selectedString $tempAtString $maxMinString $description"
        val tag = composeTestRule.activity.getString(R.string.weather_icon_and_text_split_tag)

        composeTestRule.onNodeWithTag(testTag = tag)
            .assertContentDescriptionContains(text)
    }

    @Test
    fun hourly_content_description() {
        // beware of items that might not be in view when testing
        // .performScrollTo() appears to ONLY work on single nodes NOT all-nodes
        val item = today.currentDayHourlyTemp[0]

        val text = composeTestRule.activity.getString(R.string.tempAt, item.hour, item.temp)
        val tag = composeTestRule.activity.getString(R.string.hourly_temp_item_tag)

        composeTestRule.onAllNodesWithTag(testTag = tag)
            .assertAny(hasContentDescription(text))
    }

    @Test
    fun daily_content_description() {
        val daily = today.weekTemp[1]
        val weekDay = daily.weekDay
        val maxTempIsString = composeTestRule.activity.getString(R.string.max_temperature_is, "${daily.max}°")
        val minTempIsString = composeTestRule.activity.getString(R.string.min_temperature_is, "${daily.min}°")
        val chancesOfRain = composeTestRule.activity.getString(R.string.chances_of_rain, daily.chancesOfRain)

        val chancesOfRainString = if (daily.chancesOfRain != "0%")
            "$chancesOfRain"
        else ""

        val text = "$weekDay, $maxTempIsString, $minTempIsString, $chancesOfRainString"
        val tag = composeTestRule.activity.getString(R.string.daily_weather_row_tag)

        composeTestRule.onAllNodesWithTag(testTag = tag, useUnmergedTree = true)
            .assertAny(hasContentDescription(text))
    }
}
