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
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

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
