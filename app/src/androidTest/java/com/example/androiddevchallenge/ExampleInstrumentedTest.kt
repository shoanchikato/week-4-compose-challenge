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
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onAllNodesWithText
import androidx.compose.ui.test.onNodeWithText
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
class WeatherAppInstrumentedTest {
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
    fun date_string() {
        val text = today.dateString
        composeTestRule.onNodeWithText(text, useUnmergedTree = true).assertIsDisplayed()
    }
}
